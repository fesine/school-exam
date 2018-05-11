/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : school-exam

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2018-05-11 23:54:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_classroom
-- ----------------------------
DROP TABLE IF EXISTS `t_classroom`;
CREATE TABLE `t_classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `classroom_name` varchar(20) NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`classroom_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='班级信息表';

-- ----------------------------
-- Records of t_classroom
-- ----------------------------
INSERT INTO `t_classroom` VALUES ('1', '1班');
INSERT INTO `t_classroom` VALUES ('2', '2班');
INSERT INTO `t_classroom` VALUES ('3', '3班');
INSERT INTO `t_classroom` VALUES ('4', '4班');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grade_id` int(11) NOT NULL COMMENT '年级号',
  `course_name` varchar(20) NOT NULL COMMENT '课程名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='课程信息表';

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('1', '1', '语文');
INSERT INTO `t_course` VALUES ('2', '1', '数学');
INSERT INTO `t_course` VALUES ('3', '1', '英语');
INSERT INTO `t_course` VALUES ('4', '2', '英语');
INSERT INTO `t_course` VALUES ('5', '2', '数学');
INSERT INTO `t_course` VALUES ('6', '2', '语文');
INSERT INTO `t_course` VALUES ('7', '3', '语文');
INSERT INTO `t_course` VALUES ('8', '3', '数学');
INSERT INTO `t_course` VALUES ('9', '3', '英语');
INSERT INTO `t_course` VALUES ('10', '2', '物理');
INSERT INTO `t_course` VALUES ('11', '3', '化学');
INSERT INTO `t_course` VALUES ('12', '3', '物理');

-- ----------------------------
-- Table structure for t_course_point
-- ----------------------------
DROP TABLE IF EXISTS `t_course_point`;
CREATE TABLE `t_course_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(11) NOT NULL COMMENT '课程号',
  `course_point` varchar(400) NOT NULL COMMENT '知识点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='课程知识点信息表';

-- ----------------------------
-- Records of t_course_point
-- ----------------------------
INSERT INTO `t_course_point` VALUES ('4', '1', '背诵古诗词');
INSERT INTO `t_course_point` VALUES ('5', '10', '大气压计算公式');
INSERT INTO `t_course_point` VALUES ('6', '11', '学习二氧化碳的特性');

-- ----------------------------
-- Table structure for t_exam
-- ----------------------------
DROP TABLE IF EXISTS `t_exam`;
CREATE TABLE `t_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exam_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '考试类型 1:闭卷 2:开卷',
  `grade_id` int(11) NOT NULL COMMENT '考试年级id',
  `course_id` int(11) NOT NULL COMMENT '考试课程',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='考试信息表';

-- ----------------------------
-- Records of t_exam
-- ----------------------------
INSERT INTO `t_exam` VALUES ('4', '1', '1', '2', '2018-05-02 09:00:00', '2018-05-02 11:00:00');
INSERT INTO `t_exam` VALUES ('5', '2', '1', '3', '2018-05-02 14:00:00', '2018-05-02 16:00:00');
INSERT INTO `t_exam` VALUES ('6', '1', '1', '1', '2018-05-01 09:30:00', '2018-05-01 11:00:00');
INSERT INTO `t_exam` VALUES ('7', '1', '2', '5', '2018-05-01 09:00:00', '2018-05-01 10:30:00');
INSERT INTO `t_exam` VALUES ('8', '1', '2', '6', '2018-05-01 14:00:00', '2018-05-01 16:00:00');
INSERT INTO `t_exam` VALUES ('9', '1', '2', '4', '2018-05-02 09:00:00', '2018-05-02 11:00:00');
INSERT INTO `t_exam` VALUES ('10', '2', '2', '10', '2018-05-02 14:00:00', '2018-05-02 16:00:00');
INSERT INTO `t_exam` VALUES ('11', '1', '3', '7', '2018-05-01 09:00:00', '2018-05-01 11:00:00');
INSERT INTO `t_exam` VALUES ('12', '1', '3', '8', '2018-05-01 14:00:00', '2018-05-01 16:00:00');
INSERT INTO `t_exam` VALUES ('13', '2', '3', '9', '2018-05-02 09:00:00', '2018-05-02 11:00:00');
INSERT INTO `t_exam` VALUES ('14', '1', '3', '12', '2018-05-02 14:00:00', '2018-05-02 16:00:00');
INSERT INTO `t_exam` VALUES ('15', '1', '3', '11', '2018-05-03 08:30:00', '2018-05-03 11:00:00');

-- ----------------------------
-- Table structure for t_exam_score
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_score`;
CREATE TABLE `t_exam_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stu_no` varchar(20) NOT NULL COMMENT '学号',
  `exam_id` int(11) NOT NULL COMMENT '考试编号',
  `score` int(11) NOT NULL COMMENT '成绩',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_1` (`stu_no`,`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='考试成绩表';

-- ----------------------------
-- Records of t_exam_score
-- ----------------------------
INSERT INTO `t_exam_score` VALUES ('1', '2018000001', '4', '80');
INSERT INTO `t_exam_score` VALUES ('2', '2018000002', '4', '95');
INSERT INTO `t_exam_score` VALUES ('3', '2018000003', '4', '58');
INSERT INTO `t_exam_score` VALUES ('4', '2018000001', '5', '99');
INSERT INTO `t_exam_score` VALUES ('5', '2018000002', '5', '70');
INSERT INTO `t_exam_score` VALUES ('6', '2018000003', '5', '59');
INSERT INTO `t_exam_score` VALUES ('9', '2018000001', '6', '91');
INSERT INTO `t_exam_score` VALUES ('10', '2018000002', '6', '70');
INSERT INTO `t_exam_score` VALUES ('11', '2018000003', '6', '55');
INSERT INTO `t_exam_score` VALUES ('12', '2018000004', '5', '65');
INSERT INTO `t_exam_score` VALUES ('13', '2018000005', '5', '84');
INSERT INTO `t_exam_score` VALUES ('14', '2018000006', '5', '78');
INSERT INTO `t_exam_score` VALUES ('15', '2018000007', '5', '98');
INSERT INTO `t_exam_score` VALUES ('16', '2018000008', '5', '83');
INSERT INTO `t_exam_score` VALUES ('17', '2018000009', '5', '45');
INSERT INTO `t_exam_score` VALUES ('18', '2018000010', '5', '100');
INSERT INTO `t_exam_score` VALUES ('19', '2018000011', '5', '70');
INSERT INTO `t_exam_score` VALUES ('20', '2018000012', '5', '84');
INSERT INTO `t_exam_score` VALUES ('21', '2018000013', '5', '66');
INSERT INTO `t_exam_score` VALUES ('22', '2018000014', '5', '97');
INSERT INTO `t_exam_score` VALUES ('23', '2018000015', '5', '90');
INSERT INTO `t_exam_score` VALUES ('24', '2018000016', '5', '63');
INSERT INTO `t_exam_score` VALUES ('25', '2018000017', '5', '43');
INSERT INTO `t_exam_score` VALUES ('26', '2018000018', '5', '71');
INSERT INTO `t_exam_score` VALUES ('27', '2018000019', '5', '68');
INSERT INTO `t_exam_score` VALUES ('28', '2018000020', '5', '82');
INSERT INTO `t_exam_score` VALUES ('29', '2018000004', '4', '91');
INSERT INTO `t_exam_score` VALUES ('30', '2018000005', '4', '79');
INSERT INTO `t_exam_score` VALUES ('31', '2018000006', '4', '48');
INSERT INTO `t_exam_score` VALUES ('32', '2018000007', '4', '85');
INSERT INTO `t_exam_score` VALUES ('33', '2018000008', '4', '66');
INSERT INTO `t_exam_score` VALUES ('34', '2018000009', '4', '91');
INSERT INTO `t_exam_score` VALUES ('35', '2018000010', '4', '87');
INSERT INTO `t_exam_score` VALUES ('36', '2018000011', '4', '78');
INSERT INTO `t_exam_score` VALUES ('37', '2018000012', '4', '86');
INSERT INTO `t_exam_score` VALUES ('38', '2018000013', '4', '92');
INSERT INTO `t_exam_score` VALUES ('39', '2018000014', '4', '88');
INSERT INTO `t_exam_score` VALUES ('40', '2018000015', '4', '74');
INSERT INTO `t_exam_score` VALUES ('41', '2018000016', '4', '67');
INSERT INTO `t_exam_score` VALUES ('42', '2018000017', '4', '96');
INSERT INTO `t_exam_score` VALUES ('43', '2018000018', '4', '73');
INSERT INTO `t_exam_score` VALUES ('44', '2018000019', '4', '93');
INSERT INTO `t_exam_score` VALUES ('45', '2018000020', '4', '60');
INSERT INTO `t_exam_score` VALUES ('46', '2018000004', '6', '74');
INSERT INTO `t_exam_score` VALUES ('47', '2018000005', '6', '80');
INSERT INTO `t_exam_score` VALUES ('48', '2018000006', '6', '87');
INSERT INTO `t_exam_score` VALUES ('49', '2018000007', '6', '70');
INSERT INTO `t_exam_score` VALUES ('50', '2018000008', '6', '97');
INSERT INTO `t_exam_score` VALUES ('51', '2018000009', '6', '57');
INSERT INTO `t_exam_score` VALUES ('52', '2018000010', '6', '89');
INSERT INTO `t_exam_score` VALUES ('53', '2018000011', '6', '79');
INSERT INTO `t_exam_score` VALUES ('54', '2018000012', '6', '84');
INSERT INTO `t_exam_score` VALUES ('55', '2018000013', '6', '38');
INSERT INTO `t_exam_score` VALUES ('56', '2018000014', '6', '93');
INSERT INTO `t_exam_score` VALUES ('57', '2018000015', '6', '98');
INSERT INTO `t_exam_score` VALUES ('58', '2018000016', '6', '96');
INSERT INTO `t_exam_score` VALUES ('59', '2018000017', '6', '71');
INSERT INTO `t_exam_score` VALUES ('60', '2018000018', '6', '59');
INSERT INTO `t_exam_score` VALUES ('61', '2018000019', '6', '67');
INSERT INTO `t_exam_score` VALUES ('62', '2018000020', '6', '76');
INSERT INTO `t_exam_score` VALUES ('63', '2018000033', '13', '87');
INSERT INTO `t_exam_score` VALUES ('64', '2018000031', '13', '90');
INSERT INTO `t_exam_score` VALUES ('65', '2018000035', '13', '67');

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grade_name` varchar(20) NOT NULL COMMENT '年级名称',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`grade_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='年级信息表';

-- ----------------------------
-- Records of t_grade
-- ----------------------------
INSERT INTO `t_grade` VALUES ('1', '初一');
INSERT INTO `t_grade` VALUES ('3', '初三');
INSERT INTO `t_grade` VALUES ('2', '初二');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stu_no` varchar(20) NOT NULL COMMENT '学号',
  `stu_name` varchar(20) NOT NULL COMMENT '学生姓名',
  `stu_sex` tinyint(4) NOT NULL COMMENT '性别 0:未知 1:男 2:女',
  `stu_birthday` date DEFAULT NULL COMMENT '出生日期 1990-01-10',
  `stu_cell` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `stu_address` varchar(100) DEFAULT NULL COMMENT '地址信息',
  `stu_email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `grade_id` int(11) NOT NULL COMMENT '年级号',
  `classroom_id` int(11) NOT NULL COMMENT '班级号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_1` (`stu_no`),
  KEY `Index_2` (`stu_cell`),
  KEY `Index_3` (`stu_email`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='学生信息表';

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('3', '2018000001', '刘备', '1', '2018-05-01', '13888889999', '四川成都11', '13888889999@qq.com', '1', '1', '2018-05-09 21:12:46', '2018-05-11 23:36:32');
INSERT INTO `t_student` VALUES ('4', '2018000002', '张飞', '1', '2018-05-01', '13820180501', '四川成都', '13820180501@qq.com', '1', '1', '2018-05-10 13:49:23', '2018-05-10 13:49:23');
INSERT INTO `t_student` VALUES ('5', '2018000003', '关羽', '1', '2018-04-02', '13920180402', '浙江杭州', '13920180402@qq.com', '1', '1', '2018-05-10 13:50:13', '2018-05-10 13:50:13');
INSERT INTO `t_student` VALUES ('6', '2018000004', '李丽颖', '2', '2004-01-01', '13918000004', '请输入地址', '18000004@163.com', '1', '1', '2018-05-10 21:27:27', '2018-05-10 21:29:02');
INSERT INTO `t_student` VALUES ('7', '2018000005', '李启菁', '2', '2004-02-01', '13918000005', '请输入地址', '18000005@163.com', '1', '1', '2018-05-10 21:27:45', '2018-05-10 21:29:45');
INSERT INTO `t_student` VALUES ('8', '2018000006', '李金泽', '1', '2004-02-15', '13620040215', '请输入地址', '13620040215@139.com', '1', '1', '2018-05-10 21:32:11', '2018-05-10 21:32:11');
INSERT INTO `t_student` VALUES ('9', '2018000007', '刘邦', '1', '2004-02-16', '13620040216', '请输入地址', '13620040216@139.com', '1', '1', '2018-05-10 21:33:46', '2018-05-10 21:33:46');
INSERT INTO `t_student` VALUES ('10', '2018000008', '张良', '1', '2004-02-17', '13620040217', '请输入地址', '13620040217@139.com', '1', '1', '2018-05-10 21:34:05', '2018-05-10 21:34:05');
INSERT INTO `t_student` VALUES ('11', '2018000009', '项伯', '1', '2004-02-18', '13620040218', '请输入地址', '13620040218@139.com', '1', '1', '2018-05-10 21:34:24', '2018-05-10 21:34:24');
INSERT INTO `t_student` VALUES ('12', '2018000010', '貂蝉', '2', '2004-02-19', '13620040219', '请输入地址', '13620040219@139.com', '1', '1', '2018-05-10 21:34:51', '2018-05-10 21:34:51');
INSERT INTO `t_student` VALUES ('13', '2018000011', '李广', '1', '2004-03-19', '13620040319', '请输入地址', '13620040319@139.com', '1', '2', '2018-05-10 21:38:54', '2018-05-10 21:38:54');
INSERT INTO `t_student` VALUES ('14', '2018000012', '卫青', '1', '2004-03-15', '13620040315', '请输入地址', '13620040315@139.com', '1', '2', '2018-05-10 21:40:44', '2018-05-10 21:40:44');
INSERT INTO `t_student` VALUES ('15', '2018000013', '马腾', '1', '2004-03-16', '13620040316', '请输入地址', '13620040316@139.com', '1', '2', '2018-05-10 21:41:06', '2018-05-10 21:41:06');
INSERT INTO `t_student` VALUES ('16', '2018000014', '王昭君', '2', '2004-03-17', '13620040317', '请输入地址', '13620040317@139.com', '1', '2', '2018-05-10 21:41:34', '2018-05-10 21:41:34');
INSERT INTO `t_student` VALUES ('17', '2018000015', '李清照', '2', '2004-03-18', '13620040318', '请输入地址', '13620040318@139.com', '1', '2', '2018-05-10 21:42:13', '2018-05-10 21:42:13');
INSERT INTO `t_student` VALUES ('18', '2018000016', ' 欧阳修', '1', '2004-03-19', '13620040319', '请输入地址', '13620040319@139.com', '1', '2', '2018-05-10 21:42:33', '2018-05-10 21:42:33');
INSERT INTO `t_student` VALUES ('19', '2018000017', '葛洪', '1', '2004-03-20', '13620040320', '请输入地址', '13620040320@139.com', '1', '2', '2018-05-10 21:42:57', '2018-05-10 21:42:57');
INSERT INTO `t_student` VALUES ('20', '2018000018', '仇英', '2', '2004-03-21', '13620040321', '请输入地址', '13620040321@139.com', '1', '2', '2018-05-10 21:43:36', '2018-05-10 21:43:36');
INSERT INTO `t_student` VALUES ('21', '2018000019', '慈安', '2', '2004-03-22', '13620040322', '请输入地址', '13620040322@139.com', '1', '2', '2018-05-10 21:44:00', '2018-05-10 21:44:00');
INSERT INTO `t_student` VALUES ('22', '2018000020', '张廷玉', '1', '2004-03-23', '13620040323', '请输入地址', '13620040323@139.com', '1', '2', '2018-05-10 21:44:20', '2018-05-10 21:44:20');
INSERT INTO `t_student` VALUES ('23', '2018000021', '毕升', '1', '2003-01-13', '13620030113', '请输入地址', '13620030113@139.com', '2', '3', '2018-05-10 21:45:43', '2018-05-10 21:45:43');
INSERT INTO `t_student` VALUES ('24', '2018000022', '蒲松龄', '1', '2003-01-14', '13620030114', '请输入地址', '13620030114@139.com', '2', '3', '2018-05-10 21:46:00', '2018-05-10 21:46:00');
INSERT INTO `t_student` VALUES ('25', '2018000023', '罗贯中', '1', '2003-01-15', '13620030115', '请输入地址', '13620030115@139.com', '2', '3', '2018-05-10 21:46:14', '2018-05-10 21:46:14');
INSERT INTO `t_student` VALUES ('26', '2018000024', '孟郊', '2', '2003-01-16', '13620030116', '请输入地址', '13620030116@139.com', '2', '3', '2018-05-10 21:46:36', '2018-05-10 21:46:36');
INSERT INTO `t_student` VALUES ('27', '2018000025', '吴文英', '2', '2003-01-17', '13620030117', '请输入地址', '13620030117@139.com', '2', '3', '2018-05-10 21:46:57', '2018-05-10 21:46:57');
INSERT INTO `t_student` VALUES ('28', '2018000026', '皇甫冉', '2', '2003-02-17', '13620030217', '请输入地址', '13620030217@139.com', '2', '4', '2018-05-10 21:47:32', '2018-05-10 21:47:32');
INSERT INTO `t_student` VALUES ('29', '2018000027', '张择端', '1', '2003-02-18', '13620030218', '请输入地址', '13620030218@139.com', '2', '4', '2018-05-10 21:47:55', '2018-05-10 21:47:55');
INSERT INTO `t_student` VALUES ('30', '2018000028', '中康', '1', '2003-03-18', '13620030318', '请输入地址', '13620030318@139.com', '2', '4', '2018-05-10 21:48:18', '2018-05-10 21:48:18');
INSERT INTO `t_student` VALUES ('31', '2018000029', '女娲', '2', '2003-03-28', '13620030328', '请输入地址', '13620030328@139.com', '2', '4', '2018-05-10 21:48:43', '2018-05-10 21:48:43');
INSERT INTO `t_student` VALUES ('32', '2018000030', '范增', '1', '2003-03-08', '13620030308', '请输入地址', '13620030308@139.com', '2', '4', '2018-05-10 21:49:07', '2018-05-10 21:49:07');
INSERT INTO `t_student` VALUES ('33', '2018000031', '周亚夫', '1', '2002-03-08', '13620020308', '请输入地址', '13620020308@139.com', '3', '2', '2018-05-10 21:49:55', '2018-05-10 21:49:55');
INSERT INTO `t_student` VALUES ('34', '2018000032', '赵芸', '2', '2002-04-08', '13620020408', '请输入地址', '13620020408@139.com', '3', '2', '2018-05-10 21:50:23', '2018-05-10 21:50:23');
INSERT INTO `t_student` VALUES ('35', '2018000033', '乔玄', '2', '2002-04-05', '13620020405', '请输入地址', '13620020405@139.com', '3', '2', '2018-05-10 21:50:49', '2018-05-10 21:50:49');
INSERT INTO `t_student` VALUES ('36', '2018000034', '刘玲', '2', '2002-04-15', '13620020415', '请输入地址', '13620020415@139.com', '3', '2', '2018-05-10 21:51:06', '2018-05-10 21:51:06');
INSERT INTO `t_student` VALUES ('37', '2018000035', '向秀', '2', '2002-04-25', '13620020425', '请输入地址', '13620020425@139.com', '3', '2', '2018-05-10 21:51:24', '2018-05-10 21:51:24');
INSERT INTO `t_student` VALUES ('38', '2018000036', '马超', '1', '2002-05-25', '13620020525', '请输入地址', '13620020525@139.com', '3', '3', '2018-05-10 21:51:47', '2018-05-10 21:51:47');
INSERT INTO `t_student` VALUES ('39', '2018000037', '关羽', '1', '2002-05-26', '13620020526', '请输入地址', '13620020526@139.com', '3', '3', '2018-05-10 21:52:07', '2018-05-10 21:52:07');
INSERT INTO `t_student` VALUES ('40', '2018000038', '杨玉环', '2', '2002-05-16', '13620020516', '请输入地址', '13620020516@139.com', '3', '3', '2018-05-10 21:52:37', '2018-05-10 21:52:37');
INSERT INTO `t_student` VALUES ('41', '2018000039', '夫差', '1', '2002-06-16', '13620020616', '请输入地址', '13620020616@139.com', '3', '3', '2018-05-10 21:52:58', '2018-05-10 21:52:58');
INSERT INTO `t_student` VALUES ('42', '2018000040', '王维', '1', '2002-06-18', '13620020618', '请输入地址', '13620020618@139.com', '3', '3', '2018-05-10 21:53:14', '2018-05-10 21:53:14');

-- ----------------------------
-- Table structure for t_sys_code
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_code`;
CREATE TABLE `t_sys_code` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code_id` varchar(32) NOT NULL COMMENT '代码编号',
  `value` varchar(255) DEFAULT NULL COMMENT '代码值',
  `text` varchar(32) NOT NULL COMMENT '代码文本',
  `leaf` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否是叶子',
  `parent_id` int(5) NOT NULL DEFAULT '0' COMMENT '父节点编号 默认0 根结点',
  `grade` int(1) DEFAULT '0' COMMENT '权限级别 0登录权限，1管理员权限，2系统管理员权限',
  `order_no` int(5) NOT NULL DEFAULT '100' COMMENT '排序字段',
  `remark1` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_code_id` (`code_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统代码表';

-- ----------------------------
-- Records of t_sys_code
-- ----------------------------
INSERT INTO `t_sys_code` VALUES ('1', 'workTimeGroup', '', '学习时间', '0', '0', '2', '1', null);
INSERT INTO `t_sys_code` VALUES ('2', 'workTimeSummer', '08:30-17:30', '夏令制', '1', '1', '1', '1', '');
INSERT INTO `t_sys_code` VALUES ('3', 'workTimeWinter', '08:30-17:00', '冬令制', '1', '1', '1', '2', '');
INSERT INTO `t_sys_code` VALUES ('4', 'gradeGroup', '', '权限代码', '0', '0', '0', '3', null);
INSERT INTO `t_sys_code` VALUES ('5', 'gradeLow', '0', '管理员', '1', '4', '0', '1', null);
INSERT INTO `t_sys_code` VALUES ('6', 'gradeMiddle', '1', '权限管理员', '1', '4', '1', '2', null);
INSERT INTO `t_sys_code` VALUES ('7', 'gradeHigh', '2', '超级管理员', '1', '4', '2', '3', null);

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单编号',
  `text` varchar(32) NOT NULL COMMENT '菜单文本',
  `leaf` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否是叶子',
  `parent_id` int(5) NOT NULL DEFAULT '0' COMMENT '父节点编号 默认0 根结点',
  `order_no` int(5) NOT NULL DEFAULT '100' COMMENT '排序字段，默认100',
  `grade` int(1) DEFAULT '0' COMMENT '权限级别 0登录权限，1管理员权限，2系统管理员权限',
  `fun_view_xtype` varchar(100) DEFAULT NULL COMMENT '视图别名',
  `fun_controller` varchar(255) DEFAULT NULL COMMENT '控制器类名',
  `fun_view_name` varchar(255) DEFAULT NULL COMMENT '视图类名',
  `icon_cls` varchar(32) DEFAULT NULL COMMENT '图标',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转链接',
  `remark1` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  `remark2` varchar(255) DEFAULT NULL COMMENT '备用字段2',
  `remark3` varchar(255) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='系统菜单信息表';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', 'sysMenuGroup', '系统设置', '0', '0', '998', '0', '', '', '', '', 'www.baidu.com', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('2', 'sysManager', '个人信息', '1', '1', '2', '0', 'sysLayout', 'core.system.controller.SysController', 'core.system.view.SysLayout', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('3', 'authManager', '用户管理', '1', '1', '3', '1', 'authLayout', 'core.system.controller.AuthController', 'core.system.view.AuthLayout', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('4', 'menuManager', '栏目管理', '1', '1', '4', '2', 'menuLayout', 'core.system.controller.MenuController', 'core.system.view.MenuLayout', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('5', 'examGroup', '考试管理', '0', '0', '500', '0', '', '', '', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('6', 'coursePointpManager', '知识点管理', '1', '5', '8', '0', 'coursePointLayout', 'core.exam.controller.CoursePointController', 'core.exam.view.CoursePointLayout', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('7', 'examManager', '考试管理', '1', '5', '7', '0', 'examLayout', 'core.exam.controller.ExamController', 'core.exam.view.ExamLayout', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('8', 'attMenuGroup', '学生管理', '0', '0', '100', '0', '', '', '', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('9', 'gradeManager', '年级管理', '1', '14', '104', '0', 'gradeLayout', 'core.school.controller.GradeController', 'core.school.view.GradeLayout', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('10', 'ExamScoreManager', '成绩管理', '1', '5', '102', '0', 'examScoreLayout', 'core.student.controller.ExamScoreController', 'core.student.view.ExamScoreLayout', '', '', '', '', '');
INSERT INTO `t_sys_menu` VALUES ('11', 'studentScoreManager', '学生成绩', '1', '8', '103', '0', 'studentScoreLayout', 'core.student.controller.StudentScoreController', 'core.student.view.StudentScoreLayout', '', '', '', '', '');
INSERT INTO `t_sys_menu` VALUES ('12', 'classroomManager', '班级管理', '1', '14', '101', '0', 'classroomLayout', 'core.school.controller.ClassroomController', 'core.school.view.ClassroomLayout', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('13', 'codeManager', '代码管理', '1', '1', '5', '2', 'codeLayout', 'core.system.controller.CodeController', 'core.system.view.CodeLayout', '', '', '', '', '');
INSERT INTO `t_sys_menu` VALUES ('14', 'schoolGroup', '学校管理', '1', '0', '800', '0', '', '', '', '', '', null, null, null);
INSERT INTO `t_sys_menu` VALUES ('15', 'courseManager', '课程管理', '1', '14', '102', '0', 'courseLayout', 'core.school.controller.CourseController', 'core.school.view.CourseLayout', '', '', '', '', '');
INSERT INTO `t_sys_menu` VALUES ('16', 'studentManager', '学生信息', '1', '8', '101', '0', 'studentLayout', 'core.student.controller.StudentController', 'core.student.view.StudentLayout', '', '', '', '', '');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `nickName` varchar(20) NOT NULL COMMENT '昵称',
  `grade` tinyint(4) NOT NULL DEFAULT '0' COMMENT '权限类型  0: 普通管理员 1:权限管理员  2:超级管理员',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `cell` varchar(11) NOT NULL COMMENT '手机号码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_1` (`username`),
  UNIQUE KEY `Index_2` (`email`),
  UNIQUE KEY `Index_3` (`cell`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='管理员信息表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'superAdmin', 'd4ca6c581dbea1692583fb7cf5099781', '超级管理员', '2', 'admin@admin.com', '13800138770', '2018-05-08 23:32:26', '2018-05-11 23:44:23');
INSERT INTO `t_sys_user` VALUES ('2', 'admin', '461c6c3e5797f533c42e3aa24b7e0077', '管理员', '1', 'dap@admin.com', '13812341234', '2018-05-09 00:54:21', '2018-05-11 17:21:08');
INSERT INTO `t_sys_user` VALUES ('3', 'teacher', '461c6c3e5797f533c42e3aa24b7e0077', '李老师', '0', '100@admin.com', '18810001011', '2018-05-09 15:55:03', '2018-05-11 23:43:37');
INSERT INTO `t_sys_user` VALUES ('4', 'zhanglaoshi', '461c6c3e5797f533c42e3aa24b7e0077', '张老师', '0', '13978908861@188.com', '13978908861', '2018-05-11 23:44:11', '2018-05-11 23:52:19');
