package com.markben.basic.rest.service.impl;

import com.markben.basic.rest.service.LoginService;
import com.markben.beans.bean.DefaultUserInfoImpl;
import com.markben.beans.bean.UserInfo;
import com.markben.basic.rest.entity.TSysUser;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.DefaultResultResponseImpl;
import com.markben.beans.response.ResultResponse;
import com.markben.basic.rest.service.UserService;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.rest.common.helper.RestCommonHelper;
import com.markben.rest.common.helper.SecurityFilterHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 组织的登录服务类实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerUtils.getLogger(LoginServiceImpl.class);

    private UserService userService;

    public LoginServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultResponse<UserInfo> login(String username, String password) {
        StringUtils.isAssert(username, "用户名不能为空", this);
        StringUtils.isAssert(password, "密码不能为空", this);
        ResultResponse<UserInfo> response = new DefaultResultResponseImpl<>();
        Optional<TSysUser> userOpt = userService.getUserOfLogin(username);
        if(!userOpt.isPresent()) {
            LoggerUtils.error(logger, "用户名输入错误,输入的用户名为:[{}].", username);
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.LOGIN_USER_OR_PWD_ERROR);
            return response;
        }
        TSysUser user = userOpt.get();
        Optional<String> md5PasswordOpt = userService.getMd5SaltPassword(password);
        if(!md5PasswordOpt.isPresent() || !md5PasswordOpt.get().equals(user.getPassword())) {
            LoggerUtils.error(logger, "密码输入错误,输入的密码为:[{}].", SecurityFilterHelper.filterPassword(password));
            RestCommonHelper.setResponseStatus(response, MarkbenStatusEnums.LOGIN_USER_OR_PWD_ERROR);
            return response;
        }

        UserInfo userInfo = createUserInfo(user);
        response.setResult(userInfo);
        RestCommonHelper.setSuccessResult(response);
        return response;
    }

    /**
     * 创建用户信息对象
     * @param user 用户实体对象
     * @return 如果登录成功则返回：登录结果对象，否则返回null
     */
    private UserInfo createUserInfo(TSysUser user) {
        DefaultUserInfoImpl userInfo = new DefaultUserInfoImpl();
        userInfo.setNickname(user.getNickname());
        userInfo.setUserId(user.getId());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setMobile(user.getMobile());
        userInfo.setUsername(user.getUsername());
        return userInfo;
    }
}
