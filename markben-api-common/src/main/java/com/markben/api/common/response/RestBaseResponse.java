package com.markben.api.common.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.IBaseResponse;
import io.swagger.annotations.ApiModelProperty;

/**
 * 基础REST接口响应对象
 * @author 乌草坡
 * @since 1.0
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RestBaseResponse implements IBaseResponse {

    @ApiModelProperty(value = "返回状态码；如果为1表示成功；非1表示失败")
    private Integer status = MarkbenStatusEnums.SUCCESS.getStatus();

    @ApiModelProperty(value = "返回状态码对应的结果说明")
    private String msg = MarkbenStatusEnums.SUCCESS.getMsg();

    public RestBaseResponse() {
    }

    public RestBaseResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
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
