/* 这里需要注意，初始化数据时需要注意建表的顺序，Spring-Boot无法进行类似sqldump的处理 */

/* sb_atomikos数据库的表 */
DROP TABLE IF EXISTS `tbl_account`;
DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `regTime` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL COMMENT '编号',
  `amount` double DEFAULT NULL COMMENT '金额',
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`sn`),
  KEY `FK_account_user` (`userId`),
  CONSTRAINT `FK_account_user` FOREIGN KEY (`userId`) REFERENCES `tbl_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* sb_atomikos数据库的表 */
DROP TABLE IF EXISTS `tbl_translog`;

CREATE TABLE `tbl_translog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formId` int(11) DEFAULT NULL,
  `toId` int(11) DEFAULT NULL,
  `formSn` varchar(255) DEFAULT NULL,
  `toSn` varchar(255) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
