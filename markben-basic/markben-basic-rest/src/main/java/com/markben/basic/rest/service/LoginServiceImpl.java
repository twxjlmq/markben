package com.markben.basic.rest.service;

import com.markben.basic.common.entity.TSysTenant;
import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.basic.common.entity.TSysOrg;
import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.ITenantUserService;
import com.markben.basic.common.service.IUserService;
import com.markben.basic.common.wrapper.TenantUserWrapper;
import com.markben.basic.rest.vo.user.LoginResultVO;
import com.markben.beans.bean.DefaultUserInfo;
import com.markben.beans.bean.IUserInfo;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.IResultResponse;
import com.markben.cache.ICache;
import com.markben.cache.ICacheManager;
import com.markben.common.enums.YesOrNoType;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.rest.common.helper.RestCommonHelper;
import com.markben.rest.common.helper.SecurityFilterHelper;
import com.markben.rest.common.response.RestResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录服务类实现类
 * @author 乌草坡
 * @since 1.0.0
 */
@Service
public class LoginServiceImpl implements ILoginService {

    private static final ILogger logger = LoggerUtils.getLogger(LoginServiceImpl.class);

    private static final long TEMP_TOKEN_VALID_TIME = 1000 * 60 * 5;  //5分钟

    private IUserService userService;
    private ITenantUserService tenantUserService;

    public LoginServiceImpl(IUserService userService, ITenantUserService tenantUserService) {
        this.userService = userService;
        this.tenantUserService = tenantUserService;
    }

    @Override
    public IResultResponse<LoginResultVO> login(String username, String password) {
        StringUtils.isAssert(username, "用户名不能为空", this);
        StringUtils.isAssert(password, "密码不能为空", this);
        IResultResponse<LoginResultVO> response = new RestResultResponse<>();
        TSysUser user = userService.getUserOfLogin(username);
        if(null == user) {
            LoggerUtils.error(logger, "用户名输入错误,输入的用户名为:[{}].", username);
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.LOGIN_USER_OR_PWD_ERROR);
            return response;
        }
        String md5Password = userService.getMd5SaltPassword(password);
        if(!md5Password.equals(user.getPassword())) {
            LoggerUtils.error(logger, "密码输入错误,输入的密码为:[{}].", SecurityFilterHelper.filterPassword(password));
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.LOGIN_USER_OR_PWD_ERROR);
            return response;
        }
        LoginResultVO loginResult = createLoginResult(user.getId(), response);
        if(null != loginResult) {
            RestCommonHelper.setSuccessResult(response);
            //生成一个临时TOKEN
            String token = StringUtils.uuid();
            ICacheManager cacheManager = MarkbenContextFactory.getConfiguration().getCacheManager();
            ICache<String, Object> cache = cacheManager.getCache(user.getId(), TEMP_TOKEN_VALID_TIME);
            cache.put(user.getId(), token);
            loginResult.setToken(token);

            response.setResult(loginResult);
        }
        return response;
    }

    @Override
    public IResultResponse<IUserInfo> confirmLogin(String userId, String tenantId, String deptId, String token) {
        LoggerUtils.debug(logger, "正在确认登录处理中...");
        StringUtils.isAssert(userId, "用户ID不能为空", this);
        StringUtils.isAssert(tenantId, "租户ID不能为空", this);
        StringUtils.isAssert(deptId, "部门ID不能为空", this);
        IResultResponse<IUserInfo> response = new RestResultResponse<>();
        //验证token
        LoggerUtils.debug(logger, "正在验证token，传入的token值为:[{}].", token);
        ICacheManager cacheManager = MarkbenContextFactory.getConfiguration().getCacheManager();
        ICache<String, Object> cache = cacheManager.getCacheIfAbsent(userId);
        if(null == cache) {
            LoggerUtils.debug(logger, "获取到的缓存对象为null.");
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.INVALID_TOKEN);
            return response;
        }
        String cacheToken = StringUtils.handleNull(cache.get(userId));
        LoggerUtils.debug(logger, "从缓存中获取到的token为:[{}].", cacheToken);
        if(StringUtils.isEmpty(cacheToken) || !cacheToken.equals(token)) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.INVALID_TOKEN);
            return response;
        }
        //清除临时token缓存数据
        cache.remove(userId);
        cacheManager.remove(userId);

        TSysTenantUser tenantUser = tenantUserService.getTenantUser(userId, tenantId);
        if(null == tenantUser) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.CORP_USER_NOT_EXIST);
            return response;
        }
        IUserInfo userInfo = startInitUserInfo(tenantUser, deptId);
        checkUserInfo(userInfo, response);
        return response;
    }

    /**
     * 创建登录结果
     * @param userId 用户ID
     * @param response 返回对象
     * @return 如果登录成功则返回：登录结果对象，否则返回null
     */
    private LoginResultVO createLoginResult(String userId, IResultResponse response) {
        List<TSysTenantUser> tenantUsers = tenantUserService.getTenantUser(userId);
        if(CollectionUtils.isEmpty(tenantUsers)) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_IN_CORP);
            LoggerUtils.warn(logger, "用户不在租户组织中,userId:[{}].", userId);
            return null;
        }
        List<LoginResultVO.SimpleTenantInfoVO> tenantInfoList = new ArrayList<>(tenantUsers.size());
        boolean isUserInOrg = false; //判断用户是否在部门中
        for(TSysTenantUser tenantUser : tenantUsers) {
            TenantUserWrapper tenantUserWrapper = new TenantUserWrapper(tenantUser);
            LoginResultVO.SimpleTenantInfoVO simpleTenantInfo = createSimpleTenantInfo(tenantUserWrapper);
            if(null != simpleTenantInfo) {
                simpleTenantInfo.setIsDefault(tenantUser.getIsDefault());
                List<LoginResultVO.SimpleOrgInfoVO> simpleOrgList = createSimpleOrgList(tenantUserWrapper);
                if(CollectionUtils.isNotEmpty(simpleOrgList)) {
                    simpleTenantInfo.setOrgList(simpleOrgList);
                    isUserInOrg = isUserInOrg || true;
                } else {
                    LoggerUtils.warn(logger, "租户用户不在部门中,tenantUserId:[{}]---nickname:[{}].",
                            tenantUser.getId(), tenantUser.getNickname());
                }
                tenantInfoList.add(simpleTenantInfo);
            }
        }
        if(!isUserInOrg) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_IN_ORG);
            return null;
        }
        LoginResultVO loginResult = new LoginResultVO(userId);
        loginResult.setTenants(tenantInfoList);
        return loginResult;
    }


    /**
     * 创建简单租户信息；如果有租户信息返回简单租户信息；否则返回null
     * @param tenantUserWrapper 租户的用户封装对象
     * @return 返回简单租户信息或null
     */
    private LoginResultVO.SimpleTenantInfoVO createSimpleTenantInfo(TenantUserWrapper tenantUserWrapper) {
        TSysTenant tenant = tenantUserWrapper.getTenant();
        if(null == tenant) {
            return null;
        }
        LoginResultVO.SimpleTenantInfoVO simpleTenantInfo = new LoginResultVO.SimpleTenantInfoVO();
        simpleTenantInfo.setTenantId(tenant.getId());
        simpleTenantInfo.setName(tenant.getName());
        simpleTenantInfo.setLogo(tenant.getLogo());
        return simpleTenantInfo;
    }

    /**
     * 创建简单部门信息列表；
     * 如果获取到部门实体列表返回简单部门信息列表；否则返回null
     * @param corpUserWrapper 企业用户封装对象
     * @return 返回简单部门信息或null
     */
    private List<LoginResultVO.SimpleOrgInfoVO> createSimpleOrgList(TenantUserWrapper corpUserWrapper) {
        List<TSysOrg> orgList = corpUserWrapper.getOrgList();
        if(CollectionUtils.isEmpty(orgList)) {
            return null;
        }
        return orgList.stream().map(o -> new LoginResultVO.SimpleOrgInfoVO(o.getId(), o.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 开始初始化用户信息
     * @param tenantUser 租户组织的用户实体对象
     * @param deptId 部门ID
     * @return 返回用户信息对象
     */
    private IUserInfo startInitUserInfo(TSysTenantUser tenantUser, String deptId) {
        TenantUserWrapper tenantUserWrapper = new TenantUserWrapper(tenantUser);
        IUserInfo userInfo = new DefaultUserInfo();
        userInfo.setNickname(tenantUser.getNickname());
        userInfo.setTenantUserId(tenantUser.getId());
        userInfo.setTenantId(tenantUser.getTenantId());

        TSysUser user = tenantUserWrapper.getUser();
        if(null != user) {
            userInfo.setUserId(user.getId());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setMobile(user.getMobile());
            userInfo.setUsername(user.getUsername());
        }

        //初始化租户组织的信息
        TSysTenant tenant = tenantUserWrapper.getTenant();
        if(null != tenant) {
            userInfo.setTenantName(tenant.getName());
            userInfo.setLogo(tenant.getLogo());
        }

        //初始化部门信息
        TSysOrg org = tenantUserWrapper.getDepartment(deptId);
        if(null != org && YesOrNoType.YES.getIndex() == org.getState()) {
            userInfo.setDeptId(deptId);
            userInfo.setDeptName(org.getName());
        }

        //TODO 初始化权限信息

        return userInfo;
    }

    /**
     * 检测用户信息
     * @param userInfo
     * @param response
     */
    private void checkUserInfo(IUserInfo userInfo, IResultResponse response) {
        if(StringUtils.isEmpty(userInfo.getTenantId()) || StringUtils.isEmpty(userInfo.getTenantName())) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.CORP_NOT_EXIST);
            return;
        }
        if(StringUtils.isEmpty(userInfo.getDeptId()) || StringUtils.isEmpty(userInfo.getDeptName())) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_IN_ORG);
            return;
        }
    }
}
