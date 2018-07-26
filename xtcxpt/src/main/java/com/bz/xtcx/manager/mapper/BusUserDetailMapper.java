package com.bz.xtcx.manager.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.BusUserDetail;
import com.bz.xtcx.manager.mapper.provider.BusUserDetailProvider;

public interface BusUserDetailMapper {
	
	@InsertProvider(type = BusUserDetailProvider.class, method = "insert")
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(BusUserDetail detail);
	
	@Delete("delete from `bus_user_detail` where id = #{id}")
	int del(String id);
	
	@UpdateProvider(type = BusUserDetailProvider.class, method = "update")
	int update(BusUserDetail detail);
	
	
	@Select("select * from `bus_user_detail` where user_id = #{userId}")
	@Results(
			id = "busUserDetail",
			value = {
			    @Result(id = true, property = "id", column = "id"),
			    @Result(property = "userId", column = "user_id"),
			    @Result(property = "field1", column = "field1"),
			    @Result(property = "field2", column = "field2"),
			    @Result(property = "field3", column = "field3"),
			    @Result(property = "field4", column = "field4"),
			    @Result(property = "field5", column = "field5"),
			    @Result(property = "field6", column = "field6"),
			    @Result(property = "field7", column = "field7"),
			    @Result(property = "field8", column = "field8"),
			    @Result(property = "field9", column = "field9"),
			    @Result(property = "field10", column = "field10"),
			    @Result(property = "field11", column = "field11"),
			    @Result(property = "field12", column = "field12"),
			    @Result(property = "field13", column = "field13"),
			    @Result(property = "field14", column = "field14"),
			    @Result(property = "field15", column = "field15"),
			    @Result(property = "field16", column = "field16"),
			    @Result(property = "field17", column = "field17"),
			    @Result(property = "field18", column = "field18"),
			    @Result(property = "field19", column = "field19"),
			    @Result(property = "field20", column = "field20"),
			    @Result(property = "field21", column = "field21"),
			    @Result(property = "field22", column = "field22"),
			    @Result(property = "field23", column = "field23"),
			    @Result(property = "field24", column = "field24"),
			    @Result(property = "field25", column = "field25"),
			    @Result(property = "field26", column = "field27"),
			    @Result(property = "field27", column = "field27"),
			    @Result(property = "field28", column = "field28"),
			    @Result(property = "field29", column = "field29"),
			    @Result(property = "field30", column = "field30"),
			    @Result(property = "status", column = "status"),
			    @Result(property = "creater", column = "creater"),
			    @Result(property = "createTime", column = "create_time"),
			    @Result(property = "updater", column = "updater"),
			    @Result(property = "updateTime", column = "update_time")
		    }
		)
	BusUserDetail findByUserId(String userId);
	
}
