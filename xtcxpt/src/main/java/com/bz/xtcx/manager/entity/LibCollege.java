package com.bz.xtcx.manager.entity;

import com.bz.xtcx.manager.comom.BasicEntity;

/**
 * 高校院校库
 * @author zhangkj
 *
 */
public class LibCollege extends BasicEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193852615185056708L;
	
	private String userId;
	private String name;//名称
	private String org_code;//机构代码
	private String country;//所在地区
	private String adress;//通信地址
	private String zip_code;//邮编
	private String unit_url;//单位网址
	private String major_platform;//单位简介
	private String introduction;//重大平台
	
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getOrg_code() {
		return org_code;
	}
	public String getCountry() {
		return country;
	}
	public String getAdress() {
		return adress;
	}
	public String getZip_code() {
		return zip_code;
	}
	public String getUnit_url() {
		return unit_url;
	}
	public String getMajor_platform() {
		return major_platform;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public void setUnit_url(String unit_url) {
		this.unit_url = unit_url;
	}
	public void setMajor_platform(String major_platform) {
		this.major_platform = major_platform;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
}
