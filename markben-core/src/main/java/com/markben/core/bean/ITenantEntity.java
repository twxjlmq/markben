package com.markben.core.bean;

/**
 * 租户实体接口类
 * @author 乌草坡
 * @since 1.0
 */
public interface ITenantEntity {

    /**
     * 返回租户的组织（或公司）ID
     * @return 返回组织ID
     */
    String getCorpId();

    /**
     * 设置组织ID
     * @param corpId 组织ID
     */
    void setCorpId(String corpId);

}
