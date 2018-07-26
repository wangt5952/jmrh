package com.bz.xtcx.manager.mapper.provider;

import com.bz.xtcx.manager.entity.LibService;

public class LibServiceProvider {

	public String findByCondition(LibService e) {
		StringBuilder sql = new StringBuilder("select * from `bus_user_service` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(LibService e) {
		StringBuilder sql = new StringBuilder();
		if(e != null) {
			if (e.getName() != null)
	            sql.append(" and name like '%"+e.getName()+"%'");
		}
		return sql;
	}
	
}
