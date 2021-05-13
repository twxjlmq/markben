package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.basic.common.entity.TSysOrg;
import com.markben.basic.common.entity.TSysOrgTenantUser;
import com.markben.core.service.IMgrService;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface IOrgTenantUserService extends IMgrService<TSysOrgTenantUser> {

    /**
     * 根据企业用户ID获取该租户的用户所在的部门列表
     * @param tenantUserId 租户的用户ID
     * @return 返回部门实体列表
     */
    List<TSysOrg> getDepartmentList(String tenantUserId);

    /**
     * 根据部门ID获取该部门下的所有企业用户列表
     * @param deptId 部门ID
     * @return 返回租户的用户实体列表
     */
    List<TSysTenantUser> getCorpUserList(String deptId);

    /**
     * 获取部门实体对象根据部门ID和企业用户ID
     * @param deptId 部门ID
     * @param tenantUserId 租户的用户ID
     * @return 返回部门实体对象,如果有数据；否则返回null
     */
    TSysOrg getDepartment(String deptId, String tenantUserId);

}
