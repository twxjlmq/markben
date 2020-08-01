package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseTreeEntity;
import com.markben.core.bean.IPKPrefix;

/**
 * 企业实体类
 * @autor 乌草坡 2020-03-04
 * @since 1.0
 */
@TableName(value = "t_sys_corp")
public class TSysCorp extends AbstractBaseTreeEntity implements IPKPrefix {

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
     * 添加该组织的企业用户ID；
     * 该直为{@link TSysCorpUser}实体类中的ID值，
     * 通过该字段与{@link TSysCorpUser}数据表关联
     */
    private String corpUserId;

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

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }
}
