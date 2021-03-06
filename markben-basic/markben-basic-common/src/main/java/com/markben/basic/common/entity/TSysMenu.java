package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractSupportTreeEntity;

/**
 * 系统菜单实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_menu")
public class TSysMenu extends AbstractSupportTreeEntity {

    /**
     * 资源ID；
     * 该直为{@link TSysResource}实体类中的ID值，
     * 通过该字段与{@link TSysResource}数据表关联
     */
    private String resourceId;

    private String menuType;

    private String pcIcon;

    private String mobileIcon;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getPcIcon() {
        return pcIcon;
    }

    public void setPcIcon(String pcIcon) {
        this.pcIcon = pcIcon;
    }

    public String getMobileIcon() {
        return mobileIcon;
    }

    public void setMobileIcon(String mobileIcon) {
        this.mobileIcon = mobileIcon;
    }

}
