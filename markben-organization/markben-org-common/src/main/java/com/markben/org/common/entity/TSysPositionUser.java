package com.markben.org.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;

/**
 * 职位与租户用户关联实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_position_user")
public class TSysPositionUser extends AbstractBaseEntity {

    private String positionId;

    private String userId;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
