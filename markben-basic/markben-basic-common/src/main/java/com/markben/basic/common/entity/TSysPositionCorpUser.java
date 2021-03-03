package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.ICreatorEntity;
import com.markben.core.bean.ITenantEntity;

/**
 * 职位与企业用户关联实体类
 * @author 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_position_corp_user")
public class TSysPositionCorpUser extends AbstractBaseEntity implements ICreatorEntity {

    private String corpId;

    private String positionId;

    private String corpUserId;

    @Override
    public String getCorpId() {
        return corpId;
    }

    @Override
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }
}
