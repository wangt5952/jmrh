package com.bz.xtcx.manager.mapper.provider;

import com.bz.xtcx.manager.entity.LibExpert;

public class LibExpertProvider {

	public String findByCondition(LibExpert e) {
		StringBuilder sql = new StringBuilder("select * from `bus_user_service` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(LibExpert e) {
		StringBuilder sql = new StringBuilder();
		if (e.getName() != null)
            sql.append(" and name like '%"+e.getName()+"%'");
		return sql;
	}
	
}
