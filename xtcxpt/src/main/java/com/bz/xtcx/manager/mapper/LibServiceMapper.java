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

import com.bz.xtcx.manager.entity.LibService;
import com.bz.xtcx.manager.mapper.provider.LibServiceProvider;

public interface LibServiceMapper {

	@Insert("insert into `bus_user_service`(id, user_id, name, org_type, linkman, service_amount_last, service_amount_before, service_amount_previous,"
			+ " service_quantity_last, service_quantity_before, service_quantity_previous, honor, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{name, jdbcType=VARCHAR},"
		    + " #{org_type, jdbcType=VARCHAR},"
		    + " #{linkman, jdbcType=VARCHAR},"
		    + " #{service_amount_last, jdbcType=INTEGER},"
		    + " #{service_amount_before, jdbcType=INTEGER},"
		    + " #{service_amount_previous, jdbcType=INTEGER},"
		    + " #{service_quantity_last, jdbcType=INTEGER},"
		    + " #{service_quantity_before, jdbcType=INTEGER},"
		    + " #{service_quantity_previous, jdbcType=INTEGER},"
		    + " #{honor, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(LibService e);
	
	@Delete("delete from `bus_user_service` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_service` set name=#{name, jdbcType=VARCHAR},"
			+ " org_type=#{org_type, jdbcType=VARCHAR},"
			+ " linkman=#{linkman, jdbcType=VARCHAR},"
			+ " service_amount_last=#{service_amount_last, jdbcType=INTEGER},"
			+ " service_amount_before=#{service_amount_before, jdbcType=INTEGER},"
			+ " service_amount_previous=#{service_amount_previous, jdbcType=INTEGER},"
			+ " service_quantity_last=#{service_quantity_last, jdbcType=INTEGER},"
			+ " service_quantity_before=#{service_quantity_before, jdbcType=INTEGER},"
			+ " service_quantity_previous=#{service_quantity_previous, jdbcType=INTEGER},"
			+ " honor=#{honor, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where user_id=#{id}")
	int update(LibService e);
	
	@SelectProvider(type = LibServiceProvider.class, method = "findByCondition")
	@Results(
		id = "libService",
		value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "userId", column = "user_id"),
		    @Result(property = "name", column = "name"),
		    @Result(property = "org_type", column = "org_type"),
		    @Result(property = "linkman", column = "linkman"),
		    @Result(property = "service_amount_last", column = "service_amount_last"),
		    @Result(property = "service_amount_before", column = "service_amount_before"),
		    @Result(property = "service_amount_previous", column = "service_amount_previous"),
		    @Result(property = "service_quantity_last", column = "service_quantity_last"),
		    @Result(property = "service_quantity_before", column = "service_quantity_before"),
		    @Result(property = "service_quantity_previous", column = "service_quantity_previous"),
		    @Result(property = "honor", column = "honor"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibService> findByCondition(LibService e);
}
