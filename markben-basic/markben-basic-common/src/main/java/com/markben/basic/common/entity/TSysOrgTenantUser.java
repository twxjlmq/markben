package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.ICreatorEntity;
import com.markben.core.bean.ITenantEntity;

/**
 * 组织机构与租户用户的关联表
 * @author 乌草坡
 * @since 1.0.0
 */
@TableName(value = "t_sys_org_tenant_user")
public class TSysOrgTenantUser extends AbstractBaseEntity implements ICreatorEntity {

    private String tenantId;

    private String orgId;

    private String tenantUserId;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
