package com.markben.basic.common.wrapper;

import com.markben.basic.common.entity.*;
import com.markben.basic.common.service.*;
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
 * @since 1.0.0
 */
public class TenantUserWrapper {

    private TSysTenantUser tenantUser;

    private TSysUser user;

    private TSysTenant tenant;

    private List<TSysOrg> orgList;

    private TSysOrg org;

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
    public List<TSysOrg> getOrgList() {
        if(CollectionUtils.isEmpty(this.orgList)) {
            initOrgList();
        }
        return orgList;
    }

    /**
     * 获取企业用户所在的部门列表（用户有可能在多个部门）
     * @return 返回部门实体列表
     */
    public TSysOrg getDepartment(String deptId) {
        if(null != org) {
            return org;
        }
        if(CollectionUtils.isNotEmpty(this.orgList)) {
            Optional<TSysOrg> orgOptional = this.orgList.stream().filter(o -> deptId.equals(o.getId())).findFirst();
            return orgOptional.orElse(null);
        }
        IOrgTenantUserService orgTenantUserService = MarkbenContextFactory.find(IOrgTenantUserService.class);
        if(null != orgTenantUserService) {
            this.org = orgTenantUserService.getDepartment(deptId, getTenantUser().getId());
        }
        return org;
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
        IUserService userService = MarkbenContextFactory.find(IUserService.class);
        if(null != userService) {
            this.user = userService.find(getTenantUser().getUserId());
        }
    }

    private void initTenant() {
        ITenantService tenantService = MarkbenContextFactory.find(ITenantService.class);
        if(null != tenantService) {
            this.tenant = tenantService.find(getTenantUser().getTenantId());
        }
    }

    private void initOrgList() {
        IOrgTenantUserService orgTenantUserService = MarkbenContextFactory.find(IOrgTenantUserService.class);
        if(null != orgTenantUserService) {
            this.orgList = orgTenantUserService.getDepartmentList(getTenantUser().getId());
        }
    }

    private void initRoleList() {
        IRoleTenantUserService roleTenantUserService = MarkbenContextFactory.find(IRoleTenantUserService.class);
        if(null != roleTenantUserService) {
            this.roleList = roleTenantUserService.getRoleListByTenantUserId(getTenantUser().getId());
        }
    }
}
