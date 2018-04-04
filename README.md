dtx(distributed transactions)
======
本项目主要是想通过几个项目来简单测试Spring-Boot的分布式事务处理

* dtx-simple 简单的单项目，使用传统的Service层处理事务
* dtx-atomikos 单项目多数据库的分布式事务测试
* dtx-bytejta 分布式事务测试，[ByteJTA](https://github.com/liuyangming/ByteJTA)的实现（存在缺陷）
* dtx-bytetcc 分布式事务测试，[ByteTCC](https://github.com/liuyangming/ByteTCC)的实现
* dtx-main 用来专门实验分布式事务的项目（测试使用）

ByteJTA的实现中，存在这些问题：
1. 事务使用日志文件方式处理，在跨服务器的环境下应该存在问题
2. 假设被调用服务需要进行回滚，如果被调用服务的事务开始时间晚于框架进行通知回滚（调用/org/bytesoft/bytejta/rollback/{xid}）的时间（比如出现了延时），则无法正常进行回滚，而这个问题在ByteTCC的实现中不存在