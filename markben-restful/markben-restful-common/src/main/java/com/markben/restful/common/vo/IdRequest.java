package com.markben.restful.common.vo;

import com.markben.common.enable.Checkable;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * ID请求类
 * @author 乌草坡
 * @since 0.0.1
 */
public class IdRequest implements BaseVO, Checkable {

    @NotEmpty
    @ApiModelProperty(value = "ID，多个值之间用英文逗号分隔")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
