package com.markben.core.bean;

/**
 * 租户实体抽象类
 * @author 乌草坡
 * @since 1.0.0
 */
public class AbstractBaseTenantEntity extends AbstractBaseEntity implements ITenantEntity {

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
