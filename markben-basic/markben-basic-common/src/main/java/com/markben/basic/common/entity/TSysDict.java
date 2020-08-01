package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.common.enums.YesOrNoType;
import com.markben.core.bean.AbstractBaseTreeEntity;
import com.markben.core.bean.ICreateTime;
import com.markben.core.bean.ITenantEntity;

import java.util.Date;

/**
 * 数据字典实体类
 * @autor 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_dict")
public class TSysDict extends AbstractBaseTreeEntity implements ITenantEntity {

    /**
     * 企业ID；
     * 该直为{@link TSysCorp}实体类中的ID值，
     * 通过该字段与{@link TSysCorp}数据表关联
     */
    private String corpId;

    /**
     * 数据项的值
     */
    private String value;

    /**
     * 数据项值对应的整型值（如果value值为整数的话，否则该字段为null）
     */
    private Integer intValue;

    /**
     * 企业用户ID；
     * 该直为{@link TSysCorpUser}实体类中的ID值，
     * 通过该字段与{@link TSysCorpUser}数据表关联
     */
    private String corpUserId;

    @Override
    public String getCorpId() {
        return corpId;
    }

    @Override
    public void setCorpId(String corpId) {
        this.corpId = corpId;
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

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }

}
