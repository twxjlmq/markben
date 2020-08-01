package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractTenantEntity;
import com.markben.core.bean.IPKPrefix;

import java.util.Date;

/**
 * 企业用户实体类
 * @autor 乌草坡 2020-03-04
 * @since 1.0
 */
@TableName(value = "t_sys_corp_user")
public class TSysCorpUser extends AbstractTenantEntity implements IPKPrefix {

    public static final String PREFIX = "cu";

    /**
     * 用户ID；
     * 该直为{@link TSysUser}实体类中的ID值，
     * 通过该字段与{@link TSysUser}数据表关联
     */
    private String userId;

    private String nickname;

    /**
     * 是否默认默认主组织
     */
    private Integer isDefault;

    /**
     * 是否为组织的超级管理员
     */
    private Integer isSuperAdmin;

    private Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Integer isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }
}
