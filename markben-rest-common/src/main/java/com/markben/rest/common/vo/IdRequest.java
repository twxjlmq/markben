package com.markben.rest.common.vo;

import com.markben.common.enable.ICheckable;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author 乌草坡
 * @since 1.0
 */
public class IdRequest implements IBaseVO, ICheckable {

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
