package com.bz.xtcx.manager.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.BusUserForm;

public interface BusUserFormHisMapper {
	
	@Insert("insert into `bus_user_form_his`(id, user_id, detail, check_status, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{detail, jdbcType=VARCHAR},"
		    + " #{checkStatus, jdbcType=INTEGER},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(BusUserForm form);
	
	@Delete("delete from `bus_user_form_his` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_form_his` set detail=#{detail, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER}"
			+ " where id=#{id}")
	int update(BusUserForm form);
	
	@Update("update `bus_user_form_his` set check_status=#{check, jdbcType=INTEGER} where id=#{id}")
	int updateCheck(int check, String id);
	
	
	@Select("select * from `bus_user_form_his` where user_id = #{userId} and check_status = #{check}")
	@Results(
			id = "busUserFormHis",
			value = {
			    @Result(id = true, property = "id", column = "id"),
			    @Result(property = "userId", column = "user_id"),
			    @Result(property = "detail", column = "detail"),
			    @Result(property = "checkStatus", column = "check_status"),
			    @Result(property = "status", column = "status"),
			    @Result(property = "creater", column = "creater"),
			    @Result(property = "createTime", column = "create_time"),
			    @Result(property = "updater", column = "updater"),
			    @Result(property = "updateTime", column = "update_time")
		    }
		)
	BusUserForm findByUserId(String userId, int check);
	
	@Update("select * from `bus_user_form_his` where check_status!=-1 and user_id=#{userId} order by create_time desc limit 1")
	BusUserForm findByUserId(String userId);
	
	@Update("select * from `bus_user_form_his` where id=#{id}")
	BusUserForm findById(String id);
}
