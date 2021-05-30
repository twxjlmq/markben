package com.markben.org.rest.wrapper;

import com.markben.basic.common.entity.TSysRole;
import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.RoleUserService;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.org.common.entity.TSysDepartment;
import com.markben.org.common.service.DepartmentService;
import com.markben.org.common.service.DeptUserService;

import java.util.List;
import java.util.Optional;

/**
 * 租户的用户实体对象的封装；
 * 用于获取与它相关联的其他实体信息；
 * 如：用户信息、租户信息、部门信息等
 * @author 乌草坡
 * @since 0.0.1
 */
public class UserWrapper {

    private TSysUser user;

    private List<TSysDepartment> deptList;

    private TSysDepartment dept;

    private List<TSysRole> roleList;

    public UserWrapper(TSysUser user) {
        StringUtils.isAssert(user, "user参数不能为空", this);
        this.user = user;
    }

    /**
     * 获取用户实体对象
     * @return 返回用户实体对象
     */
    public TSysUser getUser() {
        return user;
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
        DepartmentService deptService = MarkbenContextFactory.find(DepartmentService.class);
        if(null != deptService) {
            deptService.find(deptId).ifPresent(dept -> this.dept = dept);
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

    private void initDeptList() {
        DeptUserService deptUserService = MarkbenContextFactory.find(DeptUserService.class);
        if(null != deptUserService) {
            this.deptList = deptUserService.getDepartmentList(getUser().getId());
        }
    }

    private void initRoleList() {
        RoleUserService roleUserService = MarkbenContextFactory.find(RoleUserService.class);
        if(null != roleUserService) {
            this.roleList = roleUserService.getRoleListByUserId(getUser().getId());
        }
    }
}
