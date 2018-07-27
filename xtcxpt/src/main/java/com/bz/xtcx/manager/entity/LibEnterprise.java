package com.bz.xtcx.manager.entity;

/**
 * 企业库
 * @author zhangkj
 *
 */
public class LibEnterprise extends BaseLib {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193852615185056708L;
	
	private String registered_capital;//企业规模（注册资金）
	private String registered_type;//注册类型
	private int is_high_new_tech;//是否高新技术企业
	private String domain;//所属领域
	
	public String getRegistered_capital() {
		return registered_capital;
	}
	public String getRegistered_type() {
		return registered_type;
	}
	public int getIs_high_new_tech() {
		return is_high_new_tech;
	}
	public String getDomain() {
		return domain;
	}
	public void setRegistered_capital(String registered_capital) {
		this.registered_capital = registered_capital;
	}
	public void setRegistered_type(String registered_type) {
		this.registered_type = registered_type;
	}
	public void setIs_high_new_tech(int is_high_new_tech) {
		this.is_high_new_tech = is_high_new_tech;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
}
