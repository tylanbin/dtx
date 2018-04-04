package me.lb.demo.tcc.confirm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.lb.demo.dao.AccountDao;
import me.lb.demo.model.Account;
import me.lb.demo.tcc.TccService;

@Service("tccServiceConfirm")
public class TccServiceConfirm implements TccService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional
	public void deposit(int id, double amount) {
		// confirm时候将tmp中的金额移动到amount中
		Account account = accountDao.find(id);
		account.setTmp(account.getTmp() - amount);
		account.setAmount(account.getAmount() + amount);
		accountDao.update(account);
		logger.info("deposit confirm complete. accountId = {}, amount = {}", id, amount);
	}

}
