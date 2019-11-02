package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.io.Serializable;
import java.util.Collection;

/**
 * 删除实体数据DAO接口
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 * @param <PK>
 */
public interface IDeleteEntityDao<T extends IEntityBean, PK extends Serializable> extends IDeleteDao {

    /**
     * 根据实体Bean删除
     * @param t 实体对象
     */
    void delete(T t);

    /**
     * 根据主键删除
     * @param id 主键值
     */
    void delete(PK id);

    /**
     * 根据主键删除
     * @param ids 主键数组
     */
    void delete(PK[] ids);

    /**
     * 根据主键删除
     * @param clazz
     * @param id 主键值
     */
    void delete(Class<? extends IEntityBean> clazz, PK id);

    /**
     * 根据主键删除
     * @param clazz
     * @param ids 主键数组
     */
    void delete(Class<? extends IEntityBean> clazz, PK[] ids);


    /**
     * 根据实体对象集合批量删除
     * @param ts 实体对象集合
     */
    void delete(Collection<T> ts);

}
