package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.io.Serializable;

/**
 * 支持写实体DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IWriteOnlyDao<T extends IEntityBean, PK extends Serializable>
        extends ISaveEntityDao<T, PK>, IDeleteEntityDao<T, PK>, IUpdateEntityDao<T, PK>, IExecuteDao  {

}
