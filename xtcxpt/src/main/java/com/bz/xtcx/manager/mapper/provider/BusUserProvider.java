package com.bz.xtcx.manager.mapper.provider;

import java.text.MessageFormat;

import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.vo.VoQuery;

public class BusUserProvider {

	public String findCount(VoQuery user){
        StringBuilder sql = new StringBuilder("select count(*) from `bus_user` where 1=1");
        sql.append(queryCondition(user));
        return sql.toString();
    }
	
	public String findByCondition(VoQuery user) {
		StringBuilder sql = new StringBuilder("select * from `bus_user` where 1=1");
		sql.append(queryCondition(user));
		System.out.println(sql);
        return sql.toString();
	}
	
	StringBuilder queryCondition(VoQuery user) {
		StringBuilder sql = new StringBuilder();
		if(user != null) {
			if(!StringUtils.isEmpty(user.getUserType())) {
				sql.append(" and user_type = '"+user.getUserType()+"'");
			}
			if(!StringUtils.isEmpty(user.getObjName())) {
				sql.append(" and (");
				sql.append(" user_name like '%"+user.getObjName()+"%'");
				sql.append(" or email like '%"+user.getObjName()+"%'");
				sql.append(" or cellphone like '%"+user.getObjName()+"%'");
				sql.append(" or name like '%"+user.getObjName()+"%'");
				sql.append(")");
			}
			/*if (user.getUserName() != null)
	            sql.append(" and user_name like '%"+user.getUserName()+"%'");
	        if (user.getCellphone() != null)
	            sql.append(" and cellphone='"+user.getCellphone()+"'");
	        if (user.getEmail() != null)
	            sql.append(" and email like '%"+user.getEmail()+"%'");*/
			
		}
		return sql;
	}
	
	public String addUserRoles(BusUser user){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into `sys_user_role`(id, user_id, role_id, creater, create_time) values ");
        MessageFormat mf = new MessageFormat("(uuid(), #'{'id}, #'{'roles[{0}].id}, #'{'creater}, #'{'createTime} ),");
        for(int i = 0; i < user.getRoles().size(); i++) {
        	sql.append(mf.format(new Object[]{i}));
        }
        sql.deleteCharAt(sql.length() - 1);
        System.out.println(sql);
		return sql.toString();
	}
}
