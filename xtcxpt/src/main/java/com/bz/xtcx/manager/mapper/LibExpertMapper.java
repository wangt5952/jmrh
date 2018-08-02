package com.bz.xtcx.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.mapper.provider.LibExpertProvider;
import com.bz.xtcx.manager.vo.VoQuery;

public interface LibExpertMapper {

	@Insert("insert into `lib_expert`(id, user_id, form_id, name, id_number, address, country, cellphone, "
			+ "research_field, research_area, work_unit, success_record, project_desc, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{formId, jdbcType=VARCHAR},"
		    + " #{name, jdbcType=VARCHAR},"
		    + " #{code, jdbcType=VARCHAR},"
		    + " #{address, jdbcType=VARCHAR},"
		    + " #{country, jdbcType=VARCHAR},"
		    + " #{cellphone, jdbcType=VARCHAR},"
		    + " #{research_field, jdbcType=VARCHAR},"
		    + " #{research_area, jdbcType=VARCHAR},"
		    + " #{work_unit, jdbcType=VARCHAR},"
		    + " #{success_record, jdbcType=VARCHAR},"
		    + " #{project_desc, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(LibExpert e);
	
	@Delete("delete from `lib_expert` where id = #{id}")
	int del(String id);
	
	@Update("update `lib_expert` set name=#{name, jdbcType=VARCHAR},"
			+ " id_number=#{code, jdbcType=VARCHAR},"
			+ " address=#{address, jdbcType=VARCHAR},"
			+ " country=#{country, jdbcType=VARCHAR},"
			+ " cellphone=#{cellphone, jdbcType=VARCHAR},"
			+ " research_field=#{research_field, jdbcType=VARCHAR},"
			+ " research_area=#{research_area, jdbcType=VARCHAR},"
			+ " work_unit=#{work_unit, jdbcType=VARCHAR},"
			+ " success_record=#{success_record, jdbcType=VARCHAR},"
			+ " project_desc=#{project_desc, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where id=#{id}")
	int update(LibExpert e);
	
	@SelectProvider(type = LibExpertProvider.class, method = "findByCondition")
	@Results(
		id = "libExpert",
		value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "userId", column = "user_id"),
		    @Result(property = "name", column = "name"),
		    @Result(property = "code", column = "id_number"),
		    @Result(property = "address", column = "address"),
		    @Result(property = "country", column = "country"),
		    @Result(property = "cellphone", column = "cellphone"),
		    @Result(property = "research_field", column = "research_field"),
		    @Result(property = "research_area", column = "research_area"),
		    @Result(property = "work_unit", column = "work_unit"),
		    @Result(property = "success_record", column = "success_record"),
		    @Result(property = "project_desc", column = "project_desc"),
		    @Result(property = "form", column = "form_id", one = @One(select = "com.bz.xtcx.manager.mapper.BusUserFormHisMapper.findById") ),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibExpert> findByCondition(VoQuery e);
	
	@Select("select * from `lib_expert` where user_id = #{userId}")
	@ResultMap("libExpert")
	LibExpert findByUserId(String userId);
	
	@Select("select * from `lib_expert` where name = #{name} and id_number = #{code} limit 1")
	@ResultMap("libExpert")
	LibExpert findByNameAndCode(@Param("name")String name, @Param("code")String code);
	
	@Select("select * from `lib_expert` where id = #{id}")
	@ResultMap("libExpert")
	LibExpert findById(String id);
	
	@Select("select * from `lib_expert` where form_id = #{id}")
	@ResultMap("libExpert")
	LibExpert findByFormId(String id);
}
