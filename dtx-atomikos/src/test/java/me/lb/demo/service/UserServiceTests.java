package me.lb.demo.service;

import static org.junit.Assert.assertEquals;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;

	@Test
	public void test() throws Exception {
		// 这时每个用户都应该有1个账户，获取用户的账户，进行转账测试
		Account temp = null;
		Account zhangsan = accountService.find(1);
		Account lisi = accountService.find(2);
		// 转账测试
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
