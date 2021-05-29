package com.markben.beans.dao;

import com.markben.beans.entity.TSysDict;
import com.markben.core.mapper.BaseEnhanceMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典DAO接口
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface DictDao extends BaseEnhanceMapper<TSysDict> {

}
