package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysUser;
import com.markben.beans.response.IResultResponse;
import com.markben.beans.bean.IUserInfo;
import com.markben.core.service.IMgrService;

/**
 * 系统用户服务类接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IUserService extends IMgrService<TSysUser> {

    /**
     * 修改密码
     * @param userId 用户ID
     * @param newPassword 新密码
     * @param oldPassword 旧密码
     */
    void changePwd(String userId, String newPassword, String oldPassword);

    /**
     * 获取通过盐值加密过的md5密码
     * @param password
     * @return 返回加密后的密码
     */
    String getMd5SaltPassword(String password);

    /**
     * 获取用户实体信息
     * @param username 用户名
     * @return 如果获取到数据则返回用户实体对象；否则返回null
     */
    TSysUser getUser(String username);

    /**
     * 根据用户名获取用户实体对象（用于登录方法）
     * @param username 用户名（也有可能是手机号）
     * @return 如果获取到数据则返回用户实体对象；否则返回null
     */
    TSysUser getUserOfLogin(String username);

}
