package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.ITenantEntity;

/**
 * 角色表与企业用户表的关联表
 * @author 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_role_corp_user")
public class TSysRoleCorpUser extends AbstractBaseEntity implements ITenantEntity {

    private String corpId;

    private String roleId;

    private String corpUserId;

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

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }
}
