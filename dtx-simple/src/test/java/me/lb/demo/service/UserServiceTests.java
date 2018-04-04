package me.lb.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.lb.demo.Application;
import me.lb.demo.model.Account;
import me.lb.demo.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;

	@Test
	public void test() throws Exception {
		// 增加2个user
		User user1 = new User();
		user1.setName("张三");
		user1.setUsername("zhangsan");
		user1.setPassword("1");
		User user2 = new User();
		user2.setName("李四");
		user2.setUsername("lisi");
		user2.setPassword("1");
		userService.save(user1);
		userService.save(user2);

		// 为2个用户新增账户
		accountService.open(user1.getId());
		accountService.open(user2.getId());
		// 这时每个用户都应该有1个账户，获取用户的账户，进行转账测试
		List<Account> accounts1 = accountService.findByUserId(user1.getId());
		List<Account> accounts2 = accountService.findByUserId(user2.getId());
		assertEquals(1, accounts1.size());
		assertEquals(1, accounts2.size());
		if (accounts1.size() == 1 && accounts2.size() == 1) {
			// 进行存款、取款、转账测试
			Account temp = null;
			Account zhangsan = accounts1.get(0);
			Account lisi = accounts2.get(0);
			// 存款测试
			accountService.deposit(zhangsan.getId(), 2000);
			temp = accountService.find(zhangsan.getId());
			assertEquals(2000.0, temp.getAmount(), 2);
			// 取款测试
			accountService.withdraw(zhangsan.getId(), 200);
			temp = accountService.find(zhangsan.getId());
			assertEquals(1800.0, temp.getAmount(), 2);
			try {
				accountService.withdraw(zhangsan.getId(), 20000);
			} catch (Exception e) {
				if (StringUtils.isNotEmpty(e.getMessage())) {
					logger.warn(e.getMessage());
				}
			}
			temp = accountService.find(zhangsan.getId());
			assertEquals(1800.0, temp.getAmount(), 2);
			// 转账测试
			accountService.transfer(zhangsan.getId(), lisi.getId(), 800);
			temp = accountService.find(zhangsan.getId());
			assertEquals(1000.0, temp.getAmount(), 2);
			temp = accountService.find(lisi.getId());
			assertEquals(800.0, temp.getAmount(), 2);
			try {
				accountService.transfer(zhangsan.getId(), lisi.getId(), 5000);
			} catch (Exception e) {
				if (StringUtils.isNotEmpty(e.getMessage())) {
					logger.warn(e.getMessage());
				}
			}
			temp = accountService.find(zhangsan.getId());
			assertEquals(1000.0, temp.getAmount(), 2);
			temp = accountService.find(lisi.getId());
			assertEquals(800.0, temp.getAmount(), 2);
		}
	}

}
