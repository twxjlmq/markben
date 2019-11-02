package com.markben.core.dao;

import com.markben.core.bean.Paginate;

import java.util.List;
import java.util.Map;

/**
 * 查询DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IQueryDao {

    /**
     * 根据SQL语句查询；查询结果转换为指定的对象
     * @param sql SQL语句
     * @param args 参数
     * @param clazz 结果集要转换的指定对象
     * <p> 如：Map.class，则结果转换为Map数据结构 </p>
     * @return 返回指定对象集合
     */
    <E> List<E> querySql(String sql, Map<String,Object> args, Class<?> clazz);

    /**
     * 根据SQL语句查询；查询结果转换为指定的对象
     * @param sql SQL语句
     * @param args 参数
     * @param clazz 结果集要转换的指定对象
     * <p> 如：Map.class，则结果转换为Map数据结构 </p>
     * @param isFilter SQL语句是否需要处理
     * @return 返回指定对象集合
     */
    <E> List<E> querySql(String sql, Map<String,Object> args, Class<?> clazz, boolean isFilter);

    /**
     * SQL语句根据提供的参数进行过滤处理后在查询；并查询结果转换为指定的对象
     * @param sql SQL语句
     * @param args 参数
     * @param clazz 结果集要转换的指定对象
     * @return 返回指定对象集合
     */
    <E> List<E> querySqlFilter(String sql, Map<String,Object> args, Class<?> clazz);

    /**
     * 根据SQL语句分页查询(转换为指定的对象)
     * @param sql SQL语句
     * @param args 参数
     * @param start 开始显示位置
     * @param pageSize 每页显示数
     * @param clazz 结果集要转换的指定对象
     * <p> 如：Map.class，则结果转换为Map数据结构 </p>
     * @return 返回分页对象
     */
    <E> Paginate<E> querySql(String sql, Map<String,Object> args,
                                    int start, int pageSize, Class<?> clazz);

    /**
     * 根据SQL语句分页查询(转换为指定的对象)
     * @param sql SQL语句
     * @param args 参数
     * @param start 开始显示位置
     * @param pageSize 每页显示数
     * @param clazz 结果集要转换的指定对象
     * <p> 如：Map.class，则结果转换为Map数据结构 </p>
     * @param isFilter SQL语句是否需要处理
     * @return 返回分页对象
     */
    <E> Paginate<E> querySql(String sql, Map<String,Object> args,
                                    int start, int pageSize, Class<?> clazz, boolean isFilter);

    /**
     * SQL语句按参数过滤后分页查询(转换为指定的对象)
     * @param sql SQL语句
     * @param args 参数
     * @param start 开始显示位置
     * @param pageSize 每页显示数
     * @param clazz 结果集要转换的指定对象
     * <p> 如：Map.class，则结果转换为Map数据结构 </p>
     * @return 返回分页对象
     */
    <E> Paginate<E> querySqlFilter(String sql, Map<String,Object> args,
                                          int start, int pageSize, Class<?> clazz);

    /**
     * 根据SQL语句查询(返回对象数组)
     * @param sql SQL语句
     * @param args 参数
     * @return 返回对象集合
     */
    List<Object> queryObjSql(String sql, Map<String,Object> args);

    /**
     * 根据SQL语句查询(返回对象数组)
     * @param sql SQL语句
     * @param args 参数
     * @param isFilter SQL语句是否需要处理
     * @return 返回对象集合
     */
    List<Object> queryObjSql(String sql, Map<String,Object> args, boolean isFilter);

    /**
     * SQL语句按参数过滤后查询(返回对象数组)
     * @param sql SQL语句
     * @param args 参数
     * @return 返回对象集合
     */
    List<Object> queryObjSqlFilter(String sql, Map<String,Object> args);

    /**
     * 根据SQL语句分页查询(返回对象数组)
     * @param sql SQL语句
     * @param args 参数
     * @param start 开始显示位置
     * @param pageSize 每页显示数
     * @return 返回分页对象
     */
    Paginate<Object> queryObjSql(String sql, Map<String,Object> args,
                                        int start, int pageSize);

    /**
     * 根据SQL语句分页查询(返回对象数组)
     * @param sql SQL语句
     * @param args 参数
     * @param start 开始显示位置
     * @param pageSize 每页显示数
     * @param isFilter SQL语句是否需要处理
     * @return 返回分页对象
     */
    Paginate<Object> queryObjSql(String sql, Map<String,Object> args,
                                        int start, int pageSize, boolean isFilter);

    /**
     * SQL语句按参数过滤后分页查询(返回对象数组)
     * @param sql SQL语句
     * @param args 参数
     * @param start 开始显示位置
     * @param pageSize 每页显示数
     * @return 返回分页对象
     */
    Paginate<Object> queryObjSqlFilter(String sql, Map<String,Object> args,
                                              int start, int pageSize);

    /**
     * 根据SQL统计
     * @param sql SQL语句
     * @param args 参数
     * @return 统计结果
     */
    Long countSql(String sql, Map<String,Object> args);

    /**
     * 根据SQL统计
     * @param sql SQL语句
     * @param args 参数
     * @param isFilter SQL语句是否需要处理
     * @return 统计结果
     */
    Long countSql(String sql, Map<String,Object> args, boolean isFilter);

    /**
     * SQL语句按参数过滤后统计
     * @param sql SQL语句
     * @param args 参数
     * @return 统计结果
     */
    Long countSqlFilter(String sql, Map<String,Object> args);

}
