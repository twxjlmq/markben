package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.util.Map;

/**
 * 删除DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IDeleteDao {

    /**
     * 根据条件删除
     * @param clazz 实体对象
     */
    void deleteObj(Class<? extends IEntityBean> clazz);

    /**
     * 根据SQL语句删除数据
     * @param sql SQL语句
     * @param args 参数
     */
    void deleteSql(String sql, Map<String,Object> args);

    /**
     * SQL语句按参数过滤后删除数据
     * @param sql SQL语句
     * @param args 参数
     */
    void deleteSqlFilter(String sql, Map<String,Object> args);
}
