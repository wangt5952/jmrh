package com.bz.xtcx.manager.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.BusUserForm;

public interface BusUserFormMapper {
	
	@Insert("insert into `bus_user_form`(id, user_id, form_type, detail, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{formType, jdbcType=INTEGER},"
		    + " #{detail, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(BusUserForm form);
	
	@Delete("delete from `bus_user_form` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_form` set detail=#{detail, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER}"
			+ " where id=#{id}")
	int update(BusUserForm form);
	
	
	@Select("select * from `bus_user_form` where user_id = #{userId}")
	@Results(
			id = "busUserForm",
			value = {
			    @Result(id = true, property = "id", column = "id"),
			    @Result(property = "userId", column = "user_id"),
			    @Result(property = "formType", column = "form_type"),
			    @Result(property = "detail", column = "detail"),
			    @Result(property = "status", column = "status"),
			    @Result(property = "creater", column = "creater"),
			    @Result(property = "createTime", column = "create_time"),
			    @Result(property = "updater", column = "updater"),
			    @Result(property = "updateTime", column = "update_time")
		    }
		)
	BusUserForm findByUserId(String userId);
	
	@Select("select * from `bus_user_form` where user_id is null and form_type = #{type} order by create_time desc limit 1")
	@ResultMap("busUserForm")
	BusUserForm findByType(int type);
	
}
