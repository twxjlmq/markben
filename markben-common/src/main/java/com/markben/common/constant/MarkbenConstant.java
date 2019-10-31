package com.markben.common.constant;

/**
 * 定义常亮
 * @author 乌草坡
 * @since 1.0
 */
public interface MarkbenConstant {

    /**
     * 多值分隔符“,”
     */
    String MULTI_VALUE_SPLIT = ",";

    /**
     * 多条语句间分隔符“;”
     */
    String MULTI_STATEMENT_SPLIT = ";";

    /**
     * 组合值分隔符“_”
     */
    String COMBINE_VALUE_SEPARATOR = "_";

    /**
     * 序列名称分隔符“>”
     */
    String SEQUENCE_NAME_SEPARATOR = ">";

    /**
     * 树形结构的根节点ID为0
     */
    String TREE_ROOT_ID = "0";

    /**
     * 序列号名称分隔符为“>>”
     */
    String SERVIAL_NAME_SPLIT = ">>";

    /**
     * 序列编码分隔符为“.”
     */
    String SERVIAL_CODE_SPLIT = ".";

    /**
     * 项目状态----开发模式
     */
    String PROJECT_DEV_MODEL = "1";

    /**
     * 项目状态----产品模式
     */
    String PROJECT_PRODUCT_MODEL = "0";

    /**
     * 角色状态---超级管理员角色
     */
    public final static String ROLE_SUPER_ADMIN = "super_admin";

    /**
     * 角色状态---普通角色
     */
    public final static String ROLE_GENERAL = "general";

    /**
     * 角色状态 --- 企业管理员角色
     */
    public final static String ROLE_CORP_ADMIN = "corp_admin";

    /////////////操作结果常量定义///////////////////
    /**
     * 操作标识--成功
     */
    public final static String OP_SUCCESS = "1";

    /**
     * 成功标识返回的默认提示信息
     */
    public final static String OP_SUCCESS_MSG = "数据操作成功";

    /**
     * 成功获取数据的提示信息
     */
    public final static String GET_DATA_SUCCESS_MSG = "数据获取成功";

    /**
     * 操作标识--没有数据
     */
    public final static String OP_NOT_DATA_SUCCESS = "0";

    /**
     * 没有数据标识返回的默认提示信息
     */
    public final static String OP_NOT_DATA_SUCCESS_MSG = "没有查询到相关数据";

    /**
     * 操作标识--失败
     */
    public final static String OP_FAIL = "-1";

    /**
     * 数据操作失败
     */
    public final static String OP_FAIL_MSG = "数据操作失败";

    /**
     * 每页显示数量
     */
    public final static int PRE_PAGE_SIZE = 20;

    /**
     * MD5盐值
     */
    public final static String MD5_SALT = "&_markben_$";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PWD = "123456";

    /**
     * 超级管理员的企业ID
     */
    public static final String SUPER_ADMIN_CORP_ID = "1";

    /**
     * 主键ID的长度
     */
    public static final int ID_LENGTH = 50;

    /**
     * 统计SQL语句名称的后缀
     */
    public static final String SQL_NAME_COUNT_SUFFIX = "_count";

}
