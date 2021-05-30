package com.markben.core.bean;

import com.markben.common.constant.MarkbenConstant;

/**
 * 基础树形实体类
 * @author 乌草坡
 * @since 0.0.1
 */
public class AbstractSupportTreeEntity extends AbstractCommonPropEntity implements SupportTreeEntity<String> {

    private String name;

    private String parentId = MarkbenConstant.TREE_ROOT_ID;

    private Integer sortOrder = 0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public Integer getSortOrder() {
        return sortOrder;
    }

    @Override
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
