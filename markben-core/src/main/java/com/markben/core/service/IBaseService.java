package com.markben.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.markben.core.bean.IEntityBean;

import java.util.List;

/**
 * 基础服务类;
 * 提供基础的查询功能
 * @author 乌草坡
 * @since 1.0
 */
public interface IBaseService<T extends IEntityBean> {

    /**
     * 根据主键ID获取对应实体数据
     * @param id 主键ID
     * @return 返回实体对象
     */
    T find(String id);

    /**
     * 根据主键ID数组获取对应实体列表数据
     * @param ids 主键ID数组
     * @return 返回实体对象列表
     */
    List<T> finds(String[] ids);

    /**
     * 根据主键ID数组获取有效的对应实体列表数据
     * @param ids 主键ID数组
     * @return 返回实体对象列表
     */
    List<T> findListByValid(String[] ids);

    /**
     * 根据指定的字段名称<code>columnName</code>及字段值获取对应实体列表数据
     * @param columnName 字段名称
     * @param value 字段值
     * @return 返回实体对象列表
     */
    List<T> finds(String columnName, Object value);

    /**
     * 根据条件查询结果
     * @param queryWrapper 查询条件对象
     * @return 返回列表
     */
    List<T> finds(Wrapper<T> queryWrapper);
}
