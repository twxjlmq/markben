package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.util.List;
import java.util.Map;

/**
 * 更新DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IUpdateDao {

    /**
     * 更新
     * @param bean 实体对象
     */
    void updateObj(IEntityBean bean) ;

    /**
     * 批量更新
     * @param beans 实体对象集合
     */
    void updateObj(List<? extends IEntityBean> beans) ;

    /**
     * 根据SQL语句更新数据
     * @param sql SQL语句
     * @param args 参数
     */
    void updateSql(String sql, Map<String, Object> args) ;

    /**
     * SQL语句按参数过滤后更新数据
     * @param sql SQL语句
     * @param args 参数
     * @return 成功返回：true；否则返回：false
     */
    boolean updateSqlFilter(String sql, Map<String, Object> args);

}
