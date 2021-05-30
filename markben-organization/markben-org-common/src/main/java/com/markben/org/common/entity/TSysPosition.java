package com.markben.org.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;
import com.markben.core.bean.SupportCreatorEntity;

/**
 * 职位实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_position")
public class TSysPosition extends AbstractBaseEntity implements SupportCreatorEntity {

    private String name;

    private Integer sortOrder;

    private String creator;

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

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
