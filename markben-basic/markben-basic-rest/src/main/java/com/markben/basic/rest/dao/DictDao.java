package com.markben.basic.rest.dao;

import com.markben.basic.rest.entity.TSysDict;
import com.markben.core.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典DAO接口
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface DictDao extends BaseDao<TSysDict> {

}
