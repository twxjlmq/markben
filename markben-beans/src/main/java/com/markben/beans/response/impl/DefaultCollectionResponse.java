package com.markben.beans.response.impl;

import com.markben.beans.response.ICollectionResponse;

import java.util.Collection;

/**
 * 默认{@link ICollectionResponse }接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultCollectionResponse<T> extends DefaultBaseResponse implements ICollectionResponse<T> {

    private Collection<T> data;

    @Override
    public Collection<T> getData() {
        return data;
    }

    @Override
    public void setData(Collection<T> data) {
        this.data = data;
    }
}
