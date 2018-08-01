package com.bz.xtcx.manager.mapper.provider;

import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.SysOrg;
import com.bz.xtcx.manager.vo.VoQuery;

public class SysOrgProvider {

	public String findCount(VoQuery e){
        StringBuilder sql = new StringBuilder("select count(*) from `sys_org` where 1=1");
        sql.append(queryCondition(e));
        return sql.toString();
    }
	
	public String findByCondition(VoQuery e) {
		StringBuilder sql = new StringBuilder("select * from `sys_org` where 1=1");
		sql.append(queryCondition(e));
        return sql.toString();
	}
	
	StringBuilder queryCondition(VoQuery e) {
		StringBuilder sql = new StringBuilder();
		if(e != null) {
			if (!StringUtils.isEmpty(e.getObjName()))
	            sql.append(" and org_name like '%"+e.getObjName()+"%'");
		}
		return sql;
	}
}
