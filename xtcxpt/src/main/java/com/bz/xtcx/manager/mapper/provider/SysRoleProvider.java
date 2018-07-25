package com.bz.xtcx.manager.mapper.provider;

import java.text.MessageFormat;

import com.bz.xtcx.manager.entity.SysRole;

public class SysRoleProvider {

	public String findCount(SysRole e){
        StringBuilder sql = new StringBuilder("select count(*) from `sys_role` where 1=1");
        sql.append(queryCondition(e));
        return sql.toString();
    }
	
	public String findByCondition(SysRole e) {
		StringBuilder sql = new StringBuilder("select * from `sys_role` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(SysRole e) {
		StringBuilder sql = new StringBuilder();
		if (e.getRoleName() != null)
            sql.append(" and role_name like '%"+e.getRoleName()+"%'");
		if (e.getRoleType() != 0)
            sql.append(" and role_type = "+e.getRoleType());
		return sql;
	}
	
	public String addRoleMenus(SysRole role){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into `sys_role_menu`(id, role_id, menu_id, creater) values ");
        MessageFormat mf = new MessageFormat("(uuid(), #'{'id}, #'{'menus[{0}].id}, #'{'creater} ),");
        for(int i = 0; i < role.getMenus().size(); i++) {
        	sql.append(mf.format(new Object[]{i}));
        }
        sql.deleteCharAt(sql.length() - 1);
        System.out.println(sql);
		return sql.toString();
	}
}
