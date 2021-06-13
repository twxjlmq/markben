package com.markben.core.test.dao;

import com.markben.core.dao.BaseDao;
import com.markben.core.test.entity.TTestUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@Mapper
public interface UserMapper extends BaseDao<TTestUser> {

}
