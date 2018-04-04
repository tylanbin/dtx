package me.lb.demo.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.lb.demo.dao.TransLogDao;
import me.lb.demo.model.TransLog;

@Repository
public class TransLogDaoImpl implements TransLogDao {

	private final String NAMESPACE = "me.lb.demo.model.TransLog";

	@Autowired
	@Qualifier("sst2")
	// 这里使用专门用于记录日志的datasource，与user、account区分
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int save(TransLog transLog) {
		sqlSessionTemplate.insert(NAMESPACE + ".save", transLog);
		return transLog.getId();
	}

}
