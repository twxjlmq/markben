package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.basic.common.entity.TSysRole;
import com.markben.basic.common.entity.TSysRoleTenantUser;
import com.markben.core.service.IMgrService;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface IRoleTenantUserService extends IMgrService<TSysRoleTenantUser> {

    /**
     * 获取角色列表通过租户的用户ID
     * @param tenantUserId 租户的用户ID
     * @return 返回角色列表
     */
    List<TSysRole> getRoleListByTenantUserId(String tenantUserId);

    /**
     * 获取租户的用户列表通过角色ID
     * @param roleId 角色ID
     * @return 返回企业用户列表
     */
    List<TSysTenantUser> getTenantUserListByRoleId(String roleId);

}
