package me.lb.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConf {

	@Bean
	public Queue helloQueue() {
		return new Queue("spring-boot");
	}

}