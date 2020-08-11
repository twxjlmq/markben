package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.ITenantEntity;

/**
 * 角色表与菜单表的关联表
 * @author 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_role_menu")
public class TSysRoleMenu extends AbstractBaseEntity implements ITenantEntity {

    private String corpId;

    private String roleId;

    private String menuId;

    @Override
    public String getCorpId() {
        return corpId;
    }

    @Override
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
