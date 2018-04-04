package me.lb.demo.distributed.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public Queue depositQueue() {
		return new Queue("sb-deposit");
	}

	@Bean
	public Queue withdrawQueue() {
		return new Queue("sb-withdraw");
	}

	@Bean
	public RabbitTemplate amqpTemplate(ConnectionFactory factory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.setChannelTransacted(true);
		return rabbitTemplate;
	}

}