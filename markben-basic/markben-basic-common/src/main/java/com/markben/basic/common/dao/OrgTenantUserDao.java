package com.markben.basic.common.dao;

import com.markben.basic.common.entity.TSysDeptTenantUser;
import com.markben.core.mapper.BaseEnhanceMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface OrgTenantUserDao extends BaseEnhanceMapper<TSysDeptTenantUser> {
}
