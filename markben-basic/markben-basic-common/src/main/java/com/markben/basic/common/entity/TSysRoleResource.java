package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.multi.tenancy.entity.AbstractSupportTenantEntity;

/**
 * 角色表与资源表的关联表
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_role_resource")
public class TSysRoleResource extends AbstractSupportTenantEntity {

    private String roleId;

    private String resourceId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
