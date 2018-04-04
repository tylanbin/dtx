package me.lb.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.lb.demo.service.AccountService;

@RestController
public class JtaController {

	@Autowired
	private AccountService accountService;
	
	/**
	 * 转入进行到指定的账户（为了可以检测到异常进行回滚，这里不能进行try-catch）
	 * @param id 账户id
	 * @param amount 金额
	 * @return 成功：{ success: true }
	 */
	@RequestMapping("/deposit")
	public Map<String, Object> deposit(int id, double amount) {
		Map<String, Object> result = new HashMap<String, Object>();
		accountService.deposit(id, amount);
		result.put("success", true);
		return result;
	}

}
