package com.markben.basic.common.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.markben.basic.common.entity.TSysCorpUser;
import com.markben.basic.common.entity.TSysOrg;
import com.markben.basic.common.entity.TSysOrgCorpUser;
import com.markben.basic.common.service.ICorpUserService;
import com.markben.basic.common.service.IOrgCorpUserService;
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
 * @since 1.0
 */
@Service
public class OrgCorpUserServiceImpl extends EnhanceServiceImpl<TSysOrgCorpUser> implements IOrgCorpUserService {

    @Autowired
    private IOrgService orgService;
    @Autowired
    private ICorpUserService corpUserService;

    @Override
    public List<TSysOrg> getDepartmentList(String corpUserId) {
        LoggerUtils.debug(getLogger(), "正在获取企业用户ID[{}]所在的部门列表...", corpUserId);
        List<TSysOrgCorpUser> relateList = super.finds(EntityUtils.toDbField("corpUserId"), corpUserId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] orgIds = relateList.stream().map(TSysOrgCorpUser::getOrgId).toArray(String[]::new);
            return orgService.findListByValid(orgIds);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<TSysCorpUser> getCorpUserList(String deptId) {
        LoggerUtils.debug(getLogger(), "正在获取部门ID[{}]下的所有企业用户列表...", deptId);
        List<TSysOrgCorpUser> relateList = super.finds(EntityUtils.toDbField("orgId"), deptId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] orgIds = relateList.stream().map(TSysOrgCorpUser::getCorpUserId).toArray(String[]::new);
            return corpUserService.findListByValid(orgIds);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public TSysOrg getDepartment(String deptId, String corpUserId) {
        LoggerUtils.debug(getLogger(), "正在获取部门ID为[{}]，企业用户ID为[{}]的部门信息...", deptId, corpUserId);
        QueryChainWrapper queryWrapper = super.query().eq(EntityUtils.toDbField("orgId"), deptId)
                .and(w -> w.eq("corpUserId", corpUserId));
        TSysOrgCorpUser orgCorpUser = super.getOne(queryWrapper, false);
        if(null != orgCorpUser) {
            return orgService.find(orgCorpUser.getOrgId());
        }
        return null;
    }
}
