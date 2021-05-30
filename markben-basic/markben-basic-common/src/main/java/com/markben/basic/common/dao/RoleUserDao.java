package com.markben.basic.common.dao;

import com.markben.basic.common.entity.TSysRoleUser;
import com.markben.core.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface RoleUserDao extends BaseDao<TSysRoleUser> {

}
