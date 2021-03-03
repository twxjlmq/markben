package com.markben.core.bean;

/**
 * 含有创建人的实体类
 * @author 乌草坡
 * @since 1.0.0
 */
public interface ICreatorEntity extends ITenantEntity, IPKStringEntity {

    /**
     * 设置创建人对应的企业用户的id
     * @param corpUserId 企业用户ID
     */
    void setCorpUserId(String corpUserId);

    /**
     * 获取企业用户ID
     * @return 返回企业用户ID
     */
    String getCorpUserId();

}
