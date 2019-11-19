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

 Date: 19/11/2019 18:22:52
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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_annex
-- ----------------------------
INSERT INTO `tb_annex` VALUES (5, 3, '激活码_20191104.txt', 'E:/inspection/docs/激活码_20191104.txt', 3);
INSERT INTO `tb_annex` VALUES (6, 3, '叶竹洪博客设计_20191104.xlsx', 'E:/inspection/docs/叶竹洪博客设计_20191104.xlsx', 3);

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
-- Table structure for tb_flight_check
-- ----------------------------
DROP TABLE IF EXISTS `tb_flight_check`;
CREATE TABLE `tb_flight_check`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `business_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `problem` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '缺陷问题',
  `precautions` tinyint(2) NULL DEFAULT NULL COMMENT '处理措施：1-无，2-未明示，3-责令整改，4-限期整改，5-停产整改',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '飞检类型：1-国家飞检，2-地方飞检',
  `publish_unit` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布单位',
  `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
  `is_defect` tinyint(2) NULL DEFAULT NULL COMMENT '是否有缺陷：0-否，1-是',
  `source_link` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源链接',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '飞检数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_flight_check
-- ----------------------------
INSERT INTO `tb_flight_check` VALUES (1, '被飞检企业', '有问题', 2, 2, '飞检单位01', '2019-11-05', 1, NULL, 1, '2019-11-13 20:39:07', 1, '2019-11-13 20:39:07', 1);

-- ----------------------------
-- Table structure for tb_law
-- ----------------------------
DROP TABLE IF EXISTS `tb_law`;
CREATE TABLE `tb_law`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法规文号',
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '法律法规数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_law
-- ----------------------------
INSERT INTO `tb_law` VALUES (1, '法规名称1', NULL, 1, 7, 1, 2, '2019-10-09', '2019-10-31', NULL, '<p>法规内容都是编出来的，随便写写都行，不要太介意，你们看写些什么好呢，我这是纯粹 练打字速度</p>', 1, 1, '2019-10-29 11:07:42', 1, '2019-10-29 11:32:14', 1);
INSERT INTO `tb_law` VALUES (2, '法规名称2', NULL, 3, 12, 2, 1, '2019-10-09', '2019-10-30', NULL, '<p><span style=\"font-size: x-large; font-weight: bold;\">法规内容</span>都是编出来的，<span style=\"font-style: italic;\">随便写写都行</span>，不要太介意，</p><p>你们看写些什么好呢，我这是纯粹 练打字速度</p>', 2, 1, '2019-10-29 11:13:32', 1, '2019-10-29 14:52:42', 1);
INSERT INTO `tb_law` VALUES (3, '交通法', NULL, 1, 7, 2, 1, '2019-11-05', '2019-11-13', NULL, '<p>第一章 总则<br></p><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第一条&nbsp;</td><td><p>　　根据《中华人民共和国道路交通安全法》（以下简称道路交通安全法）的规定，制定本条例。</p></td></tr></tbody></table><hr><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第二条&nbsp;</td><td><p>　　中华人民共和国境内的车辆驾驶人、行人、乘车人以及与道路交通活动有关的单位和个人，应当遵守道路交通安全法和本条例。</p></td></tr></tbody></table><hr><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第三条&nbsp;</td><td><p>　　县级以上地方各级人民政府应当建立、健全道路交通安全工作协调机制，组织有关部门对城市建设项目进行交通影响评价，制定道路交通安全管理规划，确定管理目标，制定实施方案。</p></td></tr></tbody></table><hr><p id=\"150882992842700\">第二章 车辆和驾驶人</p><p id=\"150882992842700\">第一节 机动车</p><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第四条&nbsp;</td><td><p>　　机动车的登记，分为注册登记、变更登记、转移登记、抵押登记和注销登记。</p></td></tr></tbody></table><hr><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第五条&nbsp;</td><td><p>　　初次申领机动车号牌、行驶证的，应当向机动车所有人住所地的公安机关交通管理部门申请注册登记。申请机动车注册登记，应当交验机动车，并提交以下证明、凭证：</p><p>　　（一）机动车所有人的身份证明；</p><p>　　（二）购车发票等机动车来历证明；</p><p>　　（三）机动车整车出厂合格证明或者进口机动车进口凭证；</p><p>　　（四）车辆购置税完税证明或者免税凭证；</p><p>　　（五）机动车第三者责任强制保险凭证；</p><p>　　（六）法律、行政法规规定应当在机动车注册登记时提交的其他证明、凭证。</p><p>　　不属于国务院机动车产品主管部门规定免予安全技术检验的车型的，还应当提供机动车安全技术检验合格证明。</p></td></tr></tbody></table><hr><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第六条&nbsp;</td><td><p>　　已注册登记的机动车有下列情形之一的，机动车所有人应当向登记该机动车的公安机关交通管理部门申请变更登记：</p><p>　　（一）改变机动车车身颜色的；</p><p>　　（二）更换发动机的；</p><p>　　（三）更换车身或者车架的；</p><p>　　（四）因质量有问题，制造厂更换整车的；</p><p>　　（五）营运机动车改为非营运机动车或者非营运机动车改为营运机动车的；</p><p>　　（六）机动车所有人的住所迁出或者迁入公安机关交通管理部门管辖区域的。</p><p>　　申请机动车变更登记，应当提交下列证明、凭证，属于前款第（一）项、第（二）项、第（三）项、第（四）项、第（五）项情形之一的，还应当交验机动车；属于前款第（二）项、第（三）项情形之一的，还应当同时提交机动车安全技术检验合格证明：</p><p>　　（一）机动车所有人的身份证明；</p><p>　　（二）机动车登记证书；</p><p>　　（三）机动车行驶证。</p><p>　　机动车所有人的住所在公安机关交通管理部门管辖区域内迁移、机动车所有人的姓名（单位名称）或者联系方式变更的，应当向登记该机动车的公安机关交通管理部门备案。</p></td></tr></tbody></table><hr><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第七条&nbsp;</td><td><p>　　已注册登记的机动车所有权发生转移的，应当及时办理转移登记。</p><p>　　申请机动车转移登记，当事人应当向登记该机动车的公安机关交通管理部门交验机动车，并提交以下证明、凭证：</p><p>　　（一）当事人的身份证明；</p><p>　　（二）机动车所有权转移的证明、凭证；</p><p>　　（三）机动车登记证书；</p><p>　　（四）机动车行驶证。</p></td></tr></tbody></table><hr><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第八条&nbsp;</td><td><p>　　机动车所有人将机动车作为抵押物抵押的，机动车所有人应当向登记该机动车的公安机关交通管理部门申请抵押登记。</p></td></tr></tbody></table><hr><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第九条&nbsp;</td><td><p>　　已注册登记的机动车达到国家规定的强制报废标准的，公安机关交通管理部门应当在报废期满的2个月前通知机动车所有人办理注销登记。机动车所有人应当在报废期满前将机动车交售给机动车回收企业，由机动车回收企业将报废的机动车登记证书、号牌、行驶证交公安机关交通管理部门注销。机动车所有人逾期不办理注销登记的，公安机关交通管理部门应当公告该机动车登记证书、号牌、行驶证作废。</p><p>　　因机动车灭失申请注销登记的，机动车所有人应当向公安机关交通管理部门提交本人身份证明，交回机动车登记证书。</p></td></tr></tbody></table><hr><table border=\"0\"><tbody><tr valign=\"top\"><td nowrap=\"\">第十条&nbsp;</td><td><p>　　办理机动车登记的申请人提交的证明、凭证齐全、有效的，公安机关交通管理部门应当当场办理登记手续。</p><p>　　人民法院、人民<font color=\"red\">检察</font>院以及行政执法部门依法查封、扣押的机动车，公安机关交通管理部门不予办理机动车登记。</p></td></tr></tbody></table><p><br></p>', 2, 1, '2019-11-04 09:38:44', 1, '2019-11-04 17:11:21', 1);

-- ----------------------------
-- Table structure for tb_law_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_law_type`;
CREATE TABLE `tb_law_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码,关联数据常量表\"',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` int(11) NULL DEFAULT NULL COMMENT '值',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典说明',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '法规二级分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_law_type
-- ----------------------------
INSERT INTO `tb_law_type` VALUES (1, '1574147128849', '美国', 1, 1, '国际分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (2, '1574147128849', '日本', 2, 2, '国际分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (3, '1574147128849', '韩国', 3, 3, '国际分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (4, '1574147128849', '欧盟', 4, 4, '国际分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (5, '1574147128849', '其他', 5, 5, '国际分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (6, '1574147128851', '广东省', 1, 1, '地方分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (7, '1574147128851', '江苏省', 2, 2, '地方分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (8, '1574147128851', '浙江省', 3, 3, '地方分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (9, '1574147128851', '上海市', 4, 4, '地方分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);
INSERT INTO `tb_law_type` VALUES (10, '1574147128851', '其他省市', 5, 5, '地方分类', 1, '2019-11-19 16:45:56', 1, '2019-11-19 16:45:59', 1);

-- ----------------------------
-- Table structure for tb_multi_media
-- ----------------------------
DROP TABLE IF EXISTS `tb_multi_media`;
CREATE TABLE `tb_multi_media`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `business_id` int(11) NULL DEFAULT NULL COMMENT '业务ID',
  `business_type` tinyint(2) NULL DEFAULT NULL COMMENT '业务类型',
  `category` tinyint(2) NULL DEFAULT NULL COMMENT '多媒体类型：1-图片，2-视频',
  `thumbnail` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图/视频展示图名称',
  `media_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原图片/视频存储名称',
  `media_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据类型，例：image/jpeg',
  `size` double NULL DEFAULT NULL COMMENT '大小(KB)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多媒体数据表' ROW_FORMAT = Dynamic;

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
-- Table structure for tb_system_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_data`;
CREATE TABLE `tb_system_data`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典说明',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_code`(`code`) USING BTREE COMMENT '编码唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_system_data
-- ----------------------------
INSERT INTO `tb_system_data` VALUES (1, '01', '皮肤用化妆品', 'ZJCPLX', 1, '质检产品分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (2, '02', '毛发用化妆品', 'ZJCPLX', 2, '质检产品分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (3, '03', '指（趾）甲用化妆品', 'ZJCPLX', 3, '质检产品分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (4, '04', '口唇用化妆品', 'ZJCPLX', 4, '质检产品分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (5, '1574147128833', '国际标准', 'BZYJFL', 1, '标准一级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (6, '1574147128834', '国家标准', 'BZYJFL', 2, '标准一级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (7, '1574147128835', '行业标准', 'BZYJFL', 3, '标准一级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (8, '1574147128836', '地方标准', 'BZYJFL', 4, '标准一级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (9, '1574147128837', '团体标准', 'BZYJFL', 5, '标准一级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (10, '1574147128838', '其他标准', 'BZYJFL', 6, '标准一级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (11, '1574147128839', '基础标准', 'BZEJFL', 1, '标准二级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (12, '1574147128840', '产品标准', 'BZEJFL', 2, '标准二级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (13, '1574147128841', '方法标准', 'BZEJFL', 3, '标准二级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (14, '1574147128842', '安全标准', 'BZEJFL', 4, '标准二级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (15, '1574147128843', '卫生标准', 'BZEJFL', 5, '标准二级分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (16, '1574147128844', '单位1', 'BZFBDW', 1, '标准发布单位', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (17, '1574147128845', '单位2', 'BZFBDW', 2, '标准发布单位', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (18, '1574147128846', '单位3', 'BZFBDW', 3, '标准发布单位', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (19, '1574147128847', '单位4', 'BZFBDW', 4, '标准发布单位', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (20, '1574147128848', '单位5', 'BZFBDW', 5, '标准发布单位', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (21, '1574147128849', '国际法规', 'FGFL', 1, '法规分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (22, '1574147128850', '国家法规', 'FGFL', 2, '法规分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (23, '1574147128851', '地方法规', 'FGFL', 3, '法规分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (24, '1574147128852', '其他法规', 'FGFL', 4, '法规分类', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (25, '1574147128853', '发布机构1', 'FGFBDW', 1, '法规发布机构', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (26, '1574147128854', '发布机构2', 'FGFBDW', 2, '法规发布机构', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (27, '1574147128855', '法规来源1', 'FGLY', 1, '法规来源', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (28, '1574147128856', '法规来源2', 'FGLY', 2, '法规来源', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (29, '1574147128857', '法规来源3', 'FGLY', 3, '法规来源', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (30, '1574147128858', '法规来源4', 'FGLY', 4, '法规来源', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (31, '1574147128859', '法规文章', 'WZLX', 1, '文章类型', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);
INSERT INTO `tb_system_data` VALUES (32, '1574147128860', '质检文章', 'WZLX', 2, '文章类型', 1, '2019-11-19 15:05:52', 1, '2019-11-19 15:05:55', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_system_data_type
-- ----------------------------
INSERT INTO `tb_system_data_type` VALUES (1, 1, 1, 'spot_check', 'product_type', '皮肤用化妆品', NULL, NULL, 1, '2019-10-16 16:01:23', 1, '2019-10-16 16:01:28', 1);
INSERT INTO `tb_system_data_type` VALUES (2, 1, 2, 'spot_check', 'product_type', '毛发用化妆品', NULL, NULL, 1, '2019-10-16 16:01:47', 1, '2019-10-16 16:01:52', 1);
INSERT INTO `tb_system_data_type` VALUES (3, 1, 3, 'spot_check', 'product_type', '指（趾）甲用化妆品', NULL, NULL, 1, '2019-10-16 16:02:08', 1, '2019-10-16 16:02:10', 1);
INSERT INTO `tb_system_data_type` VALUES (4, 1, 4, 'spot_check', 'product_type', '口唇用化妆品', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (5, 1, 1, 'spot_check', 'institution', '宁波市海曙区市场监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (6, 1, 2, 'spot_check', 'institution', '北京市药品监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (7, 1, 3, 'spot_check', 'institution', '山西省药品监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (8, 1, 4, 'spot_check', 'institution', '国家药品监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (9, 1, 5, 'spot_check', 'institution', '江西省药品监督管理局', NULL, NULL, 1, '2019-10-16 17:29:43', 1, '2019-10-16 17:29:46', 1);
INSERT INTO `tb_system_data_type` VALUES (10, 2, 1, 'criterion', 'criterion_category', '国际标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (11, 2, 2, 'criterion', 'criterion_category', '国家标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (12, 2, 3, 'criterion', 'criterion_category', '行业标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (13, 2, 4, 'criterion', 'criterion_category', '地方标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (14, 2, 5, 'criterion', 'criterion_category', '团体标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (15, 2, 6, 'criterion', 'criterion_category', '其他标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (16, 2, 1, 'criterion', 'criterion_type', '基础标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (17, 2, 2, 'criterion', 'criterion_type', '产品标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (18, 2, 3, 'criterion', 'criterion_type', '方法标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (19, 2, 4, 'criterion', 'criterion_type', '安全标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (20, 2, 5, 'criterion', 'criterion_type', '卫生标准', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (21, 2, 1, 'criterion', 'criterion_publish_unit', '单位1', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (22, 2, 2, 'criterion', 'criterion_publish_unit', '单位2', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (23, 2, 3, 'criterion', 'criterion_publish_unit', '单位3', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (24, 2, 4, 'criterion', 'criterion_publish_unit', '单位4', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (25, 2, 5, 'criterion', 'criterion_publish_unit', '单位5', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (26, 3, 1, 'law', 'law_category', '国际法规', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (27, 3, 2, 'law', 'law_category', '国家法规', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (28, 3, 3, 'law', 'law_category', '地方法规', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (29, 3, 4, 'law', 'law_category', '其他法规', NULL, NULL, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (30, 3, 5, 'law', 'law_type', '美国', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (31, 3, 6, 'law', 'law_type', '日本', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (32, 3, 7, 'law', 'law_type', '韩国', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (33, 3, 8, 'law', 'law_type', '欧盟', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (34, 3, 9, 'law', 'law_type', '其他', NULL, 1, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (35, 3, 10, 'law', 'law_type', '广东省', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (36, 3, 11, 'law', 'law_type', '江苏省', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (37, 3, 12, 'law', 'law_type', '浙江省', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (38, 3, 13, 'law', 'law_type', '上海市', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (39, 3, 14, 'law', 'law_type', '其他省市', NULL, 3, 1, '2019-10-16 16:02:29', 1, '2019-10-16 16:02:33', 1);
INSERT INTO `tb_system_data_type` VALUES (40, 3, 1, 'law', 'law_publish_unit', '发布机构1', NULL, NULL, 1, '2019-10-29 10:29:07', 1, '2019-10-29 10:29:11', 1);
INSERT INTO `tb_system_data_type` VALUES (41, 3, 2, 'law', 'law_publish_unit', '发布机构2', NULL, NULL, 1, '2019-10-29 10:29:33', 1, '2019-10-29 10:29:37', 1);
INSERT INTO `tb_system_data_type` VALUES (42, 3, 1, 'law', 'law_source', '来源1', NULL, NULL, 1, '2019-10-29 10:30:08', 1, '2019-10-29 10:30:11', 1);
INSERT INTO `tb_system_data_type` VALUES (43, 3, 2, 'law', 'law_source', '来源2', NULL, NULL, 1, '2019-10-29 10:30:32', 1, '2019-10-29 10:30:35', 1);
INSERT INTO `tb_system_data_type` VALUES (44, 3, 3, 'law', 'law_source', '来源3', '来源3', NULL, 1, '2019-11-07 20:20:26', 1, '2019-11-07 20:20:26', 1);
INSERT INTO `tb_system_data_type` VALUES (45, 3, 4, 'law', 'law_source', '来源4', '来源4', NULL, 1, '2019-11-07 20:23:11', 1, '2019-11-07 20:23:11', 1);

-- ----------------------------
-- Table structure for tb_system_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_type`;
CREATE TABLE `tb_system_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典说明',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `usable` tinyint(2) NULL DEFAULT 1 COMMENT '数据是否有效：0-无效，1-有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_system_type
-- ----------------------------
INSERT INTO `tb_system_type` VALUES (1, 'WZLX', '文章类型', '文章类型', 1, '2019-11-19 14:54:46', 1, '2019-11-19 14:54:51', 1);
INSERT INTO `tb_system_type` VALUES (2, 'ZJCPLX', '质检产品类型', '质检产品类型', 1, '2019-11-19 14:55:53', 1, '2019-11-19 14:55:56', 1);
INSERT INTO `tb_system_type` VALUES (3, 'BZYJFL', '标准一级分类', '标准一级分类', 1, '2019-11-19 14:54:46', 1, '2019-11-19 14:54:51', 1);
INSERT INTO `tb_system_type` VALUES (4, 'BZEJFL', '标准二级分类', '标准二级分类', 1, '2019-11-19 14:54:46', 1, '2019-11-19 14:54:51', 1);
INSERT INTO `tb_system_type` VALUES (5, 'BZFBDW', '标准发布单位', '标准发布单位', 1, '2019-11-19 14:54:46', 1, '2019-11-19 14:54:51', 1);
INSERT INTO `tb_system_type` VALUES (6, 'FGFL', '法规分类', '法规分类', 1, '2019-11-19 14:54:46', 1, '2019-11-19 14:54:51', 1);
INSERT INTO `tb_system_type` VALUES (7, 'FGFBDW', '法规发布机构', '法规发布机构', 1, '2019-11-19 14:54:46', 1, '2019-11-19 14:54:51', 1);
INSERT INTO `tb_system_type` VALUES (8, 'FGLY', '法规来源', '法规来源', 1, '2019-11-19 14:54:46', 1, '2019-11-19 14:54:51', 1);

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
