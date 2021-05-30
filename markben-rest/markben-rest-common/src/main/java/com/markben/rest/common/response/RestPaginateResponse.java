package com.markben.rest.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.beans.response.PaginateResponse;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

/**
 * REST分页响应对象
 * @author 乌草坡
 * @param <T> data属性的类型
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RestPaginateResponse<T> extends RestCollectionResponse<T> implements PaginateResponse<T> {

    @ApiModelProperty(value = "总数量")
    private Long total = 0L;

    @ApiModelProperty(value = "每页显示数据量")
    private Integer size = 0;

    @ApiModelProperty(value = "总页数")
    private Integer totalPage = 0;

    public RestPaginateResponse() {
        super();
    }

    public RestPaginateResponse(Integer status, String msg, Collection<T> data, Long total, Integer size, Integer totalPage) {
        super(status, msg, data);
        this.total = total;
        this.size = size;
        this.totalPage = totalPage;
    }

    @Override
    public Long getTotal() {
        return total;
    }

    @Override
    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }

    @Override
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

}
