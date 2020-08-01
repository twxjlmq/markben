package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractTenantEntity;

/**
 * 功能菜单实体类
 * @author 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_fun_type")
public class TSysFunType extends AbstractTenantEntity {

    private String funType;

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }
}
