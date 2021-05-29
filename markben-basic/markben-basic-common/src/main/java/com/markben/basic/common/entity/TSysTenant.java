package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractSupportTreeEntity;
import com.markben.core.bean.PKPrefix;

/**
 * 系统租户实体类
 * @author  乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_tenant")
public class TSysTenant extends AbstractSupportTreeEntity implements PKPrefix {

    public static final String PREFIX = "markben";

    private String logo;

    private String shortName;

    /**
     * 描述
     */
    private String describe;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 添加该组织的租户的用户ID；
     * 该直为{@link TSysTenantUser}实体类中的ID值，
     * 通过该字段与{@link TSysTenantUser}数据表关联
     */
    private String tenantUserId;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }
}
