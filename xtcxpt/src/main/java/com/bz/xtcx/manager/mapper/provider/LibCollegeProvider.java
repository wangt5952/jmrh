package com.bz.xtcx.manager.mapper.provider;

import com.bz.xtcx.manager.entity.LibCollege;

public class LibCollegeProvider {

	public String findByCondition(LibCollege e) {
		StringBuilder sql = new StringBuilder("select * from `bus_user_college` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(LibCollege e) {
		StringBuilder sql = new StringBuilder();
		if(e != null) {
			if (e.getName() != null)
	            sql.append(" and name like '%"+e.getName()+"%'");
		}
		return sql;
	}
	
}
