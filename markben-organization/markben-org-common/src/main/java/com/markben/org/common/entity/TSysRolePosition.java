package com.markben.org.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;

/**
 * 角色与职位关联实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_role_position")
public class TSysRolePosition extends AbstractBaseEntity {

    private String roleId;

    private String positionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }
}
