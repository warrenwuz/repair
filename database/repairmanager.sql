/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : repairmanager

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-10-16 14:50:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` varchar(32) NOT NULL,
  `adminname` varchar(10) NOT NULL,
  `admintel` varchar(18) NOT NULL,
  `adminpassword` varchar(32) NOT NULL,
  `authorization` int(1) NOT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('101190000', '超级管理员', '0352-2312010', '1330a043d3d12eb01598e6583ecd2fee', '0');
INSERT INTO `admin` VALUES ('101190001', '弓婷', '18235104837', 'e43418605ac37f6aade2ae902f1a70e4', '1');

-- ----------------------------
-- Table structure for flat
-- ----------------------------
DROP TABLE IF EXISTS `flat`;
CREATE TABLE `flat` (
  `fid` int(4) NOT NULL,
  `fname` varchar(32) NOT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flat
-- ----------------------------
INSERT INTO `flat` VALUES ('11', '启程公寓9号楼');

-- ----------------------------
-- Table structure for repairarea
-- ----------------------------
DROP TABLE IF EXISTS `repairarea`;
CREATE TABLE `repairarea` (
  `raid` int(11) NOT NULL AUTO_INCREMENT,
  `raname` varchar(32) NOT NULL,
  PRIMARY KEY (`raid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repairarea
-- ----------------------------
INSERT INTO `repairarea` VALUES ('1', '宿舍');
INSERT INTO `repairarea` VALUES ('2', '洗漱间');
INSERT INTO `repairarea` VALUES ('3', '楼道');
INSERT INTO `repairarea` VALUES ('4', '饮水间');

-- ----------------------------
-- Table structure for repairman
-- ----------------------------
DROP TABLE IF EXISTS `repairman`;
CREATE TABLE `repairman` (
  `rmid` varchar(32) NOT NULL,
  `rmname` varchar(4) NOT NULL,
  `rmtel` varchar(12) NOT NULL,
  `rmpassword` varchar(32) NOT NULL,
  `rmopenid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`rmid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repairman
-- ----------------------------
INSERT INTO `repairman` VALUES ('1011900002', '吴哲', '18235119392', '364419d965d1ccac19373192f2df3426', 'oTRkNwenrFRaks2lbXKxcr_IhATE');
INSERT INTO `repairman` VALUES ('1011900003', '弓婷', '18235119392', 'd7635b2f98d8ac655a26be76117be541', null);

-- ----------------------------
-- Table structure for repairman_repairproject
-- ----------------------------
DROP TABLE IF EXISTS `repairman_repairproject`;
CREATE TABLE `repairman_repairproject` (
  `rpid` int(11) NOT NULL,
  `rmid` varchar(32) NOT NULL,
  PRIMARY KEY (`rpid`,`rmid`),
  KEY `rmid` (`rmid`),
  CONSTRAINT `repairman_repairproject_ibfk_1` FOREIGN KEY (`rpid`) REFERENCES `repairproject` (`rpid`),
  CONSTRAINT `repairman_repairproject_ibfk_2` FOREIGN KEY (`rmid`) REFERENCES `repairman` (`rmid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repairman_repairproject
-- ----------------------------
INSERT INTO `repairman_repairproject` VALUES ('1', '1011900002');
INSERT INTO `repairman_repairproject` VALUES ('1', '1011900003');
INSERT INTO `repairman_repairproject` VALUES ('2', '1011900003');
INSERT INTO `repairman_repairproject` VALUES ('4', '1011900003');

-- ----------------------------
-- Table structure for repairproject
-- ----------------------------
DROP TABLE IF EXISTS `repairproject`;
CREATE TABLE `repairproject` (
  `rpid` int(11) NOT NULL AUTO_INCREMENT,
  `rpname` varchar(32) NOT NULL,
  `raid` int(11) NOT NULL,
  PRIMARY KEY (`rpid`),
  KEY `raid` (`raid`),
  CONSTRAINT `repairproject_ibfk_1` FOREIGN KEY (`raid`) REFERENCES `repairarea` (`raid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repairproject
-- ----------------------------
INSERT INTO `repairproject` VALUES ('1', '门窗', '1');
INSERT INTO `repairproject` VALUES ('2', '家具(含床、桌椅)', '1');
INSERT INTO `repairproject` VALUES ('3', '电灯', '1');
INSERT INTO `repairproject` VALUES ('4', '墙壁', '1');
INSERT INTO `repairproject` VALUES ('5', '线路故障', '1');
INSERT INTO `repairproject` VALUES ('6', '洗漱池', '2');
INSERT INTO `repairproject` VALUES ('7', '楼道灯', '3');
INSERT INTO `repairproject` VALUES ('8', '饮水池', '4');

-- ----------------------------
-- Table structure for repair_log
-- ----------------------------
DROP TABLE IF EXISTS `repair_log`;
CREATE TABLE `repair_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `repair_log` varchar(60) NOT NULL,
  `log_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '产生日志日期',
  `wpid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_log
-- ----------------------------

-- ----------------------------
-- Table structure for simage
-- ----------------------------
DROP TABLE IF EXISTS `simage`;
CREATE TABLE `simage` (
  `simageid` varchar(32) NOT NULL,
  `simagepath` varchar(255) NOT NULL,
  `wpid` varchar(32) DEFAULT NULL,
  `imagetype` int(2) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`simageid`),
  KEY `wpid` (`wpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of simage
-- ----------------------------

-- ----------------------------
-- Table structure for studentmsg
-- ----------------------------
DROP TABLE IF EXISTS `studentmsg`;
CREATE TABLE `studentmsg` (
  `stuid` varchar(32) NOT NULL,
  `sname` varchar(32) NOT NULL,
  `fid` int(11) NOT NULL,
  `dormitory` varchar(32) NOT NULL,
  `tel` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentmsg
-- ----------------------------
INSERT INTO `studentmsg` VALUES ('2014151119', '弓婷', '11', '410', '18235104837', '6aaca607ec6e9c2f6ed702f323964ccb', null);
INSERT INTO `studentmsg` VALUES ('2014151123', '吴哲', '11', '648', '18235119392', '3ce4058d6285e8991ba36a74b1b24d31', 'oTRkNwTg-hWwvCdVFRDSiGpSvTa0');
INSERT INTO `studentmsg` VALUES ('2014151128', '张三', '11', '648', '18235119293', 'a823878535dfce872776d5ac6e3eb911', null);

-- ----------------------------
-- Table structure for wxonlinerepairmsg
-- ----------------------------
DROP TABLE IF EXISTS `wxonlinerepairmsg`;
CREATE TABLE `wxonlinerepairmsg` (
  `wpid` varchar(32) NOT NULL,
  `stuid` varchar(32) NOT NULL,
  `rpid` int(4) NOT NULL,
  `arrangeadminid` varchar(32) DEFAULT NULL,
  `checkadminid` varchar(32) DEFAULT NULL,
  `rmid` varchar(32) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `dispatchingType` int(11) DEFAULT NULL COMMENT '派工类型',
  `tel` varchar(32) NOT NULL,
  `projectdetail` varchar(255) NOT NULL,
  `completeremark` varchar(255) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(1) NOT NULL,
  PRIMARY KEY (`wpid`),
  KEY `stuid` (`stuid`),
  KEY `rpid` (`rpid`),
  KEY `arrangeadminid` (`arrangeadminid`),
  KEY `checkadminid` (`checkadminid`),
  KEY `rmid` (`rmid`),
  CONSTRAINT `wxonlinerepairmsg_ibfk_1` FOREIGN KEY (`stuid`) REFERENCES `studentmsg` (`stuid`),
  CONSTRAINT `wxonlinerepairmsg_ibfk_2` FOREIGN KEY (`rpid`) REFERENCES `repairproject` (`rpid`),
  CONSTRAINT `wxonlinerepairmsg_ibfk_3` FOREIGN KEY (`arrangeadminid`) REFERENCES `admin` (`adminid`),
  CONSTRAINT `wxonlinerepairmsg_ibfk_4` FOREIGN KEY (`checkadminid`) REFERENCES `admin` (`adminid`),
  CONSTRAINT `wxonlinerepairmsg_ibfk_5` FOREIGN KEY (`rmid`) REFERENCES `repairman` (`rmid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wxonlinerepairmsg
-- ----------------------------

-- ----------------------------
-- View structure for repairmanfree
-- ----------------------------
DROP VIEW IF EXISTS `repairmanfree`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `repairmanfree` AS select `repairmanager`.`repairman`.`rmid` AS `rmid` from `repairmanager`.`repairman` where (not(`repairmanager`.`repairman`.`rmid` in (select `a`.`rmid` from (select `wx`.`rmid` AS `rmid`,count(`wx`.`rmid`) AS `sumrmid` from `repairmanager`.`wxonlinerepairmsg` `wx` where ((`wx`.`status` in (1,2)) and (`wx`.`rmid` is not null)) group by `wx`.`rmid` having (`sumrmid` < 15)) `a`))) order by rand() limit 1 ;

-- ----------------------------
-- View structure for repairmsgview
-- ----------------------------
DROP VIEW IF EXISTS `repairmsgview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `repairmsgview` AS select `wx`.`wpid` AS `wpid`,`stu`.`sname` AS `sname`,`ra`.`raname` AS `raname`,`rp`.`rpname` AS `rpname`,`f`.`fname` AS `fname`,`stu`.`dormitory` AS `dormitory`,`wx`.`status` AS `status`,`wx`.`tel` AS `tel`,`wx`.`projectdetail` AS `projectdetail`,`wx`.`timestamp` AS `timestamp` from ((((`wxonlinerepairmsg` `wx` join `studentmsg` `stu`) join `flat` `f`) join `repairarea` `ra`) join `repairproject` `rp`) where ((`wx`.`stuid` = `stu`.`stuid`) and (`wx`.`rpid` = `rp`.`rpid`) and (`ra`.`raid` = `rp`.`raid`) and (`stu`.`fid` = `f`.`fid`)) order by `wx`.`timestamp` desc ;
