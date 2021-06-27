
CREATE TABLE `t_sys_resource` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT 0 COMMENT '父ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号',
  `rest_url` VARCHAR(255) NULL COMMENT 'REST URL地址',
  `is_fun` INT(1) NULL COMMENT '是否是功能资源；1--是；0--否',
  `is_auth` INT(1) NULL COMMENT '是否授权；1--需要授权；0--不需要授权',
  `creator` VARCHAR(50) NULL COMMENT '创建人ID（值为：租户用户ID）',
  `is_group` int NOT NULL DEFAULT 0 COMMENT '是否分组；1--是；0--否' ,
  PRIMARY KEY (`id`))
COMMENT = '系统资源表';

CREATE TABLE `t_sys_dict` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT 0 COMMENT '父ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号',
  `value` VARCHAR(127) NULL COMMENT '数据项的值',
  `int_value` INT(5) NULL COMMENT '数据项值对应的整型值,否则该字段为null',
  `creator` VARCHAR(50) NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`))
COMMENT = '数据字典表';

CREATE TABLE `t_sys_user` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态;1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `password` VARCHAR(64) NOT NULL COMMENT '密码',
  `username` VARCHAR(127) NOT NULL COMMENT '用户名',
  `nickname` VARCHAR(45) NOT NULL COMMENT '匿名',
  `mobile` VARCHAR(20) NULL COMMENT '手机号',
  `avatar` VARCHAR(127) NULL COMMENT '头像地址',
  `remarks` VARCHAR(500) NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`))
COMMENT = '用户表';

CREATE TABLE `t_sys_menu` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT 0 COMMENT '父ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `sort_order` INT(5) NOT NULL DEFAULT 0 COMMENT '排序序号',
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
  `creator` VARCHAR(50) NOT NULL COMMENT '创建人ID（值为：租户用户ID）；和t_sys_corp_user表中的主键ID关联',
  PRIMARY KEY (`id`))
COMMENT = '版本信息表';

CREATE TABLE `t_sys_role` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `state` INT(1) NOT NULL DEFAULT 1 COMMENT '状态；1--有效；0--无效',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `name` VARCHAR(127) NOT NULL COMMENT '名称',
  `describe` VARCHAR(500) NULL COMMENT '角色描述',
  `creator` VARCHAR(50) NOT NULL COMMENT '创建人ID（值为：租户用户ID）；和t_sys_corp_user表中的主键ID关联',
  PRIMARY KEY (`id`))
COMMENT = '角色信息表';

CREATE TABLE `t_sys_role_menu` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `role_id` VARCHAR(50) NOT NULL COMMENT '角色ID',
  `menu_id` VARCHAR(50) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`))
COMMENT = '角色表与菜单表的关联表';

CREATE TABLE `t_sys_role_resource` (
  `id` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `role_id` VARCHAR(50) NOT NULL COMMENT '角色ID',
  `resource_id` VARCHAR(50) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`))
COMMENT = '角色表与资源表的关联表';

