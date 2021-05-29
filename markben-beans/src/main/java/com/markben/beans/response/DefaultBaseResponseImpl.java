package com.markben.beans.response;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.BaseResponse;

/**
 * 默认{@link BaseResponse }接口实现类
 * @author 乌草坡
 * @since 0.0.1
 */
public class DefaultBaseResponseImpl implements BaseResponse {

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
