package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysCorpUser;
import com.markben.basic.common.entity.TSysOrg;
import com.markben.basic.common.entity.TSysOrgCorpUser;
import com.markben.core.service.IMgrService;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface IOrgCorpUserService extends IMgrService<TSysOrgCorpUser> {

    /**
     * 根据企业用户ID获取该企业用户所在的部门列表
     * @param corpUserId 企业用户ID
     * @return 返回部门实体列表
     */
    List<TSysOrg> getDepartmentList(String corpUserId);

    /**
     * 根据部门ID获取该部门下的所有企业用户列表
     * @param deptId 部门ID
     * @return 返回企业用户实体列表
     */
    List<TSysCorpUser> getCorpUserList(String deptId);

    /**
     * 获取部门实体对象根据部门ID和企业用户ID
     * @param deptId 部门ID
     * @param corpUserId 企业用户ID
     * @return 返回部门实体对象,如果有数据；否则返回null
     */
    TSysOrg getDepartment(String deptId, String corpUserId);

}
