package com.markben.core.dao;

import com.markben.core.bean.Paginate;
import com.markben.core.bean.entity.IEntityBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 查询实体数据DAO接口
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 * @param <PK>
 */
public interface IQueryEntityDao<T extends IEntityBean, PK extends Serializable> extends IQueryDao {

    /**
     * 通过主键值获取对应的实体对象数据
     * @param id 主键值
     * @return 返回实体对象
     */
    public T find(PK id);

    /**
     * 通过主键值获取对应的实体对象数据
     * @param ids 主键值
     * @return 返回实体对象
     */
    public List<T> finds(PK[] ids);

    /**
     * 通过主键值获取对应的实体对象数据
     * @param clazz
     * @param id 主键值
     * @return 返回实体对象
     */
    public <E> E find(Class<? extends IEntityBean> clazz,PK id);

    /**
     * 获取所有数据
     * @return 返回实体对象集合
     */
    public List<T> findAll();

    /**
     * 通过SQL语句查询，获取指定的 <code>clazz</code> 实体对象
     * @param sql SQL语句
     * @param args SQL语句参数
     * @param clazz 指定的实体对象
     * @return 返回指定的实体对象
     */
    public <E> E queryEntityBySql(String sql, Map<String,Object> args, Class<? extends IEntityBean> clazz);

    /**
     * 通过SQL语句查询，获取指定的 <code>clazz</code> 实体对象集合（列表）
     * @param sql SQL语句
     * @param args SQL语句参数
     * @param clazz 指定的实体对象
     * @return 返回指定的实体对象集合（列表）
     */
    public <E> List<E> queryEntityListBySql(String sql, Map<String,Object> args, Class<? extends IEntityBean> clazz);

    /**
     * 通过SQL语句分页查询，获取指定的 <code>clazz</code> 实体对象集合（列表）
     * @param sql SQL语句
     * @param args SQL语句参数
     * @param clazz 指定的实体对象
     * @param start 开始显示位置
     * @param pageSize 每页显示数
     * @return 返回分页对象
     */
    public <E> Paginate<E> queryEntityListBySql(String sql, Map<String,Object> args, Class<? extends IEntityBean> clazz, int start, int pageSize);

    /**
     * 根据提供SQL及参数，进行过滤处理（对象SQL语句及参数都进行过滤处理）；
     * 然后在根据处理后的SQL语句及参数进行查询，获取指定的 <code>clazz</code> 实体对象集合（列表）
     * @param sql SQL语句
     * @param args SQL语句参数
     * @param clazz 指定的实体对象
     * @return 返回指定的实体对象集合（列表）
     */
    public <E> List<E> queryEntityListBySqlFilter(String sql, Map<String,Object> args, Class<? extends IEntityBean> clazz);

    /**
     * 根据提供SQL及参数，进行过滤处理（对象SQL语句及参数都进行过滤处理）；
     * 然后在根据处理后的SQL语句及参数进行分页查询，获取指定的 <code>clazz</code> 实体对象集合（列表）
     * @param sql SQL语句
     * @param args SQL语句参数
     * @param clazz 指定的实体对象
     * @param start 开始显示位置
     * @param pageSize 每页显示数
     * @return 返回分页对象
     */
    public <E> Paginate<E> queryEntityListBySqlFilter(String sql, Map<String,Object> args, Class<? extends IEntityBean> clazz, int start, int pageSize);

}
