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
 `product_type` varchar(32) NULL DEFAULT NULL COMMENT '产品分类,关联数据常量表',
 `location` varchar(16) NULL DEFAULT NULL COMMENT '产地',
 `check_result` varchar(8) NULL DEFAULT NULL COMMENT '抽检结果：0-不合格，1-合格',
 `subject` varchar(255) NULL DEFAULT NULL COMMENT '不合格项目',
 `institution` varchar(64) NULL DEFAULT NULL COMMENT '公布机构',
 `publish_date` date NULL DEFAULT NULL COMMENT '公布日期',
 `is_fake` varchar(8) NULL DEFAULT NULL COMMENT '是否涉嫌假冒：0-否，1-是',
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
 `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型：1-抽检、2-标准、3-法规、4-飞检',
 `value` int(11) NULL DEFAULT NULL COMMENT '值',
 `code` varchar(32) NULL DEFAULT NULL COMMENT '编码',
 `param` varchar(32) NULL DEFAULT NULL COMMENT '参数',
 `name` varchar(64) NULL DEFAULT NULL COMMENT '名称', 
 `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级', 
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_system_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_type`;
CREATE TABLE `tb_system_type`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `code` varchar(32) NULL DEFAULT NULL COMMENT '编码',
 `name` varchar(64) NULL DEFAULT NULL COMMENT '名称', 
 `type` tinyint(2) NULL DEFAULT NULL COMMENT '类型：1-抽检、2-标准、3-法规、4-飞检、5-文章',
 `remark` varchar(32) DEFAULT NULL COMMENT '字典说明',
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_system_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_data`;
CREATE TABLE `tb_system_data`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `code` varchar(32) NULL DEFAULT NULL COMMENT '编码',
 `name` varchar(64) NULL DEFAULT NULL COMMENT '名称', 
 `type_code` varchar(32) NULL DEFAULT NULL COMMENT '父级编码',
 `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
 `is_view` tinyint(2) DEFAULT '0' COMMENT '是否首页展示：0-否、1-是',
 `remark` varchar(32) DEFAULT NULL COMMENT '字典说明', 
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE,
 UNIQUE KEY `idx_code` (`code`) USING BTREE COMMENT '编码唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_criterion
-- ----------------------------
DROP TABLE IF EXISTS `tb_criterion`;
CREATE TABLE `tb_criterion`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(32) NULL DEFAULT NULL COMMENT '名称', 
 `category` varchar(32) NULL DEFAULT NULL COMMENT '一级分类,关联数据常量表', 
 `type` varchar(32) NULL DEFAULT NULL COMMENT '二级分类,关联数据常量表', 
 `status` varchar(32) NULL DEFAULT NULL COMMENT '状态',
 `publish_unit` varchar(32) NULL DEFAULT NULL COMMENT '发布单位,关联数据常量表',
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
 `type` tinyint(2) NULL DEFAULT NULL COMMENT '分类：1-抽检，2-标准，3-法规', 
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_law
-- ----------------------------
DROP TABLE IF EXISTS `tb_law`;
CREATE TABLE `tb_law`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(32) NULL DEFAULT NULL COMMENT '名称', 
 `code_number` varchar(255) NULL DEFAULT NULL COMMENT '法规文号',
 `category` varchar(32) NULL DEFAULT NULL COMMENT '一级分类', 
 `type` tinyint(2) NULL DEFAULT NULL COMMENT '二级分类', 
 `status` varchar(32) NULL DEFAULT NULL COMMENT '状态',
 `publish_unit` varchar(32) NULL DEFAULT NULL COMMENT '发布单位',
 `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
 `implement_date` date NULL DEFAULT NULL COMMENT '实施日期',
 `process` tinyint(2) NULL DEFAULT NULL COMMENT '环节', 
 `content` MEDIUMTEXT NULL DEFAULT NULL COMMENT '法规内容',
 `source` varchar(32) NULL DEFAULT NULL COMMENT '来源', 
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '法律法规数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_law_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_law_type`;
CREATE TABLE `tb_law_type`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `code` varchar(32) NULL DEFAULT NULL COMMENT '编码,关联数据常量表"',
 `name` varchar(64) NULL DEFAULT NULL COMMENT '名称', 
 `value` int(11) NULL DEFAULT NULL COMMENT '值',
 `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
 `remark` varchar(32) DEFAULT NULL COMMENT '字典说明', 
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '法规二级分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_flight_check
-- ----------------------------
DROP TABLE IF EXISTS `tb_flight_check`;
CREATE TABLE `tb_flight_check`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `business_name` varchar(128) NULL DEFAULT NULL COMMENT '企业名称',
 `problem` MEDIUMTEXT NULL DEFAULT NULL COMMENT '缺陷问题',
 `precautions` tinyint(2) NULL DEFAULT NULL COMMENT '处理措施：1-无，2-未明示，3-责令整改，4-限期整改，5-停产整改',
 `type` tinyint(2) NULL DEFAULT NULL COMMENT '飞检类型：1-国家飞检，2-地方飞检',
 `publish_unit` varchar(128) NULL DEFAULT NULL COMMENT '发布单位',
 `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
 `is_defect` tinyint(2) NULL DEFAULT NULL COMMENT '是否有缺陷：0-否，1-是',
 `source_link` varchar(128) NULL DEFAULT NULL COMMENT '来源链接', 
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '飞检数据表' ROW_FORMAT = Dynamic; 

-- ----------------------------
-- Table structure for tb_multi_media
-- ----------------------------
DROP TABLE IF EXISTS `tb_multi_media`;
CREATE TABLE `tb_multi_media`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `business_id` int(11) NULL DEFAULT NULL COMMENT '业务ID',
 `business_type` tinyint(2) NULL DEFAULT NULL COMMENT '业务类型',
 `category` tinyint(2) NULL DEFAULT NULL COMMENT '多媒体类型：1-图片，2-视频',
 `thumbnail` varchar(64) NULL DEFAULT NULL COMMENT '缩略图/视频展示图名称',
 `media_name` varchar(64) NULL DEFAULT NULL COMMENT '原图片/视频存储名称',
 `media_type` varchar(16) NULL DEFAULT NULL COMMENT '数据类型，例：image/jpeg',
 `size` double DEFAULT NULL COMMENT '大小(KB)',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多媒体数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `title` varchar(128) NULL DEFAULT NULL COMMENT '文章标题',
 `author` varchar(16)  NULL DEFAULT NULL COMMENT '作者',
 `content` MEDIUMTEXT NULL DEFAULT NULL COMMENT '文章内容', 
 `type_code` varchar(32) NULL DEFAULT NULL COMMENT '文章类型',
 `subject` varchar(225) NULL DEFAULT NULL COMMENT '文章关键词',
 `is_publish` tinyint(2) DEFAULT 0 COMMENT '是否发布：0-否，1-是',
 `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
 `read_count` int(11) DEFAULT 0 COMMENT '阅读量',
 `links` text NULL DEFAULT NULL COMMENT '相关链接', 
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic; 

-- ----------------------------
-- Table structure for tb_user_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_log`;
CREATE TABLE `tb_user_log`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
 `user_name` varchar(32) NULL DEFAULT NULL COMMENT '用户名',
 `ip` varchar(16)  NULL DEFAULT NULL COMMENT '访问地址', 
 `type` varchar(16) NULL DEFAULT NULL COMMENT '日志操作类型',
 `description` varchar(225) NULL DEFAULT NULL COMMENT '操作描述',
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE,
 KEY `idx_type` (`type`) USING BTREE COMMENT '日志操作类型索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic; 

-- ----------------------------
-- Table structure for tb_banner
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner`;
CREATE TABLE `tb_banner`  (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(225) NULL DEFAULT NULL COMMENT '轮播图名称',
 `path` varchar(225) NULL DEFAULT NULL COMMENT '轮播图地址',
 `size` double  NULL DEFAULT NULL COMMENT '大小', 
 `type` varchar(16) NULL DEFAULT NULL COMMENT '图片类型',
 `is_view` tinyint(2) DEFAULT 1 COMMENT '是否展示：0-否，1-是',
 `remark` varchar(225) NULL DEFAULT NULL COMMENT '备注',
 `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
 `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
 `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
 `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
 `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '轮播图表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

