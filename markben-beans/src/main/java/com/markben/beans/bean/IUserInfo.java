package com.markben.beans.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 用户信息接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IUserInfo extends Serializable {

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
     * 获取企业ID
     * @return 返回企业ID
     */
    String getCorpId();

    /**
     * 设置用户ID
     * @param corpId
     */
    void setCorpId(String corpId);

    /**
     * 获取企业用户ID
     * @return 返回企业用户ID
     */
    String getCorpUserId();

    /**
     * 设置企业用户ID
     * @param corpUserId 企业用户ID
     */
    void setCorpUserId(String corpUserId);

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
     * 获取部门ID
     * @return 返回部门ID
     */
    Collection<String> getOrgIds();

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

    /**
     * 获取角色ID集合
     * @return 返回角色ID集合
     */
    Collection<String> getRoleIds();

    /**
     * 设置角色ID集合
     * @param roleIds 角色ID集合
     */
    void setRoleIds(Collection<String> roleIds);

    /**
     * 获取菜单角色ID集合
     * @return 返回菜单角色ID集合
     */
    Collection<String> getMenuRoleIds();

    /**
     * 设置菜单角色ID集合
     * @param menuRoleIds 菜单角色ID集合
     */
    void setMenuRoleIds(Collection<String> menuRoleIds);

    /**
     * 返回授权部门ID集合
     * @return 返回授权部门ID集合
     */
    Collection<String> getAuthOrgIds();

    /**
     * 设置授权部门ID集合
     * @param authOrgIds 授权部门ID集合
     */
    void setAuthOrgIds(Collection<String> authOrgIds);

    /**
     * 获取授权企业用户ID集合
     * @return 返回授权企业用户ID集合
     */
    Collection<String> getAuthCorpUserIds();

    /**
     * 设置授权企业用户ID集合
     * @param authCorpUserIds 授权企业用户ID集合
     */
    void setAuthCorpUserIds(Collection<String> authCorpUserIds);

}
