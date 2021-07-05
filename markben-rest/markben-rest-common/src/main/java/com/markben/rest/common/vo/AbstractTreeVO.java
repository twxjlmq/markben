package com.markben.rest.common.vo;

import com.markben.core.bean.SupportTreeEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 树形抽象类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractTreeVO implements SupportTreeEntity<String>, BaseVO {

    private String id;

    private String parentId;

    private String name;

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
    @ApiModelProperty(value = "ID")
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    @ApiModelProperty(value = "父ID")
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    @ApiModelProperty(value = "名称")
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @ApiModelProperty(value = "排序序号")
    public Integer getSortOrder() {
        return sortOrder;
    }

    @Override
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
