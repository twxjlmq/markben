package com.markben.rest.common.vo;

import com.markben.beans.bean.SearchFilter;
import io.swagger.annotations.ApiModelProperty;

/**
 * 按名称搜索的类
 * @author 乌草坡
 * @since 0.0.1
 */
public class NameSearchVO implements BaseVO, SearchFilter {

    @ApiModelProperty("按名称搜索")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
