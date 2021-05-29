package com.markben.core.bean;

/**
 * 含有创建人的抽象实体类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractSupportCreatorEntity extends  AbstractCommonPropEntity implements SupportCreatorEntity {

    private String creator;

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
