package com.markben.org.rest.service;

import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.UserService;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.helper.ResponseHelper;
import com.markben.beans.response.ResultResponse;
import com.markben.cache.ICache;
import com.markben.cache.ICacheManager;
import com.markben.common.enums.YesNoType;
import com.markben.common.logger.Logger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.org.common.entity.TSysDepartment;
import com.markben.org.rest.bean.OrgUserInfo;
import com.markben.org.rest.bean.OrgUserInfoImpl;
import com.markben.org.rest.vo.OrgLoginResultVO;
import com.markben.org.rest.wrapper.UserWrapper;
import com.markben.restful.common.helper.SecurityFilterHelper;
import com.markben.restful.common.response.RestResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 组织的登录服务类实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class OrgLoginServiceImpl implements OrgLoginService {

    private static final Logger logger = LoggerUtils.getLogger(OrgLoginServiceImpl.class);

    private static final long TEMP_TOKEN_VALID_TIME = 1000 * 60 * 5;  //5分钟

    private UserService userService;

    public OrgLoginServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultResponse<OrgLoginResultVO> login(String username, String password) {
        StringUtils.isAssert(username, "用户名不能为空", this);
        StringUtils.isAssert(password, "密码不能为空", this);
        ResultResponse<OrgLoginResultVO> response = new RestResultResponse<>();
        Optional<TSysUser> userOpt = userService.getUserOfLogin(username);
        if(!userOpt.isPresent()) {
            LoggerUtils.error(logger, "用户名输入错误,输入的用户名为:[{}].", username);
            ResponseHelper.setResponseStatus(response, MarkbenStatusEnums.LOGIN_USER_OR_PWD_ERROR);
            return response;
        }
        TSysUser user = userOpt.get();
        Optional<String> md5PasswordOpt = userService.getMd5SaltPassword(password);
        if(!md5PasswordOpt.isPresent() || !md5PasswordOpt.get().equals(user.getPassword())) {
            LoggerUtils.error(logger, "密码输入错误,输入的密码为:[{}].", SecurityFilterHelper.filterPassword(password));
            ResponseHelper.setResponseStatus(response, MarkbenStatusEnums.LOGIN_USER_OR_PWD_ERROR);
            return response;
        }

        OrgLoginResultVO loginResult = createLoginResult(user, response);
        if(null != loginResult) {
            ResponseHelper.setSuccessResult(response);
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
    public ResultResponse<OrgUserInfo> confirmLogin(String userId, String deptId, String token) {
        LoggerUtils.debug(logger, "正在确认登录处理中...");
        StringUtils.isAssert(userId, "用户ID不能为空", this);
        StringUtils.isAssert(deptId, "部门ID不能为空", this);
        ResultResponse<OrgUserInfo> response = new RestResultResponse<>();
        //验证token
        LoggerUtils.debug(logger, "正在验证token，传入的token值为:[{}].", token);
        ICacheManager cacheManager = MarkbenContextFactory.getConfiguration().getCacheManager();
        ICache<String, Object> cache = cacheManager.getCacheIfAbsent(userId);
        if(null == cache) {
            LoggerUtils.debug(logger, "获取到的缓存对象为null.");
            ResponseHelper.setResponseStatus(response, MarkbenStatusEnums.INVALID_TOKEN);
            return response;
        }
        String cacheToken = StringUtils.handleNull(cache.get(userId));
        LoggerUtils.debug(logger, "从缓存中获取到的token为:[{}].", cacheToken);
        if(StringUtils.isEmpty(cacheToken) || !cacheToken.equals(token)) {
            ResponseHelper.setResponseStatus(response, MarkbenStatusEnums.INVALID_TOKEN);
            return response;
        }
        //清除临时token缓存数据
        cache.remove(userId);
        cacheManager.remove(userId);

        Optional<TSysUser> userOpt = userService.find(userId);
        if(!userOpt.isPresent()) {
            ResponseHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_EXIST);
        } else {
            OrgUserInfo userInfo = startInitUserInfo(userOpt.get(), deptId);
            checkUserInfo(userInfo, response);
        }
        return response;
    }

    /**
     * 创建登录结果
     * @param user 用户实体对象
     * @param response 返回对象
     * @return 如果登录成功则返回：登录结果对象，否则返回null
     */
    private OrgLoginResultVO createLoginResult(TSysUser user, ResultResponse response) {
        UserWrapper userWrapper = new UserWrapper(user);
        boolean isUserInOrg = false; //判断用户是否在部门中
        List<OrgLoginResultVO.SimpleDeptInfoVO> simpleDeptList = createSimpleOrgList(userWrapper);
        if(CollectionUtils.isNotEmpty(simpleDeptList)) {
            isUserInOrg = true;
        } else {
            LoggerUtils.warn(logger, "用户不在部门中, userId:[{}]---nickname:[{}].", user.getId(), user.getNickname());
        }
        if(!isUserInOrg) {
            ResponseHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_IN_ORG);
            return null;
        }
        OrgLoginResultVO loginResult = new OrgLoginResultVO(user.getId());
        loginResult.setDeptList(simpleDeptList);
        return loginResult;
    }


    /**
     * 创建简单部门信息列表；
     * 如果获取到部门实体列表返回简单部门信息列表；否则返回null
     * @param userWrapper 用户封装对象
     * @return 返回简单部门信息或null
     */
    private List<OrgLoginResultVO.SimpleDeptInfoVO> createSimpleOrgList(UserWrapper userWrapper) {
        List<TSysDepartment> orgList = userWrapper.getDeptList();
        if(CollectionUtils.isEmpty(orgList)) {
            return null;
        }
        return orgList.stream().map(o -> new OrgLoginResultVO.SimpleDeptInfoVO(o.getId(), o.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 开始初始化用户信息
     * @param user 用户实体对象
     * @param deptId 部门ID
     * @return 返回用户信息对象
     */
    private OrgUserInfo startInitUserInfo(TSysUser user, String deptId) {
        UserWrapper userWrapper = new UserWrapper(user);
        OrgUserInfo userInfo = new OrgUserInfoImpl();
        userInfo.setNickname(user.getNickname());
        userInfo.setUserId(user.getId());

        if(null != user) {
            userInfo.setUserId(user.getId());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setMobile(user.getMobile());
            userInfo.setUsername(user.getUsername());
        }

        //初始化部门信息
        TSysDepartment department = userWrapper.getDepartment(deptId);
        if(null != department && YesNoType.YES.getIndex() == department.getState()) {
            userInfo.setDeptId(deptId);
            userInfo.setDeptName(department.getName());
        }

        //TODO 初始化权限信息

        return userInfo;
    }

    /**
     * 检测用户信息
     * @param userInfo 用户信息
     * @param response 结果相应对象
     */
    private void checkUserInfo(OrgUserInfo userInfo, ResultResponse response) {
        if(StringUtils.isEmpty(userInfo.getDeptId()) || StringUtils.isEmpty(userInfo.getDeptName())) {
            ResponseHelper.setResponseStatus(response, MarkbenStatusEnums.USER_NOT_IN_ORG);
            return;
        }
    }
}
