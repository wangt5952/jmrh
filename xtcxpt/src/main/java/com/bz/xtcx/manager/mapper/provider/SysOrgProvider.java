package com.bz.xtcx.manager.mapper.provider;

import com.bz.xtcx.manager.entity.SysOrg;

public class SysOrgProvider {

	public String findCount(SysOrg e){
        StringBuilder sql = new StringBuilder("select count(*) from `sys_org` where 1=1");
        sql.append(queryCondition(e));
        return sql.toString();
    }
	
	public String findByCondition(SysOrg e) {
		StringBuilder sql = new StringBuilder("select * from `sys_org` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(SysOrg e) {
		StringBuilder sql = new StringBuilder();
		if(e != null) {
			if (e.getOrgName() != null)
	            sql.append(" and org_name like '%"+e.getOrgName()+"%'");
		}
		return sql;
	}
}
