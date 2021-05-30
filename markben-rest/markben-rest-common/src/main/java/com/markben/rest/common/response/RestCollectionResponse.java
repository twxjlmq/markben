package com.markben.rest.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.beans.response.CollectionResponse;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

/**
 * REST接口集合响应对象
 * @author 乌草坡
 * @param <T> data属性的类型
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RestCollectionResponse<T> extends RestBaseResponse implements CollectionResponse<T> {

    @ApiModelProperty(value = "返回数据的集合")
    private Collection<T> data;

    public RestCollectionResponse() {
        super();
    }

    public RestCollectionResponse(Integer status, String msg, Collection<T> data) {
        super(status, msg);
        this.data = data;
    }

    @Override
    public Collection<T> getData() {
        return data;
    }

    @Override
    public void setData(Collection<T> data) {
        this.data = data;
    }
}
