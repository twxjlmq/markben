package com.markben.beans.response.impl;

import com.markben.beans.response.IBaseResponse;

/**
 * 默认{@link IBaseResponse }接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultBaseResponse implements IBaseResponse {

    private Integer status;

    private String msg;

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
