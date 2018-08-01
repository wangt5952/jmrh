package com.bz.xtcx.manager.mapper.provider;

import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.vo.VoQuery;

public class LibExpertProvider {

	public String findByCondition(VoQuery e) {
		StringBuilder sql = new StringBuilder("select * from `lib_expert` where 1=1");
		sql.append(queryCondition(e));
		System.out.println(sql);
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
