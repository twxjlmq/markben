package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.AbstractBaseTenantEntity;
import com.markben.core.bean.ICreatorEntity;

/**
 * 角色表与租户的用户表的关联表
 * @author 乌草坡
 * @since 1.0.0
 */
@TableName(value = "t_sys_role_tenant_user")
public class TSysRoleTenantUser extends AbstractBaseTenantEntity implements ICreatorEntity {

    private String roleId;

    private String tenantUserId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String getTenantUserId() {
        return tenantUserId;
    }

    @Override
    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }
}
