package com.markben.multi.tenancy.entity;

import com.markben.core.bean.AbstractCommonPropEntity;

/**
 * 租户实体抽象类
 * @author 乌草坡
 * @since 1.0
 */
public class AbstractSupportTenantEntity extends AbstractCommonPropEntity implements SupportTenantEntity {

    /**
     * 租户ID
     */
    private String tenantId;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
