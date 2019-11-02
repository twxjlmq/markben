package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.io.Serializable;
import java.util.Collection;

/**
 * 持久化（保存）实体对象DAO接口
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 * @param <PK>
 */
public interface ISaveEntityDao<T extends IEntityBean, PK extends Serializable> extends ISaveDao {

    /**
     * 保存
     * @param t 实体对象
     */
    void save(T t);

    /**
     * 批量保存
     * @param ts 实体对象集合
     */
    void save(Collection<T> ts);

    /**
     * 保存或更新
     * @param t 实体对象
     */
    void saveOrUpdate(T t);

    /**
     * 批量保存或更新
     * @param ts 实体对象集合
     */
    void saveOrUpdate(Collection<T> ts);

}
