package com.markben.core.bean;

/**
 * 租户实体接口类
 * @author 乌草坡
 * @since 1.0
 */
public interface ITenantEntity {

    /**
     * 返回租户ID
     * @return 返回租户ID
     */
    String getTenantId();

    /**
     * 设置租户ID
     * @param tenantId 租户ID
     */
    void setTenantId(String tenantId);

}
