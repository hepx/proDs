/*
Navicat MySQL Data Transfer

Source Server         : localhsot
Source Server Version : 50517
Source Host           : localhost:3306
Source Database       : prods

Target Server Type    : MYSQL
Target Server Version : 50517
File Encoding         : 65001

Date: 2013-07-23 16:28:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_bdg_apppush`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bdg_apppush`;
CREATE TABLE `tb_bdg_apppush` (
  `pushId` bigint(20) NOT NULL AUTO_INCREMENT,
  `appPath` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `errcode` varchar(10) DEFAULT NULL,
  `errmsg` varchar(100) DEFAULT NULL,
  `msgContent` varchar(255) DEFAULT NULL,
  `msgTitle` varchar(50) DEFAULT NULL,
  `pushTime` datetime DEFAULT NULL,
  `packageName` varchar(50) DEFAULT NULL,
  `appName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pushId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bdg_apppush
-- ----------------------------
INSERT INTO `tb_bdg_apppush` VALUES ('11', 'http://localhost:8080/attachments/market/apps/com.tencent.mobileqq/qq2013_4.0.1.1547_android.apk', '2013-03-15 16:49:54', null, '', 'QQ2013应用推送', 'APP push', null, 'com.tencent.mobileqq', 'QQ2013');
INSERT INTO `tb_bdg_apppush` VALUES ('12', 'http://localhost:8080/attachments/market/apps/com.netease.newsreader.activity/newsreader-wyyy_news.apk', '2013-03-15 16:49:54', null, null, '网易应用推送', null, null, 'com.netease.newsreader.activity', '网易');

-- ----------------------------
-- Table structure for `tb_bdg_appwall`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bdg_appwall`;
CREATE TABLE `tb_bdg_appwall` (
  `appWallId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `downloads` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `isPush` bit(1) DEFAULT b'0',
  PRIMARY KEY (`appWallId`),
  UNIQUE KEY `appId` (`appId`),
  KEY `FKF2A63940DF3D1A80` (`appId`),
  CONSTRAINT `FKF2A63940DF3D1A80` FOREIGN KEY (`appId`) REFERENCES `tb_market_appinfo` (`appId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bdg_appwall
-- ----------------------------
INSERT INTO `tb_bdg_appwall` VALUES ('4', '2013-02-26 16:11:32', 'admin', '0', null, '3', '');
INSERT INTO `tb_bdg_appwall` VALUES ('7', '2013-02-26 16:13:13', 'admin', '0', null, '5', null);
INSERT INTO `tb_bdg_appwall` VALUES ('8', '2013-02-26 16:13:13', 'admin', '0', null, '1', null);

-- ----------------------------
-- Table structure for `tb_bdg_ar_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bdg_ar_record`;
CREATE TABLE `tb_bdg_ar_record` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `collects` int(11) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `id` varchar(30) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `pic` varchar(200) DEFAULT NULL,
  `recordSource` varchar(30) DEFAULT NULL,
  `recordType` varchar(1) DEFAULT NULL,
  `screenName` varchar(50) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `text` varchar(512) DEFAULT NULL,
  `tops` int(11) DEFAULT NULL,
  `treads` int(11) DEFAULT NULL,
  `uid` varchar(20) DEFAULT NULL,
  `shares` int(11) DEFAULT NULL,
  `releaseTime` datetime DEFAULT NULL,
  PRIMARY KEY (`recordId`),
  UNIQUE KEY `pic_unique` (`pic`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bdg_ar_record
-- ----------------------------
INSERT INTO `tb_bdg_ar_record` VALUES ('50', '0', null, '3522049940775349', '北京', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzpqyjd0emj.jpg', '新浪微博', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '大智慧0', '0', '0', '2617700277', '0', '2013-03-02 15:03:00');
INSERT INTO `tb_bdg_ar_record` VALUES ('51', '0', '2012-12-21 10:56:05', '3525627519194375', '广东 广州', 'http://ww3.sinaimg.cn/bmiddle/92e69d42jw1dxlt3g1wcpj.jpg', '新浪微博', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '从此唇枪舌剑在不怕，\n识破谎言就看这个！', '0', '0', '2405096864', '0', '2013-03-08 14:42:42');
INSERT INTO `tb_bdg_ar_record` VALUES ('57', '0', '2012-12-21 11:00:11', '3525628550790347', '广东 广州', 'http://ww3.sinaimg.cn/bmiddle/81924fe4jw1dzrzozh66uj.jpg', '新浪微博', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '小阁楼一直是我无法割舍的爱', '0', '0', '2405096864', '0', '2013-03-08 15:15:10');
INSERT INTO `tb_bdg_ar_record` VALUES ('58', '0', '2012-12-21 10:52:06', '3525626512602611', '广东 广州', 'http://ww3.sinaimg.cn/bmiddle/877ff93ajw1dxzthgx0snj.jpg', '新浪微博', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '好美的中国风，大爱中国风', '0', '0', '2405096864', '0', '2013-03-08 16:08:46');
INSERT INTO `tb_bdg_ar_record` VALUES ('59', '0', '2012-12-21 10:36:05', '3525622485800675', '广东 广州', 'http://ww1.sinaimg.cn/bmiddle/a9688c0cjw1dznqgymgirj.jpg', '新浪微博', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '营销的学问！！！你了解多少？？', '0', '0', '2405096864', '0', '2013-03-08 16:10:58');
INSERT INTO `tb_bdg_ar_record` VALUES ('60', '88', '2012-12-21 10:40:14', '3525623526404250', '广东 广州', 'http://ww4.sinaimg.cn/bmiddle/70f73a53jw1dzrk0rmoqmj.jpg', '新浪微博', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '【营销名言】', '46', '41', '2405096864', '66', '2013-03-20 14:29:05');
INSERT INTO `tb_bdg_ar_record` VALUES ('61', '64', '2012-12-21 10:48:16', '3525625547777429', '广东 广州', 'http://ww2.sinaimg.cn/bmiddle/9d5b6315jw1dzngwfbu3dj.jpg', '新浪微博', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '女人们要知道的事', '44', '98', '2405096864', '99', '2013-03-20 14:29:31');
INSERT INTO `tb_bdg_ar_record` VALUES ('62', '16', '2012-12-11 13:40:06', '3522044911786581', '北京', 'http://ww4.sinaimg.cn/bmiddle/9c06efb5jw1dzpqdqnkkvg.gif', '新浪微博', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '这个蝙蝠侠太欠揍了吧[嘻嘻]   ', '20', '23', '2617700277', '68', '2013-07-03 12:05:13');
INSERT INTO `tb_bdg_ar_record` VALUES ('64', '34', '2012-12-21 10:44:08', '3525624512012097', '广东 广州', 'http://ww2.sinaimg.cn/bmiddle/b502b4cdgw1dzs2i9kiyfj.jpg', '新浪微博', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '吃喝玩乐在杭州！有机会一定要去玩~先收藏！', '94', '30', '2405096864', '51', '2013-07-03 12:05:15');

-- ----------------------------
-- Table structure for `tb_bdg_br_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bdg_br_record`;
CREATE TABLE `tb_bdg_br_record` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `bmiddlePic` varchar(200) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `id` varchar(30) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `mid` varchar(30) DEFAULT NULL,
  `originalPic` varchar(200) DEFAULT NULL,
  `recordSource` varchar(30) DEFAULT NULL,
  `recordStatus` int(11) DEFAULT NULL,
  `recordType` varchar(1) DEFAULT NULL,
  `screenName` varchar(50) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `text` varchar(512) DEFAULT NULL,
  `thumbnailPic` varchar(200) DEFAULT NULL,
  `uid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bdg_br_record
-- ----------------------------
INSERT INTO `tb_bdg_br_record` VALUES ('1', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzmaoh21nbj.jpg', '2012-12-08 14:20:08', '3520967823293744', '北京', '3520967823293744', 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzmaoh21nbj.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '多亮点!   ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzmaoh21nbj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('2', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzm9isbzswg.gif', '2012-12-08 13:40:06', '3520957748458084', '北京', null, 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzm9isbzswg.gif', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '呃。。这祸闯大了，年终奖金完蛋了。。  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzm9isbzswg.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('3', 'http://ww4.sinaimg.cn/bmiddle/9c06efb5jw1dzm8xyvcagj.jpg', '2012-12-08 13:20:03', '3520952702415305', '北京', '3520952702415305', 'http://ww4.sinaimg.cn/large/9c06efb5jw1dzm8xyvcagj.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '真实的打雪仗[赞]  ', 'http://ww4.sinaimg.cn/thumbnail/9c06efb5jw1dzm8xyvcagj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('4', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzm8d5urjcg.gif', '2012-12-08 13:00:04', '3520947673841542', '北京', null, 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzm8d5urjcg.gif', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '再也看不下去了，我也看不下去了！！！我打啊、、、  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzm8d5urjcg.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('5', 'http://ww2.sinaimg.cn/bmiddle/9c06efb5jw1dzm7scg3h9j.jpg', '2012-12-08 12:40:03', '3520942636509776', '北京', '3520942636509776', 'http://ww2.sinaimg.cn/large/9c06efb5jw1dzm7scg3h9j.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '家里的液化气坏了！夜宵只能这样煮~~~  ', 'http://ww2.sinaimg.cn/thumbnail/9c06efb5jw1dzm7scg3h9j.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('6', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzm77jc6w9j.jpg', '2012-12-08 12:20:03', '3520937603118839', '北京', '3520937603118839', 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzm77jc6w9j.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '男人与女人，聪明的老师  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzm77jc6w9j.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('7', 'http://ww2.sinaimg.cn/bmiddle/9c06efb5jw1dzm6mqr0omj.jpg', '2012-12-08 12:00:04', '3520932574191695', '北京', '3520932574191695', 'http://ww2.sinaimg.cn/large/9c06efb5jw1dzm6mqr0omj.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '悬浮术。  ', 'http://ww2.sinaimg.cn/thumbnail/9c06efb5jw1dzm6mqr0omj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('8', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzm61we3qlj.jpg', '2012-12-08 11:40:02', '3520927532670624', '北京', '3520927532670624', 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzm61we3qlj.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '骚年们，撸管大赛正式开赛啦~~~[汗]  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzm61we3qlj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('9', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzm5h6mp0tj.jpg', '2012-12-08 11:20:08', '3520922524725638', '北京', '3520922524725638', 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzm5h6mp0tj.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '临死之前  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzm5h6mp0tj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('10', 'http://ww4.sinaimg.cn/bmiddle/9c06efb5jw1dzm4bhfkqkj.jpg', '2012-12-08 10:40:03', '3520912437110395', '北京', '3520912437110395', 'http://ww4.sinaimg.cn/large/9c06efb5jw1dzm4bhfkqkj.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '目测能算出来的不超过三个！  ', 'http://ww4.sinaimg.cn/thumbnail/9c06efb5jw1dzm4bhfkqkj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('11', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzm3qpvc4og.gif', '2012-12-08 10:20:06', '3520907416551911', '北京', null, 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzm3qpvc4og.gif', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '做点热身运动就不会冷了~ /憨笑  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzm3qpvc4og.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('12', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzm35y8k0lg.gif', '2012-12-08 10:00:08', '3520902392182568', '北京', null, 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzm35y8k0lg.gif', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '最近国外兴这种没有门不停站的电梯哦，你敢坐不？  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzm35y8k0lg.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('13', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzm20efv6uj.jpg', '2012-12-08 09:20:12', '3520892342544165', '北京', null, 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzm20efv6uj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '喝醉那点事  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzm20efv6uj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('14', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzm1fffof3j.jpg', '2012-12-08 09:00:02', '3520887267171167', '北京', null, 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzm1fffof3j.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '家有恶狗，生人莫入  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzm1fffof3j.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('15', 'http://ww2.sinaimg.cn/bmiddle/9c06efb5jw1dzm0umda4bg.gif', '2012-12-08 08:40:03', '3520882238464175', '北京', null, 'http://ww2.sinaimg.cn/large/9c06efb5jw1dzm0umda4bg.gif', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '有人说HR逆袭火了是因为寂寞，我觉得是CMS   ', 'http://ww2.sinaimg.cn/thumbnail/9c06efb5jw1dzm0umda4bg.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('16', 'http://ww2.sinaimg.cn/bmiddle/9c06efb5jw1dzm09t0z4yj.jpg', '2012-12-08 08:20:02', '3520877200900500', '北京', null, 'http://ww2.sinaimg.cn/large/9c06efb5jw1dzm09t0z4yj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '  应聘 我们要的就是能干的秘书     ', 'http://ww2.sinaimg.cn/thumbnail/9c06efb5jw1dzm09t0z4yj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('17', 'http://ww2.sinaimg.cn/bmiddle/9c06efb5jw1dzlz46v7rsj.jpg', '2012-12-08 07:40:03', '3520867138627475', '北京', null, 'http://ww2.sinaimg.cn/large/9c06efb5jw1dzlz46v7rsj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '任务目标!   ', 'http://ww2.sinaimg.cn/thumbnail/9c06efb5jw1dzlz46v7rsj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('18', 'http://ww2.sinaimg.cn/bmiddle/9c06efb5jw1dzlyjda1lcj.jpg', '2012-12-08 07:20:02', '3520862101307858', '北京', null, 'http://ww2.sinaimg.cn/large/9c06efb5jw1dzlyjda1lcj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '前天晚饭后一家人出去散步，走到一半，丈老头忽然对丈母娘说，老婆，我裤子后面好像开线了，丈母娘急性子，当即不让丈人走，蹲在丈老头屁股后面凑过去看，只听一声雷响！丈母娘脸都绿了，捂着鼻子跑了N远，尼玛晚上吃的是红薯稀饭吖！！我和老婆都笑趴了。  ', 'http://ww2.sinaimg.cn/thumbnail/9c06efb5jw1dzlyjda1lcj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('19', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzlxyn4tbej.jpg', '2012-12-08 07:00:07', '3520857089181075', '北京', null, 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzlxyn4tbej.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '【因人施教】。。。[嘻嘻]   ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzlxyn4tbej.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('22', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzpvl0nrfag.gif', '2012-12-11 16:40:05', '3522090206085421', '北京', '3522090206085421', 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzpvl0nrfag.gif', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '就是吃相太特么难看了……  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzpvl0nrfag.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('23', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzpv09nqodj.jpg', '2012-12-11 16:20:08', '3522085185521606', '北京', '3522085185521606', 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzpv09nqodj.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '男人和女人的区别  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzpv09nqodj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('24', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzpufgpkgij.jpg', '2012-12-11 16:00:08', '3522080156741283', '北京', '3522080156741283', 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzpufgpkgij.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', ' 线!   ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzpufgpkgij.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('25', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzptuumsbtg.gif', '2012-12-11 15:40:20', '3522075169726450', '北京', '3522075169726450', 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzptuumsbtg.gif', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '叫你装逼。。  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzptuumsbtg.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('26', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzpt9r60mej.jpg', '2012-12-11 15:20:03', '3522070065244175', '北京', '3522070065244175', 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzpt9r60mej.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '买套了吗？！~  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzpt9r60mej.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('27', 'http://ww2.sinaimg.cn/bmiddle/9c06efb5jw1dzps48atr9j.jpg', '2012-12-11 14:40:09', '3522060023883870', '北京', null, 'http://ww2.sinaimg.cn/large/9c06efb5jw1dzps48atr9j.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '【简易挤胸教程】.....讨厌不要笑我！！！cos可以用什么的QVQ   ', 'http://ww2.sinaimg.cn/thumbnail/9c06efb5jw1dzps48atr9j.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('28', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzprjcg6xuj.jpg', '2012-12-11 14:20:04', '3522054974116224', '北京', '3522054974116224', 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzprjcg6xuj.jpg', '新浪微博', '0', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '这是辽宁工业大学南门保安大哥弄得！！！大哥是艺术设计专业毕业的吧！！！  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzprjcg6xuj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('29', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzpqyjd0emj.jpg', '2012-12-11 14:00:05', '3522049940775349', '北京', null, 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzpqyjd0emj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '大智慧  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzpqyjd0emj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('30', 'http://ww4.sinaimg.cn/bmiddle/9c06efb5jw1dzpqdqnkkvg.gif', '2012-12-11 13:40:06', '3522044911786581', '北京', null, 'http://ww4.sinaimg.cn/large/9c06efb5jw1dzpqdqnkkvg.gif', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '这个蝙蝠侠太欠揍了吧[嘻嘻]   ', 'http://ww4.sinaimg.cn/thumbnail/9c06efb5jw1dzpqdqnkkvg.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('31', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzppsyxicaj.jpg', '2012-12-11 13:20:07', '3522039887383317', '北京', null, 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzppsyxicaj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '这帅气的发型，极品  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzppsyxicaj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('32', 'http://ww4.sinaimg.cn/bmiddle/9c06efb5jw1dzpp8375eej.jpg', '2012-12-11 13:00:04', '3522034837152821', '北京', null, 'http://ww4.sinaimg.cn/large/9c06efb5jw1dzpp8375eej.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '振动的好啊 [哈哈]  ', 'http://ww4.sinaimg.cn/thumbnail/9c06efb5jw1dzpp8375eej.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('33', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzponiiouwg.gif', '2012-12-11 12:40:17', '3522029858420183', '北京', null, 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzponiiouwg.gif', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '美女回眸一笑  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzponiiouwg.gif', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('34', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzpo2j1uegj.jpg', '2012-12-11 12:20:07', '3522024783665633', '北京', null, 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzpo2j1uegj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '看不懂的都是好孩子[哈哈]   ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzpo2j1uegj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('35', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzpnhpz4smj.jpg', '2012-12-11 12:00:07', '3522019750311203', '北京', null, 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzpnhpz4smj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '#恶搞#觉得“犀利哥”和“芙蓉姐姐”很般配的举个手~~~投票中   ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzpnhpz4smj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('36', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzpmc1bua8j.jpg', '2012-12-11 11:20:03', '3522009667278142', '北京', null, 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzpmc1bua8j.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '你认为真相是这样子吗？  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzpmc1bua8j.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('37', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzplrb0si6j.jpg', '2012-12-11 11:00:08', '3522004654901622', '北京', null, 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzplrb0si6j.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '主人的帽子被我偷偷戴了，她会不会发飙？忐 ……   ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzplrb0si6j.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('38', 'http://ww4.sinaimg.cn/bmiddle/9c06efb5jw1dzpl6nzqwaj.jpg', '2012-12-11 10:40:18', '3521999663643922', '北京', null, 'http://ww4.sinaimg.cn/large/9c06efb5jw1dzpl6nzqwaj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '找菜市场买晚上的羊肉片牛肉片，问何达在哪里，何达电话里面说：到那儿有个什么宾馆？就是那什么宾馆！我一头雾水，什么宾馆啊？按照他给的路线，到菜市场，一看：擦，果然是“什么宾馆”！  ', 'http://ww4.sinaimg.cn/thumbnail/9c06efb5jw1dzpl6nzqwaj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('39', 'http://ww3.sinaimg.cn/bmiddle/9c06efb5jw1dzpk0t3qv5j.jpg', '2012-12-11 10:00:04', '3521989538862037', '北京', null, 'http://ww3.sinaimg.cn/large/9c06efb5jw1dzpk0t3qv5j.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '搞到一张防追尾新车贴，迫不及待的贴上了。。。  ', 'http://ww3.sinaimg.cn/thumbnail/9c06efb5jw1dzpk0t3qv5j.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('40', 'http://ww1.sinaimg.cn/bmiddle/9c06efb5jw1dzpiacyzglj.jpg', '2012-12-11 09:00:03', '3521974435099097', '北京', null, 'http://ww1.sinaimg.cn/large/9c06efb5jw1dzpiacyzglj.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '坐飞机神马的，女士优先的好处  ', 'http://ww1.sinaimg.cn/thumbnail/9c06efb5jw1dzpiacyzglj.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('41', 'http://ww4.sinaimg.cn/bmiddle/9c06efb5jw1dzp7vscw32j.jpg', '2012-12-11 03:00:03', '3521883838211356', '北京', null, 'http://ww4.sinaimg.cn/large/9c06efb5jw1dzp7vscw32j.jpg', '新浪微博', '2', '1', '百思不得姐应用', 'http://app.weibo.com/t/feed/G6uk2', '被打肿了么- -  ', 'http://ww4.sinaimg.cn/thumbnail/9c06efb5jw1dzp7vscw32j.jpg', '2617700277');
INSERT INTO `tb_bdg_br_record` VALUES ('42', 'http://ww3.sinaimg.cn/bmiddle/7f677930tw1dtn4jpk68aj.jpg', '2012-12-19 10:56:11', '3524902764616650', '广东 广州', '3524902764616650', 'http://ww3.sinaimg.cn/large/7f677930tw1dtn4jpk68aj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '千万不要考验人性', 'http://ww3.sinaimg.cn/thumbnail/7f677930tw1dtn4jpk68aj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('43', 'http://ww2.sinaimg.cn/bmiddle/8b5aac6bjw1dzq1l4aup7j.jpg', '2012-12-19 10:52:08', '3524901749368178', '广东 广州', '3524901749368178', 'http://ww2.sinaimg.cn/large/8b5aac6bjw1dzq1l4aup7j.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '【#双子座#本周塔罗牌面】', 'http://ww2.sinaimg.cn/thumbnail/8b5aac6bjw1dzq1l4aup7j.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('44', 'http://ww1.sinaimg.cn/bmiddle/7a085389jw1dz2uhfjia6j.jpg', '2012-12-19 10:48:08', '3524900738348883', '广东 广州', '3524900738348883', 'http://ww1.sinaimg.cn/large/7a085389jw1dz2uhfjia6j.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '做好自己就好！喜欢你的倾述，感同身受~！', 'http://ww1.sinaimg.cn/thumbnail/7a085389jw1dz2uhfjia6j.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('45', 'http://ww4.sinaimg.cn/bmiddle/8bdb4a53tw1dzr7wx0gulj.jpg', '2012-12-19 10:44:07', '3524899727600233', '广东 广州', '3524899727600233', 'http://ww4.sinaimg.cn/large/8bdb4a53tw1dzr7wx0gulj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '李敏镐 滴无水印手机壁纸来了哟，很帅，速度更换手机壁纸了。', 'http://ww4.sinaimg.cn/thumbnail/8bdb4a53tw1dzr7wx0gulj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('46', 'http://ww2.sinaimg.cn/bmiddle/8bdb4a53tw1dzpzvregbdj.jpg', '2012-12-19 10:40:11', '3524898738121448', '广东 广州', '3524898738121448', 'http://ww2.sinaimg.cn/large/8bdb4a53tw1dzpzvregbdj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '海贼王 滴无水印手机壁纸来了 很可爱。萌屎人。[心]', 'http://ww2.sinaimg.cn/thumbnail/8bdb4a53tw1dzpzvregbdj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('47', 'http://ww1.sinaimg.cn/bmiddle/b502b4cdgw1dzl85gb3qyj.jpg', '2012-12-19 10:36:09', '3524897718420898', '广东 广州', '3524897718420898', 'http://ww1.sinaimg.cn/large/b502b4cdgw1dzl85gb3qyj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '上有天堂下有苏杭！有机会一定要去杭州玩！先收藏！', 'http://ww1.sinaimg.cn/thumbnail/b502b4cdgw1dzl85gb3qyj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('48', 'http://ww1.sinaimg.cn/bmiddle/70f7ff71jw1dzq2jt4rsjj.jpg', '2012-12-19 10:32:09', '3524896716065922', '广东 广州', '3524896716065922', 'http://ww1.sinaimg.cn/large/70f7ff71jw1dzq2jt4rsjj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '语不惊人死不休', 'http://ww1.sinaimg.cn/thumbnail/70f7ff71jw1dzq2jt4rsjj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('49', 'http://ww4.sinaimg.cn/bmiddle/7093b907jw1dzofb0jdokj.jpg', '2012-12-19 10:28:07', '3524895705322692', '广东 广州', '3524895705322692', 'http://ww4.sinaimg.cn/large/7093b907jw1dzofb0jdokj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '什么街舞、杰克逊，弱爆了！看看中国人到底有多牛叉！哈哈，这里的内容笑死我了~~！', 'http://ww4.sinaimg.cn/thumbnail/7093b907jw1dzofb0jdokj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('50', 'http://ww2.sinaimg.cn/bmiddle/a49a3684jw1dzb6o7ho9zj.jpg', '2012-12-19 10:24:06', '3524894694318344', '广东 广州', '3524894694318344', 'http://ww2.sinaimg.cn/large/a49a3684jw1dzb6o7ho9zj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '总是这样对我[泪]真心恋上你的文字，总是写得那么窝心~[爱你]值得一看！', 'http://ww2.sinaimg.cn/thumbnail/a49a3684jw1dzb6o7ho9zj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('51', 'http://ww2.sinaimg.cn/bmiddle/92e69d42jw1dwihvubpwdj.jpg', '2012-12-19 10:20:08', '3524893691969294', '广东 广州', '3524893691969294', 'http://ww2.sinaimg.cn/large/92e69d42jw1dwihvubpwdj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '反复问同一个问题，说谎嫌疑大，识破谎言就看这个。', 'http://ww2.sinaimg.cn/thumbnail/92e69d42jw1dwihvubpwdj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('52', 'http://ww2.sinaimg.cn/bmiddle/819643ecgw1dy2l690pfsj.jpg', '2012-12-19 10:16:07', '3524892681231613', '广东 广州', '3524892681231613', 'http://ww2.sinaimg.cn/large/819643ecgw1dy2l690pfsj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '液晶屏幕坏点修复小技巧，这么实用的帖子，收藏吧！', 'http://ww2.sinaimg.cn/thumbnail/819643ecgw1dy2l690pfsj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('53', 'http://ww3.sinaimg.cn/bmiddle/6e7992b3jw1dzq0tdopgnj.jpg', '2012-12-19 10:12:09', '3524891687316862', '广东 广州', '3524891687316862', 'http://ww3.sinaimg.cn/large/6e7992b3jw1dzq0tdopgnj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '天机不可泄露', 'http://ww3.sinaimg.cn/thumbnail/6e7992b3jw1dzq0tdopgnj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('54', 'http://ww1.sinaimg.cn/bmiddle/9ec08657gw1dss6q2y9uij.jpg', '2012-12-19 10:08:08', '3524890672082853', '广东 广州', '3524890672082853', 'http://ww1.sinaimg.cn/large/9ec08657gw1dss6q2y9uij.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '要想你的男人一辈子不背叛你，学会看懂男人的心', 'http://ww1.sinaimg.cn/thumbnail/9ec08657gw1dss6q2y9uij.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('55', 'http://ww3.sinaimg.cn/bmiddle/92e69d42jw1dwihg6f1ixj.jpg', '2012-12-19 10:04:07', '3524889661655878', '广东 广州', '3524889661655878', 'http://ww3.sinaimg.cn/large/92e69d42jw1dwihg6f1ixj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '早点看到这个技巧就不会忽悠了~', 'http://ww3.sinaimg.cn/thumbnail/92e69d42jw1dwihg6f1ixj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('56', 'http://ww1.sinaimg.cn/bmiddle/96b2dea3jw1dzprd1d291j.jpg', '2012-12-19 10:00:18', '3524888704890074', '广东 广州', null, 'http://ww1.sinaimg.cn/large/96b2dea3jw1dzprd1d291j.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '圣诞节到了', 'http://ww1.sinaimg.cn/thumbnail/96b2dea3jw1dzprd1d291j.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('57', 'http://ww1.sinaimg.cn/bmiddle/7a085389jw1dzkvb5w5ghj.jpg', '2012-12-19 09:56:08', '3524887656710386', '广东 广州', '3524887656710386', 'http://ww1.sinaimg.cn/large/7a085389jw1dzkvb5w5ghj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '在自己眼里，这是爱； 在对方眼里，这是烦； 在别人眼里，这是贱。。!喜欢你的倾述，很贴心~！那么感同身受~！', 'http://ww1.sinaimg.cn/thumbnail/7a085389jw1dzkvb5w5ghj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('58', 'http://ww3.sinaimg.cn/bmiddle/82cbebc0gw1dyqagktg1tj.jpg', '2012-12-19 09:52:06', '3524886641532590', '广东 广州', '3524886641532590', 'http://ww3.sinaimg.cn/large/82cbebc0gw1dyqagktg1tj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '科学减肥很重要，有效且不伤害身体，减肥的mm要相当注', 'http://ww3.sinaimg.cn/thumbnail/82cbebc0gw1dyqagktg1tj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('59', 'http://ww2.sinaimg.cn/bmiddle/a49a3684jw1dzg9hj0ia5j.jpg', '2012-12-19 09:48:05', '3524885626615760', '广东 广州', null, 'http://ww2.sinaimg.cn/large/a49a3684jw1dzg9hj0ia5j.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '越是成长 越难得到朋友 说得很对！句句都说到我真实的感受！[爱你]', 'http://ww2.sinaimg.cn/thumbnail/a49a3684jw1dzg9hj0ia5j.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('60', 'http://ww4.sinaimg.cn/bmiddle/86abbcacjw1dzr82z3xbkj.jpg', '2012-12-19 09:44:07', '3524884628174070', '广东 广州', null, 'http://ww4.sinaimg.cn/large/86abbcacjw1dzr82z3xbkj.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '.', 'http://ww4.sinaimg.cn/thumbnail/86abbcacjw1dzr82z3xbkj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('61', 'http://ww4.sinaimg.cn/bmiddle/a49a3684jw1dzbr75qudbj.jpg', '2012-12-19 09:40:11', '3524883638476943', '广东 广州', '3524883638476943', 'http://ww4.sinaimg.cn/large/a49a3684jw1dzbr75qudbj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '记住这几句话 每天都要看你的文字！每一句都让人很戳心窝！[爱你]', 'http://ww4.sinaimg.cn/thumbnail/a49a3684jw1dzbr75qudbj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('62', 'http://ww2.sinaimg.cn/bmiddle/9d5b6315jw1dzngwfbu3dj.jpg', '2012-12-21 10:48:16', '3525625547777429', '广东 广州', null, 'http://ww2.sinaimg.cn/large/9d5b6315jw1dzngwfbu3dj.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '女人们要知道的事', 'http://ww2.sinaimg.cn/thumbnail/9d5b6315jw1dzngwfbu3dj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('63', 'http://ww2.sinaimg.cn/bmiddle/b502b4cdgw1dzs2i9kiyfj.jpg', '2012-12-21 10:44:08', '3525624512012097', '广东 广州', null, 'http://ww2.sinaimg.cn/large/b502b4cdgw1dzs2i9kiyfj.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '吃喝玩乐在杭州！有机会一定要去玩~先收藏！', 'http://ww2.sinaimg.cn/thumbnail/b502b4cdgw1dzs2i9kiyfj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('64', 'http://ww4.sinaimg.cn/bmiddle/70f73a53jw1dzrk0rmoqmj.jpg', '2012-12-21 10:40:14', '3525623526404250', '广东 广州', null, 'http://ww4.sinaimg.cn/large/70f73a53jw1dzrk0rmoqmj.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '【营销名言】', 'http://ww4.sinaimg.cn/thumbnail/70f73a53jw1dzrk0rmoqmj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('66', 'http://ww3.sinaimg.cn/bmiddle/96a8515bjw1dzs6kxal64j.jpg', '2012-12-21 10:32:10', '3525621496203405', '广东 广州', null, 'http://ww3.sinaimg.cn/large/96a8515bjw1dzs6kxal64j.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '鲫鱼骨头汤', 'http://ww3.sinaimg.cn/thumbnail/96a8515bjw1dzs6kxal64j.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('67', 'http://ww3.sinaimg.cn/bmiddle/877ff93ajw1dxzthgx0snj.jpg', '2012-12-21 10:52:06', '3525626512602611', '广东 广州', null, 'http://ww3.sinaimg.cn/large/877ff93ajw1dxzthgx0snj.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '好美的中国风，大爱中国风', 'http://ww3.sinaimg.cn/thumbnail/877ff93ajw1dxzthgx0snj.jpg', '2405096864');
INSERT INTO `tb_bdg_br_record` VALUES ('68', 'http://ww3.sinaimg.cn/bmiddle/81924fe4jw1dzrzozh66uj.jpg', '2012-12-21 11:00:11', '3525628550790347', '广东 广州', null, 'http://ww3.sinaimg.cn/large/81924fe4jw1dzrzozh66uj.jpg', '新浪微博', '2', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '小阁楼一直是我无法割舍的爱', 'http://ww3.sinaimg.cn/thumbnail/81924fe4jw1dzrzozh66uj.jpg', '2405096864');

-- ----------------------------
-- Table structure for `tb_bdg_contentpush`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bdg_contentpush`;
CREATE TABLE `tb_bdg_contentpush` (
  `pushId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `errcode` varchar(10) DEFAULT NULL,
  `errmsg` varchar(100) DEFAULT NULL,
  `msgContent` varchar(255) DEFAULT NULL,
  `msgTitle` varchar(50) DEFAULT NULL,
  `pushTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pushId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bdg_contentpush
-- ----------------------------
INSERT INTO `tb_bdg_contentpush` VALUES ('8', '2013-03-15 15:29:34', '', '', '小阁楼一直是我无法割舍的爱.', '吊丝不寂寞', null);
INSERT INTO `tb_bdg_contentpush` VALUES ('10', '2013-03-15 15:55:26', '', '', '好美的中国风，大爱中国风', '6776787', null);
INSERT INTO `tb_bdg_contentpush` VALUES ('11', '2013-05-11 12:45:46', null, null, '小阁楼一直是我无法割舍的爱', null, null);

-- ----------------------------
-- Table structure for `tb_bdg_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bdg_user`;
CREATE TABLE `tb_bdg_user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `imei` varchar(15) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `networkType` varchar(10) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `screenSize` varchar(10) DEFAULT NULL,
  `sdk` varchar(2) DEFAULT NULL,
  `simOperator` varchar(10) DEFAULT NULL,
  `channel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=625 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bdg_user
-- ----------------------------
INSERT INTO `tb_bdg_user` VALUES ('6', '深圳市', '2013-03-13 16:37:45', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('9', '广州市', '2013-03-13 20:04:17', 'A000002C90022B', '', 'EVDO_A', '广东省', '720X1280', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('11', '深圳市', '2013-03-14 12:39:34', '353327051862266', '13510122174', 'EDGE', '广东省', '480X800', '16', 'M-ZONE', null);
INSERT INTO `tb_bdg_user` VALUES ('15', '深圳市', '2013-03-14 16:27:09', '000000000000000', '15555215554', 'UMTS', '广东省', '320X480', '8', 'Android', null);
INSERT INTO `tb_bdg_user` VALUES ('17', '福州市', '2013-03-15 10:33:46', '353637058187255', 'null', 'UNKNOWN', '福建省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('18', '惠州市', '2013-03-15 11:04:46', '359704042801567', '', 'EDGE', '广东省', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('20', '青岛市', '2013-03-15 11:15:32', '869421019282852', '', 'EDGE', '山东省', '1080X1920', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('21', '北京市', '2013-03-15 11:25:03', '867857010119134', '', 'GPRS', '北京市', '640X960', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('22', '太原市', '2013-03-15 11:40:12', '351180180202124', '', 'EDGE', '山西省', '320X480', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('23', '深圳市', '2013-03-15 12:18:33', 'A00000228A1AC9', 'null', 'UNKNOWN', '广东省', '480X854', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('26', '绵阳市', '2013-03-15 13:26:18', '358877040348962', '', 'EDGE', '四川省', '600X976', '13', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('27', '淄博市', '2013-03-15 13:40:54', '353976050669427', '', 'EDGE', '山东省', '720X1280', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('28', '乌鲁木齐市', '2013-03-15 14:06:40', '865186014173570', '', 'EDGE', '新疆维吾尔自治区', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('29', '天津市', '2013-03-15 15:14:37', '866994000204229', '13652165365', 'EDGE', '天津市', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('30', '长春市', '2013-03-15 15:55:20', '866153000668905', 'null', 'UMTS', '吉林省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('32', '北京市', '2013-03-15 20:27:46', '353769054298701', '', 'EDGE', '北京市', '720X1280', '16', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('33', '深圳市', '2013-03-15 21:50:13', 'A00000224CCB14', '0', 'EVDO_A', '广东省', '480X854', '10', 'Verizon', null);
INSERT INTO `tb_bdg_user` VALUES ('34', '福州市', '2013-03-15 23:12:00', 'a10000277b2336', '', 'EVDO_A', '福建省', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('35', '', '2013-03-16 04:11:12', '864927000027887', 'null', 'UNKNOWN', '', '480X800', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('36', '红河哈尼族彝族自治州', '2013-03-16 10:44:13', '866454010233544', 'null', 'EDGE', '云南省', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('37', '太原市', '2013-03-16 15:49:14', '357099046585999', '', 'EDGE', '山西省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('38', '泉州市', '2013-03-17 09:04:53', '353712058749139', '', 'EDGE', '福建省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('39', '沈阳市', '2013-03-17 09:13:14', '869099010029876', '13897929897', 'GPRS', '辽宁省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('40', '广州市', '2013-03-17 17:50:19', '354411051398676', '', 'HSDPA', '广东省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('41', '太原市', '2013-03-17 20:01:01', '351180180202124', '', 'EDGE', '山西省', '320X480', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('42', '沈阳市', '2013-03-18 14:35:44', '355056057007984', '0', 'EDGE', '辽宁省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('43', '深圳市', '2013-03-18 18:56:34', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('44', '深圳市', '2013-03-18 19:02:36', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('45', '深圳市', '2013-03-18 19:05:17', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('46', '深圳市', '2013-03-19 16:47:08', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('47', '深圳市', '2013-03-20 14:39:28', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('48', '深圳市', '2013-03-20 17:59:50', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('49', '宁波市', '2013-03-20 19:15:03', '860513000355454', '', 'UNKNOWN', '浙江省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('50', '深圳市', '2013-03-20 19:39:35', 'A00000228A1AC9', 'null', 'UNKNOWN', '广东省', '480X854', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('51', '苏州市', '2013-03-20 20:21:37', '867156012468528', '', 'EDGE', '江苏省', '540X960', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('52', '福州市', '2013-03-21 10:33:31', '353638051810505', 'null', 'UNKNOWN', '福建省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('53', '北京市', '2013-03-21 11:36:00', '357194042129072', '', 'EDGE', '北京市', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('54', '合肥市', '2013-03-21 14:24:31', '867746019221464', '', 'EDGE', '安徽省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('55', '珠海市', '2013-03-21 15:29:24', '868033018108458', 'null', 'UNKNOWN', '广东省', '800X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('56', '', '2013-03-21 15:44:01', '869579011595918', '', 'EDGE', '江苏省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('57', '深圳市', '2013-03-21 15:47:57', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('58', '深圳市', '2013-03-21 16:03:21', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('59', '深圳市', '2013-03-21 16:31:49', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('60', '汕头市', '2013-03-21 17:23:08', '860308023222850', '', 'EDGE', '广东省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('61', '福州市', '2013-03-21 17:31:13', '359637035594825', 'null', 'UNKNOWN', '福建省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('62', '合肥市', '2013-03-21 17:40:52', '867064017011212', '', 'EDGE', '安徽省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('63', '汕头市', '2013-03-21 17:51:28', '860308023222850', '', 'EDGE', '广东省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('64', '', '2013-03-21 19:19:20', '355994057529427', '60177179033', 'HSDPA', '', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('65', '苏州市', '2013-03-21 19:32:11', '352110050728766', '', 'EDGE', '江苏省', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('66', '钦州市', '2013-03-21 19:43:35', '358490044810571', '', 'EDGE', '广西壮族自治区', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('67', '杭州市', '2013-03-21 20:25:35', '866323012475314', '', 'HSDPA', '浙江省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('68', '郑州市', '2013-03-21 20:42:13', '99000310065676', '', 'EVDO_A', '河南省', '720X1280', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('69', '长春市', '2013-03-21 20:51:27', '867747013605017', '', 'EDGE', '吉林省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('70', '运城市', '2013-03-21 21:26:43', '869579010825167', '15803482562', 'EDGE', '山西省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('71', '济南市', '2013-03-21 21:41:56', '867747010607693', '13406185803', 'EDGE', '山东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('72', '沧州市', '2013-03-21 21:44:08', '867746018877266', '', 'EDGE', '河北省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('73', '宁波市', '2013-03-21 22:25:06', '864841010029330', '', 'EDGE', '浙江省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('74', '西安市', '2013-03-21 22:27:41', '869904010778978', '', 'EDGE', '陕西省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('75', '北京市', '2013-03-21 22:34:19', '99000219319530', '15311221672', 'EVDO_A', '北京市', '480X854', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('76', '常州市', '2013-03-21 22:36:19', '869190017853739', '', 'HSDPA', '江苏省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('77', '天津市', '2013-03-21 22:49:55', '862426010141632', '', 'HSDPA', '天津市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('78', '深圳市', '2013-03-21 22:59:07', '359160034740737', 'null', 'UNKNOWN', '广东省', '320X480', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('79', '六安市', '2013-03-21 23:10:19', '358059042838189', '', 'EDGE', '安徽省', '800X1280', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('80', '株洲市', '2013-03-21 23:13:36', '860308023813104', '15107338915', 'EDGE', '湖南省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('81', '郴州市', '2013-03-21 23:25:35', '869323000367371', '', 'EDGE', '湖南省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('82', '济南市', '2013-03-21 23:27:19', '99000310016175', '', 'EVDO_A', '山东省', '720X1280', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('83', '太原市', '2013-03-21 23:46:32', 'a1000027725bc3', '', '1xRTT', '山西省', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('84', '珠海市', '2013-03-22 00:22:53', '012379002665044', '13560479374', 'EDGE', '广东省', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('85', '济南市', '2013-03-22 00:43:22', '860308022779124', '', 'HSDPA', '山东省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('86', '西安市', '2013-03-22 01:06:03', '356812042372175', 'null', 'EDGE', '陕西省', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('87', '来宾市', '2013-03-22 01:06:32', '359616047240502', '13907826182', 'EDGE', '广西壮族自治区', '800X1280', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('88', '滁州市', '2013-03-22 01:17:19', '355065050364805', '', 'EDGE', '安徽省', '720X1280', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('89', '南宁市', '2013-03-22 01:20:37', '863734013337761', '', 'HSPA', '广西壮族自治区', '320X480', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('90', '清远市', '2013-03-22 03:20:36', '356514041477431', '', 'EDGE', '广东省', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('91', '哈尔滨市', '2013-03-22 09:13:47', '860727014804295', '', 'UNKNOWN', '黑龙江省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('92', '上海市', '2013-03-22 09:19:55', '863802014187822', '', 'EDGE', '上海市', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('93', '石家庄市', '2013-03-22 09:48:27', '357779030366494', '', 'EDGE', '河北省', '480X762', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('94', '常州市', '2013-03-22 10:22:14', '869630010650079', '', 'EDGE', '江苏省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('95', '十堰市', '2013-03-22 10:27:56', '867746016025207', '', 'UNKNOWN', '湖北省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('96', '巴中市', '2013-03-22 11:02:54', '867320009178686', '', 'EDGE', '四川省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('97', '深圳市', '2013-03-22 11:14:52', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', '@CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('98', '兰州市', '2013-03-22 12:16:44', '352935059316902', '0', 'UMTS', '甘肃省', '800X1280', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('99', '北京市', '2013-03-22 13:00:55', '358967049899337', 'null', 'UNKNOWN', '北京市', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('100', '北京市', '2013-03-22 13:00:56', '868943001025255', 'null', 'UNKNOWN', '北京市', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('101', '北京市', '2013-03-22 13:00:57', '860381000770001', 'null', 'UNKNOWN', '北京市', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('102', '北京市', '2013-03-22 13:00:58', '864046003589437', 'null', 'UNKNOWN', '北京市', '640X960', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('103', '北京市', '2013-03-22 13:00:58', '358216045644390', 'null', 'UNKNOWN', '北京市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('104', '北京市', '2013-03-22 13:01:04', '356514044410140', 'null', 'UNKNOWN', '北京市', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('105', '北京市', '2013-03-22 13:01:08', '358510042151494', 'null', 'UNKNOWN', '北京市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('106', '北京市', '2013-03-22 13:11:08', '012789001045453', 'null', 'UNKNOWN', '北京市', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('107', '许昌市', '2013-03-22 13:14:38', '864503010489936', '', 'EDGE', '河南省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('108', '太原市', '2013-03-22 13:27:41', '352343059328433', '', 'HSDPA', '山西省', '720X1280', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('109', '石家庄市', '2013-03-22 13:31:01', '867747013736473', '', 'EDGE', '河北省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('110', '北京市', '2013-03-22 13:50:36', '359704040331195', 'null', 'GPRS', '北京市', '480X800', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('111', '北京市', '2013-03-22 15:03:55', '352600052977403', 'null', 'UNKNOWN', '北京市', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('112', '北京市', '2013-03-22 15:21:55', '357194043308659', 'null', 'UMTS', '北京市', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('113', '广州市', '2013-03-22 15:23:32', '356107049706701', '', 'EDGE', '广东省', '480X800', '8', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('114', '广州市', '2013-03-22 16:01:15', '867746015546039', '', 'EDGE', '广东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('115', '衡阳市', '2013-03-22 16:36:20', 'a10000277270c8', '', 'EVDO_A', '湖南省', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('116', '北京市', '2013-03-22 17:01:26', '865687015862954', 'null', 'UNKNOWN', '北京市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('117', '北京市', '2013-03-22 17:03:46', 'null', 'null', 'UNKNOWN', '北京市', '540X960', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('118', '北京市', '2013-03-22 17:03:54', '865446010847993', 'null', 'UNKNOWN', '北京市', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('119', '北京市', '2013-03-22 17:21:28', 'null', 'null', 'UNKNOWN', '北京市', '800X1232', '13', '', null);
INSERT INTO `tb_bdg_user` VALUES ('120', '咸阳市', '2013-03-22 17:53:10', '869904011806372', '', 'EDGE', '陕西省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('121', '杭州市', '2013-03-22 18:02:27', '868051000068662', 'null', 'EDGE', '浙江省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('122', '北京市', '2013-03-22 19:30:37', '352784041297360', 'null', 'GPRS', '北京市', '480X854', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('123', '北京市', '2013-03-22 20:12:34', '004999010640000', 'null', 'GPRS', '北京市', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('124', '北京市', '2013-03-22 20:37:57', '864701012254528', 'null', 'UNKNOWN', '北京市', '480X728', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('125', '哈尔滨市', '2013-03-22 20:40:36', '336003305008256', 'null', 'UNKNOWN', '黑龙江省', '768X976', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('126', '', '2013-03-22 21:26:19', '868145010460313', 'null', 'UNKNOWN', '', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('127', '苏州市', '2013-03-22 21:29:36', '869630013525278', '', 'EDGE', '江苏省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('128', '抚顺市', '2013-03-22 21:51:01', '357952001925081', 'null', 'UNKNOWN', '辽宁省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('129', '承德市', '2013-03-22 22:15:59', '860308022130534', '15132413682', 'EDGE', '河北省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('130', '北京市', '2013-03-22 22:34:14', 'A10000136689BD', 'null', 'UNKNOWN', '北京市', '240X320', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('131', '衡水市', '2013-03-22 23:36:31', '990000723844942', '', 'GPRS', '河北省', '720X1280', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('132', '太原市', '2013-03-23 00:49:27', '869460010304489', '', 'EDGE', '山西省', '720X1200', '16', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('133', '南通市', '2013-03-23 11:41:26', '860139022926542', '', 'EDGE', '江苏省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('134', '南京市', '2013-03-23 11:59:29', '359575047238132', '', 'EDGE', '江苏省', '800X1280', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('135', '北京市', '2013-03-23 12:15:25', '863020010560740', '', 'EDGE', '北京市', '480X854', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('136', '绥化市', '2013-03-23 13:26:44', '868856010656888', '', 'EDGE', '黑龙江省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('137', '太原市', '2013-03-23 13:51:19', '867747018711216', '', 'EDGE', '山西省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('138', '徐州市', '2013-03-23 16:56:59', '867064010655999', '', 'HSDPA', '江苏省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('139', '上海市', '2013-03-23 18:52:07', '860139022132034', '', 'EDGE', '上海市', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('140', '邯郸市', '2013-03-23 21:15:10', '354059024069096', 'null', 'UNKNOWN', '河北省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('141', '珠海市', '2013-03-23 23:45:14', '867064015644774', '', 'EDGE', '广东省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('142', '衡水市', '2013-03-24 00:24:34', '99000310084286', '', 'EVDO_A', '河北省', '720X1280', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('143', '呼和浩特市', '2013-03-24 11:02:09', '864855010820142', '', 'EDGE', '内蒙古自治区', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('144', '成都市', '2013-03-24 11:30:21', '863020015544459', '', 'EDGE', '四川省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('145', '广州市', '2013-03-24 11:36:20', '869323000641015', '', 'EDGE', '广东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('146', '', '2013-03-24 13:26:34', '354792050276033', ' 6597813897', 'UNKNOWN', '', '720X1280', '16', 'SingTel', null);
INSERT INTO `tb_bdg_user` VALUES ('147', '苏州市', '2013-03-24 15:14:30', '860139024620176', '', 'EDGE', '江苏省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('148', '四平市', '2013-03-24 16:31:13', '863802015980019', '', 'EDGE', '吉林省', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('149', '四平市', '2013-03-24 16:54:23', '863802015980019', '', 'EDGE', '吉林省', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('150', '哈尔滨市', '2013-03-24 17:52:44', '865521012522669', '13512647442', 'EDGE', '黑龙江省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('151', '杭州市', '2013-03-24 19:47:08', '863986010095848', 'null', 'EDGE', '浙江省', '320X480', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('152', '绍兴市', '2013-03-24 20:00:30', 'A00000403387A7', '', 'UNKNOWN', '浙江省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('153', '郴州市', '2013-03-24 21:14:40', '868856010419675', '', 'EDGE', '湖南省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('154', '成都市', '2013-03-24 22:08:26', '863986010085617', 'null', 'EDGE', '四川省', '320X480', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('155', '南京市', '2013-03-24 23:53:36', '355026024525111', '', 'EDGE', '江苏省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('156', '晋城市', '2013-03-25 00:47:03', '867747017090620', '', 'EDGE', '山西省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('157', '泉州市', '2013-03-25 00:58:25', '867747019235330', '', 'UNKNOWN', '福建省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('158', '济南市', '2013-03-25 08:54:49', '867746013741830', '', 'EDGE', '山东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('159', '北京市', '2013-03-25 12:18:56', '863625010725083', 'null', 'UNKNOWN', '北京市', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('160', '保定市', '2013-03-25 12:37:56', '869734000388425', '', 'EDGE', '河北省', '320X480', '8', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('161', '武汉市', '2013-03-25 13:04:31', 'A10000327115A8E', '', 'EVDO_A', '湖北省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('162', '济南市', '2013-03-25 13:18:58', '864991010373871', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('163', '佛山市', '2013-03-25 13:35:54', '865030017409757', '', 'EDGE', '广东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('164', '苏州市', '2013-03-25 14:59:24', '352375053363800', 'null', 'EDGE', '江苏省', '800X1280', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('165', '深圳市', '2013-03-25 15:14:11', '0', '', 'EVDO_A', '广东省', '720X1280', '17', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('166', '', '2013-03-25 15:25:39', '868033018108458', 'null', 'UNKNOWN', '香港特别行政区', '800X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('167', '宜昌市', '2013-03-25 15:42:04', '990001392902185', '', 'EVDO_A', '湖北省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('168', '淮安市', '2013-03-25 15:50:38', '869579010579517', '', 'EDGE', '江苏省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('169', '天津市', '2013-03-25 16:10:39', '865030012550043', '0', 'HSPA', '天津市', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('170', '郑州市', '2013-03-25 18:16:22', '868772011795122', '', 'GPRS', '河南省', '540X960', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('171', '温州市', '2013-03-25 18:19:18', '867739013563759', '', 'EDGE', '浙江省', '540X960', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('172', '西安市', '2013-03-25 18:20:31', '990002147695959', '', 'EVDO_A', '陕西省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('173', '合肥市', '2013-03-25 18:39:21', 'A00000431A5B29', '', 'EVDO_A', '安徽省', '480X854', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('174', '晋城市', '2013-03-25 19:12:22', '990002170445587', '', 'EVDO_A', '山西省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('175', '西安市', '2013-03-25 21:02:34', 'A10000326397E44', '', 'EVDO_A', '陕西省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('176', '西安市', '2013-03-25 21:22:44', 'A10000326397E44', '', 'EVDO_A', '陕西省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('177', '安阳市', '2013-03-25 22:41:10', '869197012006817', '', 'EDGE', '河南省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('178', '西安市', '2013-03-25 22:53:16', 'A10000326397E44', '', 'EVDO_A', '陕西省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('179', '大理白族自治州', '2013-03-25 23:37:32', '865215012227714', 'null', 'EDGE', '云南省', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('180', '重庆市', '2013-03-25 23:52:50', '869326012532502', '', 'HSPA', '重庆市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('181', '濮阳市', '2013-03-26 00:01:35', '867125016692953', '', 'EDGE', '河南省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('182', '德阳市', '2013-03-26 00:45:14', '869197015351251', '', 'UMTS', '四川省', '320X480', '10', '中国联通', null);
INSERT INTO `tb_bdg_user` VALUES ('183', '九江市', '2013-03-26 00:45:38', '866324010648704', '', 'EDGE', '江西省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('184', '合肥市', '2013-03-26 02:32:31', 'A10000326EE85BF', '', 'EVDO_A', '安徽省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('185', '哈尔滨市', '2013-03-26 04:10:19', '990002143214789', '', 'EVDO_A', '黑龙江省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('186', '深圳市', '2013-03-26 04:25:33', 'A10000326464916', '', 'EVDO_A', '广东省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('187', '天津市', '2013-03-26 08:03:45', '865030012550043', '0', 'HSPA', '天津市', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('188', '西宁市', '2013-03-26 08:24:48', '990001409215050', '', 'EVDO_A', '青海省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('189', '长春市', '2013-03-26 09:03:33', '990001401807961', '', 'EVDO_A', '吉林省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('190', '长春市', '2013-03-26 09:05:05', '990001401807961', '', 'EVDO_A', '吉林省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('191', '兰州市', '2013-03-26 09:31:09', 'A1000032537E244', '', 'EVDO_A', '甘肃省', '240X320', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('192', '温州市', '2013-03-26 09:38:36', '863625010447035', 'null', 'UNKNOWN', '浙江省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('193', '南宁市', '2013-03-26 09:46:27', '869459001472800', 'null', 'EDGE', '广西壮族自治区', '320X480', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('194', '天津市', '2013-03-26 10:14:29', '867746013974480', '', 'EDGE', '天津市', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('195', '沈阳市', '2013-03-26 10:28:12', '355056052201483', '', 'EDGE', '辽宁省', '720X1280', '16', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('196', '遵义市', '2013-03-26 10:31:00', '866063012058427', '', 'EDGE', '贵州省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('197', '天津市', '2013-03-26 10:52:26', '860308026171898', '13512274787', 'EDGE', '天津市', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('198', '北京市', '2013-03-26 11:11:17', '353979052616610', 'null', 'EDGE', '北京市', '480X800', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('199', '北京市', '2013-03-26 11:17:58', '866324011687768', '', 'UMTS', '北京市', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('200', '成都市', '2013-03-26 11:21:27', '860173014992774', '', 'EDGE', '四川省', '480X854', '15', '动感地带', null);
INSERT INTO `tb_bdg_user` VALUES ('201', '重庆市', '2013-03-26 11:29:06', '866324012434350', '', 'EDGE', '重庆市', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('202', '北京市', '2013-03-26 12:06:35', '357194041602343', '', 'EDGE', '北京市', '480X800', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('203', '吉林市', '2013-03-26 12:08:51', '990002160389043', '', 'UNKNOWN', '吉林省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('204', '兰州市', '2013-03-26 12:10:58', '990000497825184', '0000000000', 'EVDO_A', '甘肃省', '480X960', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('205', '武汉市', '2013-03-26 12:13:48', '864903010999894', '', 'EDGE', '湖北省', '480X800', '10', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('206', '成都市', '2013-03-26 14:29:41', '352961047602427', 'null', 'UMTS', '四川省', '600X1024', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('207', '深圳市', '2013-03-26 15:16:34', '864075010132360', '', 'UNKNOWN', '广东省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('208', '深圳市', '2013-03-26 15:35:05', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('209', '合肥市', '2013-03-26 15:38:08', '863414016265075', '', 'HSPA', '安徽省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('210', '济南市', '2013-03-26 15:48:44', '860755013467160', '', 'UMTS', '山东省', '640X960', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('211', '石家庄市', '2013-03-26 16:09:47', '865894011636784', '', 'EDGE', '河北省', '480X800', '15', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('212', '宣城市', '2013-03-26 16:56:13', '860755011315668', '', 'EDGE', '安徽省', '640X960', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('213', '北京市', '2013-03-26 17:01:40', '99000310240845', '', 'EVDO_A', '北京市', '720X1280', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('214', '金华市', '2013-03-26 17:28:42', '864687011144002', '', 'EDGE', '浙江省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('215', '深圳市', '2013-03-26 17:41:46', '864686011553238', '13751675162', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('216', '钦州市', '2013-03-26 17:49:31', '864687010641529', '', 'EDGE', '广西壮族自治区', '640X960', '15', '@CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('217', '重庆市', '2013-03-26 18:08:57', 'A100002212D1881', '', 'EVDO_A', '重庆市', '240X320', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('218', '杭州市', '2013-03-26 18:21:07', '990002147713182', '', 'EVDO_A', '浙江省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('219', '三门峡市', '2013-03-26 19:13:32', '866324012594179', '', 'HSPA', '河南省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('220', '长沙市', '2013-03-26 19:48:54', '865894012337747', '', 'EDGE', '湖南省', '480X800', '15', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('221', '温州市', '2013-03-26 20:13:13', '864046008564914', '', 'EDGE', '浙江省', '640X960', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('222', '昆明市', '2013-03-26 20:13:43', '865030012988904', '', 'EDGE', '云南省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('223', '南京市', '2013-03-26 20:41:44', '990002165524636', '', 'EVDO_A', '江苏省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('224', '嘉兴市', '2013-03-26 21:18:49', '990002161910086', '', 'EVDO_A', '浙江省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('225', '濮阳市', '2013-03-26 21:34:48', '869326010441813', '', 'EDGE', '河南省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('226', '佛山市', '2013-03-26 21:39:09', '869579012454511', '', 'EDGE', '广东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('227', '惠州市', '2013-03-26 21:46:47', '869153010174503', '', 'UMTS', '广东省', '540X960', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('228', '毕节地区', '2013-03-26 22:00:56', '867064018590750', '', 'EDGE', '贵州省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('229', '天津市', '2013-03-26 22:02:01', '990001400314381', '', 'EVDO_A', '天津市', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('230', '南京市', '2013-03-26 22:08:36', 'A1000022088AA5C', '', 'EVDO_A', '江苏省', '480X800', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('231', '日照市', '2013-03-26 22:44:19', '867747015110982', '', 'HSDPA', '山东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('232', '武汉市', '2013-03-26 23:08:26', '354330054564318', '15871571769', 'EDGE', '湖北省', '320X480', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('233', '固原市', '2013-03-26 23:43:20', '990002161731110', '', 'EVDO_A', '宁夏回族自治区', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('234', '南宁市', '2013-03-27 00:11:58', '868772013410795', '', 'HSPA', '广西壮族自治区', '540X960', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('235', '广州市', '2013-03-27 01:04:14', '864687010310521', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('236', '深圳市', '2013-03-27 01:17:15', '864046008518555', '', 'HSPA', '广东省', '640X960', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('237', '杭州市', '2013-03-27 01:53:13', '860308025613114', '', 'EDGE', '浙江省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('238', '昆明市', '2013-03-27 08:45:23', '869326012222377', '', 'EDGE', '云南省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('239', '洛阳市', '2013-03-27 08:54:00', '868772011024275', '13693802779', 'EDGE', '河南省', '540X960', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('240', '聊城市', '2013-03-27 09:38:00', '990002178401756', '', '1xRTT', '山东省', '540X960', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('241', '太原市', '2013-03-27 10:37:39', '860755011634498', '', 'EDGE', '山西省', '640X960', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('242', '盐城市', '2013-03-27 11:12:03', '864046001517588', 'null', 'EDGE', '江苏省', '640X960', '15', '@@', null);
INSERT INTO `tb_bdg_user` VALUES ('243', '太原市', '2013-03-27 11:23:07', 'A000002CC9F331', '', 'EVDO_A', '山西省', '540X894', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('244', '长春市', '2013-03-27 11:55:09', '866162014539150', '', 'EDGE', '吉林省', '320X480', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('245', '', '2013-03-27 13:43:25', '866045010283230', '', 'GPRS', '四川省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('246', '西安市', '2013-03-27 14:26:03', '354356050031689', 'null', 'UNKNOWN', '陕西省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('247', '西安市', '2013-03-27 14:27:01', '354356050031689', 'null', 'UNKNOWN', '陕西省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('248', '西安市', '2013-03-27 14:28:20', '355031040326395', 'null', 'HSDPA', '陕西省', '480X800', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('249', '北京市', '2013-03-27 14:39:53', '990002148636135', '', 'EVDO_A', '北京市', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('250', '杭州市', '2013-03-27 14:41:37', '864046008099929', '', 'EDGE', '浙江省', '640X960', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('251', '吕梁市', '2013-03-27 15:05:01', '868350015128531', '', 'HSDPA', '山西省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('252', '宝鸡市', '2013-03-27 15:07:55', '990001403689359', '', 'EVDO_A', '陕西省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('253', '北京市', '2013-03-27 17:52:33', '358314040159207', 'null', 'UNKNOWN', '北京市', '540X960', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('254', '深圳市', '2013-03-27 18:08:33', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', 'KYBMAORC01');
INSERT INTO `tb_bdg_user` VALUES ('255', '深圳市', '2013-03-27 18:18:43', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', 'KYB_AORC_01');
INSERT INTO `tb_bdg_user` VALUES ('256', '合肥市', '2013-03-27 19:27:26', 'a0000037a43c37', '', 'EVDO_A', '安徽省', '540X960', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('257', '武汉市', '2013-03-27 19:42:36', '867183011201585', '', 'EDGE', '湖北省', '480X800', '10', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('258', '深圳市', '2013-03-27 19:48:03', 'a000004203ab2f', '', 'EVDO_A', '广东省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('259', '永州市', '2013-03-27 20:13:43', 'a0000042f7a489', 'null', 'UNKNOWN', '湖南省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('260', '柳州市', '2013-03-27 20:18:45', 'a000004203e84a', '', 'EVDO_A', '广西壮族自治区', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('261', '青岛市', '2013-03-27 20:23:02', '861753009737349', '', 'EDGE', '山东省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('262', '铁岭市', '2013-03-27 20:24:52', 'a000002e19e275', '', 'EVDO_A', '辽宁省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('263', '西宁市', '2013-03-27 20:26:23', '353632051926916', '', 'EDGE', '青海省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('264', '潮州市', '2013-03-27 20:26:32', '864394015765995', '', 'EDGE', '广东省', '320X480', '10', 'cn', null);
INSERT INTO `tb_bdg_user` VALUES ('265', '郑州市', '2013-03-27 20:27:05', '990002146600992', '', 'EVDO_A', '河南省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('266', '吉林市', '2013-03-27 20:32:01', 'A000003718541D', '', 'EVDO_A', '吉林省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('267', '兰州市', '2013-03-27 20:32:15', 'A0000042C817C2', '', 'EVDO_A', '甘肃省', '480X854', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('268', '运城市', '2013-03-27 20:37:24', 'A000002FDB05E1', '', 'EVDO_A', '山西省', '240X320', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('269', '广州市', '2013-03-27 20:56:36', '863802016234218', '15907655012', 'EDGE', '广东省', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('270', '湖州市', '2013-03-27 21:11:27', 'a0000036be6c6f', '', 'EVDO_A', '浙江省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('271', '长春市', '2013-03-27 21:14:49', '860755011976485', '15844090246', 'EDGE', '吉林省', '640X960', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('272', '苏州市', '2013-03-27 21:41:09', '864686010773621', '', 'EDGE', '江苏省', '640X960', '15', '@CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('273', '北京市', '2013-03-27 22:24:58', '359788040332915', 'null', 'EDGE', '北京市', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('274', '昆明市', '2013-03-27 22:25:10', '864686010530997', '', 'EDGE', '云南省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('275', '广州市', '2013-03-27 22:32:17', '864687010751971', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('276', '济南市', '2013-03-27 22:33:11', '864687010456795', '', 'EDGE', '山东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('277', '芜湖市', '2013-03-27 22:39:59', '864046000257608', 'null', 'UNKNOWN', '安徽省', '640X960', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('278', '滁州市', '2013-03-27 23:16:12', '990002147197022', '', 'EVDO_A', '安徽省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('279', '合肥市', '2013-03-28 01:02:03', 'a000003756c2a8', '', 'EVDO_A', '安徽省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('280', '江门市', '2013-03-28 02:01:56', '860308022147181', '', 'EDGE', '广东省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('281', '银川市', '2013-03-28 07:01:15', '990002160122345', '18995317855', 'EVDO_A', '宁夏回族自治区', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('282', '太原市', '2013-03-28 07:31:30', '686072813442141', 'null', 'UNKNOWN', '山西省', '600X976', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('283', '徐州市', '2013-03-28 08:05:53', '990001401136205', '', 'EVDO_A', '江苏省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('284', '临汾市', '2013-03-28 08:48:17', '359826040092921', '', 'UMTS', '山西省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('285', '南京市', '2013-03-28 10:22:29', '860755012446660', '', 'EDGE', '江苏省', '640X960', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('286', '西安市', '2013-03-28 12:34:52', '990002164422832', '', 'EVDO_A', '陕西省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('287', '深圳市', '2013-03-28 12:51:18', '864046008688242', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('288', '北京市', '2013-03-28 13:06:37', '861892010754412', 'null', 'UNKNOWN', '北京市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('289', '北京市', '2013-03-28 13:26:20', '864687011373965', '', 'EDGE', '北京市', '640X960', '16', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('290', '上海市', '2013-03-28 14:46:33', '864687012035233', '', 'HSPA', '上海市', '640X960', '15', '@', null);
INSERT INTO `tb_bdg_user` VALUES ('291', '济南市', '2013-03-28 15:24:40', '868029010000917', '', 'EDGE', '山东省', '480X800', '15', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('292', '晋中市', '2013-03-28 20:03:42', 'A000002FDB05E1', '', 'EVDO_A', '山西省', '240X320', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('293', '惠州市', '2013-03-28 20:58:35', '869904013430569', '', 'EDGE', '广东省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('294', '合肥市', '2013-03-28 21:02:21', '990002142210465', '', 'EVDO_A', '安徽省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('295', '太原市', '2013-03-28 22:27:24', '867183012334450', '', 'UMTS', '山西省', '480X800', '10', '中国联通', null);
INSERT INTO `tb_bdg_user` VALUES ('296', '邯郸市', '2013-03-29 03:11:36', '869197012676676', '', 'EDGE', '河北省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('297', '福州市', '2013-03-29 03:16:41', '864687010132131', '', 'HSPA', '福建省', '640X960', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('298', '石家庄市', '2013-03-29 14:17:00', '860856010751703', '', 'HSDPA', '河北省', '480X800', '8', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('299', '南京市', '2013-03-29 18:02:50', 'A10000326D33E69', '', 'EVDO_A', '江苏省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('300', '中山市', '2013-03-29 19:37:16', '864686010643063', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('301', '哈尔滨市', '2013-03-29 20:48:16', '864046001639838', 'null', 'UNKNOWN', '黑龙江省', '640X960', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('302', '济南市', '2013-03-29 21:35:49', '860139025860144', '', 'EDGE', '山东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('303', '北京市', '2013-03-29 21:40:58', '868772016619186', '', 'EDGE', '北京市', '540X960', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('304', '', '2013-03-29 22:14:20', '353328051894481', '', 'HSDPA', '澳门特别行政区', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('305', '', '2013-03-29 22:25:46', '353845050571323', '89241361111', 'EDGE', '', '720X1280', '16', 'MegaFon', null);
INSERT INTO `tb_bdg_user` VALUES ('306', '昆明市', '2013-03-29 22:39:24', '862321010441191', '', 'UNKNOWN', '云南省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('307', '乌鲁木齐市', '2013-03-29 22:49:09', '862764011517297', '', 'EDGE', '新疆维吾尔自治区', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('308', '阿克苏地区', '2013-03-30 00:18:42', '353818055305062', '', 'EDGE', '新疆维吾尔自治区', '720X1280', '16', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('309', '焦作市', '2013-03-30 00:32:01', '869579012115559', '', 'EDGE', '河南省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('310', '中山市', '2013-03-30 01:15:16', '358593043508532', '', 'EDGE', '广东省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('311', '长沙市', '2013-03-30 02:51:54', 'A0000042D77F86', '', 'EVDO_A', '湖南省', '480X854', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('312', '石家庄市', '2013-03-30 03:07:47', '990001400157921', '', 'EVDO_A', '河北省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('313', '宁波市', '2013-03-30 10:52:24', '354096058758772', '', 'EDGE', '浙江省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('314', '贵阳市', '2013-03-30 11:21:03', '867183011938434', '', 'EDGE', '贵州省', '480X800', '10', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('315', '昆明市', '2013-03-30 11:25:33', '864046004988745', '', 'EDGE', '云南省', '640X960', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('316', '长沙市', '2013-03-30 16:55:00', '865316012367169', '', 'HSDPA', '湖南省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('317', '济南市', '2013-03-30 17:03:40', 'A10000265961D5', '', 'EVDO_A', '山东省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('318', '杭州市', '2013-03-30 20:13:03', '865923011545131', '', 'GPRS', '浙江省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('319', '湖州市', '2013-03-30 20:28:46', '868033018158636', '', 'EDGE', '浙江省', '800X1280', '16', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('320', '嘉兴市', '2013-03-30 21:31:15', 'a000004209c9b7', '', 'EVDO_A', '浙江省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('321', '石家庄市', '2013-03-30 21:31:46', 'A0000042D665A3', '', '1xRTT', '河北省', '480X854', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('322', '唐山市', '2013-03-30 21:34:59', 'A1000030E3C0AD', 'null', 'EVDO_A', '河北省', '480X854', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('323', '重庆市', '2013-03-30 21:35:36', 'A0000039DDE053', '', 'EVDO_A', '重庆市', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('324', '邯郸市', '2013-03-30 21:37:33', '866063019938126', '15227987124', 'UNKNOWN', '河北省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('325', '郴州市', '2013-03-30 21:40:37', 'A000002E77B8E3', '', 'EVDO_A', '湖南省', '480X800', '16', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('326', '南京市', '2013-03-30 21:42:05', 'A00000306EBB15', '', 'EVDO_A', '江苏省', '480X800', '8', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('327', '西安市', '2013-03-30 21:45:21', 'a0000036b9d9e9', '', 'EVDO_A', '陕西省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('328', '天津市', '2013-03-30 21:49:53', '861416010870612', '', 'EDGE', '天津市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('329', '威海市', '2013-03-30 21:52:42', '867064013646961', '', 'EDGE', '山东省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('330', '成都市', '2013-03-30 22:18:19', '355352050359005', '', 'EDGE', '四川省', '800X1280', '15', '动感地带', null);
INSERT INTO `tb_bdg_user` VALUES ('331', '赤峰市', '2013-03-30 22:25:26', 'A1000032667DDFD', '', 'EVDO_A', '内蒙古自治区', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('332', '呼伦贝尔市', '2013-03-30 23:26:12', '867747014808065', '', 'EDGE', '内蒙古自治区', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('333', '南京市', '2013-03-30 23:49:59', 'A00000401A953E', '', 'EVDO_A', '江苏省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('334', '鄂尔多斯市', '2013-03-30 23:54:02', 'A00000427D5C59', '', 'EVDO_A', '内蒙古自治区', '480X854', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('335', '南京市', '2013-03-31 00:09:08', 'a0000042575f77', '', 'EVDO_A', '江苏省', '540X960', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('336', '惠州市', '2013-03-31 00:41:12', '357070003329556', '0', 'UNKNOWN', '广东省', '480X764', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('337', '天津市', '2013-03-31 00:44:06', '861701011991271', 'null', 'UNKNOWN', '天津市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('338', '鄂州市', '2013-03-31 03:45:19', '860755012732374', '', 'EDGE', '湖北省', '640X960', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('339', '南京市', '2013-03-31 06:28:26', '868208010014223', '', 'EDGE', '江苏省', '480X800', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('340', '深圳市', '2013-03-31 06:42:02', '864046008571034', '', 'HSPA', '广东省', '640X960', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('341', '运城市', '2013-03-31 13:01:49', '862726016456235', '', 'EDGE', '山西省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('342', '北京市', '2013-03-31 13:19:40', '865427014866544', '13161262860', 'UMTS', '北京市', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('343', '合肥市', '2013-03-31 14:10:50', 'A10000326A70A80', '', 'EVDO_A', '安徽省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('344', '南京市', '2013-03-31 14:37:36', '868879018847010', '15950490491', 'EDGE', '江苏省', '480X800', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('345', '绍兴市', '2013-03-31 16:01:11', '869111010448269', '', 'EDGE', '浙江省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('346', '清远市', '2013-03-31 17:02:14', '860139021600890', '', 'HSDPA', '广东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('347', '银川市', '2013-03-31 19:03:14', '864687010293123', '', 'EDGE', '宁夏回族自治区', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('348', '青岛市', '2013-03-31 21:08:14', '990002147956989', '', 'EVDO_A', '山东省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('349', '郑州市', '2013-03-31 21:09:43', '868033019131921', '', 'EDGE', '河南省', '800X1280', '16', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('350', '福州市', '2013-03-31 22:28:51', '990002168716049', '', 'EVDO_A', '福建省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('351', '北京市', '2013-03-31 23:02:53', '358913047051786', '', 'EDGE', '北京市', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('352', '上海市', '2013-03-31 23:24:33', 'a0000037a13d01', '', 'EVDO_A', '上海市', '540X960', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('353', '深圳市', '2013-04-01 00:11:32', '000000000000000', '15555215554', 'UMTS', '广东省', '320X480', '8', 'Android', 'KYB_AORC_01');
INSERT INTO `tb_bdg_user` VALUES ('354', '乌鲁木齐市', '2013-04-01 01:24:36', '865334002671474', '', 'EDGE', '新疆维吾尔自治区', '320X480', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('355', '太原市', '2013-04-01 06:37:29', '867739013940197', '', 'UMTS', '山西省', '540X960', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('356', '南京市', '2013-04-01 10:57:22', '860662000633319', 'null', 'GPRS', '江苏省', '320X480', '8', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('357', '重庆市', '2013-04-01 11:31:21', '864687012721766', '', 'EDGE', '重庆市', '640X960', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('358', '深圳市', '2013-04-01 14:30:06', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', 'KYB_AORC_01');
INSERT INTO `tb_bdg_user` VALUES ('359', '大庆市', '2013-04-01 15:53:06', 'A000002FD6B7BA', '', 'EVDO_A', '黑龙江省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('360', '石家庄市', '2013-04-01 19:53:31', '990002149642686', '', 'EVDO_A', '河北省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('361', '', '2013-04-01 20:01:50', '866063011019313', '', 'EDGE', '山东省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('362', '杭州市', '2013-04-01 20:22:31', '866351011768959', '', 'GPRS', '浙江省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('363', '南京市', '2013-04-01 20:44:27', 'a000002e54efc8', '', 'EVDO_A', '江苏省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('364', '泉州市', '2013-04-01 21:35:33', '866580019635386', '', 'GPRS', '福建省', '320X480', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('365', '南昌市', '2013-04-02 08:17:07', '863324010437481', '', 'EDGE', '江西省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('366', '成都市', '2013-04-02 10:20:57', 'A100003254FAEC5', '', 'EVDO_A', '四川省', '480X800', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('367', '张家口市', '2013-04-02 18:53:49', '869579013446300', '', 'EDGE', '河北省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('368', '威海市', '2013-04-02 20:35:37', '867746014153449', '', 'HSDPA', '山东省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('369', '西安市', '2013-04-03 01:14:43', '990001401999636', '', 'EVDO_A', '陕西省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('370', '太原市', '2013-04-03 07:41:16', '860618016113175', '', 'EDGE', '山西省', '320X480', '8', '', null);
INSERT INTO `tb_bdg_user` VALUES ('371', '杭州市', '2013-04-03 09:07:50', '863802020089954', '', 'EDGE', '浙江省', '480X854', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('372', '潮州市', '2013-04-03 13:27:43', '864394015765995', '', 'EDGE', '广东省', '320X480', '10', 'cn', null);
INSERT INTO `tb_bdg_user` VALUES ('373', '天津市', '2013-04-03 14:46:22', '860308023424548', '', 'UMTS', '天津市', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('374', '南京市', '2013-04-03 15:10:27', '864048012804799', '', 'EDGE', '江苏省', '480X800', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('375', '长沙市', '2013-04-03 16:45:48', '353627055524618', '', 'UNKNOWN', '湖南省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('376', '呼和浩特市', '2013-04-03 23:27:19', 'a1000012a608a6', '', 'EVDO_A', '内蒙古自治区', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('377', '北京市', '2013-04-03 23:40:52', '861410010956448', '', 'EDGE', '北京市', '480X800', '10', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('378', '', '2013-04-04 00:06:20', 'A100002207EA516', '', 'EVDO_A', '新疆维吾尔自治区', '480X800', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('379', '深圳市', '2013-04-04 07:15:31', '862765011418907', '', 'HSPA', '广东省', '480X800', '10', '中国联通', null);
INSERT INTO `tb_bdg_user` VALUES ('380', '深圳市', '2013-04-04 07:15:38', '862765011418907', '', 'HSPA', '广东省', '480X800', '10', '中国联通', null);
INSERT INTO `tb_bdg_user` VALUES ('381', '合肥市', '2013-04-04 08:20:05', 'A100002219ECA5F', '', 'EVDO_A', '安徽省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('382', '潮州市', '2013-04-04 11:24:16', '864394015765995', '', 'EDGE', '广东省', '320X480', '10', 'cn', null);
INSERT INTO `tb_bdg_user` VALUES ('383', '温州市', '2013-04-04 13:13:54', '864686011350593', '', 'EDGE', '浙江省', '640X960', '15', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('384', '福州市', '2013-04-04 23:00:19', '866365015774938', '', 'EDGE', '福建省', '480X728', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('385', '银川市', '2013-04-04 23:28:19', '861416010784409', '', 'HSDPA', '宁夏回族自治区', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('386', '潮州市', '2013-04-05 15:06:44', '864394015765995', '', 'EDGE', '广东省', '320X480', '10', 'cn', null);
INSERT INTO `tb_bdg_user` VALUES ('387', '武汉市', '2013-04-05 15:50:15', 'A10000326AE0E06', '', 'EVDO_A', '湖北省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('388', '', '2013-04-05 17:57:16', '860173013749142', '', 'UMTS', '香港特别行政区', '480X854', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('389', '娄底市', '2013-04-05 22:02:41', '867746012944930', '', 'EDGE', '湖南省', '480X854', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('390', '温州市', '2013-04-05 23:32:17', '863802020089954', '', 'EDGE', '浙江省', '480X854', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('391', '天津市', '2013-04-06 09:27:21', '865894012400446', '', 'EDGE', '天津市', '480X800', '15', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('392', '南京市', '2013-04-06 10:40:55', '990001405811175', '', 'EVDO_A', '江苏省', '320X480', '10', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('393', '潮州市', '2013-04-06 14:49:55', '864394015765995', '', 'EDGE', '广东省', '320X480', '10', 'cn', null);
INSERT INTO `tb_bdg_user` VALUES ('394', '天津市', '2013-04-06 16:56:29', '860308025613114', '', 'EDGE', '天津市', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('395', '无锡市', '2013-04-07 13:19:53', '860308026152336', '', 'UNKNOWN', '江苏省', '720X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('396', '九江市', '2013-04-07 15:07:00', '868033011528249', '', 'HSPA', '江西省', '800X1280', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('397', '深圳市', '2013-04-07 15:42:57', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', 'KYB_AORC_01');
INSERT INTO `tb_bdg_user` VALUES ('398', '梧州市', '2013-04-07 16:54:26', '868128013758982', '', 'EDGE', '广西壮族自治区', '540X960', '15', '中国移动', null);
INSERT INTO `tb_bdg_user` VALUES ('399', '深圳市', '2013-04-07 18:29:55', '864046004369375', '', 'EDGE', '广东省', '640X960', '15', 'CMCC', 'KYB_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('400', '深圳市', '2013-04-07 19:35:07', 'A000002C90022B', '', 'EVDO_A', '广东省', '720X1280', '15', '中国电信', 'KYB_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('401', '杭州市', '2013-04-07 20:53:44', '355533054030862', '', 'EDGE', '浙江省', '720X1280', '16', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('402', '长沙市', '2013-04-07 21:25:29', '990001402181531', '', 'EVDO_A', '湖南省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('403', '武汉市', '2013-04-07 21:44:16', 'A0000042E767D5', '', 'EVDO_A', '湖北省', '480X800', '16', '', null);
INSERT INTO `tb_bdg_user` VALUES ('404', '北京市', '2013-04-08 07:16:24', '99000310240845', '', 'EVDO_A', '北京市', '720X1280', '16', '中国电信', 'KYB_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('405', '青岛市', '2013-04-08 11:38:28', '861753009737349', '', 'EDGE', '山东省', '480X800', '15', '', 'KYB_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('406', '深圳市', '2013-04-08 14:36:50', '356521044060494', '', 'EDGE', '广东省', '540X960', '16', 'GoTone', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('407', '天津市', '2013-04-08 14:37:59', '860308023424548', '', 'UNKNOWN', '天津市', '720X1280', '16', '', 'KYB_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('408', '', '2013-04-08 15:16:24', '351863046976123', '13828697612', 'UMTS', '广东省', '240X320', '10', 'Android', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('409', '', '2013-04-08 15:16:24', '351863046976123', '13828697612', 'UMTS', '广东省', '240X320', '10', 'Android', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('410', '成都市', '2013-04-08 15:29:20', 'null', 'null', 'UNKNOWN', '四川省', '800X1280', '12', '', 'TX_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('411', '北京市', '2013-04-08 16:00:08', '354765050475896', 'null', 'UNKNOWN', '北京市', '1080X1920', '16', '', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('412', '', '2013-04-08 16:07:09', '351863041805423', '13828180542', 'UMTS', '广东省', '240X320', '10', 'Android', 'YYSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('413', '', '2013-04-08 16:07:09', '351863041805423', '13828180542', 'UMTS', '广东省', '240X320', '10', 'Android', 'YYSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('414', '济南市', '2013-04-08 17:40:57', '862812010206527', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('415', '济南市', '2013-04-08 18:10:45', '862812010042336', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('416', '滁州市', '2013-04-08 18:20:46', '990001400481529', '', 'EVDO_A', '安徽省', '480X800', '15', '中国电信', null);
INSERT INTO `tb_bdg_user` VALUES ('417', '郑州市', '2013-04-08 18:34:05', '862812010031974', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('418', '昆明市', '2013-04-08 19:10:23', 'A000003296AF04', '', 'EVDO_A', '云南省', '240X320', '8', '', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('419', '武汉市', '2013-04-08 19:37:21', '862812010043722', 'null', 'EDGE', '湖北省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('420', '石家庄市', '2013-04-08 19:38:54', '861698002272598', '', 'EDGE', '河北省', '480X800', '8', '', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('421', '西安市', '2013-04-08 20:59:55', '352288051034861', '', 'EDGE', '陕西省', '320X480', '10', 'CMCC', 'TX_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('422', '苏州市', '2013-04-08 21:15:05', '866707017209498', '13771171813', 'EDGE', '江苏省', '540X960', '16', '', 'TX_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('423', '昆明市', '2013-04-08 21:17:39', '862812010043789', '15924790727', 'EDGE', '云南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('424', '广州市', '2013-04-08 21:18:18', '352751019523267', '18300175563', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('425', '深圳市', '2013-04-08 21:55:34', '865072010026097', 'null', 'EDGE', '广东省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('426', '天津市', '2013-04-08 22:05:23', '865072010159179', 'null', 'EDGE', '天津市', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('427', '天津市', '2013-04-08 22:13:34', '865072010159179', 'null', 'EDGE', '天津市', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('428', '内江市', '2013-04-08 22:22:12', '869904010808726', '', 'EDGE', '四川省', '720X1280', '16', '', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('429', '合肥市', '2013-04-08 22:32:35', '862812010055643', 'null', 'EDGE', '安徽省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('430', '延边朝鲜族自治州', '2013-04-08 23:17:13', '353723050612837', '55', 'EDGE', '吉林省', '720X1280', '10', '', 'TX_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('431', '深圳市', '2013-04-09 00:16:51', '865072010167917', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('432', '', '2013-04-09 01:37:58', '351863043214193', '13828321419', 'UMTS', '广东省', '240X320', '10', 'Android', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('433', '', '2013-04-09 01:37:58', '351863043214193', '13828321419', 'UMTS', '广东省', '240X320', '10', 'Android', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('434', '昆明市', '2013-04-09 01:56:07', '862812010050438', 'null', 'EDGE', '云南省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('435', '深圳市', '2013-04-09 02:06:22', '862812010038680', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('436', '成都市', '2013-04-09 02:44:37', '862812010038193', 'null', 'EDGE', '四川省', '320X480', '10', '神州行', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('437', '郴州市', '2013-04-09 05:54:16', '861343001402603', '15111489878', 'EDGE', '湖南省', '320X480', '10', '', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('438', '成都市', '2013-04-09 06:34:46', '865072010000977', 'null', 'EDGE', '四川省', '320X480', '10', '神州行', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('439', '深圳市', '2013-04-09 06:54:42', '865072010166463', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('440', '深圳市', '2013-04-09 06:58:47', '865072010157116', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('441', '西安市', '2013-04-09 07:47:50', '862812010046816', 'null', 'EDGE', '陕西省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('442', '济南市', '2013-04-09 08:25:05', '862812010047186', 'null', 'EDGE', '山东省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('443', '郑州市', '2013-04-09 09:07:34', '862812010044332', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('444', '济南市', '2013-04-09 09:18:54', '865072010156357', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('445', '南宁市', '2013-04-09 09:26:05', '865072010173246', 'null', 'EDGE', '广西壮族自治区', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('446', '南京市', '2013-04-09 09:28:36', '864588010229911', '13655141071', 'EDGE', '江苏省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('447', '安阳市', '2013-04-09 09:47:11', '869904011391565', '', 'UNKNOWN', '河南省', '720X1280', '16', '', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('448', '深圳市', '2013-04-09 09:48:10', '862812010041197', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('449', '银川市', '2013-04-09 09:59:07', '862812010224538', 'null', 'EDGE', '宁夏回族自治区', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('450', '济南市', '2013-04-09 10:11:10', '862812010045750', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('451', '郴州市', '2013-04-09 10:41:31', '862812010207111', 'null', 'EDGE', '湖南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('452', '中山市', '2013-04-09 10:43:39', 'A0000042E8A38F', '', 'EVDO_A', '广东省', '480X854', '16', '中国电信', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('453', '广州市', '2013-04-09 10:52:19', '865072010176041', 'null', 'EDGE', '广东省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('454', '呼和浩特市', '2013-04-09 10:59:26', '862812010040249', 'null', 'EDGE', '内蒙古自治区', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('455', '合肥市', '2013-04-09 11:02:27', '862812010054471', 'null', 'EDGE', '安徽省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('456', '北京市', '2013-04-09 11:07:26', '860173010004640', 'null', 'UNKNOWN', '北京市', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('457', '滨州市', '2013-04-09 11:07:45', '862812010056997', 'null', 'EDGE', '山东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('458', '河源市', '2013-04-09 11:22:13', '356871048228609', '0', 'EDGE', '广东省', '540X960', '15', 'CMCC', 'AZW_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('459', '深圳市', '2013-04-09 11:31:15', '865072010164393', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('460', '深圳市', '2013-04-09 11:42:52', '860876010018663', 'null', 'UNKNOWN', '广东省', '540X960', '15', '', null);
INSERT INTO `tb_bdg_user` VALUES ('461', '郑州市', '2013-04-09 11:47:44', '862812010042708', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('462', '福州市', '2013-04-09 11:53:59', '358967049974809', 'null', 'UNKNOWN', '福建省', '480X800', '15', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('463', '济南市', '2013-04-09 11:59:08', '862812010208010', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('464', '昆明市', '2013-04-09 12:06:59', '862812010039134', 'null', 'EDGE', '云南省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('465', '佛山市', '2013-04-09 12:09:41', '865072010009069', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('466', '中山市', '2013-04-09 12:39:18', '862812010052632', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('467', '绵阳市', '2013-04-09 12:45:07', '863020011431214', '', 'EDGE', '四川省', '480X854', '10', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('468', '北京市', '2013-04-09 12:48:38', '862812010181308', '13630132994', 'EDGE', '北京市', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('469', '绍兴市', '2013-04-09 12:52:53', '359836040406310', 'null', 'EDGE', '浙江省', '480X800', '10', 'CMCC', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('470', '北京市', '2013-04-09 13:00:59', '352751019523267', '15916018557', 'EDGE', '北京市', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('471', '昆明市', '2013-04-09 13:14:08', '862812010039134', 'null', 'EDGE', '云南省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('472', '福州市', '2013-04-09 13:15:09', '865072010166026', 'null', 'EDGE', '福建省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('473', '运城市', '2013-04-09 13:16:01', '860308028894596', '', 'EDGE', '山西省', '720X1280', '16', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('474', '广州市', '2013-04-09 13:23:56', '865072010000290', 'null', 'EDGE', '广东省', '320X480', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('475', '深圳市', '2013-04-09 13:23:59', '865072010164203', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('476', '沈阳市', '2013-04-09 13:27:12', '862812010036668', 'null', 'EDGE', '辽宁省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('477', '石家庄市', '2013-04-09 13:32:47', '865072010165606', 'null', 'EDGE', '河北省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('478', '南京市', '2013-04-09 13:41:44', '862812010053572', 'null', 'EDGE', '江苏省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('479', '西安市', '2013-04-09 13:42:34', '354765050794072', 'null', 'UNKNOWN', '陕西省', '1080X1920', '16', '', 'TX_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('480', '福州市', '2013-04-09 13:48:10', '866063014027461', '', 'EDGE', '福建省', '320X480', '10', '', null);
INSERT INTO `tb_bdg_user` VALUES ('481', '福州市', '2013-04-09 13:48:49', '866063014027461', '', 'EDGE', '福建省', '320X480', '10', '', 'KYB_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('482', '广州市', '2013-04-09 13:52:14', '864394015765995', '', 'EDGE', '广东省', '320X480', '10', 'cn', null);
INSERT INTO `tb_bdg_user` VALUES ('483', '深圳市', '2013-04-09 13:53:01', '862812010173925', 'null', 'GPRS', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('484', '太原市', '2013-04-09 13:53:44', '860308021747130', '', 'EDGE', '山西省', '720X1280', '16', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('485', '湛江市', '2013-04-09 13:55:49', '869579013413573', '13553504687', 'EDGE', '广东省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('486', '南京市', '2013-04-09 13:55:58', '356871048233849', 'null', 'HSDPA', '江苏省', '540X960', '10', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('487', '天津市', '2013-04-09 13:57:36', '867747019526068', '', 'EDGE', '天津市', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('488', '广州市', '2013-04-09 14:12:32', '99000216523125', '', 'EVDO_A', '广东省', '720X1280', '16', '中国电信', 'KPZXYYSD_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('489', '兰州市', '2013-04-09 14:22:49', '353742051957140', '', 'EDGE', '甘肃省', '720X1280', '15', 'CMCC', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('490', '上海市', '2013-04-09 14:34:40', '355369053812152', '', 'EDGE', '上海市', '480X800', '15', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('491', '荆门市', '2013-04-09 14:36:33', '860308029451529', '', 'EDGE', '湖北省', '720X1280', '16', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('492', '长沙市', '2013-04-09 15:07:29', '356531047196633', '', 'GPRS', '湖南省', '480X800', '10', 'CMCC', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('493', '广州市', '2013-04-09 15:18:27', '865072010174137', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('494', '昆明市', '2013-04-09 15:20:36', '862812010215023', 'null', 'GPRS', '云南省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('495', '郑州市', '2013-04-09 15:21:06', '865072010159146', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('496', '成都市', '2013-04-09 15:32:38', '865072010179516', 'null', 'EDGE', '四川省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('497', '石家庄市', '2013-04-09 15:37:12', '865072010163585', 'null', 'EDGE', '河北省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('498', '成都市', '2013-04-09 16:15:57', '860308021466160', '', 'EDGE', '四川省', '720X1280', '16', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('499', '漳州市', '2013-04-09 16:17:27', '865072010175860', 'null', 'EDGE', '福建省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('500', '上海市', '2013-04-09 16:36:41', '860173011412925', '', 'UMTS', '上海市', '480X854', '16', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('501', '郑州市', '2013-04-09 16:37:31', '862812010214075', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('502', '成都市', '2013-04-09 16:39:38', '865072010005810', '13795507726', 'EDGE', '四川省', '320X480', '10', '神州行', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('503', '广州市', '2013-04-09 16:40:21', '352273017386340', 'null', 'EDGE', '广东省', '320X480', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('504', '湘潭市', '2013-04-09 16:48:44', '860139025190229', '15173276801', 'EDGE', '湖南省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('505', '成都市', '2013-04-09 16:53:17', '862812010036528', 'null', 'EDGE', '四川省', '320X480', '10', '神州行', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('506', '深圳市', '2013-04-09 17:11:58', '865072010170598', '15817584515', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('507', '西安市', '2013-04-09 17:13:45', '867064017671023', '13630237314', 'EDGE', '陕西省', '720X1280', '16', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('508', '郑州市', '2013-04-09 17:14:45', '862812010043094', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('509', '信阳市', '2013-04-09 17:29:07', '868856010139497', '2258', 'EDGE', '河南省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('510', '北京市', '2013-04-09 17:33:31', '862812010198146', '15819337869', 'EDGE', '北京市', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('511', '济南市', '2013-04-09 17:38:45', '865072010158296', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('512', '广州市', '2013-04-09 17:45:15', '862813010208844', '13543567293', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('513', '深圳市', '2013-04-09 17:45:41', '865072010176116', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('514', '昆明市', '2013-04-09 17:47:54', '862812010050479', 'null', 'EDGE', '云南省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('515', '南昌市', '2013-04-09 17:50:25', '862812010037468', 'null', 'EDGE', '江西省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('516', '阜新市', '2013-04-09 17:56:19', '862812010202138', 'null', 'EDGE', '辽宁省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('517', '南京市', '2013-04-09 18:03:53', '865072010155136', 'null', 'EDGE', '江苏省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('518', '广州市', '2013-04-09 18:13:38', '862812010156508', '13760051781', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('519', '杭州市', '2013-04-09 18:14:00', '862812010035595', 'null', 'EDGE', '浙江省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('520', '广州市', '2013-04-09 18:16:43', '865072010161068', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('521', '郑州市', '2013-04-09 18:26:19', '865072010160029', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('522', '昆明市', '2013-04-09 18:28:11', '862812010039753', 'null', 'EDGE', '云南省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('523', '广州市', '2013-04-09 18:31:05', '864588010000890', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('524', '武汉市', '2013-04-09 18:33:28', '862324015612758', '', 'UMTS', '湖北省', '480X800', '10', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('525', '深圳市', '2013-04-09 18:44:18', '865072010151564', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('526', '杭州市', '2013-04-09 18:58:24', '862812010054844', 'null', 'EDGE', '浙江省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('527', '石家庄市', '2013-04-09 19:05:42', '865072010155938', 'null', 'EDGE', '河北省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('528', '广州市', '2013-04-09 19:14:09', '862812010182579', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('529', '福州市', '2013-04-09 19:14:21', '865072010156258', 'null', 'EDGE', '福建省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('530', '惠州市', '2013-04-09 19:17:35', '862812010038631', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('531', '成都市', '2013-04-09 19:22:43', '865072010004656', 'null', 'EDGE', '四川省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('532', '乌鲁木齐市', '2013-04-09 19:27:19', '868232000243659', '', 'HSDPA', '新疆维吾尔自治区', '480X854', '16', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('533', '东莞市', '2013-04-09 19:33:59', '864588010000932', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('534', '北京市', '2013-04-09 19:48:20', '862812010180920', '15113390957', 'EDGE', '北京市', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('535', '北京市', '2013-04-09 19:50:52', '862812010053416', 'null', 'EDGE', '北京市', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('536', '济宁市', '2013-04-09 20:09:21', '99000219466386', '13355116960', 'EVDO_A', '山东省', '480X854', '15', '中国电信', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('537', '上海市', '2013-04-09 20:11:25', '860618019816337', '', 'EDGE', '上海市', '320X480', '8', '', 'KPZXYYSD_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('538', '南京市', '2013-04-09 20:25:02', '863414015604894', '', 'HSPA', '江苏省', '320X480', '10', '', 'KPZXYYSD_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('539', '广州市', '2013-04-09 20:27:02', '862812010058290', '15875651454', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('540', '郑州市', '2013-04-09 20:34:08', '862812010207178', '13462855205', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('541', '杭州市', '2013-04-09 20:41:55', '359930046916610', '', 'EDGE', '浙江省', '480X800', '10', 'CMCC', null);
INSERT INTO `tb_bdg_user` VALUES ('542', '深圳市', '2013-04-09 20:57:27', '862812010203177', '', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('543', '广州市', '2013-04-09 20:58:12', '862812010039977', 'null', 'UNKNOWN', '广东省', '320X480', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('544', '广州市', '2013-04-09 20:59:10', '865072010176959', 'null', 'EDGE', '广东省', '480X800', '10', 'M-ZONE', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('545', '深圳市', '2013-04-09 21:10:27', '865072010171133', 'null', 'EDGE', '广东省', '480X800', '10', '神州行', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('546', '广州市', '2013-04-09 21:15:27', '862812010030182', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('547', '昆明市', '2013-04-09 21:25:30', '862812010214711', 'null', 'EDGE', '云南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('548', '郑州市', '2013-04-09 21:26:08', '862812010212806', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('549', '重庆市', '2013-04-09 21:27:19', '862812010050040', 'null', 'EDGE', '重庆市', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('550', '福州市', '2013-04-09 21:29:07', '865072010165101', 'null', 'EDGE', '福建省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('551', '深圳市', '2013-04-09 21:39:32', '862812010212103', '13680258624', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('552', '亳州市', '2013-04-09 21:55:00', '861410015703951', '', 'EDGE', '安徽省', '480X800', '10', '中国联通', 'KPZXYYSD_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('553', '', '2013-04-09 21:57:36', '867747018931608', '00000000937', 'EDGE', '广东省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('554', '成都市', '2013-04-09 21:58:38', '865072010154386', 'null', 'EDGE', '四川省', '480X800', '10', '神州行', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('555', '杭州市', '2013-04-09 21:59:01', '862812010032121', 'null', 'EDGE', '浙江省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('556', '郑州市', '2013-04-09 22:00:16', '861962015214405', '13783665993', 'EDGE', '河南省', '480X800', '10', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('557', '深圳市', '2013-04-09 22:05:03', '862812010034051', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('558', '哈尔滨市', '2013-04-09 22:06:20', 'A100002770CD7F', '', 'EVDO_A', '黑龙江省', '480X854', '15', '中国电信', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('559', '中山市', '2013-04-09 22:08:55', '862812010053358', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('560', '宁波市', '2013-04-09 22:14:26', 'A10000326D045AA', '', 'EVDO_A', '浙江省', '320X480', '10', '', 'KPZXYYSD_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('561', '广州市', '2013-04-09 22:15:34', '860139020219395', '', 'EDGE', '广东省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('562', '济南市', '2013-04-09 22:17:30', '862812010206071', 'null', 'EDGE', '山东省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('563', '深圳市', '2013-04-09 22:19:27', '865072010166562', 'null', 'EDGE', '广东省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('564', '南宁市', '2013-04-09 22:25:22', '862812010032931', 'null', 'EDGE', '广西壮族自治区', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('565', '合肥市', '2013-04-09 22:26:02', '990002140267335', '', 'EVDO_A', '安徽省', '480X800', '10', '中国电信', 'KPZXYYSD_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('566', '济南市', '2013-04-09 22:39:43', '862812010040108', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('567', '南宁市', '2013-04-09 22:43:40', '865072010165077', 'null', 'EDGE', '广西壮族自治区', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('568', '昆明市', '2013-04-09 22:48:30', '862812010051238', 'null', 'EDGE', '云南省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('569', '聊城市', '2013-04-09 22:50:14', '990001407229764', '', 'EVDO_A', '山东省', '320X480', '10', '中国电信', 'KPZXYYSD_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('570', '常州市', '2013-04-09 22:52:12', '863020017659941', '', 'EDGE', '江苏省', '480X854', '10', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('571', '南京市', '2013-04-09 22:54:28', '359575047238132', '', 'EDGE', '江苏省', '800X1280', '15', 'CMCC', 'KYB_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('572', '海口市', '2013-04-09 22:57:41', '860139025492021', '', 'EDGE', '海南省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('573', '湖州市', '2013-04-09 23:06:54', '99000310152902', '', 'EVDO_A', '浙江省', '720X1280', '16', '中国电信', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('574', '泉州市', '2013-04-09 23:20:12', '865072010168394', 'null', 'EDGE', '福建省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('575', '郑州市', '2013-04-09 23:21:44', '862812010045008', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('576', '深圳市', '2013-04-09 23:25:20', '867034010207828', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('577', '深圳市', '2013-04-09 23:27:49', '865072010167917', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('578', '石家庄市', '2013-04-09 23:29:30', '865072010156225', 'null', 'EDGE', '河北省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('579', '中山市', '2013-04-09 23:29:30', '865072010009465', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('580', '宝鸡市', '2013-04-09 23:43:56', '353824055364695', '', 'EDGE', '陕西省', '480X800', '10', 'CMCC', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('581', '广州市', '2013-04-09 23:47:48', '862812010185010', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('582', '南京市', '2013-04-09 23:51:49', '860139024341393', '', 'EDGE', '江苏省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('583', '福州市', '2013-04-09 23:56:24', '865072010158916', 'null', 'EDGE', '福建省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('584', '深圳市', '2013-04-09 23:57:09', '865072010275702', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('585', '广州市', '2013-04-10 00:07:13', '860139020219395', '', 'EDGE', '广东省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('586', '深圳市', '2013-04-10 00:25:03', '869579010321688', '18312476520', 'EDGE', '广东省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('587', '西宁市', '2013-04-10 00:47:30', '99000310295090', '', 'EVDO_A', '青海省', '720X1280', '16', '中国电信', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('588', '深圳市', '2013-04-10 00:54:39', '865072010170861', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('589', '昆明市', '2013-04-10 01:05:40', '862812010216831', 'null', 'UNKNOWN', '云南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('590', '广州市', '2013-04-10 01:09:51', '865072010156191', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('591', '杭州市', '2013-04-10 01:23:18', '862812010211634', 'null', 'EDGE', '浙江省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('592', '佛山市', '2013-04-10 01:45:53', '862812010205883', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('593', '沈阳市', '2013-04-10 01:51:05', '863802013195883', '', 'EDGE', '辽宁省', '480X854', '10', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('594', '上海市', '2013-04-10 02:23:50', '354016054600729', '', 'EDGE', '上海市', '480X800', '15', 'CMCC', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('595', '湛江市', '2013-04-10 02:32:44', '862812010046766', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('596', '天津市', '2013-04-10 02:37:00', '862812010031388', 'null', 'EDGE', '天津市', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('597', '', '2013-04-10 02:51:50', '351863048629113', '13828862911', 'UMTS', '广东省', '240X320', '10', 'Android', 'HWZHY_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('598', '', '2013-04-10 02:51:50', '351863048629113', '13828862911', 'UMTS', '广东省', '240X320', '10', 'Android', 'HWZHY_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('599', '福州市', '2013-04-10 02:53:26', '865072010165713', 'null', 'EDGE', '福建省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('600', '广州市', '2013-04-10 05:10:56', '865072010164393', 'null', 'EDGE', '广东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('601', '沈阳市', '2013-04-10 05:41:19', '862812010217045', 'null', 'EDGE', '辽宁省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('602', '长沙市', '2013-04-10 06:21:20', '862812010047665', 'null', 'EDGE', '湖南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('603', '石家庄市', '2013-04-10 06:29:33', '865072010166976', 'null', 'EDGE', '河北省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('604', '济南市', '2013-04-10 06:42:16', '862812010033970', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('605', '石家庄市', '2013-04-10 06:49:31', '865072010162199', 'null', 'EDGE', '河北省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('606', '阜新市', '2013-04-10 06:57:38', '862812010211873', 'null', 'EDGE', '辽宁省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('607', '惠州市', '2013-04-10 07:06:45', '862812010031297', 'null', 'EDGE', '广东省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('608', '成都市', '2013-04-10 07:33:26', '862812010039860', 'null', 'EDGE', '四川省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('609', '南京市', '2013-04-10 07:42:51', '864588010226040', 'null', 'EDGE', '江苏省', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('610', '深圳市', '2013-04-10 07:49:06', '862812010212400', 'null', 'EDGE', '广东省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('611', '昆明市', '2013-04-10 08:11:51', '862812010212236', 'null', 'EDGE', '云南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('612', '泉州市', '2013-04-10 08:22:48', '865072010153248', 'null', 'EDGE', '福建省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('613', '郑州市', '2013-04-10 08:27:01', '862812010212970', 'null', 'EDGE', '河南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('614', '昆明市', '2013-04-10 08:46:37', '862812010214638', 'null', 'EDGE', '云南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('615', '深圳市', '2013-04-10 08:59:40', '865067010065368', 'null', 'EDGE', '广东省', '480X800', '10', '', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('616', '济南市', '2013-04-10 09:39:30', '865072010163932', 'null', 'EDGE', '山东省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('617', '武汉市', '2013-04-10 09:57:08', '860755011634498', '', 'EDGE', '湖北省', '640X960', '10', 'CMCC', 'KYB_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('618', '福州市', '2013-04-10 10:08:25', '865072010164609', 'null', 'EDGE', '福建省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('619', '昆明市', '2013-04-10 10:20:15', '862812010212236', 'null', 'EDGE', '云南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('620', '海口市', '2013-04-10 10:22:40', '865072010154154', 'null', 'EDGE', '海南省', '480X800', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('621', '唐山市', '2013-04-10 10:24:40', '867747015536681', '', 'EDGE', '河北省', '480X854', '15', '', 'XM_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('622', '北京市', '2013-04-10 10:25:58', '352273017386340', '15015684391', 'EDGE', '北京市', '320X480', '10', 'CMCC', 'MYF_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('623', '深圳市', '2013-04-10 10:36:51', 'null', 'null', 'UNKNOWN', '广东省', '480X854', '15', '', 'AZSC_AORC_13');
INSERT INTO `tb_bdg_user` VALUES ('624', '泉州市', '2013-04-10 10:36:58', '865072010164062', 'null', 'EDGE', '福建省', '480X800', '10', 'CMCC', 'MYF_AORC_13');

-- ----------------------------
-- Table structure for `tb_billinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_billinfo`;
CREATE TABLE `tb_billinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(20) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `cvsName` varchar(30) DEFAULT NULL,
  `fee` float DEFAULT NULL,
  `linkId` varchar(30) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `projectCode` varchar(20) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `service` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `linkId` (`linkId`)
) ENGINE=InnoDB AUTO_INCREMENT=34078 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_billinfo
-- ----------------------------
INSERT INTO `tb_billinfo` VALUES ('33077', '衢州', 'BVAIX', '2011-04-27 18:49:50', '山鹰科技', '1.5', 'VS41104271849361484411', '13505709416', 'Y006001', '浙江', '移动');
INSERT INTO `tb_billinfo` VALUES ('33078', '衢州', 'BVAIX', '2011-04-27 18:50:12', '山鹰科技', '1.5', 'VS61104271849329147394', '13505709416', 'Y006001', '浙江', '移动');
INSERT INTO `tb_billinfo` VALUES ('33079', '拉萨', 'HHDD', '2011-04-27 18:57:00', '金鑫通讯', '1', '18524828163374039031', '13989016277', 'Y002001', '西藏', '移动');
INSERT INTO `tb_billinfo` VALUES ('33080', '庆阳', 'yx1726AIX', '2011-04-27 19:00:38', '金鑫通讯', '1', 'VS21104271900383822058', '15109340982', 'Y002001', '甘肃', '移动');
INSERT INTO `tb_billinfo` VALUES ('33081', '郑州', '3+25E', '2011-04-27 19:30:14', '森泰科技', '2', '3434363037360000', '13253412177', 'Y008002', '河南', '联通');
INSERT INTO `tb_billinfo` VALUES ('33082', '郑州', '3+25E', '2011-04-27 19:31:18', '森泰科技', '2', '3434363633350000', '13253412177', 'Y008002', '河南', '联通');
INSERT INTO `tb_billinfo` VALUES ('33083', '潮州', '2211AIX', '2011-04-27 19:31:27', '山鹰科技', '2', 'VS61104271931276015098', '13539351485', 'Y006001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33084', '潮州', '2211AIX', '2011-04-27 19:31:40', '山鹰科技', '2', 'VS21104271931370939438', '13539351485', 'Y006001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33085', '铜仁', '30162C', '2011-04-27 19:41:03', '瑞信科技', '2', '19403208176360604106', '13765619708', 'X001001', '贵州', '移动');
INSERT INTO `tb_billinfo` VALUES ('33086', '六盘水', '30162C', '2011-04-27 19:41:21', '雅思博', '2', '19405103157372992989', '18286814193', 'Y008002', '贵州', '移动');
INSERT INTO `tb_billinfo` VALUES ('33087', '济宁', 'DXPK5AIX', '2011-04-27 19:43:07', '森泰科技', '1', 'VS41104271943041098986', '15265790800', 'Y008002', '山东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33088', '济宁', 'DXPK5AIX', '2011-04-27 19:43:13', '森泰科技', '1', 'VS11104271943101716147', '15265790800', 'Y008002', '山东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33089', '温州', '3+25E', '2011-04-27 19:43:43', '森泰科技', '2', '3533393036340000', '15558777659', 'Y008002', '浙江', '联通');
INSERT INTO `tb_billinfo` VALUES ('33090', '佛山', '2211AIX', '2011-04-27 20:11:41', '雅思博', '2', 'VS41104272011386561829', '13690218006', 'X001001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33091', '深圳', '2211AIX', '2011-04-27 20:12:32', '瑞信科技', '2', 'VS21104272012277652660', '13715050824', 'X001001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33092', '驻马店', 'yx1726AIX', '2011-04-27 20:12:32', '森泰科技', '1', 'VS11104272012291097611', '13526363575', 'Y008002', '河南', '移动');
INSERT INTO `tb_billinfo` VALUES ('33093', '驻马店', 'yx1726AIX', '2011-04-27 20:12:33', '森泰科技', '1', 'VS71104272012304840284', '13526363575', 'Y008002', '河南', '移动');
INSERT INTO `tb_billinfo` VALUES ('33094', '深圳', '2211AIX', '2011-04-27 20:12:37', '瑞信科技', '2', 'VS31104272012153517617', '13715050824', 'X001001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33095', '洛阳', 'yx1726AIX', '2011-04-27 20:17:32', '森泰科技', '1', 'VS11104272017290781905', '15003795543', 'Y008002', '河南', '移动');
INSERT INTO `tb_billinfo` VALUES ('33096', '洛阳', 'yx1726AIX', '2011-04-27 20:17:33', '森泰科技', '1', 'VS61104272017298128314', '15003795543', 'Y008002', '河南', '移动');
INSERT INTO `tb_billinfo` VALUES ('33097', '枣庄', 'DXPK5AIX', '2011-04-27 20:18:57', '森泰科技', '1', 'VS01104272018539533842', '18264225493', 'Y008002', '山东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33098', '枣庄', 'DXPK5AIX', '2011-04-27 20:19:13', '雅思博', '1', 'VS81104272019104537128', '18264225493', 'Y008002', '山东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33099', '衢州', 'BVAIX', '2011-04-27 20:20:08', '山鹰科技', '1.5', 'VS21104272019571715609', '13567014386', 'Y006001', '浙江', '移动');
INSERT INTO `tb_billinfo` VALUES ('33100', '平顶山', 'yx1726AIX', '2011-04-27 20:22:55', '森泰科技', '1', 'VS81104272022550079539', '13461199704', 'Y008002', '河南', '移动');
INSERT INTO `tb_billinfo` VALUES ('33101', '沈阳', '3+25E', '2011-04-27 20:28:20', '瑞信科技', '2', '3830313535350000', '15609825852', 'X001001', '辽宁', '联通');
INSERT INTO `tb_billinfo` VALUES ('33102', '深圳', '2211AIX', '2011-04-27 20:28:48', '山鹰科技', '2', 'VS01104272028475547778', '13530045597', 'Y006001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33103', '深圳', '2211AIX', '2011-04-27 20:28:58', '山鹰科技', '2', 'VS41104272028553122553', '13530045597', 'Y006001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33104', '深圳', '2211AIX', '2011-04-27 20:32:04', '森泰科技', '2', 'VS11104272032041173452', '15818728622', 'Y008001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33105', '宁波', 'BVAIX', '2011-04-27 20:33:53', '瑞信科技', '1.5', 'VS21104272033418438420', '15906586135', 'X001001', '浙江', '移动');
INSERT INTO `tb_billinfo` VALUES ('33106', '宁波', 'BVAIX', '2011-04-27 20:34:43', '瑞信科技', '1.5', 'VS81104272033190311253', '15906586135', 'X001001', '浙江', '移动');
INSERT INTO `tb_billinfo` VALUES ('33107', '温州', 'BVAIX', '2011-04-27 20:36:14', '雅思博', '1.5', 'VS81104272035554840465', '15168760520', 'X001001', '浙江', '移动');
INSERT INTO `tb_billinfo` VALUES ('33108', '未知', '3+25E', '2011-04-27 20:42:00', '雅思博(未定义)', '2', '3934303634380000', '13018993311', '001005', '未知', '联通');
INSERT INTO `tb_billinfo` VALUES ('33109', '毕节', '30162C', '2011-04-27 20:47:13', '森泰科技', '2', '20464108176360750516', '13765896589', 'Y008002', '贵州', '移动');
INSERT INTO `tb_billinfo` VALUES ('33110', '大连', '8xph5AIX', '2011-04-27 20:51:59', '金鑫通讯', '1', 'VS61104272051558281933', '13644275349', 'Y002001', '辽宁', '移动');
INSERT INTO `tb_billinfo` VALUES ('33111', '安顺', '30162C', '2011-04-27 20:52:08', '雅思博', '2', '20514403113373238155', '15185357547', 'Y008002', '贵州', '移动');
INSERT INTO `tb_billinfo` VALUES ('33112', '贵阳', '30162C', '2011-04-27 21:06:21', '鑫泰通', '2', '21055508175360794143', '18786727120', 'Y007001', '贵州', '移动');
INSERT INTO `tb_billinfo` VALUES ('33113', '深圳', '2211AIX', '2011-04-27 21:06:28', '森泰科技', '2', 'VS51104272106269607538', '15112467211', 'Y008001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33114', '深圳', '2211AIX', '2011-04-27 21:06:34', '森泰科技', '2', 'VS51104272106304062276', '15112467211', 'Y008001', '广东', '移动');
INSERT INTO `tb_billinfo` VALUES ('33115', '郑州', 'yx1726AIX', '2011-04-27 21:11:46', '雅思博', '1', 'VS71104272111365232153', '15903672758', 'Y008001', '河南', '移动');
-- ----------------------------
-- Table structure for `tb_captrue_weibouser`
-- ----------------------------
DROP TABLE IF EXISTS `tb_captrue_weibouser`;
CREATE TABLE `tb_captrue_weibouser` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `feature` int(11) NOT NULL,
  `limits` int(11) DEFAULT NULL,
  `maxRecordId` varchar(30) DEFAULT NULL,
  `screenName` varchar(30) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  `uid` varchar(30) NOT NULL,
  `appId` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_captrue_weibouser
-- ----------------------------
INSERT INTO `tb_captrue_weibouser` VALUES ('1', '2', '20', '3522090206085421', '百思不得姐应用', '', '1', '2617700277', '1');
INSERT INTO `tb_captrue_weibouser` VALUES ('2', '2', '20', '3524898884651748', '内涵漫画吧', '', '1', '2690454361', '');
INSERT INTO `tb_captrue_weibouser` VALUES ('3', '2', '5', '3525628550790347', '18禁小漫画', '', '1', '', '1');

-- ----------------------------
-- Table structure for `tb_capture_weiborecord`
-- ----------------------------
DROP TABLE IF EXISTS `tb_capture_weiborecord`;
CREATE TABLE `tb_capture_weiborecord` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `bmiddlePic` varchar(200) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `id` varchar(30) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `mid` varchar(30) DEFAULT NULL,
  `originalPic` varchar(200) DEFAULT NULL,
  `recordSource` varchar(30) DEFAULT NULL,
  `recordStatus` int(11) DEFAULT NULL,
  `recordType` varchar(1) DEFAULT NULL,
  `screenName` varchar(50) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `text` varchar(512) DEFAULT NULL,
  `thumbnailPic` varchar(200) DEFAULT NULL,
  `uid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_capture_weiborecord
-- ----------------------------
INSERT INTO `tb_capture_weiborecord` VALUES ('1', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzmb9lds81j.jpg', '2012-12-08 14:40:25', '3520972927825056', '四川 成都', '3520972927825056', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzmb9lds81j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 国内新出的3D版斑马线，太逼真了，很容易看成障碍物，车主见了立马减速，太牛了。! http://t.cn/zOFspbg  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzmb9lds81j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('2', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzmaosagsej.jpg', '2012-12-08 14:20:25', '3520967899088800', '四川 成都', '3520967899088800', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzmaosagsej.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 学校综合楼餐饮街的打蛋器。让人好蛋疼。! http://t.cn/zOsfjOe  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzmaosagsej.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('3', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzm9j6j220j.jpg', '2012-12-08 13:40:24', '3520957832903424', '四川 成都', '3520957832903424', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzm9j6j220j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 哥们，好手法。! http://t.cn/zWZZMPv  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzm9j6j220j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('4', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzm8ycxc79j.jpg', '2012-12-08 13:20:25', '3520952799337837', '四川 成都', '3520952799337837', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzm8ycxc79j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 霸气侧漏！! http://t.cn/zWvu0uU  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzm8ycxc79j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('5', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzm8dmdh6aj.jpg', '2012-12-08 13:00:30', '3520947783371553', '四川 成都', '3520947783371553', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzm8dmdh6aj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 他自称神经病所长，有多少贱友喜欢这个怪咖？[2P]! http://t.cn/zOsJwTu  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzm8dmdh6aj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('6', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzm77xosczj.jpg', '2012-12-08 12:20:26', '3520937700106973', '四川 成都', '3520937700106973', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzm77xosczj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 阿福，崩了那对异性恋。! http://t.cn/zWv3evL  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzm77xosczj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('7', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzm62apt43j.jpg', '2012-12-08 11:40:25', '3520927629792754', '四川 成都', '3520927629792754', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzm62apt43j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 从你眼神中我读出了____。! http://t.cn/zWbFlBy  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzm62apt43j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('8', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzm5hu3g0pj.jpg', '2012-12-08 11:20:45', '3520922680129623', '四川 成都', '3520922680129623', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzm5hu3g0pj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 邪恶漫画：一条内裤引发的杯具。[9P]! http://t.cn/zWv4fO7  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzm5hu3g0pj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('9', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzm4wo8p2aj.jpg', '2012-12-08 11:00:23', '3520917563013403', '四川 成都', '3520917563013403', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzm4wo8p2aj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 强迫症患者求捂另一边保持平衡! http://t.cn/zWPUaSF  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzm4wo8p2aj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('10', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzm369t1ayj.jpg', '2012-12-08 10:00:27', '3520902471934196', '四川 成都', '3520902471934196', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzm369t1ayj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 萌萌的，好可爱! http://t.cn/zOsmjAB  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzm369t1ayj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('11', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzm2lgcljlj.jpg', '2012-12-08 09:40:26', '3520897434668246', '四川 成都', '3520897434668246', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzm2lgcljlj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 萌了个喵! http://t.cn/zOsTHo1  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzm2lgcljlj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('12', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzm20nisiij.jpg', '2012-12-08 09:20:25', '3520892405572499', '四川 成都', '3520892405572499', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzm20nisiij.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 堵车的时候下来打个羽毛球吧！! http://t.cn/zOFFd0B  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzm20nisiij.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('13', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzll94jwd4j.jpg', '2012-12-07 23:40:24', '3520746431422898', '四川 成都', '3520746431422898', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzll94jwd4j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 转自【神吐槽】：王尼玛大力推荐！新奇的神吐槽制作器，你也过来爽一把吧→! http://t.cn/zWvUUCk  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzll94jwd4j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('14', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzlkocmtp7j.jpg', '2012-12-07 23:20:26', '3520741406810961', '四川 成都', '3520741406810961', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzlkocmtp7j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 出来露营，只带了一顶帐篷! http://t.cn/zWPXPh7  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzlkocmtp7j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('15', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzlk3j7rybj.jpg', '2012-12-07 23:00:26', '3520736373289007', '四川 成都', '3520736373289007', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzlk3j7rybj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 人生就像DOTA，没有伙伴你什么也不是。! http://t.cn/zWvE135  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzlk3j7rybj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('16', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzlixxwffmj.jpg', '2012-12-07 22:20:27', '3520726311282028', '四川 成都', '3520726311282028', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzlixxwffmj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 线! http://t.cn/zWPVjhh  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzlixxwffmj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('17', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzlh7gpiguj.jpg', '2012-12-07 21:20:25', '3520711199308212', '四川 成都', '3520711199308212', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzlh7gpiguj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 最后一张小贝的表情太亮了! http://t.cn/zOFBC8A  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzlh7gpiguj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('18', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzlgmnxt40j.jpg', '2012-12-07 21:00:25', '3520706170478301', '四川 成都', '3520706170478301', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzlgmnxt40j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 国旗大撞衫! http://t.cn/zWPPJBy  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzlgmnxt40j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('19', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzlg1vxqwvj.jpg', '2012-12-07 20:40:27', '3520701145508717', '四川 成都', '3520701145508717', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzlg1vxqwvj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 就知道你丫有反骨，这尼玛也太强势了吧？亲一个屁股，踹一个屁股。! http://t.cn/zWhtxlo  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzlg1vxqwvj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('20', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzlfh1tf0pj.jpg', '2012-12-07 20:20:23', '3520696108285499', '四川 成都', '3520696108285499', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzlfh1tf0pj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 为什么你许的愿望都不能实现？! http://t.cn/zWZ6vh4  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzlfh1tf0pj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('21', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzpta7vb7uj.jpg', '2012-12-11 15:20:28', '3522070178737561', '四川 成都', '3522070178737561', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzpta7vb7uj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 现在知道学友哥从不和其它女星传绯闻的原因了吧？! http://t.cn/zWZMqrn  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzpta7vb7uj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('22', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzpspexhhfj.jpg', '2012-12-11 15:00:30', '3522065145894816', '四川 成都', '3522065145894816', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzpspexhhfj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 某网友借同学的化学《重难点手册》，在199页发现了这个……我擦这货太内涵了。! http://t.cn/zWva2hp  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzpspexhhfj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('23', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzps4x35yej.jpg', '2012-12-11 14:40:49', '3522060192114733', '四川 成都', '3522060192114733', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzps4x35yej.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 贱图合集[7P]! http://t.cn/zOsHHus  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzps4x35yej.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('24', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzprjsvmhgj.jpg', '2012-12-11 14:20:31', '3522055083913226', '四川 成都', '3522055083913226', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzprjsvmhgj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 【这个酒店好奇怪】松江的情侣酒店，贱友们懂的。! http://t.cn/zOsH4jV  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzprjsvmhgj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('25', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzpqyzbag5j.jpg', '2012-12-11 14:00:30', '3522050046384422', '四川 成都', '3522050046384422', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzpqyzbag5j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 这种姿势，谁想试试,有点难度！! http://t.cn/zWztbj9  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzpqyzbag5j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('26', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzpqe64yi0j.jpg', '2012-12-11 13:40:30', '3522045013046041', '四川 成都', '3522045013046041', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzpqe64yi0j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 她应该不知道! http://t.cn/zWvf8i0  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzpqe64yi0j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('27', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzpp8jp0jdj.jpg', '2012-12-11 13:00:28', '3522034946720052', '四川 成都', '3522034946720052', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzpp8jp0jdj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 无意中瞥到的，感觉好拉风啊！! http://t.cn/zOsGrHR  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzpp8jp0jdj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('28', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzpo2x8fauj.jpg', '2012-12-11 12:20:28', '3522024880534723', '四川 成都', '3522024880534723', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzpo2x8fauj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 中央四台的主持人，一定是我开电视的方式不对，一定是我心存杂念。! http://t.cn/zOsHHus  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzpo2x8fauj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('29', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzpni3ll6ej.jpg', '2012-12-11 12:00:28', '3522019843000143', '四川 成都', '3522019843000143', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzpni3ll6ej.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 就是这样自信! http://t.cn/zWvGe0p  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzpni3ll6ej.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('30', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzpmxbvi7yj.jpg', '2012-12-11 11:40:31', '3522014818100989', '四川 成都', '3522014818100989', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzpmxbvi7yj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 这张图，谁看懂了？! http://t.cn/zOsfjOe  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzpmxbvi7yj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('31', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzpmci1pquj.jpg', '2012-12-11 11:20:29', '3522009781231697', '四川 成都', '3522009781231697', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzpmci1pquj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 妖妖小精【漫画集811】-一杯酒的意义! http://t.cn/zWv7MV7  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzpmci1pquj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('32', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzplrnt1x6j.jpg', '2012-12-11 11:00:28', '3522004739685614', '四川 成都', '3522004739685614', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzplrnt1x6j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 展护卫，你要不要这么傲娇与羞涩难耐啊。! http://t.cn/zWZZMPv  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzplrnt1x6j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('33', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzpl6v2asij.jpg', '2012-12-11 10:40:29', '3521999710224111', '四川 成都', '3521999710224111', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzpl6v2asij.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 阿宅正传之帮是不帮! http://t.cn/zWvM0JY  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzpl6v2asij.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('34', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzpkm3pz0kg.gif', '2012-12-11 10:20:32', '3521994690038371', '四川 成都', '3521994690038371', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzpkm3pz0kg.gif', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 妹子，不带这么骗人的! http://t.cn/zOsxCQp  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzpkm3pz0kg.gif', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('35', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzpk18vcg5j.jpg', '2012-12-11 10:00:29', '3521989644004101', '四川 成都', '3521989644004101', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzpk18vcg5j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 咱能别这样喝酒嘛？我TM求你了。! http://t.cn/zW7N435  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzpk18vcg5j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('38', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzp245e6qvj.jpg', '2012-12-10 23:40:31', '3521833620272043', '四川 成都', '3521833620272043', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzp245e6qvj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 什么叫做控，这个才是! http://t.cn/zOsGcQ9  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzp245e6qvj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('39', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzp1jbkv17j.jpg', '2012-12-10 23:20:29', '3521828582715224', '四川 成都', '3521828582715224', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzp1jbkv17j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 旁边的哥们，你看哪呢! http://t.cn/zWvkwuS  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzp1jbkv17j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('40', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzp0yj6emtj.jpg', '2012-12-10 23:00:30', '3521823553845818', '四川 成都', '3521823553845818', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzp0yj6emtj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 改编歌曲“酒驾买卖”! http://t.cn/zWvGG6Q  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzp0yj6emtj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('41', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzyu5mgyuoj.jpg', '2012-12-19 10:40:44', '3524898884651748', '四川 成都', '3524898884651748', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzyu5mgyuoj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 每一台水货手机都来之不易啊！[3P]! http://t.cn/zWviwOs  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzyu5mgyuoj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('42', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzytkq54o8j.jpg', '2012-12-19 10:20:41', '3524893830705789', '四川 成都', '3524893830705789', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzytkq54o8j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 忧郁的眼神，深情凝望远方，米有错！我就是传说中维护正义的蒙面侠。! http://t.cn/zOFdxjB  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzytkq54o8j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('43', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzyszxr6nhj.jpg', '2012-12-19 10:00:42', '3524888801376304', '四川 成都', '3524888801376304', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzyszxr6nhj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 公交车开往哪里？! http://t.cn/zOsmwqI  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzyszxr6nhj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('44', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzysf3n6rhj.jpg', '2012-12-19 09:40:41', '3524883764019039', '四川 成都', '3524883764019039', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzysf3n6rhj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 神回复！! http://t.cn/zOs57oe  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzysf3n6rhj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('45', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzyrua80pwj.jpg', '2012-12-19 09:20:40', '3524878727009752', '四川 成都', '3524878727009752', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzyrua80pwj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 孩子给父母写了一封叛逆的信。父母于是回信了。! http://t.cn/zWvE135  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzyrua80pwj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('46', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzyr9k6q5ej.jpg', '2012-12-19 09:00:44', '3524873714612870', '四川 成都', '3524873714612870', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzyr9k6q5ej.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 有一种神奇的人他们叫最右! http://t.cn/zWAeQ8x  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzyr9k6q5ej.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('47', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzyb2twv1yj.jpg', '2012-12-18 23:40:42', '3524732773309270', '四川 成都', '3524732773309270', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzyb2twv1yj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 回复好亮! http://t.cn/zOsTHo1  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzyb2twv1yj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('48', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzyai097p5j.jpg', '2012-12-18 23:20:43', '3524727735886121', '四川 成都', '3524727735886121', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzyai097p5j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 世界上第一台照相机! http://t.cn/zWva2hp  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzyai097p5j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('49', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzy9x6ly8cj.jpg', '2012-12-18 23:00:41', '3524722698984700', '四川 成都', '3524722698984700', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzy9x6ly8cj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 传说中的s型美女! http://t.cn/zOsHHus  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzy9x6ly8cj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('50', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzy9cf43n6j.jpg', '2012-12-18 22:40:40', '3524717682286446', '四川 成都', '3524717682286446', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzy9cf43n6j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 跟拍一个乞丐妹妹，最后我崩溃了[7P]! http://t.cn/zOsfZIt  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzy9cf43n6j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('51', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzy71j453zj.jpg', '2012-12-18 21:21:05', '3524697633619624', '四川 成都', '3524697633619624', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzy71j453zj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 其实，动漫都来自生活。[8P]! http://t.cn/zWvu0uU  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzy71j453zj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('52', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzy5vlmpzvj.jpg', '2012-12-18 20:40:43', '3524687496136250', '四川 成都', '3524687496136250', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzy5vlmpzvj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 思密达好多人! http://www.fanjian8.com/manhua/10  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzy5vlmpzvj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('53', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzy452oksqj.jpg', '2012-12-18 19:40:40', '3524672366879184', '四川 成都', '3524672366879184', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzy452oksqj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# ……马先生，请自重! http://t.cn/zWPPFjP  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzy452oksqj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('54', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzy2zh512rj.jpg', '2012-12-18 19:00:41', '3524662304969211', '四川 成都', '3524662304969211', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzy2zh512rj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 红衣男子知道是谁吗？! http://t.cn/zW7a9hv  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzy2zh512rj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('55', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzy2eoau5oj.jpg', '2012-12-18 18:40:45', '3524657280169371', '四川 成都', '3524657280169371', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzy2eoau5oj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 打工记。（二）! http://t.cn/zWhDz5T  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzy2eoau5oj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('56', 'http://ww4.sinaimg.cn/bmiddle/a05d1359jw1dzy1ttvuifj.jpg', '2012-12-18 18:20:39', '3524652234621979', '四川 成都', '3524652234621979', 'http://ww4.sinaimg.cn/large/a05d1359jw1dzy1ttvuifj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 小时候的智商逆天! http://t.cn/zWvURm8  @微博精选', 'http://ww4.sinaimg.cn/thumbnail/a05d1359jw1dzy1ttvuifj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('57', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzy1915r81j.jpg', '2012-12-18 18:00:41', '3524647201149092', '四川 成都', '3524647201149092', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzy1915r81j.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 奶奶，你耍我呢？! http://t.cn/zWhDz5T  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzy1915r81j.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('58', 'http://ww1.sinaimg.cn/bmiddle/a05d1359jw1dzy0obk37xj.jpg', '2012-12-18 17:40:46', '3524642193370656', '四川 成都', '3524642193370656', 'http://ww1.sinaimg.cn/large/a05d1359jw1dzy0obk37xj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 人群恐慌症! http://t.cn/zWZCfyH  @微博精选', 'http://ww1.sinaimg.cn/thumbnail/a05d1359jw1dzy0obk37xj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('59', 'http://ww2.sinaimg.cn/bmiddle/a05d1359jw1dzy03ewnqmj.jpg', '2012-12-18 17:20:40', '3524637135207612', '四川 成都', '3524637135207612', 'http://ww2.sinaimg.cn/large/a05d1359jw1dzy03ewnqmj.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 是啊，肿么回事？! http://t.cn/zWPaVVd  @微博精选', 'http://ww2.sinaimg.cn/thumbnail/a05d1359jw1dzy03ewnqmj.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('60', 'http://ww3.sinaimg.cn/bmiddle/a05d1359jw1dzxzim387ej.jpg', '2012-12-18 17:00:42', '3524632118335733', '四川 成都', '3524632118335733', 'http://ww3.sinaimg.cn/large/a05d1359jw1dzxzim387ej.jpg', '新浪微博', '0', '1', '内涵漫画吧', 'http://app.weibo.com/t/feed/3WyHnM', '#搞笑# 靠，看了5分钟没弄明白什么套路！! http://t.cn/zOFdxjB  @微博精选', 'http://ww3.sinaimg.cn/thumbnail/a05d1359jw1dzxzim387ej.jpg', '2690454361');
INSERT INTO `tb_capture_weiborecord` VALUES ('61', 'http://ww3.sinaimg.cn/bmiddle/8191c15cjw1dznhhg1k3mj.jpg', '2012-12-19 11:00:12', '3524903775372157', '广东 广州', '3524903775372157', 'http://ww3.sinaimg.cn/large/8191c15cjw1dznhhg1k3mj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '目前为止世界上最优秀的纸艺作品之一 ：海上城堡，震撼，惊叹！！！', 'http://ww3.sinaimg.cn/thumbnail/8191c15cjw1dznhhg1k3mj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('62', 'http://ww3.sinaimg.cn/bmiddle/7f677930tw1dtn4jpk68aj.jpg', '2012-12-19 10:56:11', '3524902764616650', '广东 广州', '3524902764616650', 'http://ww3.sinaimg.cn/large/7f677930tw1dtn4jpk68aj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '千万不要考验人性', 'http://ww3.sinaimg.cn/thumbnail/7f677930tw1dtn4jpk68aj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('63', 'http://ww2.sinaimg.cn/bmiddle/8b5aac6bjw1dzq1l4aup7j.jpg', '2012-12-19 10:52:08', '3524901749368178', '广东 广州', '3524901749368178', 'http://ww2.sinaimg.cn/large/8b5aac6bjw1dzq1l4aup7j.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '【#双子座#本周塔罗牌面】', 'http://ww2.sinaimg.cn/thumbnail/8b5aac6bjw1dzq1l4aup7j.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('64', 'http://ww1.sinaimg.cn/bmiddle/7a085389jw1dz2uhfjia6j.jpg', '2012-12-19 10:48:08', '3524900738348883', '广东 广州', '3524900738348883', 'http://ww1.sinaimg.cn/large/7a085389jw1dz2uhfjia6j.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '做好自己就好！喜欢你的倾述，感同身受~！', 'http://ww1.sinaimg.cn/thumbnail/7a085389jw1dz2uhfjia6j.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('65', 'http://ww4.sinaimg.cn/bmiddle/8bdb4a53tw1dzr7wx0gulj.jpg', '2012-12-19 10:44:07', '3524899727600233', '广东 广州', '3524899727600233', 'http://ww4.sinaimg.cn/large/8bdb4a53tw1dzr7wx0gulj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '李敏镐 滴无水印手机壁纸来了哟，很帅，速度更换手机壁纸了。', 'http://ww4.sinaimg.cn/thumbnail/8bdb4a53tw1dzr7wx0gulj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('66', 'http://ww2.sinaimg.cn/bmiddle/8bdb4a53tw1dzpzvregbdj.jpg', '2012-12-19 10:40:11', '3524898738121448', '广东 广州', '3524898738121448', 'http://ww2.sinaimg.cn/large/8bdb4a53tw1dzpzvregbdj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '海贼王 滴无水印手机壁纸来了 很可爱。萌屎人。[心]', 'http://ww2.sinaimg.cn/thumbnail/8bdb4a53tw1dzpzvregbdj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('67', 'http://ww1.sinaimg.cn/bmiddle/b502b4cdgw1dzl85gb3qyj.jpg', '2012-12-19 10:36:09', '3524897718420898', '广东 广州', '3524897718420898', 'http://ww1.sinaimg.cn/large/b502b4cdgw1dzl85gb3qyj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '上有天堂下有苏杭！有机会一定要去杭州玩！先收藏！', 'http://ww1.sinaimg.cn/thumbnail/b502b4cdgw1dzl85gb3qyj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('68', 'http://ww1.sinaimg.cn/bmiddle/70f7ff71jw1dzq2jt4rsjj.jpg', '2012-12-19 10:32:09', '3524896716065922', '广东 广州', '3524896716065922', 'http://ww1.sinaimg.cn/large/70f7ff71jw1dzq2jt4rsjj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '语不惊人死不休', 'http://ww1.sinaimg.cn/thumbnail/70f7ff71jw1dzq2jt4rsjj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('69', 'http://ww4.sinaimg.cn/bmiddle/7093b907jw1dzofb0jdokj.jpg', '2012-12-19 10:28:07', '3524895705322692', '广东 广州', '3524895705322692', 'http://ww4.sinaimg.cn/large/7093b907jw1dzofb0jdokj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '什么街舞、杰克逊，弱爆了！看看中国人到底有多牛叉！哈哈，这里的内容笑死我了~~！', 'http://ww4.sinaimg.cn/thumbnail/7093b907jw1dzofb0jdokj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('70', 'http://ww2.sinaimg.cn/bmiddle/a49a3684jw1dzb6o7ho9zj.jpg', '2012-12-19 10:24:06', '3524894694318344', '广东 广州', '3524894694318344', 'http://ww2.sinaimg.cn/large/a49a3684jw1dzb6o7ho9zj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '总是这样对我[泪]真心恋上你的文字，总是写得那么窝心~[爱你]值得一看！', 'http://ww2.sinaimg.cn/thumbnail/a49a3684jw1dzb6o7ho9zj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('71', 'http://ww2.sinaimg.cn/bmiddle/92e69d42jw1dwihvubpwdj.jpg', '2012-12-19 10:20:08', '3524893691969294', '广东 广州', '3524893691969294', 'http://ww2.sinaimg.cn/large/92e69d42jw1dwihvubpwdj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '反复问同一个问题，说谎嫌疑大，识破谎言就看这个。', 'http://ww2.sinaimg.cn/thumbnail/92e69d42jw1dwihvubpwdj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('72', 'http://ww2.sinaimg.cn/bmiddle/819643ecgw1dy2l690pfsj.jpg', '2012-12-19 10:16:07', '3524892681231613', '广东 广州', '3524892681231613', 'http://ww2.sinaimg.cn/large/819643ecgw1dy2l690pfsj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '液晶屏幕坏点修复小技巧，这么实用的帖子，收藏吧！', 'http://ww2.sinaimg.cn/thumbnail/819643ecgw1dy2l690pfsj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('73', 'http://ww3.sinaimg.cn/bmiddle/6e7992b3jw1dzq0tdopgnj.jpg', '2012-12-19 10:12:09', '3524891687316862', '广东 广州', '3524891687316862', 'http://ww3.sinaimg.cn/large/6e7992b3jw1dzq0tdopgnj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '天机不可泄露', 'http://ww3.sinaimg.cn/thumbnail/6e7992b3jw1dzq0tdopgnj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('74', 'http://ww1.sinaimg.cn/bmiddle/9ec08657gw1dss6q2y9uij.jpg', '2012-12-19 10:08:08', '3524890672082853', '广东 广州', '3524890672082853', 'http://ww1.sinaimg.cn/large/9ec08657gw1dss6q2y9uij.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '要想你的男人一辈子不背叛你，学会看懂男人的心', 'http://ww1.sinaimg.cn/thumbnail/9ec08657gw1dss6q2y9uij.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('75', 'http://ww3.sinaimg.cn/bmiddle/92e69d42jw1dwihg6f1ixj.jpg', '2012-12-19 10:04:07', '3524889661655878', '广东 广州', '3524889661655878', 'http://ww3.sinaimg.cn/large/92e69d42jw1dwihg6f1ixj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '早点看到这个技巧就不会忽悠了~', 'http://ww3.sinaimg.cn/thumbnail/92e69d42jw1dwihg6f1ixj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('76', 'http://ww1.sinaimg.cn/bmiddle/96b2dea3jw1dzprd1d291j.jpg', '2012-12-19 10:00:18', '3524888704890074', '广东 广州', '3524888704890074', 'http://ww1.sinaimg.cn/large/96b2dea3jw1dzprd1d291j.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '圣诞节到了', 'http://ww1.sinaimg.cn/thumbnail/96b2dea3jw1dzprd1d291j.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('77', 'http://ww1.sinaimg.cn/bmiddle/7a085389jw1dzkvb5w5ghj.jpg', '2012-12-19 09:56:08', '3524887656710386', '广东 广州', '3524887656710386', 'http://ww1.sinaimg.cn/large/7a085389jw1dzkvb5w5ghj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '在自己眼里，这是爱； 在对方眼里，这是烦； 在别人眼里，这是贱。。!喜欢你的倾述，很贴心~！那么感同身受~！', 'http://ww1.sinaimg.cn/thumbnail/7a085389jw1dzkvb5w5ghj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('78', 'http://ww3.sinaimg.cn/bmiddle/82cbebc0gw1dyqagktg1tj.jpg', '2012-12-19 09:52:06', '3524886641532590', '广东 广州', '3524886641532590', 'http://ww3.sinaimg.cn/large/82cbebc0gw1dyqagktg1tj.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '科学减肥很重要，有效且不伤害身体，减肥的mm要相当注', 'http://ww3.sinaimg.cn/thumbnail/82cbebc0gw1dyqagktg1tj.jpg', '2405096864');
INSERT INTO `tb_capture_weiborecord` VALUES ('79', 'http://ww2.sinaimg.cn/bmiddle/a49a3684jw1dzg9hj0ia5j.jpg', '2012-12-19 09:48:05', '3524885626615760', '广东 广州', '3524885626615760', 'http://ww2.sinaimg.cn/large/a49a3684jw1dzg9hj0ia5j.jpg', '新浪微博', '0', '1', '18禁小漫画', 'http://app.weibo.com/t/feed/3auC5p', '越是成长 越难得到朋友 说得很对！句句都说到我真实的感受！[爱你]', 'http://ww2.sinaimg.cn/thumbnail/a49a3684jw1dzg9hj0ia5j.jpg', '2405096864');

-- ----------------------------
-- Table structure for `tb_dicinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dicinfo`;
CREATE TABLE `tb_dicinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dicCode` varchar(50) DEFAULT NULL,
  `dicDesc` varchar(200) DEFAULT NULL,
  `dicGroup` varchar(50) DEFAULT NULL,
  `dicValue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dicinfo
-- ----------------------------
INSERT INTO `tb_dicinfo` VALUES ('1', '系统最高', '拥有最高的管理权限。拥有所有操作权限。', 'roleType', '1');
INSERT INTO `tb_dicinfo` VALUES ('2', '管理员', '具有管理权根。可以管理系统日常操作。', 'roleType', '2');
INSERT INTO `tb_dicinfo` VALUES ('3', '普通用户', '普通操作用户。', 'roleType', '3');
INSERT INTO `tb_dicinfo` VALUES ('4', '测试人员', '测试', 'roleType', '4');
INSERT INTO `tb_dicinfo` VALUES ('5', '运营人员', '运营', 'roleType', '5');
INSERT INTO `tb_dicinfo` VALUES ('6', '客户', '第三方客户', 'roleType', '6');

-- ----------------------------
-- Table structure for `tb_function`
-- ----------------------------
DROP TABLE IF EXISTS `tb_function`;
CREATE TABLE `tb_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `functionName` varchar(300) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `tbModule_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5372F28971695874` (`tbModule_fk`),
  CONSTRAINT `FK5372F28971695874` FOREIGN KEY (`tbModule_fk`) REFERENCES `tb_module` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_function
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_logs`
-- ----------------------------
DROP TABLE IF EXISTS `tb_logs`;
CREATE TABLE `tb_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `logs` varchar(255) DEFAULT NULL,
  `user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1581 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_market_appcategory`
-- ----------------------------
DROP TABLE IF EXISTS `tb_market_appcategory`;
CREATE TABLE `tb_market_appcategory` (
  `categoryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(30) NOT NULL,
  `categoryValue` varchar(10) NOT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `categoryName` (`categoryName`,`categoryValue`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_market_appcategory
-- ----------------------------
INSERT INTO `tb_market_appcategory` VALUES ('15', '健康', 'JK');
INSERT INTO `tb_market_appcategory` VALUES ('19', '办公', 'BG');
INSERT INTO `tb_market_appcategory` VALUES ('2', '娱乐', 'YL');
INSERT INTO `tb_market_appcategory` VALUES ('6', '安全', 'AQ');
INSERT INTO `tb_market_appcategory` VALUES ('11', '导航', 'DH');
INSERT INTO `tb_market_appcategory` VALUES ('7', '工具', 'GJ');
INSERT INTO `tb_market_appcategory` VALUES ('8', '影音', 'YY');
INSERT INTO `tb_market_appcategory` VALUES ('12', '摄影', 'SY');
INSERT INTO `tb_market_appcategory` VALUES ('20', '教育', 'JY');
INSERT INTO `tb_market_appcategory` VALUES ('3', '新闻', 'XW');
INSERT INTO `tb_market_appcategory` VALUES ('16', '旅游', 'LY');
INSERT INTO `tb_market_appcategory` VALUES ('14', '浏览器', 'LLQ');
INSERT INTO `tb_market_appcategory` VALUES ('1', '游戏', 'YX');
INSERT INTO `tb_market_appcategory` VALUES ('18', '理财', 'LC');
INSERT INTO `tb_market_appcategory` VALUES ('10', '生活', 'SH');
INSERT INTO `tb_market_appcategory` VALUES ('4', '社交', 'SJ');
INSERT INTO `tb_market_appcategory` VALUES ('5', '系统', 'XT');
INSERT INTO `tb_market_appcategory` VALUES ('17', '购物', 'GW');
INSERT INTO `tb_market_appcategory` VALUES ('13', '输入法', 'SWF');
INSERT INTO `tb_market_appcategory` VALUES ('9', '阅读', 'YD');

-- ----------------------------
-- Table structure for `tb_market_appinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_market_appinfo`;
CREATE TABLE `tb_market_appinfo` (
  `appId` bigint(20) NOT NULL AUTO_INCREMENT,
  `appCategory` varchar(10) DEFAULT NULL,
  `appDesc` varchar(256) DEFAULT NULL,
  `appName` varchar(100) DEFAULT NULL,
  `appPath` varchar(200) DEFAULT NULL,
  `appPic` varchar(200) DEFAULT NULL,
  `appSize` varchar(20) DEFAULT NULL,
  `appVersionCode` int(11) DEFAULT NULL,
  `appVersionName` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `downloads` int(11) DEFAULT NULL,
  `fee` float DEFAULT NULL,
  `packageName` varchar(50) DEFAULT NULL,
  `sdkSupport` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`appId`),
  UNIQUE KEY `appName` (`appName`),
  UNIQUE KEY `packageName` (`packageName`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_market_appinfo
-- ----------------------------
INSERT INTO `tb_market_appinfo` VALUES ('1', 'XW', '', '网易', '/attachments/market/apps/com.netease.newsreader.activity/newsreader-wyyy_news.apk', '/attachments/market/apps/com.netease.newsreader.activity/icon_72.png', '5.69M', '228', '3.1.1.123456', '2013-02-04 17:05:26', 'admin', '0', '0', 'com.netease.newsreader.activity', 'Android 2.1');
INSERT INTO `tb_market_appinfo` VALUES ('3', 'SH', '', '神香道', '/attachments/market/apps/com.kybm.sxd/sxdProject.apk', '/attachments/market/apps/com.kybm.sxd/icon_72.png', '2.35M', '1', '1.0', '2013-02-25 11:58:33', null, '1', '0', 'com.kybm.sxd', 'Android 2.2');
INSERT INTO `tb_market_appinfo` VALUES ('5', 'YY', '', 'QQ音乐', '/attachments/market/apps/com.tencent.qqmusic/qqmusic_3.2.0.2565_android_20130111115436_72280.apk', '/attachments/market/apps/com.tencent.qqmusic/icon_72.png', '2.77M', '23', '3.2.0.2565', '2013-02-26 16:12:36', 'admin', '0', '0', 'com.tencent.qqmusic', 'Android 2.1');
INSERT INTO `tb_market_appinfo` VALUES ('9', 'XT', '', '腾讯手机管家', '/attachments/market/apps/com.tencent.qqpimsecure/tencentmobilemanager3.9.6_android_build0679_999001.apk', '/attachments/market/apps/com.tencent.qqpimsecure/icon_72.png', '7.11M', '92', '3.9.6', '2013-02-27 14:48:54', 'admin', '0', '0', 'com.tencent.qqpimsecure', 'Android 2.1');
INSERT INTO `tb_market_appinfo` VALUES ('10', 'XW', '', '腾讯新闻', '/attachments/market/apps/com.tencent.news/tencentnews_17_v2.4.0_74682.apk', '/attachments/market/apps/com.tencent.news/icon_72.png', '2.57M', '240', '2.4.0', '2013-02-27 14:57:44', 'admin', '0', '0', 'com.tencent.news', 'Android 2.1');
INSERT INTO `tb_market_appinfo` VALUES ('11', 'YX', '', '植物大战僵尸', '/attachments/market/apps/com.popcap.pvzq/MA2012041700121_F-A2.apk', '/attachments/market/apps/com.popcap.pvzq/icon_72.png', '9.46M', '102', '2.0', '2013-02-27 15:14:18', 'admin', '0', '0', 'com.popcap.pvzq', 'Android 2.1');
INSERT INTO `tb_market_appinfo` VALUES ('12', 'SJ', '', 'QQ2013', '/attachments/market/apps/com.tencent.mobileqq/qq2013_4.0.1.1547_android.apk', '/attachments/market/apps/com.tencent.mobileqq/icon_72.png', '10.53M', '28', '4.0.1', '2013-02-27 15:44:43', 'admin', '0', '0', 'com.tencent.mobileqq', 'Android 2.1');
INSERT INTO `tb_market_appinfo` VALUES ('14', 'YX', '', 'QQ寻仙', '/attachments/market/apps/com.tencent.qqgame.cardFwvga/cardf2.9.3_androidwvga_myapp_build0036.apk', '/attachments/market/apps/com.tencent.qqgame.cardFwvga/icon_72.png', '10.42M', '9', '2.9.3', '2013-02-27 16:19:58', 'admin', '0', '0', 'com.tencent.qqgame.cardFwvga', 'Android 2.1');

-- ----------------------------
-- Table structure for `tb_mobileinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_mobileinfo`;
CREATE TABLE `tb_mobileinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cardType` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `imsi` varchar(16) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  `service` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_mobileinfo
-- ----------------------------
INSERT INTO `tb_mobileinfo` VALUES ('3', '中国移动', '沈阳', '2011-04-07 12:36:32', '9460027424167768', '18742430970', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('4', '福建移动全球通卡', '泉州', '2011-04-07 12:37:35', '9460029059168993', '15905927546', '福建', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('5', '移动神州行卡', '白银', '2011-02-17 12:20:39', '9460009301139177', '13649307596', '甘肃', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('6', '广东移动全球通卡', '佛山', '2011-04-07 12:38:37', '9460029152158317', '15915224085', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('7', '中国移动', '重庆', '2011-04-07 12:38:44', '9460027233414238', '18723119203', '重庆', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('8', '中国移动', '通辽', '2011-04-07 12:40:50', '9460027047592641', '18747388674', '内蒙古', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('9', '江西移动神州行卡', '南昌', '2011-04-07 12:42:00', '9460023700055119', '15079173390', '江西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('10', '联通130卡', '北京', '2010-12-11 13:45:00', '9460010016202830', '13260298915', '北京', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('11', '山东移动全球通卡', '菏泽', '2011-04-07 12:42:03', '9460028540420031', '15866393440', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('12', '广西移动全球通卡', '南宁', '2011-04-07 12:43:09', '9460022964915552', '15296448240', '广西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('13', '甘肃联通GSM卡', '平凉', '2011-04-07 12:44:12', '9460012994019831', '13239337794', '甘肃', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('14', '中国联通', '鄂州', '2011-04-07 12:44:15', '9460018650621882', '18671101338', '湖北', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('15', '山东移动全球通卡', '菏泽', '2011-04-07 12:44:18', '9460021530881817', '15153001055', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('16', '山西移动全球通卡', '太原', '2011-04-07 12:44:20', '9460029034624345', '15034172212', '山西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('17', '安徽移动全球通卡', '合肥', '2011-04-07 12:45:21', '9460023560451750', '15212453181', '安徽', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('18', '云南移动全球通卡', '思茅', '2011-04-07 12:45:23', '9460023087535131', '15198305776', '云南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('19', '中国移动', '遵义', '2011-04-07 12:46:28', '9460027852591890', '18786884483', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('20', '山东联通GSM卡', '烟台', '2011-04-07 12:48:32', '9460015355258150', '13275459013', '山东', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('21', '中国移动152卡', '许昌', '2010-12-16 15:14:54', '9460029940818673', '15237469361', '河南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('22', '上海移动动感地带卡', '上海', '2011-04-07 12:48:34', '9460008813178771', '13818888389', '上海', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('23', '吉林移动全球通卡', '白山', '2011-04-07 12:48:35', '9460029439401277', '15944909506', '吉林', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('24', '中国移动', '百色', '2011-04-07 12:49:40', '9460027786606082', '18778671872', '广西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('25', '河南移动全球通卡', '商丘', '2011-04-07 12:49:41', '9460003701647717', '13781583914', '河南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('26', '中国移动', '台州', '2011-04-07 12:50:44', '9460078152304347', '18815269920', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('27', '浙江联通GSM卡', '杭州', '2011-04-07 12:51:47', '9460012148211567', '13296721052', '浙江', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('28', '吉林移动全球通卡', '长春', '2010-12-31 11:13:15', '9460026431038445', '18243160050', '吉林', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('29', '贵州移动黔中游卡', '遵义', '2011-04-07 12:51:47', '9460005664885869', '13885273522', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('30', '移动151卡', '唐山', '2011-01-01 18:05:13', '9460021325232767', '15132590406', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('31', '山东移动全球通卡', '烟台', '2011-04-07 12:52:53', '9460004544549718', '13963859285', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('32', '移动全球通卡', '北京', '2011-01-02 21:27:01', '9460028112331749', '15811279808', '北京', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('33', '广东移动全球通卡', '深圳', '2011-04-07 12:52:53', '9460029140349947', '15999674872', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('34', '广东联通GSM卡', '揭阳', '2011-04-07 12:53:56', '9460079868100815', '13076502370', '广东', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('35', '上海移动全球通卡', '上海', '2011-04-07 12:54:57', '9460022218192798', '15221815076', '上海', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('36', '广西移动大众卡', '桂林', '2011-04-07 12:56:02', '9460020713126280', '13471327563', '广西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('37', '浙江移动全球通卡', '金华', '2011-04-07 12:56:03', '9460029067908507', '15906898890', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('38', '广东移动全球通卡', '梅州', '2011-04-07 12:56:06', '9460005933270037', '13750515761', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('39', '移动151卡', '济南', '2011-01-09 14:32:12', '9460021069824003', '15165021272', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('40', '移动151卡', '西安', '2011-01-10 20:54:59', '9460021918107071', '15191817071', '陕西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('41', '移动187卡', '宁波', '2011-01-11 23:03:44', '9460027583000319', '18757424260', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('42', '移动150号段', '广州', '2011-01-12 20:52:34', '9460023117166769', '15011852121', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('43', '重庆移动全球通卡', '重庆', '2011-04-07 12:56:06', '9460005371353833', '13635362310', '重庆', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('44', '海南移动神州行卡', '海口', '2011-04-07 12:59:28', '9460008090109022', '13518845993', '海南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('45', '移动150卡', '东莞', '2011-01-16 10:32:16', '9460023199916196', '15019146145', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('46', '山西移动全球通卡', '吕梁', '2011-04-07 12:59:34', '9460020854540519', '15135278615', '山西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('47', '云南移动全球通卡', '文山', '2011-04-07 13:14:16', '9460021876502263', '15198622263', '云南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('48', '河南移动全球通卡', '驻马店', '2011-04-07 13:14:20', '9460026396254940', '15139687443', '河南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('49', '辽宁移动全球通卡', '朝阳', '2011-01-19 13:04:10', '9460026042008051', '18204220645', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('50', '黑龙江联通GSM卡', '哈尔滨', '2011-04-07 13:22:55', '9460015124445973', '15546520360', '黑龙江', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('51', '联通155卡', '长沙', '2011-01-20 23:01:55', '9460010998450282', '15575986691', '湖南', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('52', '移动全球通卡', '宁波', '2011-01-21 18:51:27', '9460006350638092', '13566340366', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('53', '移动全球通卡', '无锡', '2011-01-22 15:17:00', '9460028525817320', '15852584750', '江苏', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('54', '广东移动全球通卡', '湛江', '2011-04-07 13:22:57', '9460000060982044', '13590065654', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('55', '广东联通GSM卡', '深圳', '2011-04-07 13:23:58', '9460014716924102', '13164795292', '广东', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('56', '福建移动动感地带卡', '宁德', '2011-04-07 13:24:01', '9460000333651144', '13860364770', '福建', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('57', '移动152卡', '北京', '2011-01-25 15:49:03', '9460022100203128', '15210023278', '北京', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('58', '浙江移动全球通卡', '温州', '2011-04-07 13:24:01', '9460008834559435', '13958758190', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('59', '移动全球通卡', '金华', '2011-01-27 17:47:26', '9460007512755419', '13777537619', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('60', '移动全球通卡', '东莞', '2011-01-28 21:03:10', '9460020121803408', '13412183408', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('61', '广东移动神州行卡', '广州', '2011-04-07 12:37:34', '9460000641479493', '13640718382', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('62', '移动全球通卡', '上海', '2011-01-30 15:39:28', '9460004030218191', '13524002963', '上海', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('63', '福建移动全球通卡', '泉州', '2011-04-07 12:38:41', '9460022595211082', '15259534418', '福建', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('64', '浙江移动全球通卡', '台州', '2011-04-07 12:51:50', '9460007622559074', '13750666452', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('65', '中国移动', '赣州', '2011-04-07 12:58:15', '9460079070767115', '14796783677', '江西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('66', '联通130卡', '咸宁', '2011-02-01 20:10:33', '9460016183055135', '13036182215', '湖北', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('67', '甘肃移动全球通卡', '平凉', '2011-04-07 12:58:21', '9460027933045754', '15193316205', '甘肃', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('68', '黑龙江移动神州行卡', '黑河', '2011-04-07 13:01:59', '9460004838081287', '13555294189', '黑龙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('69', '移动全球通卡', '合肥', '2011-02-05 13:31:41', '9460005963663383', '13865967134', '安徽', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('70', '河南移动大众卡', '安阳', '2011-04-07 13:02:02', '9460026372607530', '13460936491', '河南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('71', '联通130卡', '绵阳', '2011-02-06 18:06:13', '9460016490023501', '13088112572', '四川', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('72', '山东移动全球通卡', '山东省莱芜市', '2011-04-07 13:10:58', '9460023068266888', '15020886236', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('73', '移动151卡', '湖州', '2011-02-08 14:45:51', '9460021572102471', '15157218171', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('74', '四川移动神州行卡', '眉山', '2011-04-07 13:16:29', '9460020386092821', '13550544991', '四川', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('75', '广东移动神州行卡', '汕头', '2011-04-07 13:17:38', '9460009690341579', '13542896470', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('76', '贵州移动全球通卡', '毕节', '2011-04-07 13:17:41', '9460023853694092', '15902596992', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('77', '山东移动神州行卡', '烟台', '2011-04-07 13:17:44', '9460005451627348', '13688668440', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('78', '中国移动', '周口', '2011-04-07 13:18:45', '9460022947566610', '18749258337', '河南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('79', '中国移动', '临沧', '2011-04-07 13:19:48', '9460009362579943', '18724959765', '云南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('80', '联通130卡', '新乡', '2011-02-13 02:12:53', '9460013752238036', '13233842915', '河南', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('81', '移动全球通卡', '贵阳', '2011-02-13 20:01:56', '9460004344890628', '13518510015', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('82', '广东联通如意通卡', '深圳', '2011-04-07 13:21:48', '9460018934081714', '13058158277', '广东', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('83', '内蒙古移动全球通卡', '赤峰', '2011-04-07 13:25:04', '9460021249869404', '15148316303', '内蒙古', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('84', '移动预付费卡', '博乐', '2011-02-15 17:24:09', '9460023090994847', '15909090272', '新疆', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('85', '中国移动', '红河', '2011-04-07 13:32:29', '9460021264474485', '18787384100', '云南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('86', '中国移动', '济南', '2011-04-07 13:34:45', '9460027541150625', '18753123581', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('87', '移动预付费卡', '深圳', '2011-02-17 20:21:10', '9460029140283291', '15914126564', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('88', '宁夏移动全球通卡', '石嘴山', '2011-04-07 13:35:57', '9460020696648656', '15909622466', '宁夏', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('89', '中国移动', '固原', '2011-04-07 13:36:00', '9460029096929453', '18295148061', '宁夏', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('90', '河南移动神州行卡', '郑州', '2011-04-07 13:37:06', '9460001402280812', '13663003013', '河南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('91', '移动动感地带卡', '威海', '2011-02-20 15:44:43', '9460001880678528', '13562118719', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('92', '移动全球通卡', '温州', '2011-02-21 10:07:59', '9460005871149387', '13616605935', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('93', '中国移动', '承德', '2011-04-07 13:37:09', '9460027314246790', '18730464262', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('94', '移动全球通卡', '常德', '2011-02-22 14:07:12', '9460002612636633', '13787880617', '湖南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('95', '山东移动全球通卡', '青岛', '2011-04-07 13:40:19', '9460029542739826', '15908923407', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('96', '移动187卡', '邯郸', '2011-02-23 16:52:55', '9460027031036307', '18732090816', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('97', '移动全球通卡', '温州', '2011-02-24 07:57:20', '9460000112829660', '13780116354', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('98', '中国移动', '吕梁', '2011-02-24 16:46:14', '9460000671229928', '15135487868', '山西', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('99', '中国移动', '遵义', '2011-04-07 13:42:26', '9460026852529459', '18275565241', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('100', '广东移动全球通卡', '广州', '2011-04-07 13:42:28', '9460000005326656', '13500037752', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('101', '山东移动神州行卡', '烟台', '2011-04-07 13:42:31', '9460005441683304', '13686382532', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('102', '中国移动', '遵义', '2011-04-07 13:44:35', '9460021201664965', '15208626441', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('103', '陕西移动全球通卡', '安康', '2011-04-07 13:44:36', '9460029911581800', '15191513457', '陕西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('104', '江苏移动全球通卡', '苏州', '2011-02-26 04:17:42', '9460023516621619', '15051660836', '江苏', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('105', '上海移动神州行卡', '上海', '2011-02-26 10:02:25', '9460001901298613', '13621996907', '上海', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('106', '江苏联通GSM卡', '徐州', '2011-02-26 11:54:29', '9460010217232535', '13101823262', '江苏', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('107', '河北移动全球通卡', '邢台', '2011-02-27 14:10:15', '9460021279221592', '15127948199', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('108', '广东移动神州行卡', '深圳', '2011-02-27 20:20:19', '9460008210331124', '13538233850', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('109', '浙江移动全球通卡', '宁波', '2011-03-02 08:32:58', '9460023584334689', '15058405067', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('110', '山东移动全球通卡', '德州', '2011-03-02 16:18:12', '9460021534681518', '15165341317', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('111', '黑龙江移动全球通卡', '哈尔滨', '2011-03-03 16:08:24', '9460006132941826', '15104658032', '黑龙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('112', '河北移动全球通卡', '张家口', '2011-03-04 09:51:11', '9460001314347389', '13932348758', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('113', '辽宁移动全球通卡', '铁岭', '2011-04-07 13:52:04', '9460022410208601', '15241076056', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('114', '山东移动全球通卡', '临沂', '2011-03-05 16:32:03', '9460022639312344', '15206830040', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('115', '中国移动', '海口', '2011-03-06 19:20:45', '9460078089659051', '18876779394', '海南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('116', '浙江移动全球通卡', '宁波', '2011-03-07 12:04:49', '9460006530858366', '13586527146', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('117', '四川联通GSM卡', '成都', '2011-03-08 13:03:18', '9460019041225298', '13281295107', '四川', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('118', '广东移动全球通卡', '深圳', '2011-03-08 21:03:23', '9460006451887572', '13715068825', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('119', '辽宁移动全球通卡', '盘锦', '2011-03-09 14:20:08', '9460022417217961', '15241768989', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('120', '天津移动全球通卡', '天津', '2011-03-09 20:26:54', '9460002970123541', '13512935287', '天津', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('121', '安徽联通GSM卡', '阜阳', '2011-03-10 12:27:48', '9460013195049046', '15555953553', '安徽', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('122', '浙江移动全球通卡', '嘉兴', '2011-03-10 21:41:08', '9460023673831977', '15005839293', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('123', '广东移动大众卡', '中山', '2011-03-11 18:36:49', '9460020214025630', '13421468450', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('124', '贵州移动全球通卡', '毕节', '2011-03-12 13:35:15', '9460023853248789', '15934724003', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('125', '广东移动全球通卡', '广州', '2011-03-12 22:04:53', '9460000046380511', '13600057748', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('126', '广东移动大众卡', '茂名', '2011-03-13 14:00:36', '9460003660516380', '13535921651', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('127', '浙江移动神州行卡', '杭州', '2011-03-13 20:12:25', '9460009113143078', '13819124720', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('128', '四川移动动感地带卡', '成都', '2011-03-14 13:54:41', '9460008021484944', '13880498849', '四川', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('129', '浙江移动全球通卡', '金华', '2011-03-14 20:57:17', '9460000972559501', '13757912044', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('130', '辽宁联通如意通卡', '鞍山', '2011-03-15 15:12:43', '9460012604033229', '13042625977', '辽宁', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('131', '中国移动', '定西', '2011-03-15 15:37:27', '9460027932173990', '18719626349', '甘肃', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('132', '浙江移动大众卡', '台州', '2011-03-16 11:46:13', '9460020566542662', '13456462823', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('133', '四川移动全球通卡', '内江', '2011-03-16 21:27:17', '9460000513980629', '15282144469', '四川', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('134', '北京联通GSM卡', '北京', '2011-03-17 19:24:24', '9460010354241804', '13240349552', '北京', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('135', '山东移动全球通卡', '潍坊', '2011-03-18 14:58:33', '9460003603649142', '13884701109', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('136', '广东移动动感地带卡', '深圳', '2011-03-18 23:05:29', '9460002551371898', '13632540142', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('137', '浙江联通GSM卡', '宁波', '2011-03-19 15:43:29', '9460012208248994', '13216641307', '浙江', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('138', '河北移动全球通卡', '秦皇岛', '2011-03-20 10:26:12', '9460022323190072', '15227253442', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('139', '陕西移动全球通卡', '榆林', '2011-03-20 18:02:28', '9460023915072528', '15129320306', '陕西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('140', '河北移动全球通卡', '唐山', '2011-03-21 09:37:28', '9460022305171832', '15231503385', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('141', '辽宁移动全球通卡', '沈阳', '2011-03-21 18:10:48', '9460029401493675', '13591474359', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('142', '黑龙江移动全球通卡', '绥化', '2011-03-22 12:31:24', '9460022455044676', '15846689057', '黑龙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('143', '江西移动全球通卡', '新余', '2011-03-22 18:45:09', '9460009023720130', '13879019502', '江西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('144', '陕西移动神州行卡', '西安', '2011-03-23 09:37:56', '9460009246066460', '13649214727', '陕西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('145', '中国移动', '宿迁', '2011-03-23 18:36:45', '9460027621503819', '18762153320', '江苏', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('146', '青海移动全球通卡', '西宁', '2011-03-24 12:36:48', '9460007203939168', '15003603427', '青海', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('147', '西藏联通GSM卡', '拉萨', '2011-03-24 17:47:30', '9460018992212308', '13298990291', '西藏', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('148', '吉林移动全球通卡', '长春', '2011-03-25 21:33:04', '9460004328145738', '13944972508', '吉林', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('149', '中国移动', '岳阳', '2011-03-26 19:28:51', '9460079895022648', '14789621124', '湖南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('150', '中国移动', '商丘', '2011-03-27 16:19:09', '9460026370056327', '18240760282', '河南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('151', '吉林联通GSM卡', '四平', '2011-03-27 23:29:04', '9460015241394551', '15662677952', '吉林', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('152', '浙江移动全球通卡', '宁波', '2011-03-28 17:07:27', '9460008452302524', '13736028222', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('153', '山东移动全球通卡', '济南', '2011-03-29 08:47:33', '9460023633553273', '15065418886', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('154', '辽宁移动全球通卡', '大连', '2011-03-30 09:30:33', '9460021405309644', '15140524916', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('155', '山东移动全球通卡', '东营', '2011-04-07 17:33:14', '9460010525463276', '15954687314', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('156', '宁夏移动全球通卡', '银川', '2011-04-07 17:34:20', '9460028096626923', '15008600864', '宁夏', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('157', '甘肃移动全球通卡', '兰州', '2011-04-07 17:38:39', '9460009471598021', '13919019943', '甘肃', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('158', '中国移动', '贵阳', '2011-04-07 17:38:42', '9460027851828427', '18786718392', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('159', '吉林移动全球通卡', '长春', '2011-04-07 17:38:42', '9460029487898472', '15944103284', '吉林', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('160', '广东移动全球通卡', '深圳', '2011-04-07 17:38:54', '9460023135229181', '15013557692', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('161', '山东移动全球通卡', '济宁', '2011-04-07 17:39:57', '9460023064738790', '15053770172', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('162', '河北移动全球通卡', '石家庄', '2011-04-07 17:40:00', '9460022301237688', '15231198738', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('163', '山西移动大众卡', '朔州', '2011-04-07 17:44:04', '9460003481290669', '13453091478', '山西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('164', '浙江移动大众卡', '宁波', '2011-04-07 17:44:07', '9460020860407468', '13486059530', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('165', '中国移动', '贵阳', '2011-04-07 17:44:07', '9460027850377707', '18786739300', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('166', '河北移动大众卡', '邯郸', '2011-04-07 17:44:10', '9460020630557180', '13473031722', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('167', '广东移动全球通卡', '深圳', '2011-04-07 17:46:19', '9460023020650764', '15889670049', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('168', '贵州移动全球通卡', '都匀', '2011-04-07 17:46:25', '9460023861785054', '15185525220', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('169', '中国移动', '常州', '2011-04-07 17:47:32', '9460027969602378', '18796965074', '江苏', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('170', '安徽移动全球通卡', '芜湖', '2011-04-07 17:54:06', '9460006373655259', '13955359887', '安徽', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('171', '上海移动全球通卡', '上海', '2011-04-07 17:54:07', '9460002130126232', '13512172926', '上海', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('172', '湖北联通GSM卡', '江汉', '2011-04-07 18:23:35', '9460017209255976', '15572890026', '湖北', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('173', '江西移动神州行卡', '上饶', '2011-04-07 18:24:41', '9460007931193586', '13694833398', '江西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('174', '河南移动全球通卡', '驻马店', '2011-04-07 18:24:44', '9460003426212186', '13723082491', '河南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('175', '上海移动全球通卡', '上海', '2011-04-07 18:25:51', '9460001607237672', '13701690499', '上海', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('176', '辽宁移动全球通卡', '辽阳', '2011-04-07 18:26:54', '9460021409484612', '15140971962', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('177', '陕西移动全球通卡', '西安', '2011-04-07 18:26:57', '9460002880763795', '15891497029', '陕西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('178', '甘肃移动动感地带卡', '兰州', '2011-04-07 18:29:09', '9460009451584952', '13893445338', '甘肃', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('179', '浙江移动全球通卡', '杭州', '2011-04-07 18:29:16', '9460021671825876', '15167186801', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('180', '山东移动全球通卡', '德州', '2011-04-07 18:31:23', '9460003464551153', '13953491032', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('181', '中国联通', '临沂', '2011-04-07 18:32:33', '9460010945416216', '15589034816', '山东', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('182', '河北移动全球通卡', '邯郸', '2011-04-07 18:32:34', '9460023310428308', '15032006864', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('183', '吉林移动全球通卡', '延吉', '2011-04-07 18:32:37', '9460004330193423', '13844724690', '吉林', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('184', '山东移动全球通卡', '菏泽', '2011-04-07 18:37:22', '9460022530761655', '15269037712', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('185', '湖南移动全球通卡', '常德', '2011-04-07 18:38:48', '9460002672631803', '13786656461', '湖南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('186', '内蒙古移动全球通卡', '海拉尔', '2011-04-07 18:42:15', '9460021047017374', '15148411100', '内蒙古', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('187', '辽宁移动神州行卡', '大连', '2011-04-07 18:50:07', '9460004251469180', '13644249342', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('188', '四川移动全球通卡', '成都', '2011-04-07 18:52:11', '9460029286595674', '15982008497', '四川', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('189', '山东移动全球通卡', '东营', '2011-04-07 18:52:25', '9460022638409526', '15266190155', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('190', '河北移动全球通卡', '唐山', '2011-04-07 18:54:59', '9460003257050543', '13503155335', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('191', '吉林联通GSM卡', '长春', '2011-04-07 18:56:10', '9460014420909297', '13251726915', '吉林', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('192', '辽宁移动全球通卡', '辽阳', '2011-04-07 18:58:21', '9460029419267149', '15941955737', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('193', '浙江移动全球通卡', '杭州', '2011-04-07 18:58:25', '9460029888824825', '15988410182', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('194', '云南移动全球通卡', '曲靖', '2011-04-07 18:58:29', '9460007400795012', '13769845477', '云南', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('195', '中国联通', '娄底', '2011-04-07 18:59:33', '9460017380670521', '18673872897', '湖南', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('196', '山东移动全球通卡', '临沂', '2011-04-07 18:59:36', '9460023053920329', '15092953456', '山东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('197', '甘肃移动全球通卡', '兰州', '2011-04-07 19:00:43', '9460003183997758', '13893667981', '甘肃', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('198', '广东联通如意通卡', '深圳', '2011-04-07 19:00:47', '9460018934097079', '13049828822', '广东', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('199', '贵州移动全球通卡', '毕节', '2011-04-07 19:04:22', '9460023863018989', '15117537057', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('200', '黑龙江移动全球通卡', '伊春', '2011-04-07 19:04:25', '9460004575039169', '13895931934', '黑龙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('201', '安徽移动动感地带卡', '合肥', '2011-04-07 19:05:28', '9460001092247370', '13637058261', '安徽', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('202', '浙江移动全球通卡', '宁波', '2011-04-07 19:06:31', '9460015299228335', '15088823451', '浙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('203', '中国移动', '乌兰浩特', '2011-04-07 19:09:59', '9460021489126372', '15144917162', '内蒙古', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('204', '甘肃移动全球通卡', '白银', '2011-04-07 19:11:02', '9460027943594883', '15193019358', '甘肃', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('205', '甘肃移动全球通卡', '天水', '2011-04-07 19:12:03', '9460021938109399', '15120582960', '甘肃', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('206', '贵州移动全球通卡', '铜仁', '2011-04-07 19:12:06', '9460029856358452', '15186023825', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('207', '辽宁移动全球通卡', '辽阳', '2011-04-07 19:12:12', '9460001943431631', '13841934765', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('208', '中国移动', '芜湖', '2011-04-07 19:13:10', '9460029553349211', '15240046792', '安徽', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('209', '辽宁移动全球通卡', '沈阳', '2011-04-07 19:13:13', '9460029404532925', '15940427978', '辽宁', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('210', '天津移动全球通卡', '天津', '2011-04-07 19:13:15', '9460002990120531', '13512994006', '天津', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('211', '福建移动八闽通卡', '福州', '2011-04-07 19:14:18', '9460005907295600', '13705974868', '福建', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('212', '广东移动大众卡', '惠州', '2011-04-07 19:14:19', '9460020146249818', '13428025965', '广东', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('213', '江西移动全球通卡', '宜春', '2011-04-07 19:18:41', '9460021805595753', '15180504080', '江西', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('214', '新疆移动全球通卡', '库尔勒', '2011-04-07 19:19:46', '9460023994894964', '15276225850', '新疆', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('215', '上海移动全球通卡', '上海', '2011-04-07 13:56:28', '9460028216343967', '15821656410', '上海', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('216', '中国移动', '遵义', '2011-04-07 13:56:29', '9460026852510416', '18275528305', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('217', '江苏移动全球通卡', '扬州', '2011-04-07 13:59:35', '9460003372748130', '13773378714', '江苏', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('218', '北京移动神州行卡', '北京', '2011-04-07 13:59:37', '9460001141237667', '13621047544', '北京', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('219', '安徽联通GSM卡', '安徽省毫州市', '2011-04-07 13:59:38', '9460015678203013', '13156794128', '安徽', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('220', '中国移动', '张家口', '2011-04-07 13:59:44', '9460027032321862', '18732359831', '河北', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('221', '浙江联通GSM卡', '绍兴', '2011-04-07 14:01:47', '9460013987925631', '13157500079', '浙江', '联通');
INSERT INTO `tb_mobileinfo` VALUES ('222', '黑龙江移动神州行卡', '齐齐哈尔', '2011-04-07 14:01:50', '9460021646213824', '13694527656', '黑龙江', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('223', '中国移动', '都匀', '2011-04-07 14:01:53', '9460026085470204', '18285456002', '贵州', '移动');
INSERT INTO `tb_mobileinfo` VALUES ('224', '河南移动全球通卡', '驻马店', '2011-04-07 14:01:53', '9460003806739793', '13839664701', '河南', '移动');

-- ----------------------------
-- Table structure for `tb_module`
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expanded` bit(1) DEFAULT NULL,
  `file` varchar(200) DEFAULT NULL,
  `iconCls` varchar(20) DEFAULT NULL,
  `leaf` bit(1) DEFAULT NULL,
  `moduleId` varchar(30) DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `moduleId` (`moduleId`),
  KEY `FK54F11D5D18397AEF` (`parentId`),
  CONSTRAINT `FK54F11D5D18397AEF` FOREIGN KEY (`parentId`) REFERENCES `tb_module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_module
-- ----------------------------
INSERT INTO `tb_module` VALUES ('1', null, null, 'icon-hotel', null, 'platformMgr', '平台管理', null);
INSERT INTO `tb_module` VALUES ('2', '', '', 'icon-catalog', '', 'sysManager', '系统管理', '1');
INSERT INTO `tb_module` VALUES ('3', '', '/sysManager/userManager.js', 'details', '', 'userManagerPanel', '用户管理', '2');
INSERT INTO `tb_module` VALUES ('4', '', '/sysManager/moduleManager.js,/sysManager/moduleAddWin.js', 'details', '', 'moduleManagerPanel', '模块管理', '2');
INSERT INTO `tb_module` VALUES ('5', '', '/sysManager/permissionManager.js', 'details', '', 'permissionManagerPanel', '权限管理', '2');
INSERT INTO `tb_module` VALUES ('6', '', '/sysManager/logsManager.js', 'details', '', 'logsManagerPanel', '日志管理', '2');
INSERT INTO `tb_module` VALUES ('7', '', '/sysManager/modifyPwd.js', 'details', '', 'modifyPwdPanel', '修改密码', '2');
INSERT INTO `tb_module` VALUES ('8', '', '', 'icon-hotel', '', 'sxd', '神香道APP', null);
INSERT INTO `tb_module` VALUES ('9', '', '', 'icon-catalog', '', 'sxdMgr', '应用管理', '8');
INSERT INTO `tb_module` VALUES ('10', '', '/sxd/app/dictMain.js,/sxd/app/dictAddorUpWin.js', 'details', '', 'sxdDictPanel', 'APP字典管理', '9');
INSERT INTO `tb_module` VALUES ('11', '', '/sxd/app/sxdVersionMain.js,/sxd/app/sxdVersionAddorUpWin.js', 'details', '', 'sxdVersionMainPanel', 'APP版本管理', '9');
INSERT INTO `tb_module` VALUES ('12', '', '', 'icon-catalog', '', 'sxdUser', '个人中心', '8');
INSERT INTO `tb_module` VALUES ('13', '', '/sxd/user/userMain.js,/sxd/user/userUpWin.js', 'details', '', 'sxdUserMainPanel', '用户管理', '12');
INSERT INTO `tb_module` VALUES ('14', '', '', 'icon-catalog', '', 'prayMgr', '上香祈福', '8');
INSERT INTO `tb_module` VALUES ('15', '', '/sxd/pray/buddhaImageMain.js,/sxd/pray/buddhaImageAddorUpWin.js', 'details', '', 'buddhaImagePanel', '供奉佛像', '14');
INSERT INTO `tb_module` VALUES ('16', '', '/sxd/pray/worshipArticleMain.js,/sxd/pray/worshipArticleAddorUpWin.js', 'details', '', 'worshipArticlePanel', '供奉物品', '14');
INSERT INTO `tb_module` VALUES ('17', '', '/sxd/pray/prayRecordMain.js', 'details', '', 'prayRecordPanel', '祈福记录', '14');
INSERT INTO `tb_module` VALUES ('18', '', '', 'icon-catalog', '', 'musics', '静心佛音', '8');
INSERT INTO `tb_module` VALUES ('19', '', '/sxd/music/musicMain.js,/sxd/music/musicAddorUpWin.js', 'details', '', 'musicPanel', '佛音列表', '18');
INSERT INTO `tb_module` VALUES ('20', '', '', 'icon-catalog', '', 'news', '佛教新闻', '8');
INSERT INTO `tb_module` VALUES ('21', '', '/sxd/news/newsMain.js', 'details', '', 'newsPanel', '新闻列表', '20');
INSERT INTO `tb_module` VALUES ('22', '', '', 'icon-catalog', '', 'buddhalore', '佛学常识', '8');
INSERT INTO `tb_module` VALUES ('23', '', '/sxd/lore/loreMain.js,/sxd/lore/loreAddorUpWin.js', 'details', '', 'lorePanel', '佛学常识列表', '22');
INSERT INTO `tb_module` VALUES ('24', '', '', 'icon-catalog', '', 'books', '在线诵经', '8');
INSERT INTO `tb_module` VALUES ('25', '', '/sxd/books/booksMain.js', 'details', '', 'booksPanel', '经书列表', '24');
INSERT INTO `tb_module` VALUES ('26', '', '/sxd/books/chantingRecordMain.js', 'details', '', 'chantingRecordPanel', '诵经计录', '24');
INSERT INTO `tb_module` VALUES ('30', null, null, 'icon-hotel', null, 'market', '应用商城', null);
INSERT INTO `tb_module` VALUES ('31', '', '/sxd/wb/weiboMain.js', 'details', '', 'weiboPanel', '大师微博', '20');
INSERT INTO `tb_module` VALUES ('32', null, null, 'icon-hotel', null, 'dataCaptrueMgr', '数据采集', null);
INSERT INTO `tb_module` VALUES ('35', '', '', 'icon-catalog', '', 'captrueMgr', '采集管理', '32');
INSERT INTO `tb_module` VALUES ('36', '', '/datacaptrue/jobs/jobMain.js', 'details', '', 'jobPanel', '采集任务', '35');
INSERT INTO `tb_module` VALUES ('37', '', '/datacaptrue/jobs/wbUserMain.js', 'details', '', 'wbUserPanel', '采集微博', '35');
INSERT INTO `tb_module` VALUES ('38', '', '', 'icon-hotel', '', 'bdgMgr', '吊丝不寂寞APP', null);
INSERT INTO `tb_module` VALUES ('39', '', '', 'icon-catalog', '', 'bdgRecordMgr', '记录管理', '38');
INSERT INTO `tb_module` VALUES ('40', '', '/bdg/record/bdgBrRecordMain.js', 'details', '', 'bdgBrRecordPanel', '发布前记录', '39');
INSERT INTO `tb_module` VALUES ('41', '', '/bdg/record/bdgArRecordMain.js', 'details', '', 'bdgArRecordPanel', '发布后记录', '39');
INSERT INTO `tb_module` VALUES ('42', '', '', 'icon-catalog', '', 'dataMgr', '记录管理', '32');
INSERT INTO `tb_module` VALUES ('43', '', '/datacaptrue/record/wbRecordMain.js', 'details', '', 'wbRecordPanel', '微博记录', '42');
INSERT INTO `tb_module` VALUES ('44', '', '', 'icon-catalog', '', 'bdgAppWallMgr', '软件墙', '38');
INSERT INTO `tb_module` VALUES ('45', '', '/bdg/appwall/bdgAppWallMain.js', 'details', '', 'bdgAppWallPanel', 'APP管理', '44');
INSERT INTO `tb_module` VALUES ('46', '', '', 'icon-catalog', '', 'bdgUser', '用户管理', '38');
INSERT INTO `tb_module` VALUES ('47', '', '/bdg/user/userMain.js', 'details', '', 'bdgUserMainPanel', '吊丝不寂寞用户', '46');
INSERT INTO `tb_module` VALUES ('48', '', '', 'icon-catalog', '', 'appMgr', '商城管理', '30');
INSERT INTO `tb_module` VALUES ('49', '', '/market/appMgr/appMain.js', 'details', '', 'appMainPanel', 'APP列表', '48');
INSERT INTO `tb_module` VALUES ('50', '', '/market/appMgr/appCategoryMain.js', 'details', '', 'appCategoryPanel', 'APP分类', '48');
INSERT INTO `tb_module` VALUES ('51', '', '/market/appMgr/uploadAppMain.js', 'details', '', 'uploadAppPanel', '上传APP', '48');
INSERT INTO `tb_module` VALUES ('52', '', '', 'icon-catalog', '', 'bdgPushMgr', '推送管理', '38');
INSERT INTO `tb_module` VALUES ('53', '', '/bdg/push/bdgContentPushMain.js', 'details', '', 'bdgContentPushPanel', '内容推送', '52');
INSERT INTO `tb_module` VALUES ('54', '', '/bdg/push/bdgAppPushMain.js', 'details', '', 'bdgAppPushPanel', '应用推送', '52');
INSERT INTO `tb_module` VALUES ('55', null, null, 'icon-hotel', null, 'qdtgMgr', '渠道推广平台', null);
INSERT INTO `tb_module` VALUES ('56', '', '', 'icon-catalog', '', 'qdtgCvsMgr', '客户管理', '55');
INSERT INTO `tb_module` VALUES ('57', '', '/qdtg/qdtgCvsMain.js', 'details', '', 'qdtgCvsPanel', '客户列表', '56');
INSERT INTO `tb_module` VALUES ('58', '', '', 'icon-catalog', '', 'qdtgChannelMgr', '渠道管理', '55');
INSERT INTO `tb_module` VALUES ('59', '', '/qdtg/qdtgChannelMain.js', 'details', '', 'qdtgChannelPanel', '渠道列表', '58');
INSERT INTO `tb_module` VALUES ('60', '', '', 'icon-catalog', '', 'qdtgRecordMgr', '记录管理', '55');
INSERT INTO `tb_module` VALUES ('61', '', '/qdtg/qdtgRecordImport.js', 'details', '', 'qdtgChannelImportPanel', '记录录入', '60');
INSERT INTO `tb_module` VALUES ('62', '', '/qdtg/qdtgRecordMain.js', 'details', '', 'qdtgRecordPanel', '记录列表', '60');
INSERT INTO `tb_module` VALUES ('63', '', '/qdtg/statQdtgRecordByCvs.js', 'details', '', 'statQdtgRecordByCvsPanel', '我的记录列表', '60');
INSERT INTO `tb_module` VALUES ('64', '', '', 'icon-catalog', '', 'productMgr', '产品管理', '55');
INSERT INTO `tb_module` VALUES ('65', '', '/qdtg/qdtgProductMain.js', 'details', '', 'qdtgProductPanel', '产品列表', '64');
INSERT INTO `tb_module` VALUES ('70', '', '/qdtg/statQdtgRecord.js', 'details', '', 'statQdtgRecordPanel', '记录统计', '60');

-- ----------------------------
-- Table structure for `tb_qdtb_cvs`
-- ----------------------------
DROP TABLE IF EXISTS `tb_qdtb_cvs`;
CREATE TABLE `tb_qdtb_cvs` (
  `cvsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `cvsName` varchar(100) NOT NULL,
  `unitPrice` float DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cvsId`),
  UNIQUE KEY `cvsName` (`cvsName`) USING BTREE,
  UNIQUE KEY `userId` (`userId`) USING BTREE,
  KEY `FK50FB4B93E09B02FD` (`userId`) USING BTREE,
  CONSTRAINT `tb_qdtb_cvs_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `tb_userinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_qdtb_cvs
-- ----------------------------
INSERT INTO `tb_qdtb_cvs` VALUES ('2', '掌众传媒', null, '9');
INSERT INTO `tb_qdtb_cvs` VALUES ('3', '冉丰科技', null, '10');
INSERT INTO `tb_qdtb_cvs` VALUES ('4', '优友传媒', null, '11');
INSERT INTO `tb_qdtb_cvs` VALUES ('5', '李苗', null, '12');
INSERT INTO `tb_qdtb_cvs` VALUES ('6', '斑马', null, '13');
INSERT INTO `tb_qdtb_cvs` VALUES ('7', '伍理化', null, '14');
INSERT INTO `tb_qdtb_cvs` VALUES ('8', '辉烨贾楠', null, '15');
INSERT INTO `tb_qdtb_cvs` VALUES ('9', '童帝平', null, '16');

-- ----------------------------
-- Table structure for `tb_qdtg_channel`
-- ----------------------------
DROP TABLE IF EXISTS `tb_qdtg_channel`;
CREATE TABLE `tb_qdtg_channel` (
  `channelId` bigint(20) NOT NULL AUTO_INCREMENT,
  `channelNo` varchar(30) NOT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `cvsId` bigint(20) DEFAULT NULL,
  `productId` bigint(20) DEFAULT NULL,
  `proportion` float DEFAULT NULL,
  `unitPrice` float DEFAULT NULL,
  PRIMARY KEY (`channelId`),
  UNIQUE KEY `channelNo` (`channelNo`) USING BTREE,
  KEY `FK511D9FBB235CDF9E` (`productId`) USING BTREE,
  KEY `FK511D9FBB983AACC0` (`cvsId`) USING BTREE,
  CONSTRAINT `tb_qdtg_channel_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tb_qdtg_product` (`productId`),
  CONSTRAINT `tb_qdtg_channel_ibfk_2` FOREIGN KEY (`cvsId`) REFERENCES `tb_qdtb_cvs` (`cvsId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_qdtg_channel
-- ----------------------------
INSERT INTO `tb_qdtg_channel` VALUES ('6', 'mn01', 'null', '2', '3', '0', '1.8');
INSERT INTO `tb_qdtg_channel` VALUES ('7', 'mn04', 'null', '4', '3', '0', '1.8');
INSERT INTO `tb_qdtg_channel` VALUES ('8', 'C6340', 'C6340', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('9', 'C6510', 'C6510', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('10', 'C6511', 'C6511', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('11', 'C6512', 'C6512', '3', '2', '0', '1');
INSERT INTO `tb_qdtg_channel` VALUES ('12', 'C6586', 'C6586', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('13', 'C6587', 'C6587', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('14', 'C6588', 'C6588', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('15', 'C6589', 'C6589', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('16', 'C6590', 'C6590', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('17', 'C6591', 'C6591', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('18', 'C6592', 'C6592', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('19', 'C6593', 'C6593', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('20', 'C6594', 'C6594', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('21', 'C6595', 'C6595', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('22', 'C6555', 'C6555', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('23', 'C6556', 'C6556', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('24', 'C6557', 'C6557', '5', '2', '0', '1.2');
INSERT INTO `tb_qdtg_channel` VALUES ('25', 'C6558', 'C6558', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('26', 'C6559', 'C6559', '6', '2', '0', '1.2');
INSERT INTO `tb_qdtg_channel` VALUES ('27', 'C6712', 'C6712', '7', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('28', 'C6713', 'C6713', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('29', 'C6714', 'C6714', '2', '2', '0', '0.65');
INSERT INTO `tb_qdtg_channel` VALUES ('30', 'C6715', 'C6715', '8', '2', '0', '1');
INSERT INTO `tb_qdtg_channel` VALUES ('31', 'C6716', 'C6716', '9', '2', '0.55', '0.65');

-- ----------------------------
-- Table structure for `tb_qdtg_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_qdtg_product`;
CREATE TABLE `tb_qdtg_product` (
  `productId` bigint(20) NOT NULL AUTO_INCREMENT,
  `company` varchar(100) DEFAULT NULL,
  `productName` varchar(50) NOT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_qdtg_product
-- ----------------------------
INSERT INTO `tb_qdtg_product` VALUES ('2', '高德软件有限公司', '高德地图');
INSERT INTO `tb_qdtg_product` VALUES ('3', '飞拓', '蒙牛未来星');

-- ----------------------------
-- Table structure for `tb_qdtg_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_qdtg_record`;
CREATE TABLE `tb_qdtg_record` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `activateQt` int(11) DEFAULT NULL,
  `channelId` bigint(20) DEFAULT NULL,
  `channelNo` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `cvsId` bigint(20) DEFAULT NULL,
  `cvsName` varchar(100) DEFAULT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `productId` bigint(20) DEFAULT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `status` bit(1) DEFAULT b'0',
  `totalPrice` float DEFAULT NULL,
  `unitPrice` float DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `activateQt_Net` int(11) DEFAULT NULL,
  `recordTime` date DEFAULT NULL,
  `totalPrice_Net` float DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_qdtg_record
-- ----------------------------
INSERT INTO `tb_qdtg_record` VALUES ('10', '11', '8', 'C6340', '2013-05-24 11:44:31', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '7.15', '0.65', '9', '11', '2013-04-09', '7.15');
INSERT INTO `tb_qdtg_record` VALUES ('11', '77', '8', 'C6340', '2013-05-24 11:45:45', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '50.05', '0.65', '9', '77', '2013-04-10', '50.05');
INSERT INTO `tb_qdtg_record` VALUES ('12', '111', '8', 'C6340', '2013-05-24 11:46:20', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '72.15', '0.65', '9', '111', '2013-04-11', '72.15');
INSERT INTO `tb_qdtg_record` VALUES ('13', '126', '8', 'C6340', '2013-05-24 11:47:01', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '81.9', '0.65', '9', '126', '2013-04-12', '81.9');
INSERT INTO `tb_qdtg_record` VALUES ('14', '113', '8', 'C6340', '2013-05-24 11:47:23', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '73.45', '0.65', '9', '113', '2013-04-13', '73.45');
INSERT INTO `tb_qdtg_record` VALUES ('15', '108', '8', 'C6340', '2013-05-24 11:47:48', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '70.2', '0.65', '9', '108', '2013-04-14', '70.2');
INSERT INTO `tb_qdtg_record` VALUES ('16', '143', '8', 'C6340', '2013-05-24 11:48:34', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '92.95', '0.65', '9', '143', '2013-04-15', '92.95');
INSERT INTO `tb_qdtg_record` VALUES ('17', '119', '8', 'C6340', '2013-05-24 11:48:57', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '77.35', '0.65', '9', '119', '2013-04-16', '77.35');
INSERT INTO `tb_qdtg_record` VALUES ('18', '110', '8', 'C6340', '2013-05-24 11:49:19', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '71.5', '0.65', '9', '110', '2013-04-17', '71.5');
INSERT INTO `tb_qdtg_record` VALUES ('19', '148', '8', 'C6340', '2013-05-24 11:49:44', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '96.2', '0.65', '9', '148', '2013-04-18', '96.2');
INSERT INTO `tb_qdtg_record` VALUES ('20', '98', '8', 'C6340', '2013-05-24 11:50:11', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '63.7', '0.65', '9', '98', '2013-04-19', '63.7');
INSERT INTO `tb_qdtg_record` VALUES ('21', '61', '8', 'C6340', '2013-05-24 11:50:31', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '39.65', '0.65', '9', '61', '2013-04-20', '39.65');
INSERT INTO `tb_qdtg_record` VALUES ('22', '55', '8', 'C6340', '2013-05-24 11:50:57', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '35.75', '0.65', '9', '55', '2013-04-21', '35.75');
INSERT INTO `tb_qdtg_record` VALUES ('23', '58', '8', 'C6340', '2013-05-24 11:51:23', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '37.7', '0.65', '9', '58', '2013-04-22', '37.7');
INSERT INTO `tb_qdtg_record` VALUES ('24', '21', '8', 'C6340', '2013-05-24 11:51:47', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '13.65', '0.65', '9', '21', '2013-04-23', '13.65');
INSERT INTO `tb_qdtg_record` VALUES ('25', '16', '8', 'C6340', '2013-05-24 11:52:03', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '10.4', '0.65', '9', '16', '2013-04-24', '10.4');
INSERT INTO `tb_qdtg_record` VALUES ('26', '27', '8', 'C6340', '2013-05-24 11:52:32', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '17.55', '0.65', '9', '27', '2013-04-25', '17.55');
INSERT INTO `tb_qdtg_record` VALUES ('27', '298', '8', 'C6340', '2013-05-24 11:52:57', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '193.7', '0.65', '9', '298', '2013-04-26', '193.7');
INSERT INTO `tb_qdtg_record` VALUES ('28', '154', '8', 'C6340', '2013-05-24 11:53:20', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '100.1', '0.65', '9', '154', '2013-04-27', '100.1');
INSERT INTO `tb_qdtg_record` VALUES ('29', '234', '8', 'C6340', '2013-05-24 11:53:44', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '152.1', '0.65', '9', '234', '2013-04-28', '152.1');
INSERT INTO `tb_qdtg_record` VALUES ('30', '223', '8', 'C6340', '2013-05-24 11:54:07', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '144.95', '0.65', '9', '223', '2013-04-29', '144.95');
INSERT INTO `tb_qdtg_record` VALUES ('31', '249', '8', 'C6340', '2013-05-24 11:54:29', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '161.85', '0.65', '9', '249', '2013-04-30', '161.85');
INSERT INTO `tb_qdtg_record` VALUES ('32', '292', '8', 'C6340', '2013-05-24 11:55:53', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '189.8', '0.65', '9', '292', '2013-05-01', '189.8');
INSERT INTO `tb_qdtg_record` VALUES ('33', '281', '8', 'C6340', '2013-05-24 11:57:55', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '182.65', '0.65', '9', '281', '2013-05-02', '182.65');
INSERT INTO `tb_qdtg_record` VALUES ('34', '261', '8', 'C6340', '2013-05-24 11:58:18', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '169.65', '0.65', '9', '261', '2013-05-03', '169.65');
INSERT INTO `tb_qdtg_record` VALUES ('35', '239', '8', 'C6340', '2013-05-24 11:58:41', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '155.35', '0.65', '9', '239', '2013-05-04', '155.35');
INSERT INTO `tb_qdtg_record` VALUES ('36', '431', '8', 'C6340', '2013-05-24 11:59:02', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '280.15', '0.65', '9', '431', '2013-05-05', '280.15');
INSERT INTO `tb_qdtg_record` VALUES ('37', '447', '8', 'C6340', '2013-05-24 11:59:23', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '290.55', '0.65', '9', '447', '2013-05-06', '290.55');
INSERT INTO `tb_qdtg_record` VALUES ('38', '512', '8', 'C6340', '2013-05-24 11:59:41', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '332.8', '0.65', '9', '512', '2013-05-07', '332.8');
INSERT INTO `tb_qdtg_record` VALUES ('39', '484', '8', 'C6340', '2013-05-24 12:00:03', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '314.6', '0.65', '9', '484', '2013-05-08', '314.6');
INSERT INTO `tb_qdtg_record` VALUES ('40', '480', '8', 'C6340', '2013-05-24 12:00:28', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '312', '0.65', '9', '480', '2013-05-09', '312');
INSERT INTO `tb_qdtg_record` VALUES ('41', '443', '8', 'C6340', '2013-05-24 12:00:50', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '287.95', '0.65', '9', '443', '2013-05-10', '287.95');
INSERT INTO `tb_qdtg_record` VALUES ('42', '262', '8', 'C6340', '2013-05-24 12:01:09', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '170.3', '0.65', '9', '262', '2013-05-11', '170.3');
INSERT INTO `tb_qdtg_record` VALUES ('43', '259', '8', 'C6340', '2013-05-24 12:01:30', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '168.35', '0.65', '9', '259', '2013-05-12', '168.35');
INSERT INTO `tb_qdtg_record` VALUES ('44', '210', '8', 'C6340', '2013-05-24 12:01:51', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '136.5', '0.65', '9', '210', '2013-05-13', '136.5');
INSERT INTO `tb_qdtg_record` VALUES ('45', '216', '8', 'C6340', '2013-05-24 12:02:12', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '140.4', '0.65', '9', '216', '2013-05-14', '140.4');
INSERT INTO `tb_qdtg_record` VALUES ('46', '234', '8', 'C6340', '2013-05-24 12:02:31', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '152.1', '0.65', '9', '234', '2013-05-15', '152.1');
INSERT INTO `tb_qdtg_record` VALUES ('47', '209', '8', 'C6340', '2013-05-24 12:02:47', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '135.85', '0.65', '9', '209', '2013-05-16', '135.85');
INSERT INTO `tb_qdtg_record` VALUES ('48', '249', '8', 'C6340', '2013-05-24 12:03:00', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '161.85', '0.65', '9', '249', '2013-05-17', '161.85');
INSERT INTO `tb_qdtg_record` VALUES ('49', '194', '8', 'C6340', '2013-05-24 12:03:17', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '126.1', '0.65', '9', '194', '2013-05-18', '126.1');
INSERT INTO `tb_qdtg_record` VALUES ('50', '204', '8', 'C6340', '2013-05-24 12:03:35', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '132.6', '0.65', '9', '204', '2013-05-19', '132.6');
INSERT INTO `tb_qdtg_record` VALUES ('51', '176', '8', 'C6340', '2013-05-24 12:03:56', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '114.4', '0.65', '9', '176', '2013-05-20', '114.4');
INSERT INTO `tb_qdtg_record` VALUES ('52', '293', '8', 'C6340', '2013-05-24 12:04:08', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '190.45', '0.65', '9', '293', '2013-05-21', '190.45');
INSERT INTO `tb_qdtg_record` VALUES ('53', '645', '8', 'C6340', '2013-05-24 12:04:25', '2', '掌众传媒', 'C6340', '2', '高德地图', '', '419.25', '0.65', '9', '645', '2013-05-22', '419.25');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleDesc` varchar(100) DEFAULT NULL,
  `roleName` varchar(64) DEFAULT NULL,
  `roleType` varchar(255) DEFAULT NULL,
  `roleCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleName` (`roleName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '普通用户：就是系统的普通使用者。', '普通用户', '3', '普通用户');
INSERT INTO `tb_role` VALUES ('2', '拥有最高的权限。', '系统最高', '1', '系统最高');
INSERT INTO `tb_role` VALUES ('3', '作为系统的管理人员，权限弱小于系统最高用户。', '管理员', '2', '管理员');
INSERT INTO `tb_role` VALUES ('5', '吊丝不寂寞营运人员', 'BDG营运员', '5', '运营人员');
INSERT INTO `tb_role` VALUES ('7', '把渠道分配给第三方的客户', '渠道客户', '6', '客户');

-- ----------------------------
-- Table structure for `tb_role_tb_function`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_tb_function`;
CREATE TABLE `tb_role_tb_function` (
  `tbRoles_id` bigint(20) NOT NULL,
  `tbFunctions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`tbRoles_id`,`tbFunctions_id`),
  KEY `FK5AD00DF1EC5B743` (`tbFunctions_id`),
  KEY `FK5AD00DF1D736C77F` (`tbRoles_id`),
  CONSTRAINT `FK5AD00DF1D736C77F` FOREIGN KEY (`tbRoles_id`) REFERENCES `tb_role` (`id`),
  CONSTRAINT `FK5AD00DF1EC5B743` FOREIGN KEY (`tbFunctions_id`) REFERENCES `tb_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_tb_function
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_role_tb_module`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_tb_module`;
CREATE TABLE `tb_role_tb_module` (
  `tbRoles_id` bigint(20) NOT NULL,
  `tbModules_id` bigint(20) NOT NULL,
  PRIMARY KEY (`tbRoles_id`,`tbModules_id`),
  KEY `FK382DF2C51A698D6B` (`tbModules_id`),
  KEY `FK382DF2C5D736C77F` (`tbRoles_id`),
  CONSTRAINT `FK382DF2C51A698D6B` FOREIGN KEY (`tbModules_id`) REFERENCES `tb_module` (`id`),
  CONSTRAINT `FK382DF2C5D736C77F` FOREIGN KEY (`tbRoles_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_tb_module
-- ----------------------------
INSERT INTO `tb_role_tb_module` VALUES ('2', '1');
INSERT INTO `tb_role_tb_module` VALUES ('3', '1');
INSERT INTO `tb_role_tb_module` VALUES ('5', '1');
INSERT INTO `tb_role_tb_module` VALUES ('7', '1');
INSERT INTO `tb_role_tb_module` VALUES ('2', '2');
INSERT INTO `tb_role_tb_module` VALUES ('3', '2');
INSERT INTO `tb_role_tb_module` VALUES ('5', '2');
INSERT INTO `tb_role_tb_module` VALUES ('7', '2');
INSERT INTO `tb_role_tb_module` VALUES ('2', '3');
INSERT INTO `tb_role_tb_module` VALUES ('2', '4');
INSERT INTO `tb_role_tb_module` VALUES ('2', '5');
INSERT INTO `tb_role_tb_module` VALUES ('2', '6');
INSERT INTO `tb_role_tb_module` VALUES ('2', '7');
INSERT INTO `tb_role_tb_module` VALUES ('3', '7');
INSERT INTO `tb_role_tb_module` VALUES ('5', '7');
INSERT INTO `tb_role_tb_module` VALUES ('7', '7');
INSERT INTO `tb_role_tb_module` VALUES ('2', '8');
INSERT INTO `tb_role_tb_module` VALUES ('3', '8');
INSERT INTO `tb_role_tb_module` VALUES ('2', '9');
INSERT INTO `tb_role_tb_module` VALUES ('3', '9');
INSERT INTO `tb_role_tb_module` VALUES ('2', '10');
INSERT INTO `tb_role_tb_module` VALUES ('3', '10');
INSERT INTO `tb_role_tb_module` VALUES ('2', '11');
INSERT INTO `tb_role_tb_module` VALUES ('3', '11');
INSERT INTO `tb_role_tb_module` VALUES ('2', '12');
INSERT INTO `tb_role_tb_module` VALUES ('3', '12');
INSERT INTO `tb_role_tb_module` VALUES ('2', '13');
INSERT INTO `tb_role_tb_module` VALUES ('3', '13');
INSERT INTO `tb_role_tb_module` VALUES ('2', '14');
INSERT INTO `tb_role_tb_module` VALUES ('3', '14');
INSERT INTO `tb_role_tb_module` VALUES ('2', '15');
INSERT INTO `tb_role_tb_module` VALUES ('3', '15');
INSERT INTO `tb_role_tb_module` VALUES ('2', '16');
INSERT INTO `tb_role_tb_module` VALUES ('3', '16');
INSERT INTO `tb_role_tb_module` VALUES ('2', '17');
INSERT INTO `tb_role_tb_module` VALUES ('3', '17');
INSERT INTO `tb_role_tb_module` VALUES ('2', '18');
INSERT INTO `tb_role_tb_module` VALUES ('3', '18');
INSERT INTO `tb_role_tb_module` VALUES ('2', '19');
INSERT INTO `tb_role_tb_module` VALUES ('3', '19');
INSERT INTO `tb_role_tb_module` VALUES ('2', '20');
INSERT INTO `tb_role_tb_module` VALUES ('3', '20');
INSERT INTO `tb_role_tb_module` VALUES ('2', '21');
INSERT INTO `tb_role_tb_module` VALUES ('3', '21');
INSERT INTO `tb_role_tb_module` VALUES ('2', '22');
INSERT INTO `tb_role_tb_module` VALUES ('3', '22');
INSERT INTO `tb_role_tb_module` VALUES ('2', '23');
INSERT INTO `tb_role_tb_module` VALUES ('3', '23');
INSERT INTO `tb_role_tb_module` VALUES ('2', '24');
INSERT INTO `tb_role_tb_module` VALUES ('3', '24');
INSERT INTO `tb_role_tb_module` VALUES ('2', '25');
INSERT INTO `tb_role_tb_module` VALUES ('3', '25');
INSERT INTO `tb_role_tb_module` VALUES ('2', '26');
INSERT INTO `tb_role_tb_module` VALUES ('3', '26');
INSERT INTO `tb_role_tb_module` VALUES ('2', '30');
INSERT INTO `tb_role_tb_module` VALUES ('3', '30');
INSERT INTO `tb_role_tb_module` VALUES ('2', '31');
INSERT INTO `tb_role_tb_module` VALUES ('3', '31');
INSERT INTO `tb_role_tb_module` VALUES ('2', '32');
INSERT INTO `tb_role_tb_module` VALUES ('2', '35');
INSERT INTO `tb_role_tb_module` VALUES ('2', '36');
INSERT INTO `tb_role_tb_module` VALUES ('2', '37');
INSERT INTO `tb_role_tb_module` VALUES ('2', '38');
INSERT INTO `tb_role_tb_module` VALUES ('3', '38');
INSERT INTO `tb_role_tb_module` VALUES ('5', '38');
INSERT INTO `tb_role_tb_module` VALUES ('2', '39');
INSERT INTO `tb_role_tb_module` VALUES ('3', '39');
INSERT INTO `tb_role_tb_module` VALUES ('5', '39');
INSERT INTO `tb_role_tb_module` VALUES ('2', '40');
INSERT INTO `tb_role_tb_module` VALUES ('3', '40');
INSERT INTO `tb_role_tb_module` VALUES ('5', '40');
INSERT INTO `tb_role_tb_module` VALUES ('2', '41');
INSERT INTO `tb_role_tb_module` VALUES ('3', '41');
INSERT INTO `tb_role_tb_module` VALUES ('5', '41');
INSERT INTO `tb_role_tb_module` VALUES ('2', '42');
INSERT INTO `tb_role_tb_module` VALUES ('2', '43');
INSERT INTO `tb_role_tb_module` VALUES ('2', '44');
INSERT INTO `tb_role_tb_module` VALUES ('3', '44');
INSERT INTO `tb_role_tb_module` VALUES ('5', '44');
INSERT INTO `tb_role_tb_module` VALUES ('2', '45');
INSERT INTO `tb_role_tb_module` VALUES ('3', '45');
INSERT INTO `tb_role_tb_module` VALUES ('5', '45');
INSERT INTO `tb_role_tb_module` VALUES ('2', '46');
INSERT INTO `tb_role_tb_module` VALUES ('3', '46');
INSERT INTO `tb_role_tb_module` VALUES ('5', '46');
INSERT INTO `tb_role_tb_module` VALUES ('2', '47');
INSERT INTO `tb_role_tb_module` VALUES ('3', '47');
INSERT INTO `tb_role_tb_module` VALUES ('5', '47');
INSERT INTO `tb_role_tb_module` VALUES ('2', '48');
INSERT INTO `tb_role_tb_module` VALUES ('3', '48');
INSERT INTO `tb_role_tb_module` VALUES ('2', '49');
INSERT INTO `tb_role_tb_module` VALUES ('3', '49');
INSERT INTO `tb_role_tb_module` VALUES ('2', '50');
INSERT INTO `tb_role_tb_module` VALUES ('3', '50');
INSERT INTO `tb_role_tb_module` VALUES ('2', '51');
INSERT INTO `tb_role_tb_module` VALUES ('3', '51');
INSERT INTO `tb_role_tb_module` VALUES ('2', '52');
INSERT INTO `tb_role_tb_module` VALUES ('3', '52');
INSERT INTO `tb_role_tb_module` VALUES ('5', '52');
INSERT INTO `tb_role_tb_module` VALUES ('2', '53');
INSERT INTO `tb_role_tb_module` VALUES ('3', '53');
INSERT INTO `tb_role_tb_module` VALUES ('5', '53');
INSERT INTO `tb_role_tb_module` VALUES ('2', '54');
INSERT INTO `tb_role_tb_module` VALUES ('3', '54');
INSERT INTO `tb_role_tb_module` VALUES ('5', '54');
INSERT INTO `tb_role_tb_module` VALUES ('2', '55');
INSERT INTO `tb_role_tb_module` VALUES ('3', '55');
INSERT INTO `tb_role_tb_module` VALUES ('5', '55');
INSERT INTO `tb_role_tb_module` VALUES ('7', '55');
INSERT INTO `tb_role_tb_module` VALUES ('2', '56');
INSERT INTO `tb_role_tb_module` VALUES ('3', '56');
INSERT INTO `tb_role_tb_module` VALUES ('2', '57');
INSERT INTO `tb_role_tb_module` VALUES ('3', '57');
INSERT INTO `tb_role_tb_module` VALUES ('2', '58');
INSERT INTO `tb_role_tb_module` VALUES ('3', '58');
INSERT INTO `tb_role_tb_module` VALUES ('2', '59');
INSERT INTO `tb_role_tb_module` VALUES ('3', '59');
INSERT INTO `tb_role_tb_module` VALUES ('2', '60');
INSERT INTO `tb_role_tb_module` VALUES ('3', '60');
INSERT INTO `tb_role_tb_module` VALUES ('5', '60');
INSERT INTO `tb_role_tb_module` VALUES ('7', '60');
INSERT INTO `tb_role_tb_module` VALUES ('2', '61');
INSERT INTO `tb_role_tb_module` VALUES ('5', '61');
INSERT INTO `tb_role_tb_module` VALUES ('2', '62');
INSERT INTO `tb_role_tb_module` VALUES ('3', '62');
INSERT INTO `tb_role_tb_module` VALUES ('2', '63');
INSERT INTO `tb_role_tb_module` VALUES ('7', '63');
INSERT INTO `tb_role_tb_module` VALUES ('2', '64');
INSERT INTO `tb_role_tb_module` VALUES ('3', '64');
INSERT INTO `tb_role_tb_module` VALUES ('2', '65');
INSERT INTO `tb_role_tb_module` VALUES ('3', '65');
INSERT INTO `tb_role_tb_module` VALUES ('2', '70');
INSERT INTO `tb_role_tb_module` VALUES ('3', '70');

-- ----------------------------
-- Table structure for `tb_sxd_article`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_article`;
CREATE TABLE `tb_sxd_article` (
  `articleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `articleDesc` varchar(255) DEFAULT NULL,
  `articleName` varchar(100) NOT NULL,
  `articlePath` varchar(100) DEFAULT NULL,
  `articleType` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(20) NOT NULL,
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_article
-- ----------------------------
INSERT INTO `tb_sxd_article` VALUES ('1', '每柱香2小时', '清香', '/attachments/sxd/article/images/u6_original.png', 'FX', '2012-11-09 10:23:03', 'admin');
INSERT INTO `tb_sxd_article` VALUES ('2', '水仙', '水仙', '/attachments/sxd/article/images/u14_original.png', 'GH', '2012-11-09 10:43:06', 'admin');

-- ----------------------------
-- Table structure for `tb_sxd_buddhabook`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_buddhabook`;
CREATE TABLE `tb_sxd_buddhabook` (
  `bookId` bigint(20) NOT NULL AUTO_INCREMENT,
  `backMusicPath` varchar(100) DEFAULT NULL,
  `bookImagePath` varchar(100) DEFAULT NULL,
  `bookPath` varchar(100) DEFAULT NULL,
  `bookTitle` varchar(100) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(20) NOT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_buddhabook
-- ----------------------------
INSERT INTO `tb_sxd_buddhabook` VALUES ('1', '/attachments/sxd/books/musics/AND I KNOW.mp3', '/attachments/sxd/books/images/u34_original.png', '/attachments/sxd/books/books/zk.txt', '早课(国语版)', '2012-10-29 14:36:52', 'admin');
INSERT INTO `tb_sxd_buddhabook` VALUES ('8', '/attachments/sxd/books/musics/冬吧拉.mp3', '/attachments/sxd/books/images/u36_original.png', '/attachments/sxd/books/books/测试.txt', '测试一下经书', '2012-10-31 11:56:11', 'admin');
INSERT INTO `tb_sxd_buddhabook` VALUES ('10', null, '/attachments/sxd/books/images/u38_original.png', null, '金刚经，不容错过的好书，值得阅读。今天你阅读了吗？', '2012-10-31 17:27:19', 'admin');

-- ----------------------------
-- Table structure for `tb_sxd_buddhaimage`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_buddhaimage`;
CREATE TABLE `tb_sxd_buddhaimage` (
  `imageId` bigint(20) NOT NULL AUTO_INCREMENT,
  `buddhaImageDesc` varchar(255) DEFAULT NULL,
  `buddhaImageName` varchar(50) NOT NULL,
  `buddhaImagePath` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(20) NOT NULL,
  PRIMARY KEY (`imageId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_buddhaimage
-- ----------------------------
INSERT INTO `tb_sxd_buddhaimage` VALUES ('1', '如来佛像', '如来佛', '/attachments/sxd/buddhaImage/images/u7_original.jpg', '2012-11-07 18:32:47', 'admin');
INSERT INTO `tb_sxd_buddhaimage` VALUES ('2', '手机0', '背景', '/attachments/sxd/buddhaImage/images/u0_original.png', '2012-11-08 13:40:15', 'admin');

-- ----------------------------
-- Table structure for `tb_sxd_buddhamusic`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_buddhamusic`;
CREATE TABLE `tb_sxd_buddhamusic` (
  `musicId` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(20) NOT NULL,
  `lyricPath` varchar(100) DEFAULT NULL,
  `musicPath` varchar(100) DEFAULT NULL,
  `musicTitle` varchar(100) NOT NULL,
  `musicType` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`musicId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_buddhamusic
-- ----------------------------
INSERT INTO `tb_sxd_buddhamusic` VALUES ('1', '黑鸭子', '2012-11-05 17:01:12', 'admin', '/attachments/sxd/musics/lyrics/error.txt', '/attachments/sxd/musics/musics/爱满人间.wma', '爱满人间', 'FGYF');
INSERT INTO `tb_sxd_buddhamusic` VALUES ('2', 'UU', '2012-11-10 13:44:45', 'admin', null, '/attachments/sxd/musics/musics/朋月.wma', 'IUU', 'FBZJ');

-- ----------------------------
-- Table structure for `tb_sxd_buddhaweibo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_buddhaweibo`;
CREATE TABLE `tb_sxd_buddhaweibo` (
  `wbId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(20) NOT NULL,
  `wbDesc` varchar(255) DEFAULT NULL,
  `wbName` varchar(50) NOT NULL,
  `wbUrl` varchar(100) NOT NULL,
  PRIMARY KEY (`wbId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_buddhaweibo
-- ----------------------------
INSERT INTO `tb_sxd_buddhaweibo` VALUES ('2', '2012-11-24 14:33:18', 'admin', '抓蛇妖而闻名', '法海', 'http://wb/fh');

-- ----------------------------
-- Table structure for `tb_sxd_chantingrecord`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_chantingrecord`;
CREATE TABLE `tb_sxd_chantingrecord` (
  `chantRecordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `chantingBook` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `meritContent` varchar(255) DEFAULT NULL,
  `restoreStatus` varchar(1) DEFAULT NULL,
  `restoreContent` varchar(255) DEFAULT NULL,
  `xyNo` varchar(8) NOT NULL,
  PRIMARY KEY (`chantRecordId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_chantingrecord
-- ----------------------------
INSERT INTO `tb_sxd_chantingrecord` VALUES ('3', '金刚经', '2012-10-13 14:49:16', '测试一下OK', '1', '还原测测', '68044300');
INSERT INTO `tb_sxd_chantingrecord` VALUES ('4', '早课', '2012-11-13 15:02:25', '每天诵一次。', '0', null, '68044300');

-- ----------------------------
-- Table structure for `tb_sxd_dict`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_dict`;
CREATE TABLE `tb_sxd_dict` (
  `dictId` bigint(20) NOT NULL AUTO_INCREMENT,
  `dictDesc` varchar(255) DEFAULT NULL,
  `dictGroup` varchar(30) NOT NULL,
  `dictName` varchar(30) NOT NULL,
  `dictValue` varchar(30) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `iconPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dictId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_dict
-- ----------------------------
INSERT INTO `tb_sxd_dict` VALUES ('3', '有关大陆佛教新闻分类。', 'newsType', '大陆', 'DL', '1', null);
INSERT INTO `tb_sxd_dict` VALUES ('4', '', 'newsType', '港澳台', 'GAT', '2', null);
INSERT INTO `tb_sxd_dict` VALUES ('5', '', 'newsType', '国际', 'GJ', '3', null);
INSERT INTO `tb_sxd_dict` VALUES ('6', '', 'newsType', '其它', 'OTHER', '4', null);
INSERT INTO `tb_sxd_dict` VALUES ('7', '推荐佛学文章。', 'loreType', '推荐文章', 'TJWZ', '1', null);
INSERT INTO `tb_sxd_dict` VALUES ('8', '经典教学常识。', 'loreType', '经典文章', 'JDWZ', '2', null);
INSERT INTO `tb_sxd_dict` VALUES ('9', '', 'musicType', '梵呗赞偈', 'FBZJ', '1', '/attachments/sxd/dict/icons/u46_original.png');
INSERT INTO `tb_sxd_dict` VALUES ('10', '', 'musicType', '经忏诵唱', 'JCSC', '2', null);
INSERT INTO `tb_sxd_dict` VALUES ('11', '', 'musicType', '静心禅乐', 'JXCL', '3', null);
INSERT INTO `tb_sxd_dict` VALUES ('12', '', 'musicType', '佛号圣号', 'FHSH', '4', null);
INSERT INTO `tb_sxd_dict` VALUES ('13', '', 'musicType', '凡歌咏法', 'FGYF', '5', null);
INSERT INTO `tb_sxd_dict` VALUES ('14', '', 'musicType', '密咒真言', 'MZZY', '6', null);
INSERT INTO `tb_sxd_dict` VALUES ('15', '', 'prayType', '财富', 'CF', '1', null);
INSERT INTO `tb_sxd_dict` VALUES ('16', '', 'prayType', '健康', 'JK', '2', null);
INSERT INTO `tb_sxd_dict` VALUES ('17', '', 'prayType', '求子', 'QZ', '3', null);
INSERT INTO `tb_sxd_dict` VALUES ('18', '', 'prayType', '平安', 'PA', '4', null);
INSERT INTO `tb_sxd_dict` VALUES ('19', '', 'prayType', '学业', 'XY', '5', null);
INSERT INTO `tb_sxd_dict` VALUES ('20', '', 'prayType', '姻缘', 'YY', '6', null);
INSERT INTO `tb_sxd_dict` VALUES ('21', '', 'prayType', '事业', 'SY', '7', null);
INSERT INTO `tb_sxd_dict` VALUES ('22', '', 'articleType', '佛香', 'FX', '1', '');
INSERT INTO `tb_sxd_dict` VALUES ('23', '', 'articleType', '供果', 'GG', '2', null);
INSERT INTO `tb_sxd_dict` VALUES ('24', '', 'articleType', '花', 'GH', '3', null);

-- ----------------------------
-- Table structure for `tb_sxd_lore`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_lore`;
CREATE TABLE `tb_sxd_lore` (
  `loreId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(20) NOT NULL,
  `loreTitle` varchar(100) NOT NULL,
  `loreType` varchar(30) NOT NULL,
  `loreContent` text,
  PRIMARY KEY (`loreId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_lore
-- ----------------------------
INSERT INTO `tb_sxd_lore` VALUES ('1', '2012-11-01 17:29:38', 'admin', '测试一下', 'JDWZ', null);
INSERT INTO `tb_sxd_lore` VALUES ('2', '2012-11-01 17:31:55', 'admin', '佛之禅语语录', 'TJWZ', null);

-- ----------------------------
-- Table structure for `tb_sxd_news`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_news`;
CREATE TABLE `tb_sxd_news` (
  `newsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(20) NOT NULL,
  `newsDesc` varchar(255) DEFAULT NULL,
  `newsTitle` varchar(100) NOT NULL,
  `newsType` varchar(30) NOT NULL,
  `newsUrl` varchar(100) NOT NULL,
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_news
-- ----------------------------
INSERT INTO `tb_sxd_news` VALUES ('2', '2012-11-02 11:51:06', 'admin', '是不是真的呀', '香港信佛的人越来越多', 'GAT', 'http://www.google.com');
INSERT INTO `tb_sxd_news` VALUES ('3', '2012-11-02 13:46:05', 'admin', '这是新闻简单描述', '这是新闻标题', 'OTHER', 'http://www.163.com');

-- ----------------------------
-- Table structure for `tb_sxd_prayrecord`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_prayrecord`;
CREATE TABLE `tb_sxd_prayrecord` (
  `prayId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `meritContent` varchar(255) DEFAULT NULL,
  `prayType` varchar(30) DEFAULT NULL,
  `restoreStatus` varchar(1) DEFAULT NULL,
  `xyNo` varchar(20) NOT NULL,
  `articleId` bigint(20) DEFAULT NULL,
  `buddhaImageIdId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`prayId`),
  KEY `FKD190DE1CCB176F1B` (`articleId`),
  KEY `FKD190DE1CD9D5C88` (`buddhaImageIdId`),
  CONSTRAINT `FKD190DE1CCB176F1B` FOREIGN KEY (`articleId`) REFERENCES `tb_sxd_article` (`articleId`),
  CONSTRAINT `FKD190DE1CD9D5C88` FOREIGN KEY (`buddhaImageIdId`) REFERENCES `tb_sxd_buddhaimage` (`imageId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_prayrecord
-- ----------------------------
INSERT INTO `tb_sxd_prayrecord` VALUES ('1', '2012-11-15 10:30:56', '财富一生', '财富', '0', '68044300', '1', '1');
INSERT INTO `tb_sxd_prayrecord` VALUES ('3', '2012-11-15 10:40:31', '财富来吧来吧', '财富', '0', '68044300', '2', '1');
INSERT INTO `tb_sxd_prayrecord` VALUES ('7', '2012-11-15 10:43:59', '平平安安', '平安', '0', '68044300', '2', '1');
INSERT INTO `tb_sxd_prayrecord` VALUES ('8', '2012-11-15 10:44:39', '健健康康', '健康', '0', '68044300', '1', '1');

-- ----------------------------
-- Table structure for `tb_sxd_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_user`;
CREATE TABLE `tb_sxd_user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) unsigned DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `password` varchar(40) NOT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `realName` varchar(20) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `xyNo` varchar(8) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `xyNo` (`xyNo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_user
-- ----------------------------
INSERT INTO `tb_sxd_user` VALUES ('5', '20', '/attachments/sxd/user/auatar/u13_original.png', '深圳', 'hi@kyb.com', '13510122174', '21218cca77804d2ba1922c33e0151105', '8234234', '小丽', '男', '68044300', '2012-11-12 17:31:20');
INSERT INTO `tb_sxd_user` VALUES ('6', null, null, null, null, null, '21218cca77804d2ba1922c33e0151105', null, null, null, '71040449', '2013-05-16 10:43:49');

-- ----------------------------
-- Table structure for `tb_sxd_version`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sxd_version`;
CREATE TABLE `tb_sxd_version` (
  `versionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `apkPath` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(20) NOT NULL,
  `downLoads` int(11) DEFAULT NULL,
  `internalVersion` int(11) NOT NULL,
  `version` varchar(20) DEFAULT NULL,
  `versionDesc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`versionId`),
  UNIQUE KEY `internalVersion` (`internalVersion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sxd_version
-- ----------------------------
INSERT INTO `tb_sxd_version` VALUES ('1', '/attachments/sxd/apk/versions/V1.0/SXD.apk', '2012-11-16 15:40:51', 'admin', '0', '1', 'V1.0', '1：V1.0正式发布');
INSERT INTO `tb_sxd_version` VALUES ('2', '/attachments/sxd/apk/versions/V1.1/神香道.apk', '2012-11-19 11:41:17', 'admin', '1', '2', 'V1.1', '1:更新APK支持中文名');
INSERT INTO `tb_sxd_version` VALUES ('3', '/attachments/sxd/apk/versions/V1.2/SXD_神香道.apk', '2012-11-19 12:18:01', 'admin', '6', '3', 'V1.2', '测试u');

-- ----------------------------
-- Table structure for `tb_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_userinfo`;
CREATE TABLE `tb_userinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` varchar(4) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF13C65CA3C6A4145` (`roleId`),
  CONSTRAINT `FKF13C65CA3C6A4145` FOREIGN KEY (`roleId`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_userinfo
-- ----------------------------
INSERT INTO `tb_userinfo` VALUES ('1', '2011-03-10 00:00:00', 'admin@163.com', 'fe4053114c7c4e7c02bd6362063bfa9f', '13510122175', '正常', '2012-11-24 16:34:39', 'admin', '2');
INSERT INTO `tb_userinfo` VALUES ('3', '2012-11-26 14:16:18', 'luojb@kybm.net', '21218cca77804d2ba1922c33e0151105', '', '正常', null, 'luojb', '3');
INSERT INTO `tb_userinfo` VALUES ('4', '2012-11-27 10:01:25', 'zhaozq@kybm.net', '21218cca77804d2ba1922c33e0151105', '', '正常', null, 'zhaozq', '3');
INSERT INTO `tb_userinfo` VALUES ('5', '2012-12-06 11:11:22', 'zhouy@kybm.net', '21218cca77804d2ba1922c33e0151105', '', '正常', null, 'zhouy', '3');
INSERT INTO `tb_userinfo` VALUES ('6', '2013-03-11 15:24:16', 'guosr@kybm.net', '21218cca77804d2ba1922c33e0151105', '', '正常', null, 'guosr', '5');
INSERT INTO `tb_userinfo` VALUES ('9', '2013-05-25 14:03:42', '', '21218cca77804d2ba1922c33e0151105', '', '正常', null, 'aa_cvs', '7');
INSERT INTO `tb_userinfo` VALUES ('35', '2013-05-11 16:26:05', 'hepx@kybm.net', '21218cca77804d2ba1922c33e0151105', '13510122174', '正常', null, 'test_admin', '3');
INSERT INTO `tb_userinfo` VALUES ('36', '2013-05-11 16:27:00', 'hepx@kybm.net', '21218cca77804d2ba1922c33e0151105', '', '正常', null, 'test_yy', '5');
