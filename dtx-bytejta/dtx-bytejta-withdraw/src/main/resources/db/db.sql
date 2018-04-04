/* 这里需要注意，初始化数据时需要注意建表的顺序，Spring-Boot无法进行类似sqldump的处理 */

DROP TABLE IF EXISTS `bytejta`;
DROP TABLE IF EXISTS `tbl_account`;
DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `bytejta` (
  `xid` VARCHAR(32) NOT NULL DEFAULT '',
  `gxid` VARCHAR(40) DEFAULT NULL,
  `bxid` VARCHAR(40) DEFAULT NULL,
  `ctime` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`xid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

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
