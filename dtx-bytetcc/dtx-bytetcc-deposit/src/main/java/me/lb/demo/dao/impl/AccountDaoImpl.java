package me.lb.demo.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.lb.demo.dao.AccountDao;
import me.lb.demo.model.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

	private final String NAMESPACE = "me.lb.demo.model.Account";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Account find(int id) {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".find", id);
	}

	@Override
	public int save(Account account) {
		sqlSessionTemplate.insert(NAMESPACE + ".save", account);
		return account.getId();
	}

	@Override
	public void update(Account account) {
		sqlSessionTemplate.update(NAMESPACE + ".update", account);
	}

	@Override
	public int countBySn(String sn) {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".countBySn", sn);
	}

	@Override
	public List<Account> findByUserId(int userId) {
		return sqlSessionTemplate.selectList(NAMESPACE + ".findByUserId", userId);
	}

}
