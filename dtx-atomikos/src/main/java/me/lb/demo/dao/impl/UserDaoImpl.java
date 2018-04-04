package me.lb.demo.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.lb.demo.dao.UserDao;
import me.lb.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private final String NAMESPACE = "me.lb.demo.model.User";

	@Autowired
	// 直接注入会默认使用@Primary的Bean
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int save(User user) {
		sqlSessionTemplate.insert(NAMESPACE + ".save", user);
		return user.getId();
	}

	@Override
	public User findByUsername(String username) {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".findByUsername", username);
	}

}
