package com.markben.basic.rest.dao;

import com.markben.basic.rest.entity.TSysResource;
import com.markben.core.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统资源Mapper类
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface ResourceDao extends BaseDao<TSysResource> {

}
