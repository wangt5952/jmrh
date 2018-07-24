package com.bz.xtcx.manager.entity;

import java.util.List;

import com.bz.xtcx.manager.comom.BasicEntity;

public class SysRole extends BasicEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8571895183610594811L;
	private String roleName;
	private String roleDesc;
	private int roleType;
	private List<SysMenu> menus;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public List<SysMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	
	
}
