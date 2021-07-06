package com.markben.basic.rest.vo.menu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.basic.common.bean.MenuItem;
import io.swagger.annotations.ApiModelProperty;

/**
 * 菜单类
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MenuVO extends UpdateMenuRequest implements MenuItem {

    private String resourceName;

    private String stateText;

    private String menuTypeText;

    private String createTime;

    @Override
    @ApiModelProperty(value = "菜单ID")
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @ApiModelProperty(value = "资源名称")
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    @ApiModelProperty(value = "菜单类型")
    public String getMenuType() {
        return super.getMenuType();
    }

    @Override
    @ApiModelProperty(value = "PC端图标")
    public String getPcIcon() {
        return super.getPcIcon();
    }

    @Override
    @ApiModelProperty(value = "移动端图标")
    public String getMobileIcon() {
        return super.getMobileIcon();
    }

    @Override
    @ApiModelProperty(value = "菜单名称")
    public String getName() {
        return super.getName();
    }

    @Override
    @ApiModelProperty(value = "父菜单ID")
    public String getParentId() {
        return super.getParentId();
    }

    @ApiModelProperty(value = "状态；1--有效；0--无效")
    public Integer getState() {
        return super.getState();
    }

    @Override
    @ApiModelProperty(value = "状态文本")
    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    @Override
    @ApiModelProperty(value = "菜单类型文本")
    public String getMenuTypeText() {
        return menuTypeText;
    }

    public void setMenuTypeText(String menuTypeText) {
        this.menuTypeText = menuTypeText;
    }

    @Override
    @ApiModelProperty(value = "创建时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
