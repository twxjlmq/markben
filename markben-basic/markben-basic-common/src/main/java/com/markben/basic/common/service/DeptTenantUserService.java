package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysDepartment;
import com.markben.basic.common.entity.TSysDeptTenantUser;
import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.core.service.MgrService;

import java.util.List;
import java.util.Optional;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
public interface DeptTenantUserService extends MgrService<TSysDeptTenantUser> {

    /**
     * 根据企业用户ID获取该租户的用户所在的部门列表
     * @param tenantUserId 租户的用户ID
     * @return 返回部门实体列表
     */
    List<TSysDepartment> getDepartmentList(String tenantUserId);

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
    Optional<TSysDepartment> getDepartment(String deptId, String tenantUserId);

}
