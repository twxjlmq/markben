package com.markben.rest.org.bean;

import com.markben.beans.bean.DefaultUserInfoImpl;

import java.util.Collection;

/**
 * 在组织中用户信息实现类
 * @author 乌草坡
 * @since 0.0.1
 */
public class OrgUserInfoImpl extends DefaultUserInfoImpl implements OrgUserInfo {


    private String tenantId;

    private String tenantUserId;

    private String deptId;

    private String tenantName;

    private String deptName;

    private String logo;

    private Collection<String> orgIds;

    private Collection<String> menuRoleIds;

    private Collection<String> roleIds;

    private Collection<String> authOrgIds;

    private Collection<String> authCorpUserIds;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String getTenantUserId() {
        return tenantUserId;
    }

    @Override
    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
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
    public String getTenantName() {
        return tenantName;
    }

    @Override
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
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
    public Collection<String> getOrgIds() {
        return orgIds;
    }

    @Override
    public void setOrgIds(Collection<String> orgIds) {
        this.orgIds = orgIds;
    }

    @Override
    public Collection<String> getMenuRoleIds() {
        return menuRoleIds;
    }

    @Override
    public void setMenuRoleIds(Collection<String> menuRoleIds) {
        this.menuRoleIds = menuRoleIds;
    }

    @Override
    public Collection<String> getRoleIds() {
        return roleIds;
    }

    @Override
    public void setRoleIds(Collection<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public Collection<String> getAuthOrgIds() {
        return authOrgIds;
    }

    @Override
    public void setAuthOrgIds(Collection<String> authOrgIds) {
        this.authOrgIds = authOrgIds;
    }

    @Override
    public Collection<String> getAuthCorpUserIds() {
        return authCorpUserIds;
    }

    @Override
    public void setAuthCorpUserIds(Collection<String> authCorpUserIds) {
        this.authCorpUserIds = authCorpUserIds;
    }
}
