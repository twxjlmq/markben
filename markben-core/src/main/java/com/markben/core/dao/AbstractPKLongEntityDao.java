package com.markben.core.dao;

import com.markben.core.bean.entity.IPKLongEntity;

/**
 * 实体主键为Long型的DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public abstract class AbstractPKLongEntityDao<T extends IPKLongEntity> extends AbstractSupportEntityDao<T, Long> {

}
