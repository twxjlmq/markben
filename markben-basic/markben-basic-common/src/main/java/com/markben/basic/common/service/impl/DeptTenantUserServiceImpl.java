package com.markben.basic.common.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.markben.basic.common.entity.TSysDepartment;
import com.markben.basic.common.entity.TSysDeptTenantUser;
import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.basic.common.service.DepartmentService;
import com.markben.basic.common.service.DeptTenantUserService;
import com.markben.basic.common.service.TenantUserService;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.service.EnhanceServiceImpl;
import com.markben.core.utils.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class DeptTenantUserServiceImpl extends EnhanceServiceImpl<TSysDeptTenantUser> implements DeptTenantUserService {

    private DepartmentService deptService;

    private TenantUserService tenantUserService;

    public DeptTenantUserServiceImpl(DepartmentService deptService, TenantUserService tenantUserService) {
        this.deptService = deptService;
        this.tenantUserService = tenantUserService;
    }

    @Override
    public List<TSysDepartment> getDepartmentList(String tenantUserId) {
        LoggerUtils.debug(getLogger(), "正在获取租户的用户ID[{}]所在的部门列表...", tenantUserId);
        List<TSysDeptTenantUser> relateList = super.finds(EntityUtils.toDbField("tenantUserId"), tenantUserId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] orgIds = relateList.stream().map(TSysDeptTenantUser::getDeptId).toArray(String[]::new);
            return deptService.findListByValid(orgIds);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TSysTenantUser> getCorpUserList(String deptId) {
        LoggerUtils.debug(getLogger(), "正在获取部门ID[{}]下的所有企业用户列表...", deptId);
        List<TSysDeptTenantUser> relateList = super.finds(EntityUtils.toDbField("orgId"), deptId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] orgIds = relateList.stream().map(TSysDeptTenantUser::getTenantUserId).toArray(String[]::new);
            return tenantUserService.findListByValid(orgIds);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<TSysDepartment> getDepartment(String deptId, String tenantUserId) {
        LoggerUtils.debug(getLogger(), "正在获取部门ID为[{}]，租户的用户ID为[{}]的部门信息...", deptId, tenantUserId);
        QueryChainWrapper queryWrapper = super.query().eq(EntityUtils.toDbField("orgId"), deptId)
                .and(w -> w.eq(EntityUtils.toDbField("tenantUserId"), tenantUserId));
        TSysDeptTenantUser deptTenantUser = super.getOne(queryWrapper, false);
        if(null != deptTenantUser) {
            return deptService.find(deptTenantUser.getDeptId());
        }
        return null;
    }
}
