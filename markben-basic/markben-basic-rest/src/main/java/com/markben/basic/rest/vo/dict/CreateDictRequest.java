package com.markben.basic.rest.vo.dict;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.beans.validator.hibernate.IntValueContain;
import com.markben.common.constant.MarkbenConstant;
import com.markben.common.enable.Checkable;
import com.markben.common.enums.YesNoType;
import com.markben.restful.common.vo.RestRequest;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 创建数据字典请求对象
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateDictRequest implements RestRequest, Checkable {

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "父ID；为空时使用默认值：0")
    private String parentId = MarkbenConstant.TREE_ROOT_ID;

    @ApiModelProperty(value = "状态，1--有效；0--无效；默认为：1")
    @IntValueContain(message = "状态值，范围为：0和1", value = {0, 1})
    private Integer state = YesNoType.YES.getIndex();

    @ApiModelProperty(value = "排序序号，默认为0")
    private Integer sortOrder = 0;

    @ApiModelProperty(value = "业务值", required = true)
    @NotBlank(message = "业务值不能为空")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
