package com.markben.org.common.dao;

import com.markben.core.dao.BaseDao;
import com.markben.org.common.entity.TSysDepartment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface DepartmentDao extends BaseDao<TSysDepartment> {

}
