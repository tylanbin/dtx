package me.lb.demo.controller;

import org.bytesoft.compensable.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.lb.demo.model.Account;
import me.lb.demo.service.AccountService;
import me.lb.demo.tcc.TccService;

/**
 * 使用ByteTCC时，Controller就相当于常见的Service实现
 * @author lanbin
 * @date 2018年3月6日
 */
@RestController
@Compensable(interfaceClass = TccService.class, confirmableKey = "tccServiceConfirm", cancellableKey = "tccServiceCancel")
public class TccController implements TccService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;

	/**
	 * 转入进行到指定的账户
	 * @param id 账户id
	 * @param amount 金额
	 * @return 成功：{ success: true }
	 */
	@RequestMapping("/deposit")
	@Transactional
	public void deposit(int id, double amount) {
		// try时候将的金额存储至tmp中
		Account account = accountService.find(id);
		account.setTmp(account.getTmp() + amount);
		accountService.update(account);
		logger.info("deposit try complete. accountId = {}, amount = {}", id, amount);
	}

}
