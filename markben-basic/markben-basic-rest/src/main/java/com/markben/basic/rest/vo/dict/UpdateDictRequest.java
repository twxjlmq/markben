package com.markben.basic.rest.vo.dict;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.restful.common.vo.UpdateRequest;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 更新数据字典请求对象
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateDictRequest extends CreateDictRequest implements UpdateRequest {

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
