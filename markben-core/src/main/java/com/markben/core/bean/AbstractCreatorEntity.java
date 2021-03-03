package com.markben.core.bean;

/**
 * 含有创建人的抽象实体类
 * @author 乌草坡
 * @since 1.0.0
 */
public abstract class AbstractCreatorEntity extends  AbstractCommonPropEntity implements ICreatorEntity {

    private String corpUserId;

    @Override
    public String getCorpUserId() {
        return corpUserId;
    }

    @Override
    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }
}
