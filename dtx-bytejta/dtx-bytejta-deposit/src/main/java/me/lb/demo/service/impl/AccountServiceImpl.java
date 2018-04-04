package me.lb.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.lb.demo.dao.AccountDao;
import me.lb.demo.model.Account;
import me.lb.demo.service.AccountService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public Account open(int userId) {
		Account account = new Account();
		// 使用时分秒+随机数，避免重复
		account.setSn(getUniqueSn());
		account.setUserId(userId);
		account.setAmount(0);
		accountDao.save(account);
		return account;
	}

	@Override
	public Account find(int id) {
		return accountDao.find(id);
	}

	@Override
	public List<Account> findByUserId(int userId) {
		return accountDao.findByUserId(userId);
	}

	@Override
	public void deposit(int id, double amount) {
		// 存钱逻辑上不需要判断余额
		Account account = accountDao.find(id);
		account.setAmount(account.getAmount() + amount);
		accountDao.update(account);
	}

	@Override
	public void withdraw(int id, double amount) {
		Account account = accountDao.find(id);
		// 余额判断，不足则抛出异常
		if (account.getAmount() < amount) {
			throw new RuntimeException("账户“" + account.getSn() + "”余额不足");
		} else {
			account.setAmount(account.getAmount() - amount);
			accountDao.update(account);
		}
	}

	@Override
	public void transfer(int fromId, int toId, double amount) {
		// 为了验证事务，专门先存再取，查看能否回滚
		deposit(toId, amount);
		withdraw(fromId, amount);
	}

	/**
	 * 获取唯一的账户编号
	 * @return 唯一的账户编号
	 */
	private String getUniqueSn() {
		String sn = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomStringUtils.random(6, false, true);
		if (accountDao.countBySn(sn) > 0) {
			return getUniqueSn();
		} else {
			return sn;
		}
	}

}
