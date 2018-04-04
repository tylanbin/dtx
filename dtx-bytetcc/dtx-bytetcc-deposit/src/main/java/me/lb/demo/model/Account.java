package me.lb.demo.model;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String sn;// 编号
	private double amount;// 金额
	private double tmp;// 用于tcc的临时数据
	private Integer userId;// 账户所属的用户id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTmp() {
		return tmp;
	}

	public void setTmp(double tmp) {
		this.tmp = tmp;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
