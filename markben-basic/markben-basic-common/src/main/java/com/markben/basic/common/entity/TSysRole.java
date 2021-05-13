package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractTenantEntity;
import com.markben.core.bean.ICreatorEntity;

/**
 * 角色实体类
 * @author 乌草坡
 * @since 1.0.0
 */
@TableName(value = "t_sys_role")
public class TSysRole extends AbstractTenantEntity implements ICreatorEntity {

    private String name;

    private String describe;

    private String tenantUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String getTenantUserId() {
        return tenantUserId;
    }

    @Override
    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }
}
