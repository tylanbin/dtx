package me.lb.demo.model;

import java.io.Serializable;

public class TransLog implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int formId;
	private int toId;
	private String formSn;// 来源账号sn
	private String toSn;// 目标账号sn
	private double amount;// 金额

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public String getFormSn() {
		return formSn;
	}

	public void setFormSn(String formSn) {
		this.formSn = formSn;
	}

	public String getToSn() {
		return toSn;
	}

	public void setToSn(String toSn) {
		this.toSn = toSn;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
