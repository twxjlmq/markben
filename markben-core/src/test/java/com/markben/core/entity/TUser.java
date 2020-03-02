package com.markben.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.IPKStringEntity;

import java.util.Date;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@TableName(value = "t_user")
public class TUser implements IPKStringEntity {

    @TableId
    @TableField(value = "id")
    private String id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "age")
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
