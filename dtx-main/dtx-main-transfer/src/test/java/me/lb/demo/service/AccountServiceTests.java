package me.lb.demo.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.lb.demo.Application;
import me.lb.demo.distributed.feign.DepositService;
import me.lb.demo.distributed.feign.WithdrawService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AccountServiceTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;
	
	// 远程的服务
	@Autowired
	private DepositService depositService;
	@Autowired
	private WithdrawService withdrawService;

	@Test
	public void test1() throws Exception {
		int zhangsanId = 1;
		int lisiId = 2;
		Map<String, Object> result = null;
		// 存款测试
		result = depositService.deposit(zhangsanId, 2000);
		assertTrue((boolean) result.get("success"));
		// 取款测试
		result = withdrawService.withdraw(zhangsanId, 200);
		assertTrue((boolean) result.get("success"));
		result = withdrawService.withdraw(zhangsanId, 20000);
		assertFalse((boolean) result.get("success"));
		// 转账测试
		try {
			accountService.transfer(zhangsanId, lisiId, 800);
			accountService.transfer(zhangsanId, lisiId, 5000);
		} catch (Exception e) {
			if (StringUtils.isNotEmpty(e.getMessage())) {
				logger.warn(e.getMessage());
			}
		}
		// 如果账户中原来都是0，那么最终结果分别是1000和800
		// 日志级别如果为info，则可能会出现异常信息，是正确的
	}
	
	@Test
	public void test2() throws Exception {
		int zhangsanId = 1;
		int lisiId = 2;
		accountService.transfer(zhangsanId, lisiId, 800);
	}

}
