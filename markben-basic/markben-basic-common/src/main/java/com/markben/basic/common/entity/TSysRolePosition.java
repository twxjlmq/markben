package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseTenantEntity;
import com.markben.core.bean.ITenantEntity;

/**
 * 角色与职位关联实体类
 * @author 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_role_position")
public class TSysRolePosition extends AbstractBaseTenantEntity implements ITenantEntity {

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
