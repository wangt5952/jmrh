package com.bz.xtcx.manager.entity;

import com.bz.xtcx.manager.comom.BasicEntity;

public class BusUserForm extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9125060355274617641L;

	private String userId;
	private int formType;
	private String detail;
	private int checkStatus;
	
	public int getFormType() {
		return formType;
	}
	public void setFormType(int formType) {
		this.formType = formType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
