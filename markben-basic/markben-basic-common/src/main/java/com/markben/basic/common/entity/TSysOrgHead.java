package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.ICreatorEntity;
import com.markben.core.bean.ITenantEntity;

/**
 * 部门主管实体类
 * @author 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_org_head")
public class TSysOrgHead extends AbstractBaseEntity implements ICreatorEntity {

    private String corpId;

    private String orgId;

    private String corpUserId;

    @Override
    public String getCorpId() {
        return corpId;
    }

    @Override
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }
}
