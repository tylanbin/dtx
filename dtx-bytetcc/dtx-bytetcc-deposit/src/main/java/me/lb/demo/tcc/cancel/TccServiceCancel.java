package me.lb.demo.tcc.cancel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.lb.demo.dao.AccountDao;
import me.lb.demo.model.Account;
import me.lb.demo.tcc.TccService;

@Service("tccServiceCancel")
public class TccServiceCancel implements TccService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional
	public void deposit(int id, double amount) {
		// cancel时候将tmp中的金额扣除
		Account account = accountDao.find(id);
		account.setTmp(account.getTmp() - amount);
		accountDao.update(account);
		logger.info("deposit cancel complete. accountId = {}, amount = {}", id, amount);
	}

}
