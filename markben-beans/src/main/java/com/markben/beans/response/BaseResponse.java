package com.markben.beans.response;

/**
 * 返回对象接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface BaseResponse {

    /**
     * 获取状态码
     * @return 返回状态码
     */
    int getStatus();

    /**
     * 设置状态码
     * @param status 状态码
     */
    void setStatus(int status);

    /**
     * 获取状态对应的状态信息
     * @return 返回信息
     */
    String getMsg();

    /**
     * 设置状态对应的信息
     * @param msg 状态信息
     */
    void setMsg(String msg);
}
