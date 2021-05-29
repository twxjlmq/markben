package com.markben.core.bean;

import com.markben.common.enums.YesOrNoType;

import java.util.Date;

/**
 * 通用属性实体抽象类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractCommonPropEntity extends AbstractBaseEntity implements SupportCreateTime, SupportStateEntity {

    private Date createTime;

    /**
     * 是否有效
     * 1--有效;
     * 0--无效
     */
    private Integer state = YesOrNoType.YES.getIndex();

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
