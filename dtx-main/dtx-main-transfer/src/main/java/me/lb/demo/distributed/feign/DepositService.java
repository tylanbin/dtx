package me.lb.demo.distributed.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-boot-demo-deposit")
public interface DepositService {

	/**
	 * 存钱
	 * @param id 账户id
	 * @param amount 金额
	 */
	@RequestMapping("/account/deposit")
	public Map<String, Object> deposit(@RequestParam("id") int id, @RequestParam("amount") double amount);

}
