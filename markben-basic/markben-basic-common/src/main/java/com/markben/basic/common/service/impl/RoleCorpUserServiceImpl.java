package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysCorpUser;
import com.markben.basic.common.entity.TSysRole;
import com.markben.basic.common.entity.TSysRoleCorpUser;
import com.markben.basic.common.service.ICorpUserService;
import com.markben.basic.common.service.IRoleCorpUserService;
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
 * @since 1.0
 */
@Service
public class RoleCorpUserServiceImpl extends EnhanceServiceImpl<TSysRoleCorpUser> implements IRoleCorpUserService {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICorpUserService corpUserService;

    @Override
    public List<TSysRole> getRoleListByCorpUserId(String corpUserId) {
        LoggerUtils.debug(getLogger(), "正在获取企业用户ID[{}]所在的角色列表...", corpUserId);
        List<TSysRoleCorpUser> relateList = super.finds(EntityUtils.toDbField("corpUserId"), corpUserId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] roleIds = relateList.stream().map(TSysRoleCorpUser::getRoleId).toArray(String[]::new);
            return roleService.findListByValid(roleIds);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<TSysCorpUser> getCorpUserListByRoleId(String roleId) {
        LoggerUtils.debug(getLogger(), "正在获取角色ID[{}]下的企业用户列表...", roleId);
        List<TSysRoleCorpUser> relateList = super.finds(EntityUtils.toDbField("roleId"), roleId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] corpUserIds = relateList.stream().map(TSysRoleCorpUser::getCorpUserId).toArray(String[]::new);
            return corpUserService.findListByValid(corpUserIds);
        }
        return Collections.EMPTY_LIST;
    }
}
