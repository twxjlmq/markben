package com.markben.restful.common.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.common.constant.MarkbenConstant;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TreeCreateRequest implements RestRequest {

    private String name;

    private String parentId = MarkbenConstant.TREE_ROOT_ID;

    private Integer sortOrder = 1;

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "父ID；默认为：0")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @ApiModelProperty(value = "排序序号")
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
