/*
Navicat MySQL Data Transfer

Source Server         : localhsot
Source Server Version : 50517
Source Host           : localhost:3306
Source Database       : prods

Target Server Type    : MYSQL
Target Server Version : 50199
File Encoding         : 65001

Date: 2012-11-24 17:03:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_dicinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dicinfo`;
CREATE TABLE `tb_dicinfo` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`dicCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`dicDesc`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`dicGroup`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`dicValue`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=5

;

-- ----------------------------
-- Records of tb_dicinfo
-- ----------------------------
BEGIN;
INSERT INTO `tb_dicinfo` VALUES ('1', '系统最高', '拥有最高的管理权限。拥有所有操作权限。', 'roleType', '1'), ('2', '管理员', '具有管理权根。可以管理系统日常操作。', 'roleType', '2'), ('3', '普通用户', '普通操作用户。', 'roleType', '3'), ('4', '测试人员', '测试', 'roleType', '4');
COMMIT;

-- ----------------------------
-- Table structure for `tb_function`
-- ----------------------------
DROP TABLE IF EXISTS `tb_function`;
CREATE TABLE `tb_function` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`functionName`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`type`  int(11) NULL DEFAULT NULL ,
`tbModule_fk`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`tbModule_fk`) REFERENCES `tb_module` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK5372F28971695874` USING BTREE (`tbModule_fk`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of tb_function
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `tb_module`
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`expanded`  bit(1) NULL DEFAULT NULL ,
`file`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`iconCls`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`leaf`  bit(1) NULL DEFAULT NULL ,
`moduleId`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`text`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`parentId`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`parentId`) REFERENCES `tb_module` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `moduleId` USING BTREE (`moduleId`) ,
INDEX `FK54F11D5D18397AEF` USING BTREE (`parentId`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=32

;

-- ----------------------------
-- Records of tb_module
-- ----------------------------
BEGIN;
INSERT INTO `tb_module` VALUES ('1', null, null, 'icon-hotel', null, 'platformMgr', '平台管理', null), ('2', '', '', 'icon-catalog', '', 'sysManager', '系统管理', '1'), ('3', '', '/sysManager/userManager.js', 'details', '', 'userManagerPanel', '用户管理', '2'), ('4', '', '/sysManager/moduleManager.js,/sysManager/moduleAddWin.js', 'details', '', 'moduleManagerPanel', '模块管理', '2'), ('5', '', '/sysManager/permissionManager.js', 'details', '', 'permissionManagerPanel', '权限管理', '2'), ('6', '', '/sysManager/logsManager.js', 'details', '', 'logsManagerPanel', '日志管理', '2'), ('7', '', '/sysManager/modifyPwd.js', 'details', '', 'modifyPwdPanel', '修改密码', '2'), ('8', '', '', 'icon-hotel', '', 'sxd', '神香道APP', null), ('9', '', '', 'icon-catalog', '', 'sxdMgr', '应用管理', '8'), ('10', '', '/sxd/app/dictMain.js,/sxd/app/dictAddorUpWin.js', 'details', '', 'sxdDictPanel', 'APP字典管理', '9'), ('11', '', '/sxd/app/sxdVersionMain.js,/sxd/app/sxdVersionAddorUpWin.js', 'details', '', 'sxdVersionMainPanel', 'APP版本管理', '9'), ('12', '', '', 'icon-catalog', '', 'sxdUser', '个人中心', '8'), ('13', '', '/sxd/user/userMain.js,/sxd/user/userUpWin.js', 'details', '', 'sxdUserMainPanel', '用户管理', '12'), ('14', '', '', 'icon-catalog', '', 'prayMgr', '上香祈福', '8'), ('15', '', '/sxd/pray/buddhaImageMain.js,/sxd/pray/buddhaImageAddorUpWin.js', 'details', '', 'buddhaImagePanel', '供奉佛像', '14'), ('16', '', '/sxd/pray/worshipArticleMain.js,/sxd/pray/worshipArticleAddorUpWin.js', 'details', '', 'worshipArticlePanel', '供奉物品', '14'), ('17', '', '/sxd/pray/prayRecordMain.js', 'details', '', 'prayRecordPanel', '祈福记录', '14'), ('18', '', '', 'icon-catalog', '', 'musics', '静心佛音', '8'), ('19', '', '/sxd/music/musicMain.js,/sxd/music/musicAddorUpWin.js', 'details', '', 'musicPanel', '佛音列表', '18'), ('20', '', '', 'icon-catalog', '', 'news', '佛教新闻', '8'), ('21', '', '/sxd/news/newsMain.js', 'details', '', 'newsPanel', '新闻列表', '20'), ('22', '', '', 'icon-catalog', '', 'buddhalore', '佛学常识', '8'), ('23', '', '/sxd/lore/loreMain.js,/sxd/lore/loreAddorUpWin.js', 'details', '', 'lorePanel', '佛学常识列表', '22'), ('24', '', '', 'icon-catalog', '', 'books', '在线诵经', '8'), ('25', '', '/sxd/books/booksMain.js', 'details', '', 'booksPanel', '经书列表', '24'), ('26', '', '/sxd/books/chantingRecordMain.js', 'details', '', 'chantingRecordPanel', '诵经计录', '24'), ('30', null, null, 'icon-hotel', null, 'market', '应用商城', null), ('31', '', '/sxd/wb/weiboMain.js', 'details', '', 'weiboPanel', '大师微博', '20');
COMMIT;

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`roleDesc`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`roleName`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`roleType`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`roleCode`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `roleName` USING BTREE (`roleName`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=15

;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` VALUES ('1', '普通用户：就是系统的普通使用都。', '普通用户', '3', '普通用户'), ('2', '拥有最高的权限。', '系统最高', '1', '系统最高'), ('3', '作为系统的管理人员，权限弱小于系统最高用户。', '管理员', '2', '管理员');
COMMIT;

-- ----------------------------
-- Table structure for `tb_role_tb_function`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_tb_function`;
CREATE TABLE `tb_role_tb_function` (
`tbRoles_id`  bigint(20) NOT NULL ,
`tbFunctions_id`  bigint(20) NOT NULL ,
PRIMARY KEY (`tbRoles_id`, `tbFunctions_id`),
FOREIGN KEY (`tbRoles_id`) REFERENCES `tb_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`tbFunctions_id`) REFERENCES `tb_function` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK5AD00DF1EC5B743` USING BTREE (`tbFunctions_id`) ,
INDEX `FK5AD00DF1D736C77F` USING BTREE (`tbRoles_id`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of tb_role_tb_function
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `tb_role_tb_module`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_tb_module`;
CREATE TABLE `tb_role_tb_module` (
`tbRoles_id`  bigint(20) NOT NULL ,
`tbModules_id`  bigint(20) NOT NULL ,
PRIMARY KEY (`tbRoles_id`, `tbModules_id`),
FOREIGN KEY (`tbModules_id`) REFERENCES `tb_module` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`tbRoles_id`) REFERENCES `tb_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK382DF2C51A698D6B` USING BTREE (`tbModules_id`) ,
INDEX `FK382DF2C5D736C77F` USING BTREE (`tbRoles_id`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of tb_role_tb_module
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_tb_module` VALUES ('2', '1'), ('2', '2'), ('2', '3'), ('2', '4'), ('2', '5'), ('2', '6'), ('2', '7'), ('2', '8'), ('2', '9'), ('2', '10'), ('2', '11'), ('2', '12'), ('2', '13'), ('2', '14'), ('2', '15'), ('2', '16'), ('2', '17'), ('2', '18'), ('2', '19'), ('2', '20'), ('2', '21'), ('2', '22'), ('2', '23'), ('2', '24'), ('2', '25'), ('2', '26'), ('2', '31');
COMMIT;

-- ----------------------------
-- Table structure for `tb_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_userinfo`;
CREATE TABLE `tb_userinfo` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`createTime`  datetime NULL DEFAULT NULL ,
`email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`state`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`updateTime`  datetime NULL DEFAULT NULL ,
`userName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`roleId`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`roleId`) REFERENCES `tb_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FKF13C65CA3C6A4145` USING BTREE (`roleId`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of tb_userinfo
-- ----------------------------
BEGIN;
INSERT INTO `tb_userinfo` VALUES ('1', '2011-03-10 00:00:00', 'admin@163.com', 'fe4053114c7c4e7c02bd6362063bfa9f', '13510122175', '正常', '2012-11-24 16:34:39', 'admin', '2');
COMMIT;

-- ----------------------------
-- Auto increment value for `tb_dicinfo`
-- ----------------------------
ALTER TABLE `tb_dicinfo` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for `tb_function`
-- ----------------------------
ALTER TABLE `tb_function` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `tb_module`
-- ----------------------------
ALTER TABLE `tb_module` AUTO_INCREMENT=32;

-- ----------------------------
-- Auto increment value for `tb_role`
-- ----------------------------
ALTER TABLE `tb_role` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for `tb_userinfo`
-- ----------------------------
ALTER TABLE `tb_userinfo` AUTO_INCREMENT=3;
