package com.markben.beans.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 用户信息接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface UserInfo extends Serializable {

    /**
     * 用户ID
     * @return 返回用户ID
     */
    String getUserId();

    /**
     * 设置用户ID
     * @param userId 用户ID
     */
    void setUserId(String userId);

    /**
     * 获取用户名
     * @return 返回用户名
     */
    String getUsername();

    /**
     * 设置用户名
     * @param username 用户名
     */
    void setUsername(String username);

    /**
     * 获取用户匿名
     * @return 返回匿名
     */
    String getNickname();

    /**
     * 设置匿名
     * @param nickname 匿名
     */
    void setNickname(String nickname);

    /**
     * 获取手机号
     * @return 返回手机号
     */
    String getMobile();

    /**
     * 设置手机号
     * @param mobile 手机号
     */
    void setMobile(String mobile);

    /**
     * 获取头像地址
     * @return 返回头衔地址
     */
    String getAvatar();

    /**
     * 设置头像
     * @param avatar 头像路径
     */
    void setAvatar(String avatar);

    /**
     * 获取token
     * @return 返回token
     */
    String getToken();

    /**
     * 设置Token
     * @param token TOKEN
     */
    void setToken(String token);

}
