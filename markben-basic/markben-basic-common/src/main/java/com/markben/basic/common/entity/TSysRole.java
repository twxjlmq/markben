package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.multi.tenancy.entity.AbstractSupportTenantEntity;
import com.markben.core.bean.SupportCreatorEntity;

/**
 * 角色实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_role")
public class TSysRole extends AbstractSupportTenantEntity implements SupportCreatorEntity {

    private String name;

    private String describe;

    private String creator;

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
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
