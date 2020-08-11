package com.markben.core.bean;

/**
 * 租户实体抽象类
 * @author 乌草坡
 * @since 1.0
 */
public class AbstractTenantEntity extends AbstractCommonPropEntity implements ITenantEntity {

    /**
     * 企业或组织ID
     */
    private String corpId;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
}
