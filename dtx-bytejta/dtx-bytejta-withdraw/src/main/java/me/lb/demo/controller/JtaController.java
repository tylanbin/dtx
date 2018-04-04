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
	 * 从指定的账户中取出指定的金额（为了可以检测到异常进行回滚，这里不能进行try-catch）
	 * @param id 账户id
	 * @param amount 金额
	 * @return 成功：{ success: true }
	 */
	@RequestMapping("/withdraw")
	public Map<String, Object> withdraw(int id, double amount) {
		Map<String, Object> result = new HashMap<String, Object>();
		accountService.withdraw(id, amount);
		result.put("success", true);
		return result;
	}

}
