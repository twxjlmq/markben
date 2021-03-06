package com.markben.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markben.core.bean.EntityBean;

/**
 * 增强BaseMapper接口的功能
 * @author 乌草坡
 * @since 0.0.1
 */
public interface BaseDao<T extends EntityBean> extends BaseMapper<T> {

}
