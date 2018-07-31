package com.bz.xtcx.manager.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.BusUserImage;

public interface BusUserImageMapper {
	
	@Insert("insert into `bus_user_image`(id, user_id, field1, field2, field3, field4, field5, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{field1, jdbcType=VARCHAR},"
		    + " #{field2, jdbcType=VARCHAR},"
		    + " #{field3, jdbcType=VARCHAR},"
		    + " #{field4, jdbcType=VARCHAR},"
		    + " #{field5, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(BusUserImage e);
	
	@Delete("delete from `bus_user_form` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_image` set field1=#{field1, jdbcType=VARCHAR},"
			+ " field1=#{field1, jdbcType=VARCHAR},"
			+ " field2=#{field2, jdbcType=VARCHAR},"
			+ " field3=#{field3, jdbcType=VARCHAR},"
			+ " field4=#{field4, jdbcType=VARCHAR},"
			+ " field5=#{field5, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER}"
			+ " where id=#{id}")
	int update(BusUserImage e);
	
	
	@Select("select * from `bus_user_image` where user_id = #{userId}")
	@Results(
			id = "busUserImage",
			value = {
			    @Result(id = true, property = "id", column = "id"),
			    @Result(property = "userId", column = "user_id"),
			    @Result(property = "field1", column = "field1"),
			    @Result(property = "field2", column = "field2"),
			    @Result(property = "field3", column = "field3"),
			    @Result(property = "field4", column = "field4"),
			    @Result(property = "field5", column = "field5"),
			    @Result(property = "status", column = "status"),
			    @Result(property = "creater", column = "creater"),
			    @Result(property = "createTime", column = "create_time"),
			    @Result(property = "updater", column = "updater"),
			    @Result(property = "updateTime", column = "update_time")
		    }
		)
	BusUserImage findByUserId(String userId);
	
}
