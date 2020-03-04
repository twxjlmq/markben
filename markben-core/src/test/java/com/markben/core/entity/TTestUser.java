package com.markben.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.BaseEntity;
import com.markben.core.bean.IPKStringEntity;

import java.util.Date;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@TableName(value = "t_test_user")
public class TTestUser extends BaseEntity {

    private String fullName;

    private Date createTime;

    private Integer age;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
