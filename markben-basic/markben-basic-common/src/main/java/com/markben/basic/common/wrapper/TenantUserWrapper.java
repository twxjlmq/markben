package com.markben.basic.common.wrapper;

import com.markben.basic.common.entity.*;
import com.markben.basic.common.service.*;
import com.markben.beans.entity.TSysUser;
import com.markben.beans.service.UserService;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.context.MarkbenContextFactory;

import java.util.List;
import java.util.Optional;

/**
 * 租户的用户实体对象的封装；
 * 用于获取与它相关联的其他实体信息；
 * 如：用户信息、租户信息、部门信息等
 * @author 乌草坡
 * @since 0.0.1
 */
public class TenantUserWrapper {

    private TSysTenantUser tenantUser;

    private TSysUser user;

    private TSysTenant tenant;

    private List<TSysDepartment> deptList;

    private TSysDepartment dept;

    private List<TSysRole> roleList;

    public TenantUserWrapper(TSysTenantUser tenantUser) {
        StringUtils.isAssert(tenantUser, "tenantUser参数不能为空", this);
        this.tenantUser = tenantUser;
    }

    public TSysTenantUser getTenantUser() {
        return tenantUser;
    }

    /**
     * 获取用户实体对象
     * @return 返回用户实体对象
     */
    public TSysUser getUser() {
        if(null == user) {
            initUser();
        }
        return user;
    }

    /**
     * 获取企业用户所在的企业实体对象
     * @return 返回企业实体对象
     */
    public TSysTenant getTenant() {
        if(null == tenant) {
            initTenant();
        }
        return tenant;
    }

    /**
     * 获取企业用户所在的部门列表（用户有可能在多个部门）
     * @return 返回部门实体列表
     */
    public List<TSysDepartment> getDeptList() {
        if(CollectionUtils.isEmpty(this.deptList)) {
            initDeptList();
        }
        return deptList;
    }

    /**
     * 获取企业用户所在的部门列表（用户有可能在多个部门）
     * @return 返回部门实体列表
     */
    public TSysDepartment getDepartment(String deptId) {
        if(null != dept) {
            return dept;
        }
        if(CollectionUtils.isNotEmpty(this.deptList)) {
            Optional<TSysDepartment> orgOptional = this.deptList.stream().filter(o -> deptId.equals(o.getId())).findFirst();
            return orgOptional.orElse(null);
        }
        DeptTenantUserService deptTenantUserService = MarkbenContextFactory.find(DeptTenantUserService.class);
        if(null != deptTenantUserService) {
            deptTenantUserService.getDepartment(deptId, getTenantUser().getId()).ifPresent(dept -> this.dept = dept);
        }
        return dept;
    }

    /**
     * 获取租户的用户所拥有的角色列表
     * @return 返回角色实体列表
     */
    public List<TSysRole> getRoleList() {
        if(CollectionUtils.isEmpty(this.roleList)) {
            initRoleList();
        }
        return roleList;
    }

    private void initUser() {
        UserService userService = MarkbenContextFactory.find(UserService.class);
        if(null != userService) {
            userService.find(getTenantUser().getUserId()).ifPresent(user -> this.user = user);
        }
    }

    private void initTenant() {
        TenantService tenantService = MarkbenContextFactory.find(TenantService.class);
        if(null != tenantService) {
            tenantService.find(getTenantUser().getTenantId()).ifPresent(tenant -> this.tenant = tenant);
        }
    }

    private void initDeptList() {
        DeptTenantUserService deptTenantUserService = MarkbenContextFactory.find(DeptTenantUserService.class);
        if(null != deptTenantUserService) {
            this.deptList = deptTenantUserService.getDepartmentList(getTenantUser().getId());
        }
    }

    private void initRoleList() {
        RoleTenantUserService roleTenantUserService = MarkbenContextFactory.find(RoleTenantUserService.class);
        if(null != roleTenantUserService) {
            this.roleList = roleTenantUserService.getRoleListByTenantUserId(getTenantUser().getId());
        }
    }
}
