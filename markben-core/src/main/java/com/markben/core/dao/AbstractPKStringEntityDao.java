package com.markben.core.dao;

import com.markben.core.bean.entity.IPKStringEntity;

/**
 * 实体主键为字符串的DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public abstract class AbstractPKStringEntityDao<T extends IPKStringEntity> extends AbstractSupportEntityDao<T, String> {

}
