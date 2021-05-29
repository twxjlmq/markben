package com.markben.basic.rest.service;

import com.markben.basic.rest.vo.user.LoginResultVO;
import com.markben.beans.bean.UserInfo;
import com.markben.beans.response.ResultResponse;
import com.markben.rest.org.bean.OrgUserInfo;

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
    ResultResponse<LoginResultVO> login(String username, String password);

    /**
     * 确认登录方法
     * @param userId 用户ID
     * @param tenantId 租户ID
     * @param deptId 部门ID
     * @param token TOKEN
     * @return 返回结果
     */
    ResultResponse<OrgUserInfo> confirmLogin(String userId, String tenantId, String deptId, String token);

}
