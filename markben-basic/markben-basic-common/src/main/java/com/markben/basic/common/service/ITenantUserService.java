package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.core.service.IMgrService;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface ITenantUserService extends IMgrService<TSysTenantUser> {

    /**
     * 获取租户的用户实体对象类表通过用户ID
     * @param userId 用户ID
     * @return 返回租户用户实体对象列表
     */
    List<TSysTenantUser> getTenantUser(String userId);

    /**
     * 获取租户的用户实体对象通过用户ID和企业ID；
     * 如果获取数据返回对于的实体类，否则返回null
     * @param userId 用户ID
     * @param tenantId 租户ID
     * @return 返回租户用户实体类或null
     */
    TSysTenantUser getTenantUser(String userId, String tenantId);

}
