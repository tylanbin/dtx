package me.lb.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.lb.demo.model.Account;
import me.lb.demo.model.User;
import me.lb.demo.service.AccountService;
import me.lb.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;

	/**
	 * 增加用户
	 * @param user 用户实体参数
	 * @return 成功：{ success: true, id: 用户id }
	 */
	@RequestMapping("/add")
	public Map<String, Object> add(User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			user.setRegTime(new Date());
			int id = userService.save(user);
			if (id > 0) {
				result.put("success", true);
				result.put("id", id);
			} else {
				result.put("success", false);
				result.put("msg", "用户名已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "插入用户失败");
		}
		return result;
	}
	
	/**
	 * 为指定的用户创建账户
	 * @param userId 用户id
	 * @return 成功：{ success: true, account: 账户实体信息 }
	 */
	@RequestMapping("/{userId}/account/open")
	public Map<String, Object> open(@PathVariable int userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Account account = accountService.open(userId);
			result.put("success", true);
			result.put("account", account);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "新增账户失败");
		}
		return result;
	}

}
