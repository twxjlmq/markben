package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.basic.common.entity.TSysRole;
import com.markben.basic.common.entity.TSysRoleTenantUser;
import com.markben.basic.common.service.ITenantUserService;
import com.markben.basic.common.service.IRoleTenantUserService;
import com.markben.basic.common.service.IRoleService;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.service.EnhanceServiceImpl;
import com.markben.core.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0.0
 */
@Service
public class RoleTenantUserServiceImpl extends EnhanceServiceImpl<TSysRoleTenantUser> implements IRoleTenantUserService {

    private IRoleService roleService;

    private ITenantUserService tenantUserService;

    public RoleTenantUserServiceImpl(IRoleService roleService, ITenantUserService tenantUserService) {
        this.roleService = roleService;
        this.tenantUserService = tenantUserService;
    }

    @Override
    public List<TSysRole> getRoleListByTenantUserId(String tenantUserId) {
        LoggerUtils.debug(getLogger(), "正在获取租户的用户ID[{}]所在的角色列表...", tenantUserId);
        List<TSysRoleTenantUser> relateList = super.finds(EntityUtils.toDbField("tenantUserId"), tenantUserId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] roleIds = relateList.stream().map(TSysRoleTenantUser::getRoleId).toArray(String[]::new);
            return roleService.findListByValid(roleIds);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<TSysTenantUser> getTenantUserListByRoleId(String roleId) {
        LoggerUtils.debug(getLogger(), "正在获取角色ID[{}]下的租户的用户列表...", roleId);
        List<TSysRoleTenantUser> relateList = super.finds(EntityUtils.toDbField("roleId"), roleId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] corpUserIds = relateList.stream().map(TSysRoleTenantUser::getTenantUserId).toArray(String[]::new);
            return tenantUserService.findListByValid(corpUserIds);
        }
        return Collections.EMPTY_LIST;
    }
}
