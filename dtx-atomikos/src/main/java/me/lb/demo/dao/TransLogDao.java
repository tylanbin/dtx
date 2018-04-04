package me.lb.demo.dao;

import me.lb.demo.model.TransLog;

public interface TransLogDao {

	/**
	 * 存储转账记录信息
	 * @param transLog 转账记录信息
	 * @return 存储信息id
	 */
	public int save(TransLog transLog);

}
