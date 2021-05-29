package com.markben.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markben.core.bean.EntityBean;

/**
 * 增强BaseMapper接口的功能
 * @autor 乌草坡
 * @since 0.0.1
 */
public interface BaseEnhanceMapper<T extends EntityBean> extends BaseMapper<T> {

}
