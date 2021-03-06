package com.markben.core.bean;

import java.io.Serializable;

/**
 * 有主键的实体接口
 * @author 乌草坡
 * @since 0.0.1
 * @param <PK>
 */
public interface PKEntityBean<PK extends Serializable> extends EntityBean {

    /**
     * 获取主键ID
     * @return 返回主键值
     */
    PK getId();

    /**
     * 设置主键
     * @param id 主键值
     */
    void setId(PK id);
}
