package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.multi.tenancy.entity.AbstractSupportTenantEntity;

/**
 * 角色表与菜单表的关联表
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_role_menu")
public class TSysRoleMenu extends AbstractSupportTenantEntity {

    private String roleId;

    private String menuId;

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
