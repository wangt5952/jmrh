package com.bz.xtcx.manager.entity;

import com.bz.xtcx.manager.comom.BasicEntity;

public class LibBase extends BasicEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3009507610019421031L;
	private String name;//名称
	private String code;//编码
	private String userId;//用户ID
	private String country;//所在地区
	private String address;//通信地址
	private int checkStatus;//审核状态
	private String formId;//表格Id
	private BusUserForm form;
	
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getUserId() {
		return userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BusUserForm getForm() {
		return form;
	}
	public void setForm(BusUserForm form) {
		this.form = form;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
}
