package com.bz.xtcx.manager.entity;

import java.io.Serializable;

import com.bz.xtcx.manager.comom.BasicEntity;

public class BusUserForm extends BasicEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9125060355274617641L;

	private String userId;
	private int formType;
	private String detail;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	
}
