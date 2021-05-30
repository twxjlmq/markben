package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;

/**
 * 角色表与用户的关联表
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_role_user")
public class TSysRoleUser extends AbstractBaseEntity {

    private String roleId;

    private String userId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
