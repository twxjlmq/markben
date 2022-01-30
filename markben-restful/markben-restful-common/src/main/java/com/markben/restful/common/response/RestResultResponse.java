package com.markben.restful.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.beans.response.ResultResponse;
import io.swagger.annotations.ApiModelProperty;

/**
 * REST接口结果响应对象
 * @author 乌草坡
 * @param <T> result属性的类型
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RestResultResponse<T> extends RestBaseResponse implements ResultResponse<T> {

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
