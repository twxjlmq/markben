package com.markben.beans.response.impl;

import com.markben.beans.response.IPaginateResponse;

/**
 * 默认{@link IPaginateResponse }接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultPaginateResponse<T> extends DefaultCollectionResponse<T> implements IPaginateResponse<T> {

    private Long total;

    private Integer size;

    private Integer totalPage;

    @Override
    public Long getTotal() {
        return null;
    }

    @Override
    public void setTotal(Long total) {

    }

    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public void setSize(Integer size) {

    }

    @Override
    public Integer getTotalPage() {
        return null;
    }

    @Override
    public void setTotalPage(Integer totalPage) {

    }
}
