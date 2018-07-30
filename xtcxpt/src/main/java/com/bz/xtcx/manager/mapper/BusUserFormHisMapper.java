package com.bz.xtcx.manager.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.BusUserForm;

public interface BusUserFormHisMapper {
	
	@Insert("insert into `bus_user_form_his`(id, user_id, detail_id, form_type, detail, check_status, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{detailId, jdbcType=VARCHAR},"
		    + " #{formType, jdbcType=VARCHAR},"
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
	int updateCheck(@Param("check")int check, @Param("id")String id);
	
	
	@Select("select * from `bus_user_form_his` where user_id = #{userId} and check_status = #{check}")
	@Results(
			id = "busUserFormHis",
			value = {
			    @Result(id = true, property = "id", column = "id"),
			    @Result(property = "userId", column = "user_id"),
			    @Result(property = "detailId", column = "detail_id"),
			    @Result(property = "formType", column = "form_type"),
			    @Result(property = "detail", column = "detail"),
			    @Result(property = "checkStatus", column = "check_status"),
			    @Result(property = "status", column = "status"),
			    @Result(property = "creater", column = "creater"),
			    @Result(property = "createTime", column = "create_time"),
			    @Result(property = "updater", column = "updater"),
			    @Result(property = "updateTime", column = "update_time")
		    }
		)
	BusUserForm findByUserIdAndCheck(@Param("userId")String userId, @Param("check")int check);
	
	@Select("select * from `bus_user_form_his` where check_status!=-1 and user_id=#{userId} order by create_time desc limit 1")
	@ResultMap("busUserFormHis")
	BusUserForm findByUserId(String userId);
	
	@Select("select * from `bus_user_form_his` where detail_id=#{id}")
	@ResultMap("busUserFormHis")
	BusUserForm findByDetailId(String id);
	
	@Select("select * from `bus_user_form_his` where id=#{id}")
	@ResultMap("busUserFormHis")
	BusUserForm findById(String id);
}
