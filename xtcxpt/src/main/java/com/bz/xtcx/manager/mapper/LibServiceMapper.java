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

import com.bz.xtcx.manager.entity.LibServices;
import com.bz.xtcx.manager.mapper.provider.LibServiceProvider;
import com.bz.xtcx.manager.vo.VoQuery;

public interface LibServiceMapper {

	@Insert("insert into `bus_user_service`(id, user_id, form_id, name, org_code, org_type, linkman, address, country, service_amount_last, service_amount_before, service_amount_previous,"
			+ " service_quantity_last, service_quantity_before, service_quantity_previous, honor, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{formId, jdbcType=VARCHAR},"
		    + " #{name, jdbcType=VARCHAR},"
		    + " #{code, jdbcType=VARCHAR},"
		    + " #{org_type, jdbcType=VARCHAR},"
		    + " #{linkman, jdbcType=VARCHAR},"
		    + " #{address, jdbcType=VARCHAR},"
		    + " #{country, jdbcType=VARCHAR},"
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
	int insert(LibServices e);
	
	@Delete("delete from `bus_user_service` where id = #{id}")
	int del(String id);
	
	@Update("update `bus_user_service` set name=#{name, jdbcType=VARCHAR},"
			+ " org_code=#{code, jdbcType=VARCHAR},"
			+ " org_type=#{org_type, jdbcType=VARCHAR},"
			+ " linkman=#{linkman, jdbcType=VARCHAR},"
			+ " #{address, jdbcType=VARCHAR},"
		    + " #{country, jdbcType=VARCHAR},"
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
	int update(LibServices e);
	
	@SelectProvider(type = LibServiceProvider.class, method = "findByCondition")
	@Results(
		id = "libService",
		value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "userId", column = "user_id"),
		    @Result(property = "name", column = "name"),
		    @Result(property = "code", column = "org_code"),
		    @Result(property = "org_type", column = "org_type"),
		    @Result(property = "linkman", column = "linkman"),
		    @Result(property = "address", column = "address"),
		    @Result(property = "country", column = "country"),
		    @Result(property = "service_amount_last", column = "service_amount_last"),
		    @Result(property = "service_amount_before", column = "service_amount_before"),
		    @Result(property = "service_amount_previous", column = "service_amount_previous"),
		    @Result(property = "service_quantity_last", column = "service_quantity_last"),
		    @Result(property = "service_quantity_before", column = "service_quantity_before"),
		    @Result(property = "service_quantity_previous", column = "service_quantity_previous"),
		    @Result(property = "honor", column = "honor"),
		    @Result(property = "form", column = "id", one = @One(select = "com.bz.xtcx.manager.mapper.BusUserFormHisMapper.findById") ),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibServices> findByCondition(VoQuery e);
	
	@Select("select * from `bus_user_service` where user_id = #{userId}")
	@ResultMap("libService")
	LibServices findByUserId(String userId);
	
	@Select("select * from `bus_user_service` where name = #{name} and org_code = #{code}")
	@ResultMap("libService")
	LibServices findByNameAndCode(@Param("name")String name, @Param("code")String code);
	
	@Select("select * from `bus_user_service` where id = #{id}")
	@ResultMap("libService")
	LibServices findById(String id);
}
