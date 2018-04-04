package me.lb.demo.dao;

import me.lb.demo.model.User;

public interface UserDao {

	/**
	 * 存储用户的信息
	 * @param user 用户实体
	 * @return 存储到数据库的id
	 */
	public int save(User user);
	
	/**
	 * 根据用户名查询用户
	 * @param username 用户名
	 * @return 用户实体
	 */
	public User findByUsername(String username);

}
