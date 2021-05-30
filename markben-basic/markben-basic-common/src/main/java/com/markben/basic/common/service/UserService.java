package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysUser;
import com.markben.core.service.MgrService;

import java.util.Optional;

/**
 * 系统用户服务类接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface UserService extends MgrService<TSysUser> {

    /**
     * 修改密码
     * @param userId 用户ID
     * @param newPassword 新密码
     * @param oldPassword 旧密码
     */
    void changePassword(String userId, String newPassword, String oldPassword);

    /**
     * 获取通过盐值加密过的md5密码
     * @param password 密码
     * @return 返回加密后的密码
     */
    Optional<String> getMd5SaltPassword(String password);

    /**
     * 获取用户实体信息
     * @param username 用户名
     * @return 如果获取到数据则返回用户实体对象；否则返回null
     */
    Optional<TSysUser> getUser(String username);

    /**
     * 根据用户名获取用户实体对象（用于登录方法）
     * @param username 用户名（也有可能是手机号）
     * @return 如果获取到数据则返回用户实体对象；否则返回null
     */
    Optional<TSysUser> getUserOfLogin(String username);

}
