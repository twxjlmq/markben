package com.markben.basic.rest.vo.menu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.beans.validator.hibernate.IntValueContain;
import com.markben.common.enums.YesNoType;
import com.markben.restful.common.vo.TreeCreateRequest;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 创建菜单请求对象
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateMenuRequest extends TreeCreateRequest {

    private String resourceId;

    private String menuType;

    private String pcIcon;

    private String mobileIcon;

    private Integer state = YesNoType.YES.getIndex();

    @ApiModelProperty(value = "关联的资源ID")
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @ApiModelProperty(value = "菜单类型", required = true)
    @NotBlank(message = "菜单类型不能为空")
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @ApiModelProperty(value = "PC端菜单图标")
    public String getPcIcon() {
        return pcIcon;
    }

    public void setPcIcon(String pcIcon) {
        this.pcIcon = pcIcon;
    }

    @ApiModelProperty(value = "移动端菜单图标")
    public String getMobileIcon() {
        return mobileIcon;
    }

    public void setMobileIcon(String mobileIcon) {
        this.mobileIcon = mobileIcon;
    }

    @ApiModelProperty(value = "状态；1--有效；0--无效", required = true)
    @IntValueContain(message = "状态值范围只能是1或0", value = {1, 0})
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
