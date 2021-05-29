package com.markben.beans.response;

/**
 * 结果返回对象接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface ResultResponse<T> extends BaseResponse {

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
