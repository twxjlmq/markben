package com.markben.core.service;

import com.markben.core.bean.EntityBean;

import java.util.Collection;

/**
 * 管理服务类接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface MgrService<T extends EntityBean> extends BaseService<T> {

    /**
     * 保存实体对象数据
     * @param t 实体对象
     * @return 如果保存成功返回true；否则返回false
     */
    boolean save(T t);

    /**
     * 批量保存实体对象数据
     * @param ts 实体对象集合
     * @return 如果保存成功返回true；否则返回false
     */
    boolean save(Collection<T> ts);

    /**
     * 保存或更新实体数据
     * @param t 实体对象
     * @return 如果保存成功返回true；否则返回false
     */
    boolean saveOrUpdate(T t);

    /**
     * 更新实体对象数据
     * @param t 实体对象
     * @return 如果保存成功返回true；否则返回false
     */
    boolean update(T t);

    /**
     * 批量更新实体对象数据
     * @param ts 实体对象集合
     * @return 如果保存成功返回true；否则返回false
     */
    boolean update(Collection<T> ts);

    /**
     * 删除实体对象数据
     * @param t 实体对象
     * @return 如果保存成功返回true；否则返回false
     */
    boolean delete(T t);

    /**
     * 批量删除实体对象数据
     * @param ts 实体对象集合
     * @return 如果保存成功返回true；否则返回false
     */
    boolean delete(Collection<T> ts);

    /**
     * 删除数据根据主键ID
     * @param id 主键ID
     * @return 如果保存成功返回true；否则返回false
     */
    boolean delete(String id);

    /**
     * 批量删除数据根据主键ID数组
     * @param ids 主键ID数组
     * @return 如果保存成功返回true；否则返回false
     */
    boolean delete(String[] ids);

}
