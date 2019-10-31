package com.markben.core.entity;

import java.io.Serializable;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface IEntityBean<T> extends Serializable {

    /**
     * 获取主键ID
     * @return 返回主键
     */
    T getId();

    /**
     * 设置主键
     * @param id 主键值
     */
    void setId(T id);

}
