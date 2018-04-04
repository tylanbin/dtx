/* 这里需要注意，初始化数据时需要注意建表的顺序，Spring-Boot无法进行类似sqldump的处理 */

DROP TABLE IF EXISTS `bytejta`;

/* 注意，即使该项目只调用服务，该表也必须存在，否则无法处理事务 */
CREATE TABLE `bytejta` (
  `xid` VARCHAR(32) NOT NULL DEFAULT '',
  `gxid` VARCHAR(40) DEFAULT NULL,
  `bxid` VARCHAR(40) DEFAULT NULL,
  `ctime` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`xid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
