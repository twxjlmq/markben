package com.markben.org.rest.service;

import com.markben.beans.response.ResultResponse;
import com.markben.org.rest.bean.OrgUserInfo;
import com.markben.org.rest.vo.OrgLoginResultVO;

/**
 * 组织的登录接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface OrgLoginService {

    /**
     * 登录方法
     * @param username 用户名
     * @param password 密码
     * @return 返回结果
     */
    ResultResponse<OrgLoginResultVO> login(String username, String password);

    /**
     * 确认登录方法
     * @param userId 用户ID
     * @param deptId 部门ID
     * @param token TOKEN
     * @return 返回结果
     */
    ResultResponse<OrgUserInfo> confirmLogin(String userId, String deptId, String token);

}
