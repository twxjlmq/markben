package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.io.Serializable;

/**
 * 支持实体读写的DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public interface ISupportReadAndWriteDao<T extends IEntityBean, PK extends Serializable>
        extends IReadonlyDao<T, PK>, IWriteOnlyDao<T, PK> {
}
