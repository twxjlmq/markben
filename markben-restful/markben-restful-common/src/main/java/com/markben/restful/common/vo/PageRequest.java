package com.markben.restful.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.common.constant.MarkbenConstant;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页请求对象
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageRequest implements BaseVO {

    private static final int MAX_SIZE = 1000;

    private static final int DEFAULT_SIZE = MarkbenConstant.PRE_PAGE_SIZE;

    @ApiModelProperty(value = "当前页数")
    private Integer page;

    @ApiModelProperty(value = "每页显示数量")
    private Integer size;


    public Integer getPage() {
        if(null == page) {
            return 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        if(null != page && page >= Integer.MAX_VALUE) {
            this.page = 0;
        } else {
            this.page = page;
        }
    }

    public Integer getSize() {
        if(null == size) {
            return DEFAULT_SIZE;
        }
        return size;
    }

    public void setSize(Integer size) {
        if(null != size && size > MAX_SIZE) {
            this.size = DEFAULT_SIZE;
        } else {
            this.size = size;
        }
    }

    /**
     * 计算偏移量
     * @return 返回偏移量
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public int getOffset() {
        return (this.getPage()-1) * this.getSize();
    }
}
