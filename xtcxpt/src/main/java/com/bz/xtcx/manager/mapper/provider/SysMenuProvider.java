package com.bz.xtcx.manager.mapper.provider;

import com.bz.xtcx.manager.entity.SysMenu;

public class SysMenuProvider {

	public String findCount(SysMenu e){
        StringBuilder sql = new StringBuilder("select count(*) from `sys_menu` where 1=1");
        sql.append(queryCondition(e));
        return sql.toString();
    }
	
	public String findByCondition(SysMenu e) {
		StringBuilder sql = new StringBuilder("select * from `sys_menu` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(SysMenu e) {
		StringBuilder sql = new StringBuilder();
		if (e.getMenuName() != null)
            sql.append(" and menu_name like '%"+e.getMenuName()+"%'");
		return sql;
	}
}
