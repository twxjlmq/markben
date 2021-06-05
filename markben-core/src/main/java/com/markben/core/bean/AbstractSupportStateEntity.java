package com.markben.core.bean;

import com.markben.common.enums.YesNoType;

/**
 * 支持状态的实体抽象类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractSupportStateEntity extends AbstractBaseEntity {

    /**
     * 是否有效
     * 1--有效;
     * 0--无效
     */
    private Integer state = YesNoType.YES.getIndex();

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
