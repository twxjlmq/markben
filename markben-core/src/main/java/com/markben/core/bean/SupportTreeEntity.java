package com.markben.core.bean;

import java.io.Serializable;

/**
 * 树型结构接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface SupportTreeEntity<T extends Serializable> extends PKEntityBean<T> {

    /**
     * 名称
     * @return 返回名称
     */
    String getName();

    /**
     * 设置名称
     * @param name 名称
     */
    void setName(String name);

    /**
     * 获取父ID
     * @return 返回父ID
     */
    T getParentId();

    /**
     * 设置父ID
     * @param parentId 父ID
     */
    void setParentId(T parentId);

    /**
     * 获取序号
     * @return 返回序号
     */
    Integer getSortOrder();

    /**
     * 设置序号
     * @param sortOrder 序号
     */
    void setSortOrder(Integer sortOrder);

}
