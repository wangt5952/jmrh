package com.bz.xtcx.manager.entity;

/**
 * 高校院校库
 * @author zhangkj
 *
 */
public class LibCollege extends BaseLib{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193852615185056708L;
	
	private String zip_code;//邮编
	private String unit_url;//单位网址
	private String major_platform;//单位简介
	private String introduction;//重大平台
	
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
