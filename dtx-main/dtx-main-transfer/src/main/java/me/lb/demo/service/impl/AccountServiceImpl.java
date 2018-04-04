package me.lb.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rabbitmq.client.Channel;

import me.lb.demo.service.AccountService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private ConnectionFactory factory;

	@Override
	public void transfer(int fromId, int toId, double amount) throws Exception {
		// FIXME: 该项目的核心就是该方法逻辑，但目前知识所限，无法完成

		// 1、按照如下的方式，代码会依次执行，但无法接收到错误，故不受事务控制
		// depositService.deposit(toId, amount);
		// withdrawService.withdraw(fromId, amount);

		// 2、获取取款的状态后进行处理，但无法处理网络问题导致的超时等问题
		/* Map<String, Object> result = withdrawService.withdraw(fromId, amount);
		if (result.get("success") != null && "true".equals(String.valueOf(result.get("success")))) {
			depositService.deposit(toId, amount);// 只有余额足够，才可以转给他人
		} else {
			throw new RuntimeException(String.valueOf(result.get("msg")));
		} */

		// 3、使用rabbitmq进行处理
		Connection connection = factory.createConnection();
		Channel channel = connection.createChannel(true);
		// 事务方式处理（只有不发生错误的情况下会发送到服务器中）
		try {
			String msg = "now time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			channel.txSelect();
			channel.basicPublish("", "spring-boot", null, msg.getBytes());
			channel.txCommit();
		} catch (Exception e) {
			e.printStackTrace();
			// 出现异常进行回滚（如果有需求，仍然可以重发消息）
			channel.txRollback();
		}
		channel.close();
		connection.close();
	}

}
