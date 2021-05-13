package com.markben.basic.common.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.basic.common.entity.TSysOrg;
import com.markben.basic.common.entity.TSysOrgTenantUser;
import com.markben.basic.common.service.ITenantUserService;
import com.markben.basic.common.service.IOrgTenantUserService;
import com.markben.basic.common.service.IOrgService;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.service.EnhanceServiceImpl;
import com.markben.core.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0.0
 */
@Service
public class OrgTenantUserServiceImpl extends EnhanceServiceImpl<TSysOrgTenantUser> implements IOrgTenantUserService {

    private IOrgService orgService;

    private ITenantUserService tenantUserService;

    public OrgTenantUserServiceImpl(IOrgService orgService, ITenantUserService tenantUserService) {
        this.orgService = orgService;
        this.tenantUserService = tenantUserService;
    }

    @Override
    public List<TSysOrg> getDepartmentList(String tenantUserId) {
        LoggerUtils.debug(getLogger(), "正在获取租户的用户ID[{}]所在的部门列表...", tenantUserId);
        List<TSysOrgTenantUser> relateList = super.finds(EntityUtils.toDbField("tenantUserId"), tenantUserId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] orgIds = relateList.stream().map(TSysOrgTenantUser::getOrgId).toArray(String[]::new);
            return orgService.findListByValid(orgIds);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<TSysTenantUser> getCorpUserList(String deptId) {
        LoggerUtils.debug(getLogger(), "正在获取部门ID[{}]下的所有企业用户列表...", deptId);
        List<TSysOrgTenantUser> relateList = super.finds(EntityUtils.toDbField("orgId"), deptId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] orgIds = relateList.stream().map(TSysOrgTenantUser::getTenantUserId).toArray(String[]::new);
            return tenantUserService.findListByValid(orgIds);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public TSysOrg getDepartment(String deptId, String tenantUserId) {
        LoggerUtils.debug(getLogger(), "正在获取部门ID为[{}]，租户的用户ID为[{}]的部门信息...", deptId, tenantUserId);
        QueryChainWrapper queryWrapper = super.query().eq(EntityUtils.toDbField("orgId"), deptId)
                .and(w -> w.eq(EntityUtils.toDbField("tenantUserId"), tenantUserId));
        TSysOrgTenantUser orgTenantUser = super.getOne(queryWrapper, false);
        if(null != orgTenantUser) {
            return orgService.find(orgTenantUser.getOrgId());
        }
        return null;
    }
}
