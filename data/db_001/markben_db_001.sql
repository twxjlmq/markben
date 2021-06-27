-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: markben
-- ------------------------------------------------------
-- Server version	8.0.21-0ubuntu0.20.04.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_sys_dict`
--

DROP TABLE IF EXISTS `t_sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_dict` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `parent_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态；1--有效；0--无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(127) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序序号',
  `value` varchar(127) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据项的值',
  `int_value` int DEFAULT NULL COMMENT '数据项值对应的整型值,否则该字段为null',
  `creator` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID（值为：租户用户ID）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_dict`
--

LOCK TABLES `t_sys_dict` WRITE;
/*!40000 ALTER TABLE `t_sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_fun_type`
--

DROP TABLE IF EXISTS `t_sys_fun_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_fun_type` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态；1--有效；0--无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(127) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序序号',
  `fun_type` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_fun_type`
--

LOCK TABLES `t_sys_fun_type` WRITE;
/*!40000 ALTER TABLE `t_sys_fun_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_fun_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_menu`
--

DROP TABLE IF EXISTS `t_sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_menu` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `parent_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态；1--有效；0--无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(127) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序序号',
  `resource_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源ID；和t_sys_resource表中的主键关联',
  `menu_type` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单类型',
  `pc_icon` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'PC端图标',
  `mobile_icon` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '移动端图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_menu`
--

LOCK TABLES `t_sys_menu` WRITE;
/*!40000 ALTER TABLE `t_sys_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_resource`
--

DROP TABLE IF EXISTS `t_sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_resource` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `parent_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态；1--有效；0--无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(127) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序序号',
  `rest_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'REST URL地址',
  `mobile_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '移动端URL地址',
  `pc_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'PC端前端地址',
  `is_fun` int DEFAULT NULL COMMENT '是否是功能资源；1--是；0--否',
  `is_auth` int DEFAULT NULL COMMENT '是否授权；1--需要授权；0--不需要授权',
  `creator` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID（值为：租户用户ID）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_resource`
--

LOCK TABLES `t_sys_resource` WRITE;
/*!40000 ALTER TABLE `t_sys_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role`
--

DROP TABLE IF EXISTS `t_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_role` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态；1--有效；0--无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(127) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `describe` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
  `creator` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人ID（值为：租户用户ID）；和t_sys_tenant_user表中的主键ID关联',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role`
--

LOCK TABLES `t_sys_role` WRITE;
/*!40000 ALTER TABLE `t_sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role_menu`
--

DROP TABLE IF EXISTS `t_sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_role_menu` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表与菜单表的关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role_menu`
--

LOCK TABLES `t_sys_role_menu` WRITE;
/*!40000 ALTER TABLE `t_sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role_resource`
--

DROP TABLE IF EXISTS `t_sys_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_role_resource` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `resource_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表与资源表的关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role_resource`
--

LOCK TABLES `t_sys_role_resource` WRITE;
/*!40000 ALTER TABLE `t_sys_role_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user`
--

DROP TABLE IF EXISTS `t_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_user` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `state` int NOT NULL COMMENT '状态;1--有效；0--无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `password` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `username` varchar(127) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nickname` VARCHAR(45) COLLATE utf8mb4_general_ci NOT NULL COMMENT '匿名',
  `mobile` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(127) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像地址',
  `remarks` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user`
--

LOCK TABLES `t_sys_user` WRITE;
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` (`id`, `state`, `create_time`, `password`, `username`, `nickname`, `mobile`, `avatar`, `remarks`) VALUES ('ddd8d648631e45548ba97e456d8e137c',1,'2020-08-12 23:50:50','9307367f24edcfc5b11077e7caad2885','admin','管理员',NULL,NULL,'系统超级管理员');
/*!40000 ALTER TABLE `t_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_version`
--

DROP TABLE IF EXISTS `t_sys_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_version` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态；1--有效；0--无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `version` varchar(127) COLLATE utf8mb4_general_ci NOT NULL COMMENT '版本号',
  `num_version` int NOT NULL DEFAULT '0' COMMENT '数字版本号',
  `type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型',
  `describe` varchar(4000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本描述',
  `update_date` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新日期',
  `creator` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人ID（值为：租户用户ID）；和t_sys_tenant_user表中的主键ID关联',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_version`
--

LOCK TABLES `t_sys_version` WRITE;
/*!40000 ALTER TABLE `t_sys_version` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_version` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-12 23:55:57
