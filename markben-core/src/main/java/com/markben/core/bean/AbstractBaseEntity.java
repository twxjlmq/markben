package com.markben.core.bean;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 基础实体
 * @autor 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractBaseEntity implements PKStringEntity {

    @TableId(value = "id")
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
