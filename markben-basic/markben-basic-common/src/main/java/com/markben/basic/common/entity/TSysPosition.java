package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractTenantEntity;
import com.markben.core.bean.ICreatorEntity;

/**
 * 职位实体类
 * @author 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_position")
public class TSysPosition extends AbstractTenantEntity implements ICreatorEntity {

    private String name;

    private Integer sortOrder;

    private String corpUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }
}
