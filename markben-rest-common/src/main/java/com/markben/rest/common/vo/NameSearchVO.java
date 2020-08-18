package com.markben.rest.common.vo;

import com.markben.beans.bean.ISearchFilter;
import io.swagger.annotations.ApiModelProperty;

/**
 * 按名称搜索对象
 * @author 乌草坡
 * @since 1.0
 */
public class NameSearchVO implements IBaseVO, ISearchFilter {

    @ApiModelProperty("按名称搜索")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
