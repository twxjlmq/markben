package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractCommonPropEntity;

/**
 * 系统用户实体类
 * @autor 乌草坡 2020-03-04
 * @since 1.0
 */
@TableName(value = "t_sys_user")
public class TSysUser extends AbstractCommonPropEntity {

    private String password;

    private String username;

    private String mobile;

    private String avatar;

    private String remarks;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}