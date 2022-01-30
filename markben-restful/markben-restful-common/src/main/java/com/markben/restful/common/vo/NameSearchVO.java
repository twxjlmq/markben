package com.markben.restful.common.vo;

import com.markben.beans.bean.NameSearch;
import io.swagger.annotations.ApiModelProperty;

/**
 * 按名称搜索的类
 * @author 乌草坡
 * @since 0.0.1
 */
public class NameSearchVO implements BaseVO, NameSearch {

    @ApiModelProperty("按名称搜索")
    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
