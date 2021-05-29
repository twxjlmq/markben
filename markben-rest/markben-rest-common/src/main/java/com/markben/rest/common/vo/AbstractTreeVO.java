package com.markben.rest.common.vo;

import com.markben.core.bean.SupportTreeEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 树形抽象类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractTreeVO implements SupportTreeEntity<String> {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "排序序号")
    private Integer sortOrder;

    public AbstractTreeVO() {

    }

    public AbstractTreeVO(String id, String parentId, String name, Integer sortOrder) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.sortOrder = sortOrder;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
