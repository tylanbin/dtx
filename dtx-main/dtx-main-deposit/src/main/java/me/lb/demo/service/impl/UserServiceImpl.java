package me.lb.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.lb.demo.dao.UserDao;
import me.lb.demo.model.User;
import me.lb.demo.service.UserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int save(User user) {
		if (userDao.findByUsername(user.getUsername()) != null) {
			return -1;// 表示用户已存在
		} else {
			return userDao.save(user);
		}
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
