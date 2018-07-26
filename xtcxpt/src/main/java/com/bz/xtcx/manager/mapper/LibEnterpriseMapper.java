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

import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.mapper.provider.LibEnterpriseProvider;

public interface LibEnterpriseMapper {

	@Insert("insert into `bus_user_enterprise`(id, user_id, enterprise_name, registered_capital, registered_type, is_high_new_tech, domain, country, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{enterprise_name, jdbcType=VARCHAR},"
		    + " #{registered_capital, jdbcType=VARCHAR},"
		    + " #{registered_type, jdbcType=VARCHAR},"
		    + " #{is_high_new_tech, jdbcType=INTEGER},"
		    + " #{domain, jdbcType=VARCHAR},"
		    + " #{country, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(LibEnterprise e);
	
	@Delete("delete from `bus_user_enterprise` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_enterprise` set enterprise_name=#{enterprise_name, jdbcType=VARCHAR},"
			+ " registered_capital=#{registered_capital, jdbcType=VARCHAR},"
			+ " registered_type=#{registered_type, jdbcType=VARCHAR},"
			+ " is_high_new_tech=#{is_high_new_tech, jdbcType=INTEGER},"
			+ " domain=#{domain, jdbcType=VARCHAR},"
			+ " country=#{country, jdbcType=VARCHAR},"
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
		    @Result(property = "enterprise_name", column = "enterprise_name"),
		    @Result(property = "registered_capital", column = "registered_capital"),
		    @Result(property = "registered_type", column = "registered_type"),
		    @Result(property = "is_high_new_tech", column = "is_high_new_tech"),
		    @Result(property = "domain", column = "domain"),
		    @Result(property = "country", column = "country"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibEnterprise> findByCondition(LibEnterprise e);
}
