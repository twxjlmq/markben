package com.markben.beans.dao;

import com.markben.beans.entity.TSysVersion;
import com.markben.core.mapper.BaseEnhanceMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统版本DAO接口
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface VersionDao extends BaseEnhanceMapper<TSysVersion> {

}
