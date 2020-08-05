package com.markben.beans.response.impl;

import com.markben.beans.response.IResultResponse;

/**
 * 默认{@link IResultResponse }接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultResultResponse<T> extends DefaultBaseResponse implements IResultResponse<T> {

    private T result;

    @Override
    public T getResult() {
        return result;
    }

    @Override
    public void setResult(T result) {
        this.result = result;
    }
}
