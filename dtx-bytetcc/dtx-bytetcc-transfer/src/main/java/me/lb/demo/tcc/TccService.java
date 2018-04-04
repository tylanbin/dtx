package me.lb.demo.tcc;

import java.util.Map;

public interface TccService {

	/**
	 * 用户转账
	 * @param fromId 来源账户id
	 * @param toId 目标账户id
	 * @param amount 金额
	 */
	public Map<String, Object> transfer(int fromId, int toId, double amount);

}
