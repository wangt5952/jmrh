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

import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.mapper.provider.LibEnterpriseProvider;
import com.bz.xtcx.manager.vo.VoQuery;

public interface LibEnterpriseMapper {

	@Insert("insert into `bus_user_enterprise`(id, user_id, form_id, enterprise_name, business_license, lxname, lxzw, lxphone, lxemail, lpname, lpzw, lpphone, lpemail,"
			+ " registered_capital, registered_type, is_high_new_tech, domain, address, country, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{formId, jdbcType=VARCHAR},"
		    + " #{name, jdbcType=VARCHAR},"
		    + " #{code, jdbcType=VARCHAR},"
		    + " #{lxname, jdbcType=VARCHAR},"
		    + " #{lxzw, jdbcType=VARCHAR},"
		    + " #{lxphone, jdbcType=VARCHAR},"
		    + " #{lxemail, jdbcType=VARCHAR},"
		    + " #{lpname, jdbcType=VARCHAR},"
		    + " #{lpzw, jdbcType=VARCHAR},"
		    + " #{lpphone, jdbcType=VARCHAR},"
		    + " #{lpemail, jdbcType=VARCHAR},"
		    + " #{registered_capital, jdbcType=VARCHAR},"
		    + " #{registered_type, jdbcType=VARCHAR},"
		    + " #{is_high_new_tech, jdbcType=INTEGER},"
		    + " #{domain, jdbcType=VARCHAR},"
		    + " #{address, jdbcType=VARCHAR},"
		    + " #{country, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(LibEnterprise e);
	
	@Delete("delete from `bus_user_enterprise` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_enterprise` set enterprise_name=#{name, jdbcType=VARCHAR},"
			+ " business_license=#{code, jdbcType=VARCHAR},"
			+ " registered_capital=#{registered_capital, jdbcType=VARCHAR},"
			+ " registered_type=#{registered_type, jdbcType=VARCHAR},"
			+ " is_high_new_tech=#{is_high_new_tech, jdbcType=INTEGER},"
			+ " domain=#{domain, jdbcType=VARCHAR},"
			+ " address=#{address, jdbcType=VARCHAR},"
			+ " country=#{country, jdbcType=VARCHAR},"
			+ " lxname=#{lxname, jdbcType=VARCHAR},"
			+ " lxzw=#{lxzw, jdbcType=VARCHAR},"
			+ " lxphone=#{lxphone, jdbcType=VARCHAR},"
			+ " lxemail=#{lxemail, jdbcType=VARCHAR},"
			+ " lpname=#{lpname, jdbcType=VARCHAR},"
			+ " lpzw=#{lpzw, jdbcType=VARCHAR},"
			+ " lpphone=#{lpphone, jdbcType=VARCHAR},"
			+ " lpemail=#{lpemail, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where user_id=#{id}")
	int update(LibEnterprise e);
	
	@SelectProvider(type = LibEnterpriseProvider.class, method = "findByCondition")
	@Results(
		id = "libEnterprise",
		value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "userId", column = "user_id"),
		    @Result(property = "name", column = "enterprise_name"),
		    @Result(property = "code", column = "business_license"),
		    @Result(property = "lxname", column = "lxname"),
		    @Result(property = "lxzw", column = "lxzw"),
		    @Result(property = "lxphone", column = "lxphone"),
		    @Result(property = "lxemail", column = "lxemail"),
		    @Result(property = "lpname", column = "lpname"),
		    @Result(property = "lpzw", column = "lpzw"),
		    @Result(property = "lpphone", column = "lpphone"),
		    @Result(property = "lpemail", column = "lpemail"),
		    @Result(property = "registered_capital", column = "registered_capital"),
		    @Result(property = "registered_type", column = "registered_type"),
		    @Result(property = "is_high_new_tech", column = "is_high_new_tech"),
		    @Result(property = "domain", column = "domain"),
		    @Result(property = "address", column = "address"),
		    @Result(property = "country", column = "country"),
		    @Result(property = "form", column = "form_id", one = @One(select = "com.bz.xtcx.manager.mapper.BusUserFormHisMapper.findById") ),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibEnterprise> findByCondition(VoQuery e);
	
	@Select("select * from `bus_user_enterprise` where user_id = #{userId}")
	@ResultMap("libEnterprise")
	LibEnterprise findByUserId(String userId);
	
	@Select("select * from `bus_user_enterprise` where enterprise_name = #{name} and business_license = #{code}")
	@ResultMap("libEnterprise")
	LibEnterprise findByNameAndCode(@Param("name")String name, @Param("code")String code);
	
	@Select("select * from `bus_user_enterprise` where id = #{id}")
	@ResultMap("libEnterprise")
	LibEnterprise findById(String id);
}
