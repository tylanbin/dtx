package me.lb.demo.service;

public interface AccountService {

	/**
	 * 用户转账
	 * @param fromId 来源账户id
	 * @param toId 目标账户id
	 * @param amount 金额
	 */
	public void transfer(int fromId, int toId, double amount) throws Exception;

}
