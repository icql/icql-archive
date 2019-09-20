/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.5.27 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `tb_easyui_grid` */

DROP TABLE IF EXISTS `tb_easyui_grid`;

CREATE TABLE `tb_easyui_grid` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_easyui_grid` */

insert  into `tb_easyui_grid`(`id`,`name`,`price`,`status`,`created`,`updated`,`version`) values ('03b7e25be27011e8a6814ccc6aaa705c','人',4343,'0','2018-11-07 17:32:17','2018-11-09 20:12:25',3),('4200bccee22711e8a6814ccc6aaa705c','rwer',342424,'0','2018-11-07 08:51:27','2018-11-07 08:51:27',1),('453daf4ee22711e8a6814ccc6aaa705c','rewr',34234,'0','2018-11-07 08:51:33','2018-11-07 08:51:33',1),('69303c3be23d11e8a6814ccc6aaa705c','test',23,'0','2018-11-07 11:30:02','2019-03-06 15:40:10',1),('6cd5247ae23d11e8a6814ccc6aaa705c','test43',23,'1','2018-11-07 11:30:08','2018-11-07 11:30:08',1),('6d96da59e23411e8a6814ccc6aaa705c','et',232,'0','2018-11-07 10:25:44','2018-11-07 10:25:44',1),('6eb97ec0e23d11e8a6814ccc6aaa705c','test43ewe',23,'1','2018-11-07 11:30:11','2018-11-07 11:30:11',1),('7087cea4e23d11e8a6814ccc6aaa705c','test43ewe232',23,'1','2018-11-07 11:30:14','2018-11-07 11:30:14',1),('728ae3cae23411e8a6814ccc6aaa705c','et',232,'0','2018-11-07 10:25:52','2019-03-06 15:40:14',1),('770ca751e0d211e884d24ccc6aaa705c','name1',1.2,'0','2018-11-05 16:11:59','2018-11-05 16:11:59',1),('7da22c72e0d211e884d24ccc6aaa705c','name2',4.28,'0','2018-11-05 16:12:10','2018-11-05 16:12:10',1),('803478a6e27b11e8a6814ccc6aaa705c','特特',3434,'0','2018-11-07 18:54:30','2018-11-07 18:54:30',1),('85827e2ae24d11e8a6814ccc6aaa705c','放到',1,'0','2018-11-07 13:25:22','2018-11-07 13:25:22',1),('864018e5e0d211e884d24ccc6aaa705c','name3',75,'1','2018-11-05 16:12:24','2018-11-05 16:12:24',1),('8ded3d8ae0d211e884d24ccc6aaa705c','name4',10.2,'0','2018-11-05 16:12:37','2018-11-05 16:12:37',1),('942606d1e26f11e8a6814ccc6aaa705c','tested挼哇挼',2323,'0','2018-11-07 17:29:10','2018-11-07 17:29:10',1),('96993cb7e26f11e8a6814ccc6aaa705c','tested挼哇挼',2323,'0','2018-11-07 17:29:14','2018-11-07 17:29:14',1),('9bcbb2dce22911e8a6814ccc6aaa705c','tested',1,'0','2018-11-07 09:08:17','2018-11-07 09:08:17',1),('a30ebf8be22911e8a6814ccc6aaa705c','tested11',1,'0','2018-11-07 09:08:29','2018-11-07 09:08:29',1),('bcb2433ce23511e8a6814ccc6aaa705c','werwqe',435,'0','2018-11-07 10:35:06','2018-11-07 10:35:06',1),('c0a20b71e23511e8a6814ccc6aaa705c','trewt',34,'0','2018-11-07 10:35:13','2018-11-07 10:35:13',1),('c4be7655e23511e8a6814ccc6aaa705c','trewtrtt',34,'0','2018-11-07 10:35:20','2018-11-07 10:35:20',1),('c9c8a18fe23611e8a6814ccc6aaa705c','f  ve r',434,'0','2018-11-07 10:42:38','2018-11-07 10:42:38',1),('cc9d7135e32811e8b4fc4ccc6aaa705c','excel1',12,NULL,'2018-11-08 15:35:01','2018-11-08 15:35:01',1),('d0f65fe1e32811e8b4fc4ccc6aaa705c','excel2',454,NULL,'2018-11-08 15:35:08','2018-11-08 15:35:08',1),('ebadfa34e27c11e8a6814ccc6aaa705c','01010101010',1010,'0','2018-11-07 19:04:40','2018-11-07 19:04:40',1),('f29eb324e32811e8b4fc4ccc6aaa705c','excel1',12,NULL,'2018-11-08 15:36:05','2018-11-08 15:36:05',1),('f2a3ec4ce32811e8b4fc4ccc6aaa705c','excel2',454,NULL,'2018-11-08 15:36:05','2018-11-08 15:36:05',1),('f82ab411e27c11e8a6814ccc6aaa705c','456456',6454,'1','2018-11-07 19:05:01','2018-11-07 19:05:01',1),('f848d6c2e23211e8a6814ccc6aaa705c','4',3424,'0','2018-11-07 10:15:18','2018-11-07 10:15:18',1);

/*Table structure for table `tb_easyui_tree` */

DROP TABLE IF EXISTS `tb_easyui_tree`;

CREATE TABLE `tb_easyui_tree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `sort` int(20) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_easyui_tree` */

insert  into `tb_easyui_tree`(`id`,`pid`,`name`,`url`,`sort`,`created`,`updated`,`version`) values (1,0,'samples','',0,'2018-11-05 09:51:08','2018-11-05 09:51:08',1),(3,1,'grid','/easyui/grid',0,'2018-11-05 09:53:08','2018-11-05 09:53:08',1);

/*Table structure for table `tb_shiro_func` */

DROP TABLE IF EXISTS `tb_shiro_func`;

CREATE TABLE `tb_shiro_func` (
  `funcId` varchar(32) NOT NULL,
  `funcName` varchar(255) DEFAULT NULL,
  `funcCode` varchar(255) DEFAULT NULL,
  `funcDesc` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `isMenu` varchar(255) DEFAULT NULL,
  `zindex` int(11) DEFAULT NULL,
  `pId` varchar(32) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`funcId`),
  KEY `FK_tb_shiro_func_pid` (`pId`),
  CONSTRAINT `FK_tb_shiro_func_pid` FOREIGN KEY (`PId`) REFERENCES `tb_shiro_func` (`FuncId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_shiro_func` */

insert  into `tb_shiro_func`(`funcId`,`funcName`,`funcCode`,`funcDesc`,`url`,`isMenu`,`zindex`,`pId`,`created`,`updated`,`version`) values ('func1','func1','func1','','/shiro/func1','0',0,NULL,'2018-11-13 15:54:50','2018-11-13 15:54:50',1),('func2','func2','func2',NULL,'/shiro/func2','0',1,'func1',NULL,NULL,NULL);

/*Table structure for table `tb_shiro_role` */

DROP TABLE IF EXISTS `tb_shiro_role`;

CREATE TABLE `tb_shiro_role` (
  `roleId` varchar(32) NOT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `roleDesc` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `roleCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_shiro_role` */

insert  into `tb_shiro_role`(`roleId`,`roleName`,`roleDesc`,`created`,`updated`,`version`,`roleCode`) values ('role1','role1',NULL,'2018-11-13 00:00:00','2018-11-13 00:00:00',1,'role1');

/*Table structure for table `tb_shiro_role_func` */

DROP TABLE IF EXISTS `tb_shiro_role_func`;

CREATE TABLE `tb_shiro_role_func` (
  `roleId` varchar(32) NOT NULL,
  `funcId` varchar(32) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`roleId`,`funcId`),
  KEY `FK_tb_shiro_role_func_funcid` (`funcId`),
  CONSTRAINT `FK_tb_shiro_role_func_funcid` FOREIGN KEY (`FuncId`) REFERENCES `tb_shiro_func` (`FuncId`),
  CONSTRAINT `FK_tb_shiro_role_func_roleid` FOREIGN KEY (`RoleId`) REFERENCES `tb_shiro_role` (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_shiro_role_func` */

insert  into `tb_shiro_role_func`(`roleId`,`funcId`,`created`,`updated`,`version`) values ('role1','func1',NULL,NULL,NULL),('role1','func2',NULL,NULL,NULL);

/*Table structure for table `tb_shiro_user` */

DROP TABLE IF EXISTS `tb_shiro_user`;

CREATE TABLE `tb_shiro_user` (
  `userId` varchar(32) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `mobilePhone` varchar(11) DEFAULT NULL,
  `memo` text,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `UserName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_shiro_user` */

insert  into `tb_shiro_user`(`userId`,`userName`,`mobilePhone`,`memo`,`created`,`updated`,`version`,`password`) values ('user1','user1','13333333333',NULL,'2018-11-13 00:00:00','2018-11-13 00:00:00',1,'123456');

/*Table structure for table `tb_shiro_user_role` */

DROP TABLE IF EXISTS `tb_shiro_user_role`;

CREATE TABLE `tb_shiro_user_role` (
  `userId` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK_tb_shiro_user_role_roleid` (`roleId`),
  CONSTRAINT `FK_tb_shiro_user_role_roleid` FOREIGN KEY (`RoleId`) REFERENCES `tb_shiro_role` (`RoleId`),
  CONSTRAINT `FK_tb_shiro_user_role_userid` FOREIGN KEY (`UserId`) REFERENCES `tb_shiro_user` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_shiro_user_role` */

insert  into `tb_shiro_user_role`(`userId`,`roleId`,`created`,`updated`,`version`) values ('user1','role1','0000-00-00 00:00:00',NULL,NULL);

/*Table structure for table `tb_sso_user` */

DROP TABLE IF EXISTS `tb_sso_user`;

CREATE TABLE `tb_sso_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_sso_user` */

insert  into `tb_sso_user`(`id`,`username`,`password`) values ('132146','test','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `vw_shiro_auth` */

DROP TABLE IF EXISTS `vw_shiro_auth`;

/*!50001 DROP VIEW IF EXISTS `vw_shiro_auth` */;
/*!50001 DROP TABLE IF EXISTS `vw_shiro_auth` */;

/*!50001 CREATE TABLE  `vw_shiro_auth`(
 `userId` varchar(32) ,
 `userName` varchar(50) ,
 `password` varchar(64) ,
 `mobilePhone` varchar(11) ,
 `memo` text ,
 `roleId` varchar(32) ,
 `roleName` varchar(255) ,
 `roleCode` varchar(255) ,
 `roleDesc` varchar(255) ,
 `funcId` varchar(32) ,
 `funcName` varchar(255) ,
 `funcCode` varchar(255) ,
 `isMenu` varchar(255) ,
 `url` varchar(255) ,
 `pId` varchar(32) ,
 `zindex` int(11) 
)*/;

/*View structure for view vw_shiro_auth */

/*!50001 DROP TABLE IF EXISTS `vw_shiro_auth` */;
/*!50001 DROP VIEW IF EXISTS `vw_shiro_auth` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_shiro_auth` AS select `user`.`userId` AS `userId`,`user`.`userName` AS `userName`,`user`.`password` AS `password`,`user`.`mobilePhone` AS `mobilePhone`,`user`.`memo` AS `memo`,`role`.`roleId` AS `roleId`,`role`.`roleName` AS `roleName`,`role`.`roleCode` AS `roleCode`,`role`.`roleDesc` AS `roleDesc`,`func`.`funcId` AS `funcId`,`func`.`funcName` AS `funcName`,`func`.`funcCode` AS `funcCode`,`func`.`isMenu` AS `isMenu`,`func`.`url` AS `url`,`func`.`pId` AS `pId`,`func`.`zindex` AS `zindex` from ((((`tb_shiro_user` `user` left join `tb_shiro_user_role` `user_role` on((`user`.`userId` = `user_role`.`userId`))) left join `tb_shiro_role` `role` on((`role`.`roleId` = `user_role`.`roleId`))) left join `tb_shiro_role_func` `role_func` on((`role`.`roleId` = `role_func`.`roleId`))) left join `tb_shiro_func` `func` on((`role_func`.`funcId` = `func`.`funcId`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
