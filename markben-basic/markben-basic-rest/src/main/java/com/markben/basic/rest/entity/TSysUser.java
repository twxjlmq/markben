package com.markben.basic.rest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractCommonPropEntity;

/**
 * 系统用户实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_user")
public class TSysUser extends AbstractCommonPropEntity {

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 备注
     */
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
