package com.markben.basic.rest.service;

import com.markben.basic.rest.vo.user.LoginResultVO;
import com.markben.beans.bean.IUserInfo;
import com.markben.beans.response.IResultResponse;

/**
 * 登录接口
 * @author 乌草坡
 * @since 1.0
 */
public interface ILoginService {

    /**
     * 登录方法
     * @param username 用户名
     * @param password 密码
     * @return 返回结果
     */
    IResultResponse<LoginResultVO> login(String username, String password);

    /**
     * 确认登录方法
     * @param userId 用户ID
     * @param tenantId 租户ID
     * @param deptId 部门ID
     * @param token TOKEN
     * @return 返回结果
     */
    IResultResponse<IUserInfo> confirmLogin(String userId, String tenantId, String deptId, String token);

}
