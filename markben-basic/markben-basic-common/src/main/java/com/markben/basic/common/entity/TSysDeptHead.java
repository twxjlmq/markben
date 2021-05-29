package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.AbstractSupportTreeEntity;
import com.markben.core.bean.SupportCreatorEntity;
import com.markben.multi.tenancy.entity.AbstractSupportTenantEntity;

/**
 * 部门主管实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_org_head")
public class TSysDeptHead extends AbstractSupportTenantEntity {

    private String deptId;

    private String tenantUserId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }
}
