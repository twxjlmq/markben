package com.markben.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractSupportTreeEntity;
import com.markben.core.bean.SupportCreatorEntity;

/**
 * 数据字典实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_dict")
public class TSysDict extends AbstractSupportTreeEntity implements SupportCreatorEntity {

    /**
     * 数据项的值
     */
    private String value;

    /**
     * 数据项值对应的整型值（如果value值为整数的话，否则该字段为null）
     */
    private Integer intValue;

    /**
     * 创建人
     */
    private String creator;

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
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
