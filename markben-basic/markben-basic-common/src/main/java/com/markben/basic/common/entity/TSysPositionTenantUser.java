package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.multi.tenancy.entity.AbstractSupportTenantEntity;

/**
 * 职位与租户用户关联实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_position_tenant_user")
public class TSysPositionTenantUser extends AbstractSupportTenantEntity {

    private String positionId;

    private String tenantUserId;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }
}
