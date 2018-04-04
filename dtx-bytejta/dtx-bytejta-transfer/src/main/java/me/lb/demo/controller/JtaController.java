package me.lb.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.lb.demo.distributed.feign.DepositService;
import me.lb.demo.distributed.feign.WithdrawService;

@RestController
public class JtaController {

	@Autowired
	private DepositService depositService;
	@Autowired
	private WithdrawService withdrawService;

	/**
	 * 转账（为了可以检测到异常进行回滚，这里不能进行try-catch）
	 * @param fromId 扣款账户id
	 * @param toId 转入账户id
	 * @param amount 金额
	 * @return 成功：{ success: true }
	 */
	@RequestMapping("/transfer")
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> transfer(int fromId, int toId, double amount) {
		// 测试路径：/transfer?fromId=1&toId=2&amount=100（张三给李四转100）
		Map<String, Object> result = new HashMap<String, Object>();
		withdrawService.withdraw(fromId, amount);
		depositService.deposit(toId, amount);
		result.put("success", true);
		return result;
	}

}
