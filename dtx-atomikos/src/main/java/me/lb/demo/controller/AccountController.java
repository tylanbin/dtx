package me.lb.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.lb.demo.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	/**
	 * 转入进行到指定的账户
	 * @param id 账户id
	 * @param amount 金额
	 * @return 成功：{ success: true }
	 */
	@RequestMapping("/deposit")
	public Map<String, Object> deposit(int id, double amount) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			accountService.deposit(id, amount);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "转入失败");
		}
		return result;
	}
	
	/**
	 * 从指定的账户中取出指定的金额
	 * @param id 账户id
	 * @param amount 金额
	 * @return 成功：{ success: true }
	 */
	@RequestMapping("/withdraw")
	public Map<String, Object> withdraw(int id, double amount) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			accountService.withdraw(id, amount);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "转出失败");
		}
		return result;
	}

	/**
	 * 转账
	 * @param fromId 扣款账户id
	 * @param toId 转入账户id
	 * @param amount 金额
	 * @return 成功：{ success: true }
	 */
	@RequestMapping("/transfer")
	public Map<String, Object> transfer(int fromId, int toId, double amount) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			accountService.transfer(fromId, toId, amount);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "转账失败");
		}
		return result;
	}

}
