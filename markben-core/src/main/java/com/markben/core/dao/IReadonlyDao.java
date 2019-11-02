package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.io.Serializable;

/**
 * 只读实体DAO接口；主要实现查询接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IReadonlyDao<T extends IEntityBean, PK extends Serializable> extends IQueryEntityDao<T, PK> {
}
