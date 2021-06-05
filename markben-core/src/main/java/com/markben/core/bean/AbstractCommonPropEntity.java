package com.markben.core.bean;

import com.markben.common.enums.YesNoType;

import java.util.Date;

/**
 * 通用属性实体抽象类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractCommonPropEntity extends AbstractSupportStateEntity implements SupportCreateTime {

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
