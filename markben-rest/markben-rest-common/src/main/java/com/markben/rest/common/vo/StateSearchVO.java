package com.markben.rest.common.vo;

import com.markben.beans.bean.StateSearch;
import io.swagger.annotations.ApiModelProperty;

/**
 * 按状态搜索对象
 * @author 乌草坡
 * @since 0.0.1
 */
public class StateSearchVO extends NameSearchVO implements StateSearch {

    @ApiModelProperty(value = "按状态搜索", example="0")
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
