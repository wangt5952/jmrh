package com.bz.xtcx.manager.mapper.provider;

import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.vo.VoQuery;

public class LibReqProvider {

	public String findByCondition(VoQuery e) {
		StringBuilder sql = new StringBuilder("select * from `lib_requirements` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(VoQuery e) {
		StringBuilder sql = new StringBuilder();
		if(e != null) {
			if (!StringUtils.isEmpty(e.getObjName()))
	            sql.append(" and name like '%"+e.getObjName()+"%'");
		}
		return sql;
	}
	
}
