package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysCorpUser;
import com.markben.basic.common.entity.TSysRole;
import com.markben.basic.common.entity.TSysRoleCorpUser;
import com.markben.core.service.IMgrService;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface IRoleCorpUserService extends IMgrService<TSysRoleCorpUser> {

    /**
     * 获取角色列表通过企业用户ID
     * @param corpUserId 企业用户ID
     * @return 返回角色列表
     */
    List<TSysRole> getRoleListByCorpUserId(String corpUserId);

    /**
     * 获取企业用户列表通过角色ID
     * @param roleId 角色ID
     * @return 返回企业用户列表
     */
    List<TSysCorpUser> getCorpUserListByRoleId(String roleId);

}
