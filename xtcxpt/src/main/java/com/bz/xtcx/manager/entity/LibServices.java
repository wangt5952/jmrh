package com.bz.xtcx.manager.entity;

/**
 * 服务机构库
 * @author zhangkj
 *
 */
public class LibServices extends LibBase{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3874228762810906032L;
	
	
	private String org_type;//机构类型
	private String linkman;//联系人
	private String service_amount_last;//近三年服务情况
	private String service_amount_before;
	private String service_amount_previous;
	private String service_quantity_last;
	private String service_quantity_before;
	private String service_quantity_previous;
	private String honor;//所获资质
	
	public String getOrg_type() {
		return org_type;
	}
	public String getLinkman() {
		return linkman;
	}
	
	public String getHonor() {
		return honor;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getService_amount_last() {
		return service_amount_last;
	}
	public String getService_amount_before() {
		return service_amount_before;
	}
	public String getService_amount_previous() {
		return service_amount_previous;
	}
	public String getService_quantity_last() {
		return service_quantity_last;
	}
	public String getService_quantity_before() {
		return service_quantity_before;
	}
	public String getService_quantity_previous() {
		return service_quantity_previous;
	}
	public void setService_amount_last(String service_amount_last) {
		this.service_amount_last = service_amount_last;
	}
	public void setService_amount_before(String service_amount_before) {
		this.service_amount_before = service_amount_before;
	}
	public void setService_amount_previous(String service_amount_previous) {
		this.service_amount_previous = service_amount_previous;
	}
	public void setService_quantity_last(String service_quantity_last) {
		this.service_quantity_last = service_quantity_last;
	}
	public void setService_quantity_before(String service_quantity_before) {
		this.service_quantity_before = service_quantity_before;
	}
	public void setService_quantity_previous(String service_quantity_previous) {
		this.service_quantity_previous = service_quantity_previous;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
}
