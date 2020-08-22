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
    String ROLE_SUPER_ADMIN = "super_admin";

    /**
     * 角色状态---普通角色
     */
     String ROLE_GENERAL = "general";

    /**
     * 角色状态 --- 企业管理员角色
     */
    String ROLE_CORP_ADMIN = "corp_admin";

    /**
     * 每页显示数量
     */
    int PRE_PAGE_SIZE = 20;

    /**
     * MD5盐值
     */
    String MD5_SALT = "&_markben_$";

    /**
     * 默认密码
     */
    String DEFAULT_PWD = "123456";

    /**
     * 超级管理员的企业ID
     */
    String SUPER_ADMIN_CORP_ID = "1";

    /**
     * 主键ID的长度
     */
    int ID_LENGTH = 50;


    /**
     * 授权token头标记
     */
    String AUTHORIZATION_TOKEN_HEADER = "X-Authorization-Token";

    /**
     * 异常指示符
     */
    String EXCEPTION_INDICATOR = "===>";

}
