package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.io.Serializable;

/**
 * 支持实体DAO的抽象类；具体实现方式需要有子类实现
 * @author 乌草坡
 * @since 1.0
 */
public abstract class AbstractSupportEntityDao<T extends IEntityBean, PK extends Serializable>
        implements ISupportReadAndWriteDao<T, PK> {

}
