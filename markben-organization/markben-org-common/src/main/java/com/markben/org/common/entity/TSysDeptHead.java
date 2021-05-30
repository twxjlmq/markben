package com.markben.org.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.core.bean.AbstractBaseEntity;

/**
 * 部门主管实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_dept_head")
public class TSysDeptHead extends AbstractBaseEntity {

    private String deptId;

    private String userId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
