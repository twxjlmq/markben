package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.ICreatorEntity;
import com.markben.core.bean.ITenantEntity;

/**
 * 职位与租户用户关联实体类
 * @author 乌草坡
 * @since 1.0.0
 */
@TableName(value = "t_sys_position_tenant_user")
public class TSysPositionTenantUser extends AbstractBaseEntity implements ICreatorEntity {

    private String tenantId;

    private String positionId;

    private String tenantUserId;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
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
