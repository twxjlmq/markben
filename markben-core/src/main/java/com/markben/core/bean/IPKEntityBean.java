package com.markben.core.bean;

import java.io.Serializable;

/**
 * 有主键的实体接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IPKEntityBean<T extends Serializable> extends IEntityBean {

    /**
     * 获取主键ID
     * @return 返回主键值
     */
    T getId();

    /**
     * 设置主键
     * @param id 主键值
     */
    void setId(T id);
}