package com.markben.core.bean;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 基础实体
 * @autor 乌草坡
 * @since 1.0
 */
public abstract class AbstractBaseEntity implements IPKStringEntity {

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
