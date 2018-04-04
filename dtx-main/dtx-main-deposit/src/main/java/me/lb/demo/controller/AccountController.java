package me.lb.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.lb.demo.service.AccountService;

/**
 * 这里只提供存款接口
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

}
