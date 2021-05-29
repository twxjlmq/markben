package com.markben.beans.response;

import java.util.Collection;

/**
 * 默认{@link CollectionResponse }接口实现类
 * @author 乌草坡
 * @since 0.0.1
 */
public class DefaultCollectionResponseImpl<T> extends DefaultBaseResponseImpl implements CollectionResponse<T> {

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
