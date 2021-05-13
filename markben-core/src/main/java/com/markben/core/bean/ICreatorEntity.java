package com.markben.core.bean;

/**
 * 含有创建人的实体类
 * @author 乌草坡
 * @since 1.0.0
 */
public interface ICreatorEntity extends ITenantEntity, IPKStringEntity {

    /**
     * 设置创建人对应的租户用户的id
     * @param tenantUserId 租户的用户ID
     */
    void setTenantUserId(String tenantUserId);

    /**
     * 获取租户用户ID
     * @return 返回租户用户ID
     */
    String getTenantUserId();

}
