package me.lb.demo.distributed.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import me.lb.demo.distributed.feign.WithdrawService;

@Component
// 这里的队列名称与生产者的取款队列一致
@RabbitListener(queues = { "sb-withdraw" })
public class Client {

	@Autowired
	private WithdrawService withdrawService;

	// 接收消息的方法
	@RabbitHandler
	public void receive(String data) {
		try {
			ObjectMapper om = new ObjectMapper();
			ObjectNode obj = (ObjectNode) om.readTree(data);
			if (obj.get("id") != null && obj.get("amount") != null) {
				// 有正常的参数才进行处理
				int id = obj.get("id").asInt();
				double amount = obj.get("amount").asDouble();
				withdrawService.withdraw(id, amount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}