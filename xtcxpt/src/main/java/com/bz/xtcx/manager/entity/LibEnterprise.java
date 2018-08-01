package com.bz.xtcx.manager.entity;

/**
 * 企业库
 * @author zhangkj
 *
 */
public class LibEnterprise extends LibBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193852615185056708L;
	
	private String registered_capital;//企业规模（注册资金）
	private String registered_type;//注册类型
	private String is_high_new_tech;//是否高新技术企业
	private String domain;//所属领域
	private String lxname;
	private String lxzw;
	private String lxphone;
	private String lxemail;
	private String lpname;
	private String lpzw;
	private String lpphone;
	private String lpemail;
	
	public String getLxname() {
		return lxname;
	}
	public String getLxzw() {
		return lxzw;
	}
	public String getLxphone() {
		return lxphone;
	}
	public String getLxemail() {
		return lxemail;
	}
	public String getLpname() {
		return lpname;
	}
	public String getLpzw() {
		return lpzw;
	}
	public String getLpphone() {
		return lpphone;
	}
	public String getLpemail() {
		return lpemail;
	}
	public void setLxname(String lxname) {
		this.lxname = lxname;
	}
	public void setLxzw(String lxzw) {
		this.lxzw = lxzw;
	}
	public void setLxphone(String lxphone) {
		this.lxphone = lxphone;
	}
	public void setLxemail(String lxemail) {
		this.lxemail = lxemail;
	}
	public void setLpname(String lpname) {
		this.lpname = lpname;
	}
	public void setLpzw(String lpzw) {
		this.lpzw = lpzw;
	}
	public void setLpphone(String lpphone) {
		this.lpphone = lpphone;
	}
	public void setLpemail(String lpemail) {
		this.lpemail = lpemail;
	}
	public String getRegistered_capital() {
		return registered_capital;
	}
	public String getRegistered_type() {
		return registered_type;
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
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getIs_high_new_tech() {
		return is_high_new_tech;
	}
	public void setIs_high_new_tech(String is_high_new_tech) {
		this.is_high_new_tech = is_high_new_tech;
	}
}
