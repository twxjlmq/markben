CREATE TABLE `t_sys_corp` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `state` INT(1) NOT NULL COMMENT '状态;1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '企业（组织）名称',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT 0 COMMENT '父ID，默认值为：0',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号；默认为0',
  `logo` VARCHAR(255) NULL COMMENT '企业（组织）LOGO',
  `short_name` VARCHAR(127) NOT NULL COMMENT '企业简称；如果不设置该值时，其值和name字段值一致',
  `describe` VARCHAR(1000) NULL COMMENT '描述',
  `remarks` VARCHAR(500) NULL COMMENT '备注',
  `corp_user_id` VARCHAR(50) NOT NULL COMMENT '添加企业的企业用户ID；和t_sys_corp_user表中的主键ID关联',
  PRIMARY KEY (`id`))
COMMENT = '系统企业信息表';

CREATE TABLE `t_sys_resource` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT 0 COMMENT '父ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号',
  `corp_id` VARCHAR(50) NULL COMMENT '企业ID',
  `rest_url` VARCHAR(255) NULL COMMENT 'REST URL地址',
  `mobile_url` VARCHAR(255) NULL COMMENT '移动端URL地址',
  `pc_url` VARCHAR(255) NULL COMMENT 'PC端前端地址',
  `is_fun` INT(1) NULL COMMENT '是否是功能资源；1--是；0--否',
  `is_auth` INT(1) NULL COMMENT '是否授权；1--需要授权；0--不需要授权',
  `corp_user_id` VARCHAR(50) NULL COMMENT '企业用户ID',
  PRIMARY KEY (`id`))
COMMENT = '系统资源表';

CREATE TABLE `t_sys_dict` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT 0 COMMENT '父ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号',
  `corp_id` VARCHAR(50) NULL COMMENT '企业ID',
  `value` VARCHAR(127) NULL COMMENT '数据项的值',
  `int_value` INT(5) NULL COMMENT '数据项值对应的整型值,否则该字段为null',
  `corp_user_id` VARCHAR(50) NULL COMMENT '企业用户ID',
  PRIMARY KEY (`id`))
COMMENT = '数据字典表';

CREATE TABLE `t_sys_corp_user` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `state` INT(1) NOT NULL COMMENT '状态;1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `corp_id` VARCHAR(50) NOT NULL COMMENT '企业（组织）ID;和t_sys_corp表中的主键ID关联',
  `user_id` VARCHAR(50) NOT NULL COMMENT '用户ID;和t_sys_user表中的主键ID关联',
  `is_default` INT(1) NOT NULL DEFAULT 0 COMMENT '是否默认主组织;1--是；0--否',
  `nickname` VARCHAR(127) NOT NULL COMMENT '匿名',
  `is_supper_admin` INT(1) NOT NULL DEFAULT 0 COMMENT '是否为组织的超级管理员;1--是；0--否',
  `update_time` DATETIME NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`))
COMMENT = '企业用户表';

CREATE TABLE `t_sys_user` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `state` INT(1) NOT NULL COMMENT '状态;1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `password` VARCHAR(64) NOT NULL COMMENT '密码',
  `username` VARCHAR(127) NOT NULL COMMENT '用户名',
  `mobile` VARCHAR(20) NULL DEFAULT 0 COMMENT '手机号',
  `avatar` VARCHAR(127) NOT NULL COMMENT '头像地址',
  `remarks` VARCHAR(500) NOT NULL DEFAULT 0 COMMENT '备注',
  PRIMARY KEY (`id`))
COMMENT = '用户表';

CREATE TABLE `t_sys_org` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT 0 COMMENT '父ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号',
  `corp_id` VARCHAR(50) NOT NULL COMMENT '企业ID',
  `code` VARCHAR(255) NULL COMMENT '编号',
  `seq_parent_id` VARCHAR(500) NULL COMMENT '父序列；如：1111.2222.3333',
  `seq_name` VARCHAR(500) NULL COMMENT '名称序列;如: xxx有限公司>软件部>产品组',
  `remarks` VARCHAR(500) NULL COMMENT '备注',
  PRIMARY KEY (`id`))
COMMENT = '组织架构表';

CREATE TABLE `t_sys_menu` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT 0 COMMENT '父ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号',
  `corp_id` VARCHAR(50) NOT NULL COMMENT '企业ID',
  `resource_id` VARCHAR(50) NULL COMMENT '资源ID；和t_sys_resource表中的主键关联',
  `menu_type` VARCHAR(50) NULL COMMENT '菜单类型',
  `pc_icon` VARCHAR(100) NULL COMMENT 'PC端图标',
  `mobile_icon` VARCHAR(100) NULL COMMENT '移动端图标',
  PRIMARY KEY (`id`))
COMMENT = '菜单表';

CREATE TABLE `t_sys_fun_type` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号',
  `fun_type` VARCHAR(50) NULL COMMENT '菜单类型',
  PRIMARY KEY (`id`))
COMMENT = '功能类型表';

CREATE TABLE `t_sys_version` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `version` VARCHAR(127) NOT NULL COMMENT '版本号',
  `num_version` INT(9) NOT NULL DEFAULT 0 COMMENT '数字版本号',
  `type` VARCHAR(20) NULL COMMENT '类型',
  `describe` VARCHAR(4000) NULL COMMENT '版本描述',
  `update_date` VARCHAR(20) NULL COMMENT '更新日期',
  `corp_user_id` VARCHAR(50) NOT NULL COMMENT '企业用户ID；和t_sys_corp_user表中的主键ID关联',
  PRIMARY KEY (`id`))
COMMENT = '版本信息表';

CREATE TABLE `t_sys_role` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `corp_id` VARCHAR(50) NOT NULL COMMENT '企业ID',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `describe` VARCHAR(500) NULL COMMENT '角色描述',
  `corp_user_id` VARCHAR(50) NOT NULL COMMENT '企业用户ID；和t_sys_corp_user表中的主键ID关联',
  PRIMARY KEY (`id`))
COMMENT = '角色信息表';

CREATE TABLE `t_sys_org_corp_user` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `corp_id` VARCHAR(50) NOT NULL COMMENT '企业ID',
  `org_id` VARCHAR(50) NOT NULL COMMENT '组织机构ID',
  `corp_user_id` VARCHAR(50) NOT NULL COMMENT '企业用户ID；和t_sys_corp_user表中的主键ID关联',
  PRIMARY KEY (`id`))
COMMENT = '组织机构与企业用户的关联表';

CREATE TABLE `t_sys_role_corp_user` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `corp_id` VARCHAR(50) NOT NULL COMMENT '企业ID',
  `role_id` VARCHAR(50) NOT NULL COMMENT '角色ID',
  `corp_user_id` VARCHAR(50) NOT NULL COMMENT '企业用户ID；和t_sys_corp_user表中的主键ID关联',
  PRIMARY KEY (`id`))
COMMENT = '角色表与企业用户表的关联表';

CREATE TABLE `t_sys_role_menu` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `corp_id` VARCHAR(50) NOT NULL COMMENT '企业ID',
  `role_id` VARCHAR(50) NOT NULL COMMENT '角色ID',
  `menu_id` VARCHAR(50) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`))
COMMENT = '角色表与菜单表的关联表';

CREATE TABLE `t_sys_role_resource` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `corp_id` VARCHAR(50) NOT NULL COMMENT '企业ID',
  `role_id` VARCHAR(50) NOT NULL COMMENT '角色ID',
  `resource_id` VARCHAR(50) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`))
COMMENT = '角色表与资源表的关联表';
