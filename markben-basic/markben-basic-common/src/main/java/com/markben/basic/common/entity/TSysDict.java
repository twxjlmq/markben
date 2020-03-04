package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.common.enums.YesOrNoType;
import com.markben.core.bean.BaseTreeEntity;
import com.markben.core.bean.ICreateTime;

import java.util.Date;

/**
 * 数据字典实体类
 * @autor 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_dict")
public class TSysDict extends BaseTreeEntity implements ICreateTime {

    private String value;

    private Integer intValue;

    private Integer state = YesOrNoType.YES.getIndex();

    private String corpUserId;

    private Date createTime;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
