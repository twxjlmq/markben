package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.core.service.MgrService;

import java.util.List;
import java.util.Optional;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
public interface TenantUserService extends MgrService<TSysTenantUser> {

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
     * @return 返回Optional类型租户用户实体对象
     */
    Optional<TSysTenantUser> getTenantUser(String userId, String tenantId);

}
