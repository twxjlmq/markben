package com.markben.core.dao;

import com.markben.core.bean.entity.IEntityBean;

import java.util.Collection;
import java.util.Map;

/**
 * 持久化（保存）DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public interface ISaveDao {

    /**
     * 保存实体对象
     * @param bean 实体对象
     * @return 成功返回：true；否则返回：false
     */
    void saveObj(IEntityBean bean);

    /**
     * 批量保存
     * @param beans 实体对象集合
     */
    void saveObj(Collection<? extends IEntityBean> beans);

    /**
     * 保存或更新数据
     * @param bean 实体对象
     */
    void saveOrUpdateObj(IEntityBean bean);

    /**
     * 批量保存或更新数据
     * @param beans 实体对象
     * @return 成功返回：true；否则返回：false
     */
    void saveOrUpdateObj(Collection<? extends IEntityBean> beans);

    /**
     * 插入数据
     * @param sql 插入SQL语句
     * @param args 参数
     * @return 成功返回：true；否则返回：false
     */
    void insertSql(String sql, Map<String, Object> args);

}
