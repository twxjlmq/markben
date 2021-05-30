package com.markben.org.rest.bean;

import com.markben.beans.bean.UserInfo;

import java.util.Collection;

/**
 * 在组织中的用户信息类
 * @author 乌草坡
 * @since 0.0.1
 */
public interface OrgUserInfo extends UserInfo {

    /**
     * 获取部门ID
     * @return 返回部门ID
     */
    Collection<String> getOrgIds();

    void setOrgIds(Collection<String> orgIds);

    /**
     * 获取部门ID
     * @return 返回部门ID
     */
    String getDeptId();

    /**
     * 设置部门ID
     * @param deptId 部门ID
     */
    void setDeptId(String deptId);

    /**
     * 获取部门名称
     * @return 返回部门名称
     */
    String getDeptName();

    /**
     * 设置部门名称
     * @param deptName 部门名称
     */
    void setDeptName(String deptName);

    /**
     * 获取角色ID集合
     * @return 返回角色ID集合
     */
    Collection<String> getRoleIds();

    /**
     * 设置角色ID集合
     * @param roleIds 角色ID集合
     */
    void setRoleIds(Collection<String> roleIds);

    /**
     * 获取菜单角色ID集合
     * @return 返回菜单角色ID集合
     */
    Collection<String> getMenuRoleIds();

    /**
     * 设置菜单角色ID集合
     * @param menuRoleIds 菜单角色ID集合
     */
    void setMenuRoleIds(Collection<String> menuRoleIds);

    /**
     * 返回授权部门ID集合
     * @return 返回授权部门ID集合
     */
    Collection<String> getAuthOrgIds();

    /**
     * 设置授权部门ID集合
     * @param authOrgIds 授权部门ID集合
     */
    void setAuthOrgIds(Collection<String> authOrgIds);

    /**
     * 获取授权企业用户ID集合
     * @return 返回授权企业用户ID集合
     */
    Collection<String> getAuthCorpUserIds();

    /**
     * 设置授权企业用户ID集合
     * @param authCorpUserIds 授权企业用户ID集合
     */
    void setAuthCorpUserIds(Collection<String> authCorpUserIds);

}
