package com.markben.basic.common.mapper;

import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.core.mapper.BaseEnhanceMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户的用户Mapper接口
 * @author 乌草坡
 * @since 1.0.0
 */
@Mapper
public interface TenantUserMapper extends BaseEnhanceMapper<TSysTenantUser> {
}
