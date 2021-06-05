package com.markben.personal.rest.service;

import com.markben.beans.bean.UserInfo;
import com.markben.beans.response.ResultResponse;

/**
 * 登录接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface LoginService {

    /**
     * 登录方法
     * @param username 用户名
     * @param password 密码
     * @return 返回结果
     */
    ResultResponse<UserInfo> login(String username, String password);

}
