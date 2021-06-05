package com.markben.basic.common.service.impl;


import com.markben.basic.common.entity.TSysRole;
import com.markben.basic.common.entity.TSysRoleUser;
import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.RoleService;
import com.markben.basic.common.service.RoleUserService;
import com.markben.basic.common.service.UserService;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.service.MgrServiceImpl;
import com.markben.core.utils.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 角色用户服务实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class RoleUserServiceImpl extends MgrServiceImpl<TSysRoleUser> implements RoleUserService {

    private RoleService roleService;

    private UserService userService;

    public RoleUserServiceImpl(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public List<TSysRole> getRoleListByUserId(String userId) {
        LoggerUtils.debug(getLogger(), "正在获取用户ID[{}]所在的角色列表...", userId);
        List<TSysRoleUser> relateList = super.finds(EntityUtils.toDbField("userId"), userId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] roleIds = relateList.stream().map(TSysRoleUser::getRoleId).toArray(String[]::new);
            return roleService.findListByValid(roleIds);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TSysUser> getUserListByRoleId(String roleId) {
        LoggerUtils.debug(getLogger(), "正在获取角色ID[{}]下的用户列表...", roleId);
        List<TSysRoleUser> relateList = super.finds(EntityUtils.toDbField("roleId"), roleId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] corpUserIds = relateList.stream().map(TSysRoleUser::getUserId).toArray(String[]::new);
            return userService.findListByValid(corpUserIds);
        }
        return Collections.emptyList();
    }
}
