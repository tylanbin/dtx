package me.lb.demo.service;

import java.util.List;

import me.lb.demo.model.Account;

public interface AccountService {

	/**
	 * 为用户分配一个新的账户，余额为0
	 * @param userId 用户id
	 * @return 新增的账户
	 */
	public Account open(int userId);
	
	/**
	 * 查询指定id的账户信息
	 * @param id 账户id
	 * @return 账户实体
	 */
	public Account find(int id);
	
	/**
	 * 更新账户信息
	 * @param account 账户实体（不处理userId）
	 */
	public void update(Account account);
	
	/**
	 * 查询用户id下所有的账户
	 * @param userId 用户id
	 * @return 账户列表集合
	 */
	public List<Account> findByUserId(int userId);

	/**
	 * 存钱
	 * @param id 账户id
	 * @param amount 金额
	 */
	public void deposit(int id, double amount);

	/**
	 * 取钱
	 * @param id 账户id
	 * @param amount 金额
	 */
	public void withdraw(int id, double amount);

	/**
	 * 用户转账
	 * @param fromId 来源账户id
	 * @param toId 目标账户id
	 * @param amount 金额
	 */
	public void transfer(int fromId, int toId, double amount);

}
