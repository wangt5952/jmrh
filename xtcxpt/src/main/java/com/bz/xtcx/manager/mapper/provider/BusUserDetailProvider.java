package com.bz.xtcx.manager.mapper.provider;

import java.text.MessageFormat;

import com.bz.xtcx.manager.entity.BusUserDetail;

public class BusUserDetailProvider {

	public String insert(BusUserDetail detail){
        StringBuilder sql = new StringBuilder("insert into `bus_user_detail` values ");
        MessageFormat mf = new MessageFormat("(uuid(), #'{'userId}, #'{'{0}}, #'{'creater}, #'{'createTime} ),");
        for(int i = 0; i <= 30; i++) {
        	String field = "field" + i;
        	sql.append(mf.format(new Object[]{field}));
        }
        sql.deleteCharAt(sql.length() - 1);
        System.out.println(sql);
		return sql.toString();
    }
	
	public String update(BusUserDetail detail){
        StringBuilder sql = new StringBuilder("insert into `bus_user_detail` values ");
        MessageFormat mf = new MessageFormat("(uuid(), #'{'userId}, #'{'{0}}, #'{'creater}, #'{'createTime} ),");
        for(int i = 0; i <= 30; i++) {
        	String field = "field" + i;
        	sql.append(mf.format(new Object[]{field}));
        }
        sql.deleteCharAt(sql.length() - 1);
        System.out.println(sql);
		return sql.toString();
    }
	
}
