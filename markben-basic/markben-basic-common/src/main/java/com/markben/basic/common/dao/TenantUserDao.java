package com.markben.basic.common.dao;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.core.mapper.BaseEnhanceMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户的用户Mapper接口
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface TenantUserDao extends BaseEnhanceMapper<TSysTenantUser> {
}
