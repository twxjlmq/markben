package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseTreeEntity;
import com.markben.core.bean.ICreatorEntity;

/**
 * 数据字典实体类
 * @author 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_dict")
public class TSysDict extends AbstractBaseTreeEntity implements ICreatorEntity {

    /**
     * 企业ID；
     * 该直为{@link TSysTenant}实体类中的ID值，
     * 通过该字段与{@link TSysTenant}数据表关联
     */
    private String tenantId;

    /**
     * 数据项的值
     */
    private String value;

    /**
     * 数据项值对应的整型值（如果value值为整数的话，否则该字段为null）
     */
    private Integer intValue;

    /**
     * 租户用户ID；
     * 该直为{@link TSysTenantUser}实体类中的ID值，
     * 通过该字段与{@link TSysTenantUser}数据表关联
     */
    private String tenantUserId;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

}
