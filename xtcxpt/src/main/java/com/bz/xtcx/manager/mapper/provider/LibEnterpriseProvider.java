package com.bz.xtcx.manager.mapper.provider;

import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.vo.VoQuery;

public class LibEnterpriseProvider {

	public String findByCondition(VoQuery e) {
		StringBuilder sql = new StringBuilder("select * from `lib_enterprise` where 1=1");
		sql.append(queryCondition(e));
		System.out.println(sql);
        return sql.toString();
	}
	
	StringBuilder queryCondition(VoQuery e) {
		StringBuilder sql = new StringBuilder();
		if(e != null) {
			if (!StringUtils.isEmpty(e.getObjName()))
	            sql.append(" and enterprise_name like '%"+e.getObjName()+"%'");
		}
		return sql;
	}
	
}
