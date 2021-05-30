package com.markben.org.common.dao;

import com.markben.core.dao.BaseDao;
import com.markben.org.common.entity.TSysDeptUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface DeptUserDao extends BaseDao<TSysDeptUser> {
}
