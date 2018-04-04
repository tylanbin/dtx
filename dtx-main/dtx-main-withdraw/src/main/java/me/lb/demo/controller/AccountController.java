package me.lb.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.lb.demo.service.AccountService;

/**
 * 这里只提供取款接口
 * AccountController
 * @author lanbin
 * @date 2017年12月21日
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
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
			result.put("msg", e.getMessage() != null ? e.getMessage() : "");
		}
		return result;
	}

}
