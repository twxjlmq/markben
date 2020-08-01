package com.markben.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markben.core.bean.IEntityBean;

/**
 * 增强BaseMapper接口的功能
 * @autor 乌草坡 2020-03-02
 * @since 1.0
 */
public interface BaseEnhanceMapper<T extends IEntityBean> extends BaseMapper<T> {

}
