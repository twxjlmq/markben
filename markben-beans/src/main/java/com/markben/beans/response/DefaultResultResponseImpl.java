package com.markben.beans.response;

/**
 * 默认{@link ResultResponse }接口实现类
 * @author 乌草坡
 * @since 0.0.1
 */
public class DefaultResultResponseImpl<T> extends DefaultBaseResponseImpl implements ResultResponse<T> {

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
