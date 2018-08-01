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

import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.mapper.provider.LibCollegeProvider;
import com.bz.xtcx.manager.vo.VoQuery;

public interface LibCollegeMapper {

	@Insert("insert into `bus_user_college`(id, user_id, form_id, name, org_code, country, address, zip_code, unit_url, major_platform, introduction, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{formId, jdbcType=VARCHAR},"
		    + " #{name, jdbcType=VARCHAR},"
		    + " #{code, jdbcType=VARCHAR},"
		    + " #{country, jdbcType=VARCHAR},"
		    + " #{address, jdbcType=VARCHAR},"
		    + " #{zip_code, jdbcType=VARCHAR},"
		    + " #{unit_url, jdbcType=VARCHAR},"
		    + " #{major_platform, jdbcType=VARCHAR},"
		    + " #{introduction, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(LibCollege e);
	
	@Delete("delete from `bus_user_college` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_college` set name=#{name, jdbcType=VARCHAR},"
			+ " org_code=#{code, jdbcType=VARCHAR},"
			+ " country=#{country, jdbcType=VARCHAR},"
			+ " address=#{address, jdbcType=VARCHAR},"
			+ " zip_code=#{zip_code, jdbcType=VARCHAR},"
			+ " unit_url=#{unit_url, jdbcType=VARCHAR},"
			+ " major_platform=#{major_platform, jdbcType=VARCHAR},"
			+ " introduction=#{introduction, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where id=#{id}")
	int update(LibCollege e);
	
	@SelectProvider(type = LibCollegeProvider.class, method = "findByCondition")
	@Results(
		id = "libCollege",
		value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "userId", column = "user_id"),
		    @Result(property = "name", column = "name"),
		    @Result(property = "code", column = "org_code"),
		    @Result(property = "country", column = "country"),
		    @Result(property = "address", column = "address"),
		    @Result(property = "zip_code", column = "zip_code"),
		    @Result(property = "unit_url", column = "unit_url"),
		    @Result(property = "major_platform", column = "major_platform"),
		    @Result(property = "introduction", column = "introduction"),
		    @Result(property = "form", column = "id", one = @One(select = "com.bz.xtcx.manager.mapper.BusUserFormHisMapper.findById") ),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibCollege> findByCondition(VoQuery e);
	
	@Select("select * from `bus_user_college` where user_id = #{userId}")
	@ResultMap("libCollege")
	LibCollege findByUserId(String userId);
	
	@Select("select * from `bus_user_college` where name = #{name} and org_code = #{code} limit 1")
	@ResultMap("libCollege")
	LibCollege findByNameAndCode(@Param("name")String name, @Param("code")String code);
	
	@Select("select * from `bus_user_college` where id = #{id}")
	@ResultMap("libCollege")
	LibCollege findById(String id);
	
	@Select("select * from `bus_user_college` where form_id = #{id}")
	@ResultMap("libCollege")
	LibCollege findByFormId(String id);
}
