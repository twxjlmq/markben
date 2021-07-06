package com.markben.basic.rest.vo.menu;

import com.markben.rest.common.vo.UpdateRequest;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 更新菜单请求类
 * @author 乌草坡
 * @since 0.0.1
 */
public class UpdateMenuRequest extends CreateMenuRequest implements UpdateRequest {

    private String id;

    @Override
    @ApiModelProperty(value = "ID", required = true)
    @NotBlank(message = "ID不能为空")
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
