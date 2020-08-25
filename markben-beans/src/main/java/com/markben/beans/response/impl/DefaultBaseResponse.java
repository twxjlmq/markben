package com.markben.beans.response.impl;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.IBaseResponse;

/**
 * 默认{@link IBaseResponse }接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultBaseResponse implements IBaseResponse {

    private int status = MarkbenStatusEnums.SUCCESS.getStatus();

    private String msg = MarkbenStatusEnums.SUCCESS.getMsg();;

    @Override
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
