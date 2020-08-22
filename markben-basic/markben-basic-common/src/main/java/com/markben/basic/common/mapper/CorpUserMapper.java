package com.markben.basic.common.mapper;

import com.markben.basic.common.entity.TSysCorpUser;
import com.markben.core.mapper.BaseEnhanceMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 企业用户Mapper接口
 * @author 乌草坡
 * @since 1.0
 */
@Mapper
public interface CorpUserMapper extends BaseEnhanceMapper<TSysCorpUser> {
}
