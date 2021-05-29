package com.markben.beans.response;

/**
 * 默认{@link PaginateResponse }接口实现类
 * @author 乌草坡
 * @since 0.0.1
 */
public class DefaultPaginateResponseImpl<T> extends DefaultCollectionResponseImpl<T> implements PaginateResponse<T> {

    private Long total;

    private Integer size;

    private Integer totalPage;

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
