package com.bz.xtcx.manager.mapper.provider;

import com.bz.xtcx.manager.entity.LibEnterprise;

public class LibEnterpriseProvider {

	public String findByCondition(LibEnterprise e) {
		StringBuilder sql = new StringBuilder("select * from `bus_user_enterprise` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(LibEnterprise e) {
		StringBuilder sql = new StringBuilder();
		if(e != null) {
			if (e.getName() != null)
	            sql.append(" and enterprise_name like '%"+e.getName()+"%'");
		}
		return sql;
	}
	
}
