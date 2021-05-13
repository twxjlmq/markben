package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.basic.common.service.ITenantUserService;
import com.markben.common.enums.YesOrNoType;
import com.markben.core.service.EnhanceServiceImpl;
import com.markben.core.utils.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
@Service
public class TenantUserServiceImpl extends EnhanceServiceImpl<TSysTenantUser> implements ITenantUserService {

    @Override
    public List<TSysTenantUser> getTenantUser(String userId) {
        //StringUtils.isAssert(userId, "userId参数不能为空", this);
        return super.finds(query().eq(EntityUtils.toDbField("userId"), userId)
                .and(w -> w.eq("state", YesOrNoType.YES.getIndex())));
    }

    @Override
    public TSysTenantUser getTenantUser(String userId, String tenantId) {
        return super.getOne(query().eq(EntityUtils.toDbField("userId"), userId)
                .and(w -> w.eq(EntityUtils.toDbField("tenantId"), tenantId)
                        .eq("state", YesOrNoType.YES.getIndex())), false);
    }
}
