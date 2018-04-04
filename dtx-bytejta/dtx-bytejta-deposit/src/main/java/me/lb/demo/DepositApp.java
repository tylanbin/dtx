package me.lb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "me.lb.demo")
@ImportResource({ "classpath:bytejta-supports-springcloud.xml" })
public class DepositApp {

	public static void main(String[] args) {
		SpringApplication.run(DepositApp.class, args);
	}

}
