package com.markben.beans.bean;

import java.util.Collection;

/**
 * 默认用户信息接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultUserInfo implements IUserInfo {

    private String userId;

    private String corpId;

    private String corpUserId;

    private String username;

    private String nickname;

    private String mobile;

    private String avatar;

    private String token;

    private String deptId;

    private String corpName;

    private String deptName;

    private String logo;

    private Collection<String> orgIds;

    private Collection<String> menuRoleIds;

    private Collection<String> roleIds;

    private Collection<String> authOrgIds;

    private Collection<String> authCorpUserIds;

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    @Override
    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getDeptId() {
        return deptId;
    }

    @Override
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String getCorpName() {
        return corpName;
    }

    @Override
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    @Override
    public String getDeptName() {
        return deptName;
    }

    @Override
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    @Override
    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Collection<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(Collection<String> orgIds) {
        this.orgIds = orgIds;
    }

    @Override
    public Collection<String> getMenuRoleIds() {
        return menuRoleIds;
    }

    public void setMenuRoleIds(Collection<String> menuRoleIds) {
        this.menuRoleIds = menuRoleIds;
    }

    @Override
    public Collection<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Collection<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public Collection<String> getAuthOrgIds() {
        return authOrgIds;
    }

    public void setAuthOrgIds(Collection<String> authOrgIds) {
        this.authOrgIds = authOrgIds;
    }

    @Override
    public Collection<String> getAuthCorpUserIds() {
        return authCorpUserIds;
    }

    public void setAuthCorpUserIds(Collection<String> authCorpUserIds) {
        this.authCorpUserIds = authCorpUserIds;
    }
}
