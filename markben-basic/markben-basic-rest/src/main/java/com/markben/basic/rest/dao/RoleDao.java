package com.markben.basic.rest.dao;

import com.markben.basic.rest.entity.TSysRole;
import com.markben.core.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface RoleDao extends BaseDao<TSysRole> {
}
