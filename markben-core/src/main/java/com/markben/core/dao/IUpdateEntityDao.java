package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.io.Serializable;
import java.util.Collection;

/**
 * 更新实体数据DAO接口
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 * @param <PK>
 */
public interface IUpdateEntityDao<T extends IEntityBean, PK extends Serializable> extends IUpdateDao {

    /**
     * 更新
     * @param t 实体对象
     */
    void update(T t) ;

    /**
     * 批量更新
     * @param ts 实体对象集合
     */
    void update(Collection<T> ts) ;

}
