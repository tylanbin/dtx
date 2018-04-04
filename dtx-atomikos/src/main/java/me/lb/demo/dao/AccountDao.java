package me.lb.demo.dao;

import java.util.List;

import me.lb.demo.model.Account;

public interface AccountDao {
	
	/**
	 * 查询账户
	 * @param id 账户id
	 * @return 账户实体
	 */
	public Account find(int id);

	/**
	 * 为用户创建账户
	 * @param account 账户实体（包含userId）
	 * @return 存储到数据库的id
	 */
	public int save(Account account);
	
	/**
	 * 更新账户信息
	 * @param account 账户实体（不处理userId）
	 */
	public void update(Account account);
	
	/**
	 * 查询编号为sn的账户条数（用于逻辑排重）
	 * @param sn 编号
	 * @return 条数
	 */
	public int countBySn(String sn);
	
	/**
	 * 查询用户id下所有的账户
	 * @param userId 用户id
	 * @return 账户列表集合
	 */
	public List<Account> findByUserId(int userId);

}
