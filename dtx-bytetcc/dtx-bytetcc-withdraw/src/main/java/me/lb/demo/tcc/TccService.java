package me.lb.demo.tcc;

public interface TccService {

	/**
	 * 取钱
	 * @param id 账户id
	 * @param amount 金额
	 */
	public void withdraw(int id, double amount);

}
