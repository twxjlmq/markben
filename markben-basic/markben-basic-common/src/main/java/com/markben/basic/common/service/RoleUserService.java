package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysRole;
import com.markben.basic.common.entity.TSysRoleUser;
import com.markben.basic.common.entity.TSysUser;
import com.markben.core.service.MgrService;

import java.util.List;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
public interface RoleUserService extends MgrService<TSysRoleUser> {

    /**
     * 获取角色列表通过用户ID
     * @param userId 用户ID
     * @return 返回角色列表
     */
    List<TSysRole> getRoleListByUserId(String userId);

    /**
     * 获取用户列表通过角色ID
     * @param roleId 角色ID
     * @return 返回用户列表
     */
    List<TSysUser> getUserListByRoleId(String roleId);

}
