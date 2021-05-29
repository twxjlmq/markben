package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractCommonPropEntity;

/**
 * 功能类型实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_fun_type")
public class TSysFunType extends AbstractCommonPropEntity {

    private String name;

    private String funType;

    private Integer sortOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
