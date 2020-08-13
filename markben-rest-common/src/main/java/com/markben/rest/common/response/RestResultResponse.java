package com.markben.rest.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.beans.response.IResultResponse;
import io.swagger.annotations.ApiModelProperty;

/**
 * REST接口结果响应对象
 * @author 乌草坡
 * @since 1.0
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RestResultResponse<T> extends RestBaseResponse implements IResultResponse<T> {

    @ApiModelProperty(value = "返回数据")
    private T result;

    public RestResultResponse() {
        super();
    }

    public RestResultResponse(Integer status, String msg, T result) {
        super(status, msg);
        this.result = result;
    }

    @Override
    public T getResult() {
        return result;
    }

    @Override
    public void setResult(T result) {
        this.result = result;
    }

}
