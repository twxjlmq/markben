package com.markben.beans.response;

/**
 * 结果返回对象接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IResultResponse<T> extends IBaseResponse {

    /**
     * 获取结果对象
     * @return 返回结果对象
     */
    T getResult();

    /**
     * 设置结果对象
     * @param result 结果对象
     */
    void setResult(T result);

}
