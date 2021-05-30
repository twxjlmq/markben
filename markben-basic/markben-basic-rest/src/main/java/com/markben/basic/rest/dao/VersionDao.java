package com.markben.basic.rest.dao;

import com.markben.basic.rest.entity.TSysVersion;
import com.markben.core.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统版本DAO接口
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface VersionDao extends BaseDao<TSysVersion> {

}
