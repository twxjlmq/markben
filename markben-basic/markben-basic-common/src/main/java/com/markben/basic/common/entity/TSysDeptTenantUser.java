package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.multi.tenancy.entity.AbstractSupportTenantEntity;

/**
 * 组织机构与租户用户的关联表
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_org_tenant_user")
public class TSysDeptTenantUser extends AbstractSupportTenantEntity {

    private String deptId;

    private String tenantUserId;

    public String getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
