/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : inspection

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 10/10/2019 18:18:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级权限ID',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '权限类型：1-page,2-link,3-button',
  `request_url` varchar(64) NULL DEFAULT NULL COMMENT '请求url',
  `request_type` varchar(16) NULL DEFAULT NULL  COMMENT '请求方式：POST,GET,PUT,DELETE',
  `icon_class` varchar(16) NULL DEFAULT NULL COMMENT '图标类',
  `sort` int(11) NULL DEFAULT 1 COMMENT '排序字段',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：1-启用，2-禁用',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
    `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
    `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
    `role_status` tinyint(4) DEFAULT '1' COMMENT '状态：1-启用，2-禁用',
    `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
   `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
   `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限ID',
   PRIMARY KEY (`id`) USING BTREE,
   UNIQUE KEY `idx_role_permission` (`role_id`,`permission_id`) COMMENT '唯一索引，角色ID和权限ID组合唯一性'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
    `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
    `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MD5加密密码',
    `gender` tinyint(2) NULL DEFAULT NULL COMMENT '性别：1-男，2-女',
    `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
    `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
    `work_place` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作单位',
    `job` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
    `is_member` tinyint(2) NULL DEFAULT 0 COMMENT '是否会员：0-不是，1-是',
    `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '会员到期时间',
    `user_status` tinyint(2) NULL DEFAULT 1 COMMENT '账号状态：1-生效，2-禁用',
    `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
 `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
 PRIMARY KEY (`id`) USING BTREE,
 UNIQUE KEY `idx_user_role` (`user_id`,`role_id`) COMMENT '唯一索引，用户ID和角色ID组合唯一性'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for tb_spot_check
-- ----------------------------
DROP TABLE IF EXISTS `tb_spot_check`;
CREATE TABLE `tb_spot_check`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `company` varchar(128) NULL DEFAULT NULL COMMENT '标称委托企业',
 `producer` varchar(128) NULL DEFAULT NULL COMMENT '标称生产企业/进口代理商名称',
 `unit` varchar(128) NULL DEFAULT NULL COMMENT '被采样单位名称', 
 `sample` varchar(255) NULL DEFAULT NULL COMMENT '样品名称',
 `specification` varchar(128) NULL DEFAULT NULL COMMENT '包装规格',
 `expire_time` varchar(64) NULL DEFAULT NULL COMMENT '保质期',
 `product_type` tinyint(2) NULL DEFAULT NULL COMMENT '产品分类：1-皮肤用化妆品，2-毛发用化妆品，3-指（趾）甲用化妆品，4-口唇用化妆品',
 `location` varchar(16) NULL DEFAULT NULL COMMENT '产地',
 `check_result` tinyint(2) NULL DEFAULT NULL COMMENT '抽检结果：0-不合格，1-合格',
 `subject` varchar(255) NULL DEFAULT NULL COMMENT '不合格项目',
 `institution` varchar(64) NULL DEFAULT NULL COMMENT '公布机构',
 `publish_date` date NULL DEFAULT NULL COMMENT '公布日期',
 `is_fake` tinyint(2) NULL DEFAULT NULL COMMENT '是否涉嫌假冒：0-否，1-是',
 `source_link` varchar(128) NULL DEFAULT NULL COMMENT '来源链接',
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE,
 KEY `idx_institution` (`institution`) USING BTREE COMMENT '公布机构索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '抽检数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_system_data_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_data_type`;
CREATE TABLE `tb_system_data_type`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型：1-抽检、2-标准',
 `value` int(11) NULL DEFAULT NULL COMMENT '值'
 `code` varchar(32) NULL DEFAULT NULL COMMENT '编码',
 `name` varchar(64) NULL DEFAULT NULL COMMENT '名称', 
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_criterion
-- ----------------------------
DROP TABLE IF EXISTS `tb_criterion`;
CREATE TABLE `tb_criterion`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(32) NULL DEFAULT NULL COMMENT '名称', 
 `category` tinyint(2) NULL DEFAULT NULL COMMENT '一级分类', 
 `type` tinyint(2) NULL DEFAULT NULL COMMENT '二级分类', 
 `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
 `publish_unit` tinyint(2) NULL DEFAULT NULL COMMENT '发布单位',
 `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
 `implement_date` date NULL DEFAULT NULL COMMENT '实施日期',
 `summary` varchar(255) NULL DEFAULT NULL COMMENT '摘要',
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标准数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_annex
-- ----------------------------
DROP TABLE IF EXISTS `tb_annex`;
CREATE TABLE `tb_annex`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', 
 `business_id` int(11) NULL DEFAULT NULL COMMENT '业务ID',
 `name` varchar(32) NULL DEFAULT NULL COMMENT '附件名称', 
 `path` varchar(128) NULL DEFAULT NULL COMMENT '附件路径',
 `type` tinyint(2) NULL DEFAULT NULL COMMENT '分类：1-标准，2-法规', 
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_law
-- ----------------------------
DROP TABLE IF EXISTS `tb_law`;
CREATE TABLE `tb_law`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(32) NULL DEFAULT NULL COMMENT '名称', 
 `category` tinyint(2) NULL DEFAULT NULL COMMENT '一级分类', 
 `type` tinyint(2) NULL DEFAULT NULL COMMENT '二级分类', 
 `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
 `publish_unit` tinyint(2) NULL DEFAULT NULL COMMENT '发布单位',
 `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
 `implement_date` date NULL DEFAULT NULL COMMENT '实施日期',
 `process` tinyint(2) NULL DEFAULT NULL COMMENT '环节', 
 `content` MEDIUMTEXT NULL DEFAULT NULL COMMENT '法规内容',
 `source` tinyint(2) NULL DEFAULT NULL COMMENT '来源', 
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '法律法规数据表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

