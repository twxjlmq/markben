package com.markben.basic.common.wrapper;

import com.markben.basic.common.entity.*;
import com.markben.basic.common.service.*;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.context.MarkbenContextFactory;

import java.util.List;
import java.util.Optional;

/**
 * 企业用户实体对象的封装；
 * 用于获取与它相关联的其他实体信息；
 * 如：用户信息、企业信息、部门信息等
 * @author 乌草坡
 * @since 1.0
 */
public class CorpUserWrapper {

    private TSysCorpUser corpUser;

    private TSysUser user;

    private TSysCorp corp;

    private List<TSysOrg> orgList;

    private TSysOrg org;

    private List<TSysRole> roleList;

    public CorpUserWrapper(TSysCorpUser corpUser) {
        StringUtils.isAssert(corpUser, "corpUser参数不能为空", this);
        this.corpUser = corpUser;
    }

    public TSysCorpUser getCorpUser() {
        return corpUser;
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
    public TSysCorp getCorp() {
        if(null == corp) {
            initCorp();
        }
        return corp;
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
            if(orgOptional.isPresent()) {
                return orgOptional.get();
            } else {
                return null;
            }
        }
        IOrgCorpUserService orgCorpUserService = MarkbenContextFactory.find(IOrgCorpUserService.class);
        if(null != orgCorpUserService) {
            this.org = orgCorpUserService.getDepartment(deptId, getCorpUser().getId());
        }
        return org;
    }

    /**
     * 获取企业用户所拥有的角色列表
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
            this.user = userService.find(getCorpUser().getUserId());
        }
    }

    private void initCorp() {
        ICorpService corpService = MarkbenContextFactory.find(ICorpService.class);
        if(null != corpService) {
            this.corp = corpService.find(getCorpUser().getCorpId());
        }
    }

    private void initOrgList() {
        IOrgCorpUserService orgCorpUserService = MarkbenContextFactory.find(IOrgCorpUserService.class);
        if(null != orgCorpUserService) {
            this.orgList = orgCorpUserService.getDepartmentList(getCorpUser().getId());
        }
    }

    private void initRoleList() {
        IRoleCorpUserService roleCorpUserService = MarkbenContextFactory.find(IRoleCorpUserService.class);
        if(null != roleCorpUserService) {
            this.roleList = roleCorpUserService.getRoleListByCorpUserId(getCorpUser().getId());
        }
    }
}
