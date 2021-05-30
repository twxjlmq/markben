package com.markben.org.common.service;

import com.markben.basic.common.entity.TSysUser;
import com.markben.org.common.entity.TSysDepartment;
import com.markben.core.service.MgrService;
import com.markben.org.common.entity.TSysDeptUser;

import java.util.List;
import java.util.Optional;

/**
 * 部门用户服务类接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface DeptUserService extends MgrService<TSysDeptUser> {

    /**
     * 根据用户ID获取该用户所在的部门列表
     * @param userId 用户ID
     * @return 返回部门实体列表
     */
    List<TSysDepartment> getDepartmentList(String userId);

    /**
     * 根据部门ID获取该部门下的所有用户列表
     * @param deptId 部门ID
     * @return 返回租户的用户实体列表
     */
    List<TSysUser> getCorpUserList(String deptId);

    /**
     * 获取部门实体对象根据部门ID和用户ID
     * @param deptId 部门ID
     * @param userId 用户ID
     * @return 返回部门实体对象,如果有数据；否则返回null
     */
    Optional<TSysDepartment> getDepartment(String deptId, String userId);

}
