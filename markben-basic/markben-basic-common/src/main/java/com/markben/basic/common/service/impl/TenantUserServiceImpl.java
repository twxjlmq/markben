package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.basic.common.service.TenantUserService;
import com.markben.common.enums.YesOrNoType;
import com.markben.core.service.EnhanceServiceImpl;
import com.markben.core.utils.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class TenantUserServiceImpl extends EnhanceServiceImpl<TSysTenantUser> implements TenantUserService {

    @Override
    public List<TSysTenantUser> getTenantUser(String userId) {
        //StringUtils.isAssert(userId, "userId参数不能为空", this);
        return super.finds(query().eq(EntityUtils.toDbField("userId"), userId)
                .and(w -> w.eq("state", YesOrNoType.YES.getIndex())));
    }

    @Override
    public Optional<TSysTenantUser> getTenantUser(String userId, String tenantId) {

        TSysTenantUser tenantUser = super.getOne(query().eq(EntityUtils.toDbField("userId"), userId)
                .and(w -> w.eq(EntityUtils.toDbField("tenantId"), tenantId)
                        .eq("state", YesOrNoType.YES.getIndex())), false);
        return Optional.ofNullable(tenantUser);
    }
}
