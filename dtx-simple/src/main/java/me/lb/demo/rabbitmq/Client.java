package me.lb.demo.rabbitmq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 这里的队列名称与RabbitMQConf中一致
@RabbitListener(queues = "spring-boot")
public class Client {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AmqpTemplate rabbitTemplate;

	// 发送消息的方法
	public void send() {
		String msg = "now time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		logger.info("send msg: {}", msg);
		this.rabbitTemplate.convertAndSend("spring-boot", msg);
	}

	// 接收消息的方法
	@RabbitHandler
	public void receive(String msg) {
		logger.info("receive msg: {}", msg);
	}

}