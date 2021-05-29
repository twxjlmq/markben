package com.markben.basic.rest.vo.resource;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 更新资源请求对象
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateResourceRequest extends CreateResourceRequest {

    @ApiModelProperty(value = "ID", required = true)
    @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
