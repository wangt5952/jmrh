package com.bz.xtcx.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.mapper.provider.LibExpertProvider;

public interface LibExpertMapper {

	@Insert("insert into `bus_user_expert`(id, user_id, name, research_field, country, research_area, work_unit, success_record, project_desc, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{name, jdbcType=VARCHAR},"
		    + " #{research_field, jdbcType=VARCHAR},"
		    + " #{country, jdbcType=VARCHAR},"
		    + " #{research_area, jdbcType=INTEGER},"
		    + " #{work_unit, jdbcType=VARCHAR},"
		    + " #{success_record, jdbcType=VARCHAR},"
		    + " #{project_desc, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(LibExpert e);
	
	@Delete("delete from `bus_user_expert` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_expert` set name=#{name, jdbcType=VARCHAR},"
			+ " research_field=#{research_field, jdbcType=VARCHAR},"
			+ " country=#{country, jdbcType=VARCHAR},"
			+ " research_area=#{research_area, jdbcType=VARCHAR},"
			+ " work_unit=#{work_unit, jdbcType=VARCHAR},"
			+ " success_record=#{success_record, jdbcType=VARCHAR},"
			+ " project_desc=#{project_desc, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where user_id=#{id}")
	int update(LibExpert e);
	
	@SelectProvider(type = LibExpertProvider.class, method = "findByCondition")
	@Results(
		id = "libExpert",
		value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "userId", column = "user_id"),
		    @Result(property = "name", column = "name"),
		    @Result(property = "research_field", column = "research_field"),
		    @Result(property = "country", column = "country"),
		    @Result(property = "research_area", column = "research_area"),
		    @Result(property = "work_unit", column = "work_unit"),
		    @Result(property = "success_record", column = "success_record"),
		    @Result(property = "project_desc", column = "project_desc"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibExpert> findByCondition(LibExpert e);
}
