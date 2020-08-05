package com.markben.beans.bean;

import java.io.Serializable;
import java.util.Collection;

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
     * 获取企业ID
     * @return 返回企业ID
     */
    String getCorpId();

    /**
     * 获取企业用户ID
     * @return 返回企业用户ID
     */
    String getCorpUserId();

    /**
     * 获取用户名
     * @return 返回用户名
     */
    String getUsername();

    /**
     * 获取用户匿名
     * @return 返回匿名
     */
    String getNickname();

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
     * 获取头像地址
     * @return 返回头衔地址
     */
    String getAvatar();

    /**
     * 获取token
     * @return 返回token
     */
    String getToken();

    /**
     * 获取角色ID集合
     * @return 返回角色ID集合
     */
    Collection<String> getRoleIds();

    /**
     * 获取菜单角色ID集合
     * @return 返回菜单角色ID集合
     */
    Collection<String> getMenuRoleIds();

    /**
     * 返回授权部门ID集合
     * @return 返回授权部门ID集合
     */
    Collection<String> getAuthOrgIds();

    /**
     * 获取授权企业用户ID集合
     * @return 返回授权企业用户ID集合
     */
    Collection<String> getAuthCorpUserIds();

}
