package com.markben.basic.rest.service;

import com.markben.basic.common.entity.TSysCorp;
import com.markben.basic.common.entity.TSysCorpUser;
import com.markben.basic.common.entity.TSysOrg;
import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.ICorpUserService;
import com.markben.basic.common.service.IUserService;
import com.markben.basic.common.wrapper.CorpUserWrapper;
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
 * @since 1.0
 */
@Service
public class LoginServiceImpl implements ILoginService {

    private static final ILogger logger = LoggerUtils.getLogger(LoginServiceImpl.class);

    private static final long TEMP_TOKEN_VALID_TIME = 1000 * 60 * 5;  //5分钟

    @Autowired
    private IUserService userService;
    @Autowired
    private ICorpUserService corpUserService;

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
    public IResultResponse<IUserInfo> confirmLogin(String userId, String corpId, String deptId, String token) {
        LoggerUtils.debug(logger, "正在确认登录处理中...");
        StringUtils.isAssert(userId, "用户ID不能为空", this);
        StringUtils.isAssert(corpId, "企业ID不能为空", this);
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

        TSysCorpUser corpUser = corpUserService.getCorpUser(userId, corpId);
        if(null == corpUser) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.CORP_USER_NOT_EXIST);
            return response;
        }
        IUserInfo userInfo = startInitUserInfo(corpUser, deptId);
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
        List<TSysCorpUser> corpUsers = corpUserService.getCorpUser(userId);
        if(CollectionUtils.isEmpty(corpUsers)) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_IN_CORP);
            LoggerUtils.warn(logger, "用户不在企业中,userId:[{}].", userId);
            return null;
        }
        List<LoginResultVO.SimpleCorpInfoVO> corpInfoList = new ArrayList<>(corpUsers.size());
        boolean isUserInOrg = false; //判断用户是否在部门中
        for(TSysCorpUser corpUser : corpUsers) {
            CorpUserWrapper corpUserWrapper = new CorpUserWrapper(corpUser);
            LoginResultVO.SimpleCorpInfoVO simpleCorpInfo = createSimpleCorpInfo(corpUserWrapper);
            if(null != simpleCorpInfo) {
                simpleCorpInfo.setIsDefault(corpUser.getIsDefault());
                List<LoginResultVO.SimpleOrgInfoVO> simpleOrgList = createSimpleOrgList(corpUserWrapper);
                if(CollectionUtils.isNotEmpty(simpleOrgList)) {
                    simpleCorpInfo.setOrgList(simpleOrgList);
                    isUserInOrg = isUserInOrg || true;
                } else {
                    LoggerUtils.warn(logger, "企业用户不在部门中,corpUserId:[{}]---nickname:[{}].",
                            corpUser.getId(), corpUser.getNickname());
                }
                corpInfoList.add(simpleCorpInfo);
            }
        }
        if(!isUserInOrg) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_IN_ORG);
            return null;
        }
        LoginResultVO loginResult = new LoginResultVO(userId);
        loginResult.setCorps(corpInfoList);
        return loginResult;
    }


    /**
     * 创建简单企业信息；如果有企业信息返回简单企业信息；否则返回null
     * @param corpUserWrapper 企业用户封装对象
     * @return 返回简单企业信息或null
     */
    private LoginResultVO.SimpleCorpInfoVO createSimpleCorpInfo(CorpUserWrapper corpUserWrapper) {
        TSysCorp corp = corpUserWrapper.getCorp();
        if(null == corp) {
            return null;
        }
        LoginResultVO.SimpleCorpInfoVO simpleCorpInfo = new LoginResultVO.SimpleCorpInfoVO();
        simpleCorpInfo.setCorpId(corp.getId());
        simpleCorpInfo.setName(corp.getName());
        simpleCorpInfo.setLogo(corp.getLogo());
        return simpleCorpInfo;
    }

    /**
     * 创建简单部门信息列表；
     * 如果获取到部门实体列表返回简单部门信息列表；否则返回null
     * @param corpUserWrapper 企业用户封装对象
     * @return 返回简单部门信息或null
     */
    private List<LoginResultVO.SimpleOrgInfoVO> createSimpleOrgList(CorpUserWrapper corpUserWrapper) {
        List<TSysOrg> orgList = corpUserWrapper.getOrgList();
        if(CollectionUtils.isEmpty(orgList)) {
            return null;
        }
        return orgList.stream().map(o -> new LoginResultVO.SimpleOrgInfoVO(o.getId(), o.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 开始初始化用户信息
     * @param corpUser 企业用户实体对象
     * @param deptId 部门ID
     * @return 返回用户信息对象
     */
    private IUserInfo startInitUserInfo(TSysCorpUser corpUser, String deptId) {
        CorpUserWrapper corpUserWrapper = new CorpUserWrapper(corpUser);
        IUserInfo userInfo = new DefaultUserInfo();
        userInfo.setNickname(corpUser.getNickname());
        userInfo.setCorpUserId(corpUser.getId());
        userInfo.setCorpId(corpUser.getCorpId());

        TSysUser user = corpUserWrapper.getUser();
        if(null != user) {
            userInfo.setUserId(user.getId());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setMobile(user.getMobile());
            userInfo.setUsername(user.getUsername());
        }

        //初始化企业信息
        TSysCorp corp = corpUserWrapper.getCorp();
        if(null != corp) {
            userInfo.setCorpName(corp.getName());
            userInfo.setLogo(corp.getLogo());
        }

        //初始化部门信息
        TSysOrg org = corpUserWrapper.getDepartment(deptId);
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
        if(StringUtils.isEmpty(userInfo.getCorpId()) || StringUtils.isEmpty(userInfo.getCorpName())) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.CORP_NOT_EXIST);
            return;
        }
        if(StringUtils.isEmpty(userInfo.getDeptId()) || StringUtils.isEmpty(userInfo.getDeptName())) {
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_IN_ORG);
            return;
        }
    }
}
