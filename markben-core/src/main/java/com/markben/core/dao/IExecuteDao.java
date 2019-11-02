package com.markben.core.dao;

import java.util.List;
import java.util.Map;

/**
 * 执行DAO接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IExecuteDao {

	/**
	 * 执行指定资源名称对应的SQL语句
	 * @param sql SQL语句
	 * @return 返回执行SQL语句后影响的行数
	 */
	int execute(String sql);
	
	/**
	 * 执行指定资源名称对应的SQL语句
	 * @param sql SQL语句
	 * @param args 参数
	 * @return 返回执行SQL语句后影响的行数
	 */
	int execute(String sql, Map<String, Object> args);
	
	/**
     * 执行指定资源名称对应的SQL语句
     * @param sql SQL语句
     * @param args 参数列表
     * @return 返回执行SQL语句后影响的行数
     */
    int execute(String sql, List<Map<String, Object>> args);
	
	/**
	 * 过滤条件语句后执行指定资源名称对应的SQL语句
	 * @param sql SQL语句
	 * @param args 参数
	 * @return 返回执行SQL语句后影响的行数
	 */
	int executeByFilter(String sql, Map<String, Object> args);

	
	/**
	 * 执行指定资源名称对应的多条SQL语句
	 * @param sqlArray SQL语句数组
	 * @return 返回执行SQL语句后影响的行数
	 */
	int executes(String[] sqlArray);
	
	/**
	 * 执行指定资源名称对应的多条SQL语句
	 * @param sqlArray SQL语句数组
	 * @param args 参数
	 * @return 返回执行SQL语句后影响的行数
	 */
	int executes(String[] sqlArray, Map<String, Object> args);
	
	/**
	 * 过滤条件语句后执行指定资源名称对应的多条SQL语句
	 * @param sqlArray SQL语句数组
	 * @param args 参数
	 * @return 返回执行SQL语句后影响的行数
	 */
	int executesByFilter(String[] sqlArray, Map<String, Object> args);
	
	
}
