package com.markben.rest.common.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 按状态搜索对象
 * @author 乌草坡
 * @since 0.0.1
 */
public class StateSearchVO extends NameSearchVO {

    @ApiModelProperty("按状态搜索")
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
