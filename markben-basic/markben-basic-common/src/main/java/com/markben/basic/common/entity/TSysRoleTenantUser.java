package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.multi.tenancy.entity.AbstractSupportTenantEntity;

/**
 * 角色表与租户的用户表的关联表
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_role_tenant_user")
public class TSysRoleTenantUser extends AbstractSupportTenantEntity {

    private String roleId;

    private String tenantUserId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }
}
