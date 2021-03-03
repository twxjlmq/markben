package com.markben.basic.rest.vo.dict;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.rest.common.vo.AbstractTreeVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 数据字典项对象
 * @author 乌草坡
 * @since 1.0
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DictItemVO extends AbstractTreeVO {

    @ApiModelProperty(value = "业务值")
    private String value;

    @ApiModelProperty(value = "状态; 1--有效；0--无效")
    private Integer state;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public DictItemVO() {
        super();
    }

    public DictItemVO(String id, String parentId, String name, Integer sortOrder, String value, Integer state) {
        super(id, parentId, name, sortOrder);
        this.value = value;
        this.state = state;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
