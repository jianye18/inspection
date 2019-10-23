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

 Date: 23/10/2019 20:37:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_annex
-- ----------------------------
DROP TABLE IF EXISTS `tb_annex`;
CREATE TABLE `tb_annex`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `business_id` int(11) NULL DEFAULT NULL COMMENT '业务ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件路径',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '分类：1-抽检，2-标准，3-法规',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_criterion
-- ----------------------------
DROP TABLE IF EXISTS `tb_criterion`;
CREATE TABLE `tb_criterion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `category` tinyint(2) NULL DEFAULT NULL COMMENT '一级分类',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '二级分类',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  `publish_unit` tinyint(2) NULL DEFAULT NULL COMMENT '发布单位',
  `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
  `implement_date` date NULL DEFAULT NULL COMMENT '实施日期',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标准数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_criterion
-- ----------------------------
INSERT INTO `tb_criterion` VALUES (1, '标准数据001', 1, 2, 1, 1, '2019-09-30', '2019-10-03', '摘要', 1, '2019-10-19 14:30:35', 1, '2019-10-19 15:29:26', 1);
INSERT INTO `tb_criterion` VALUES (2, '标准数据002', 1, 2, 1, 1, '2019-09-30', '2019-10-03', '摘要', 1, '2019-10-10 16:46:39', 1, '2019-10-19 16:46:19', 0);
INSERT INTO `tb_criterion` VALUES (3, '标准数据003', 1, 2, 1, 1, '2019-10-01', '2019-10-04', '摘要', 1, '2019-10-17 16:46:44', 1, '2019-10-19 15:57:08', 1);
INSERT INTO `tb_criterion` VALUES (4, '标准数据004', 1, 2, 1, 1, '2019-10-03', '2019-10-08', '摘要', 1, '2019-10-16 16:46:46', 1, '2019-10-19 15:57:45', 1);

-- ----------------------------
-- Table structure for tb_law
-- ----------------------------
DROP TABLE IF EXISTS `tb_law`;
CREATE TABLE `tb_law`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `category` tinyint(2) NULL DEFAULT NULL COMMENT '一级分类',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '二级分类',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  `publish_unit` tinyint(2) NULL DEFAULT NULL COMMENT '发布单位',
  `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
  `implement_date` date NULL DEFAULT NULL COMMENT '实施日期',
  `process` tinyint(2) NULL DEFAULT NULL COMMENT '环节',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '法规内容',
  `source` tinyint(2) NULL DEFAULT NULL COMMENT '来源',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '法律法规数据表' ROW_FORMAT = Dynamic;

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
  `request_url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `request_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式：POST,GET,PUT,DELETE',
  `icon_class` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标类',
  `sort` int(11) NULL DEFAULT 1 COMMENT '排序字段',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态：1-启用，2-禁用',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, '用户新增', 'user_add', '测试', 2, 3, '/user/saveUser', 'POST', 'md-contact', 1, 1, 1, '2019-10-13 18:36:11', 1, '2019-10-13 18:36:11', 1);
INSERT INTO `tb_permission` VALUES (2, '用户管理', 'user', '用户管理页面', 0, 1, '/system-manager/user/user.vue', NULL, 'ios-contact', 1, 1, 1, '2019-10-13 18:36:11', 1, '2019-10-13 18:36:11', 1);
INSERT INTO `tb_permission` VALUES (3, '角色管理', 'role', '角色管理页面', 0, 1, '/system-manager/role/role.vue', NULL, 'md-contacts', 2, 1, 1, '2019-10-13 18:36:11', 1, '2019-10-13 18:36:11', 1);
INSERT INTO `tb_permission` VALUES (4, '权限管理', 'permission', '权限管理页面', 0, 1, '/system-manager/permission/permission.vue', NULL, 'md-flower', 3, 1, 1, '2019-10-13 18:36:11', 1, '2019-10-13 18:36:11', 1);
INSERT INTO `tb_permission` VALUES (5, '角色新增', 'role_add', '测试', 3, 3, '/role/saveRole', 'POST', 'md-add', 1, 1, 1, '2019-10-13 21:52:30', 1, '2019-10-13 21:52:30', 1);
INSERT INTO `tb_permission` VALUES (6, '权限新增', 'user_add', '可以新增权限', 4, 3, '/permission/savePermission', 'POST', 'md-add', 1, 1, 1, '2019-10-13 21:53:27', 1, '2019-10-13 21:53:27', 1);
INSERT INTO `tb_permission` VALUES (7, '用户修改', 'user_edit', '用户修改信息权限', 2, 3, '/user/saveUser', 'POST', 'md-contact', 2, 1, 1, '2019-10-14 16:51:19', 1, '2019-10-14 16:51:19', 1);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_status` tinyint(4) NULL DEFAULT 1 COMMENT '状态：1-启用，2-禁用',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '管理员', 'sys-admin', '测试', 1, 1, '2019-10-13 10:18:59', 1, '2019-10-14 22:29:32', 1);
INSERT INTO `tb_role` VALUES (2, '测试人员', 'dev-test', '测试', 1, 1, '2019-10-13 10:25:54', 1, '2019-10-19 16:44:40', 1);

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_role_permission`(`role_id`, `permission_id`) USING BTREE COMMENT '唯一索引，角色ID和权限ID组合唯一性'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES (2, 1, 1);
INSERT INTO `tb_role_permission` VALUES (1, 1, 2);
INSERT INTO `tb_role_permission` VALUES (4, 1, 3);
INSERT INTO `tb_role_permission` VALUES (6, 1, 4);
INSERT INTO `tb_role_permission` VALUES (5, 1, 5);
INSERT INTO `tb_role_permission` VALUES (7, 1, 6);
INSERT INTO `tb_role_permission` VALUES (3, 1, 7);

-- ----------------------------
-- Table structure for tb_spot_check
-- ----------------------------
DROP TABLE IF EXISTS `tb_spot_check`;
CREATE TABLE `tb_spot_check`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标称委托企业',
  `producer` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标称生产企业/进口代理商名称',
  `unit` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被采样单位名称',
  `sample` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样品名称',
  `specification` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '包装规格',
  `expire_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保质期',
  `product_type` tinyint(2) NULL DEFAULT NULL COMMENT '产品分类：1-皮肤用化妆品，2-毛发用化妆品，3-指（趾）甲用化妆品，4-口唇用化妆品',
  `location` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产地',
  `check_result` tinyint(2) NULL DEFAULT NULL COMMENT '抽检结果：0-不合格，1-合格',
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不合格项目',
  `institution` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公布机构',
  `publish_date` date NULL DEFAULT NULL COMMENT '公布日期',
  `is_fake` tinyint(2) NULL DEFAULT NULL COMMENT '是否涉嫌假冒：0-否，1-是',
  `source_link` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源链接',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_institution`(`institution`) USING BTREE COMMENT '公布机构索引'
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '抽检数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_spot_check
-- ----------------------------
INSERT INTO `tb_spot_check` VALUES (1, '/', '宁波御坊堂生物科技有限公司', '宁波御坊堂生物科技有限公司', '舒泊阑迷迭香凉爽舒润沐浴露', 'H04170045', '735g/瓶', 1, '浙江', 1, '/', '宁波市海曙区市场监督管理局', '2018-01-02', 0, 'http://www.nbcredit.net/gscms/cms/wzy?wzid=77bb6064f0e54e1eb80bd27b0e1648e5&type=CJXX_HZP', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (2, '/', '苏州尚美国际化妆品有限公司', '宁波海曙陈军美发店（陈艳军）', '巴黎欧莱雅探索水润烫发膏', '/', '400mL/袋', 2, '江苏', 1, '/', '宁波市海曙区市场监督管理局', '2018-01-02', 0, 'http://www.nbcredit.net/gscms/cms/wzy?wzid=77bb6064f0e54e1eb80bd27b0e1648e5&type=CJXX_HZP', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (3, '/', '上海汇捷文化传播有限公司', '宁波海曙天鸥化妆品店（黎俊）', 'DERMINA舒缓自然防晒乳SPF 30PA+++', 'F645101', '40mL/支', 1, '上海', 1, '/', '宁波市海曙区市场监督管理局', '2018-01-02', 0, 'http://www.nbcredit.net/gscms/cms/wzy?wzid=77bb6064f0e54e1eb80bd27b0e1648e5&type=CJXX_HZP', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (4, '/', '上海汇捷文化传播有限公司', '宁波海曙天鸥化妆品店（黎俊）', 'DERMINA舒护美白修护面膜', 'F647201', '50mL/支', 1, '上海', 1, '/', '宁波市海曙区市场监督管理局', '2018-01-02', 0, 'http://www.nbcredit.net/gscms/cms/wzy?wzid=77bb6064f0e54e1eb80bd27b0e1648e5&type=CJXX_HZP', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (5, '/', '韩国珂朵玛药妆研制公司', '宁波海曙天鸥化妆品店（黎俊）', '森颜树语自然精华面膜-石榴', 'OFK035', '20mL/片', 1, '韩国', 1, '/', '宁波市海曙区市场监督管理局', '2018-01-02', 0, 'http://www.nbcredit.net/gscms/cms/wzy?wzid=77bb6064f0e54e1eb80bd27b0e1648e5&type=CJXX_HZP', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (6, '/', '上海清轩生物科技有限公司', '银泰百货宁波海曙有限公司天一广场分公司', '林清轩 山茶花高保湿水', '5774733-46A', '150mL/瓶', 1, '上海', 1, '/', '宁波市海曙区市场监督管理局', '2018-01-02', 0, 'http://www.nbcredit.net/gscms/cms/wzy?wzid=77bb6064f0e54e1eb80bd27b0e1648e5&type=CJXX_HZP', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (7, '/', '广州市白云区人和荻薇日用化妆品厂', '北京鼎典格调美发有限公司', '弹簧水吧', '250ml/瓶', '(DW)161216', 1, '广东', 0, '菌落总数', '北京市药品监督管理局', '2018-01-04', 0, 'http://syj.beijing.gov.cn/bjfda/aqxx82/zlgg47/hzpl/448612/index.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (8, '/', '江西初美化妆品有限公司', '江西初美化妆品有限公司', '雪玲妃®白松露泥浆面膜', '150g', '//2021/12/04', 1, '江西', 1, '/', '山西省药品监督管理局', '2019-09-10', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/31918510.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (9, '/', '江西初美化妆品有限公司', '江西初美化妆品有限公司', '雪玲妃®柠檬去角质素', '250g', '//2022/07/15', 1, '江西', 1, '/', '山西省药品监督管理局', '2019-09-10', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/31918510.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (10, '/', '江西初美化妆品有限公司', '江西初美化妆品有限公司', '雪玲妃®乳木果身体磨砂膏', '250g', '//2022/07/09', 1, '江西', 1, '/', '山西省药品监督管理局', '2019-09-10', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/31918510.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (11, '/', '江西初美化妆品有限公司', '江西初美化妆品有限公司', '鲜叶®多效润颜修护精华', '340mg×30粒/盒', '//2022/03/18', 1, '江西', 1, '/', '山西省药品监督管理局', '2019-09-10', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/31918510.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (12, '/', '江西东弘药业有限公司', '江西东弘药业有限公司', '爱蒂拉®美妍撕拉面膜', '100g', '//20210617', 1, '江西', 1, '/', '山西省药品监督管理局', '2019-09-10', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/31918510.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (13, '/', '广州市元和堂化妆品制造有限公司', '吉安市御美丽健康产业股份有限公司', 'YUMEILI®深层舒缓冰点面膜', '25ml/片×6片装', '//2021/07/25', 1, '广东', 1, '/', '山西省药品监督管理局', '2019-09-10', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/31918510.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (14, '/', '殊眼国际贸易（上海）有限公司', '内蒙古包头百货大楼集团股份有限公司大楼超市', '花嫉温和染发膏1N黑色', '第一剂68g/第二剂68g', '170220，3年', 2, '上海', 0, '检出禁用组分：邻氨基苯酚；检出批件及标签未标识的染发剂', '国家药品监督管理局', '2019-09-27', 0, 'http://www.nmpa.gov.cn/directory/web/WS04/images/ufq80tKpxre84La9udzA7b7WMjAxOcTqtdo2N7rFzai45ri9vP4uZG9j.doc', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (15, '/', '殊眼国际贸易（上海）有限公司', '内蒙古包头百货大楼集团股份有限公司大楼超市', '花嫉温和染发膏3N深褐色', '第一剂68g/第二剂68g', '170220，3年', 2, '上海', 0, '检出禁用组分：邻氨基苯酚；检出批件及标签未标识的染发剂', '国家药品监督管理局', '2019-09-27', 0, 'http://www.nmpa.gov.cn/directory/web/WS04/images/ufq80tKpxre84La9udzA7b7WMjAxOcTqtdo2N7rFzai45ri9vP4uZG9j.doc', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (16, '/', '广州宝洁有限公司', '南昌百货大楼股份有限公司大众购物中心', '玉兰油®美白润肤霜', '30g', '73100386BA/20201106', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (17, '/', '广东华润顺峰药业有限公司', '婺源县好妈咪孕婴生活馆', '80\'s mom®80后妈妈痱子护理乳', '50g', 'AH0403641/20210416', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (18, '汕头市雅娜化妆品实业有限公司', '广东博禧高新科技有限公司', '婺源县同心实业有限公司派拉朦新时代店', '馥佩®馥佩祛死皮凝露', '100g+送28g', 'SE880810032/2021/08/15', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (19, '/', '珀莱雅化妆品股份有限公司', '江西财富广场有限公司中山路分公司', '珀莱雅靓白芯肌晶采淡斑精华液', '40ml', 'ETJ2/2023年03月02日', 1, '浙江', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (20, '/', '广州市采诗化妆品有限公司', '南昌青山湖大润发商业有限公司', '采诗®美白润肤霜', '50g', '2704/20210926', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (21, '/', '广州市采诗化妆品有限公司', '南昌青山湖大润发商业有限公司', '采诗®祛斑嫩肤面膜', '4片装', '2601/20230925', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (22, '上海相宜本草化妆品股份有限公司', '诺斯贝尔化妆品股份有限公司', '南昌青山湖大润发商业有限公司', '相宜本草嫩亮补水面膜', '25g×5片', 'U9321B41/20220920', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (23, '北京大宝化妆品有限公司', '莹口三喜加工有限公司', '南昌青山湖大润发商业有限公司', '大宝®花草茶润肌面膜 紧致弹滑', '23克×10片', 'B20171220.3/20201219', 1, '辽宁', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (24, '/', '广州悦目生物科技有限公司', '南昌青山湖大润发商业有限公司', '膜法世家®·大米酵素水润雪肌黑面膜贴', '25ml/片×7片', 'FC0185B/20210627', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (25, '/', '广州市采诗化妆品有限公司', '南昌青山湖大润发商业有限公司', '采诗®芦荟莹肌修复面膜', '5片装', '1401/20220613', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (26, '/', '广州悦目生物科技有限公司', '南昌青山湖大润发商业有限公司', '膜法世家®·玻尿酸补水保湿蚕丝面膜贴', '7片', 'GBN185B/20210717', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (27, '/', '联合利华(中国)有限公司', '南昌高新技术产业开发区万事达超市', '旁氏亮采净澈系列粉润莹泽洁面乳', '75g', 'A1AT/20210421', 1, '安徽', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (28, '/', '深圳市兰亭科技股份有限公司', '南昌高新技术产业开发区万事达超市', '兰亭 LANTERN®草本补水去死皮嫩肤素', '60g', '02KY8010/20211109', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (29, '/', '深圳市兰亭科技股份有限公司', '南昌高新技术产业开发区万事达超市', '兰亭 LANTERN®全天润白滋润乳', '100ml', '18HY7008/20200908', 1, '广东', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);
INSERT INTO `tb_spot_check` VALUES (30, '/', '上海家化联合股份有限公司', '南昌高新技术产业开发区万事达超市', '水润滢亮洁面乳', '100g', 'AAJZS/20230219', 1, '上海', 1, '/', '江西省药品监督管理局', '2019-10-09', 0, 'http://mpa.jiangxi.gov.cn/xwzx/zwtg/jdcj/hzpqxjdcj/32492549.html', 1, '2019-10-16 15:25:15', 1, '2019-10-16 15:25:15', 1);

-- ----------------------------
-- Table structure for tb_system_data_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_data_type`;
CREATE TABLE `tb_system_data_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型：1-抽检、2-标准',
  `value` int(11) NULL DEFAULT NULL COMMENT '值',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `param` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_system_data_type
-- ----------------------------
INSERT INTO `tb_system_data_type` VALUES (1, 1, 1, 'product_type', NULL, '皮肤用化妆品', NULL, NULL, 1, '2019-10-16 16:01:23', 1, '2019-10-16 16:01:28', 1);
INSERT INTO `tb_system_data_type` VALUES (2, 1, 2, 'product_type', NULL, '毛发用化妆品', NULL, NULL, 1, '2019-10-16 16:01:47', 1, '2019-10-16 16:01:52', 1);
INSERT INTO `tb_system_data_type` VALUES (3, 1, 3, 'product_type', NULL, '指（趾）甲用化妆品', NULL, NULL, 1, '2019-10-16 16:02:08', 1, '2019-10-16 16:02:10', 1);
INSERT INTO `tb_system_data_type` VALUES (4, 1, 4, 'product_type', NULL, '口唇用化妆品', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (5, 1, 1, 'institution', NULL, '宁波市海曙区市场监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (6, 1, 2, 'institution', NULL, '北京市药品监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (7, 1, 3, 'institution', NULL, '山西省药品监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (8, 1, 4, 'institution', NULL, '国家药品监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (9, 1, 5, 'institution', NULL, '江西省药品监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (10, 2, 1, 'criterion_category', NULL, '国际标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (11, 2, 2, 'criterion_category', NULL, '国家标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (12, 2, 3, 'criterion_category', NULL, '行业标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (13, 2, 4, 'criterion_category', NULL, '地方标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (14, 2, 5, 'criterion_category', NULL, '团体标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (15, 2, 6, 'criterion_category', NULL, '其他标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (16, 2, 1, 'criterion_type', NULL, '基础标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (17, 2, 2, 'criterion_type', NULL, '产品标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (18, 2, 3, 'criterion_type', NULL, '方法标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (19, 2, 4, 'criterion_type', NULL, '安全标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (20, 2, 5, 'criterion_type', NULL, '卫生标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (21, 2, 1, 'criterion_publish_unit', NULL, '单位1', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (22, 2, 2, 'criterion_publish_unit', NULL, '单位2', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (23, 2, 3, 'criterion_publish_unit', NULL, '单位3', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (24, 2, 4, 'criterion_publish_unit', NULL, '单位4', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (25, 2, 5, 'criterion_publish_unit', NULL, '单位5', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (26, 3, 1, 'law_category', NULL, '国际法规', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (27, 3, 2, 'law_category', NULL, '国家法规', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (28, 3, 3, 'law_category', NULL, '地方法规', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (29, 3, 4, 'law_category', NULL, '其他法规', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (30, 3, 5, 'law_category', NULL, '美国', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (31, 3, 6, 'law_category', NULL, '日本', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (32, 3, 7, 'law_category', NULL, '韩国', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (33, 3, 8, 'law_category', NULL, '欧盟', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (34, 3, 9, 'law_category', NULL, '其他', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (35, 3, 10, 'law_category', NULL, '广东省', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (36, 3, 11, 'law_category', NULL, '江苏省', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (37, 3, 12, 'law_category', NULL, '浙江省', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (38, 3, 13, 'law_category', NULL, '上海市', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (39, 3, 14, 'law_category', NULL, '其他省市', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);

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
  `mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, '15212786971', 'jianye@qq.com', '合肥市检', '检察专员', 1, '2030-12-31 00:00:00', 1, 1, '2019-10-10 09:07:09', 1, '2019-10-14 22:56:35', 1);
INSERT INTO `tb_user` VALUES (3, 'test', 'test', 'e10adc3949ba59abbe56e057f20f883e', 2, '15212786972', 'jdd82sj83@qq.com', '质检局', '质检员', 0, NULL, 1, 1, '2019-10-12 13:56:17', 1, '2019-10-12 13:56:17', 1);
INSERT INTO `tb_user` VALUES (4, '测试', '测试', 'e10adc3949ba59abbe56e057f20f883e', 1, '15212786973', 'dhhwi282347@qq.com', '测试', '测试', 1, '2019-10-12 19:55:21', 1, 1, '2019-10-12 19:55:26', 1, '2019-10-12 19:55:26', 1);
INSERT INTO `tb_user` VALUES (5, '测试1', '测试1', 'e10adc3949ba59abbe56e057f20f883e', 2, '15212786974', 'dhhwi282347@qq.com', '测试1', '测试1', 1, '2019-10-31 22:09:15', 1, 1, '2019-10-12 22:08:06', 1, '2019-10-12 22:08:06', 1);
INSERT INTO `tb_user` VALUES (6, '测试2', '测试2', 'e10adc3949ba59abbe56e057f20f883e', 1, '15212786975', 'dhhwi282347@qq.com', '测试2', '测试2', 1, '2019-10-23 23:59:59', 1, 1, '2019-10-12 22:11:48', 1, '2019-10-13 09:10:36', 0);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
