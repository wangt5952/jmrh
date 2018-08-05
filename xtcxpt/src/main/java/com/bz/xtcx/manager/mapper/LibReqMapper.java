package com.bz.xtcx.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.LibRequirements;
import com.bz.xtcx.manager.mapper.provider.LibReqProvider;
import com.bz.xtcx.manager.vo.VoQuery;

public interface LibReqMapper {

	@Insert("insert into `lib_requirements`(id, user_id, form_id, req_name, fzname, fzzw, fzphone, fzemail,  fzname2, fzzw2, fzphone2, fzemail2,"
			+ " domain, req_desc, req_new, req_other, req_background, req_get, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{formId, jdbcType=VARCHAR},"
		    + " #{reqName, jdbcType=VARCHAR},"
		    + " #{fzname, jdbcType=VARCHAR},"
		    + " #{fzzw, jdbcType=VARCHAR},"
		    + " #{fzphone, jdbcType=VARCHAR},"
		    + " #{fzemail, jdbcType=VARCHAR},"
		    + " #{fzname2, jdbcType=VARCHAR},"
		    + " #{fzzw2, jdbcType=VARCHAR},"
		    + " #{fzphone2, jdbcType=VARCHAR},"
		    + " #{fzemail2, jdbcType=VARCHAR},"
		    + " #{domain, jdbcType=VARCHAR},"
		    + " #{reqDesc, jdbcType=VARCHAR},"
		    + " #{reqNew, jdbcType=VARCHAR},"
		    + " #{reqOther, jdbcType=VARCHAR},"
		    + " #{reqBack, jdbcType=VARCHAR},"
		    + " #{reqGet, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(LibRequirements e);
	
	@Delete("delete from `lib_requirements` where id = #{id}")
	int del(String id);
	
	@Update("update `lib_requirements` set req_name=#{reqName, jdbcType=VARCHAR},"
			+ " fzname=#{fzname, jdbcType=VARCHAR},"
			+ " fzzw=#{fzzw, jdbcType=VARCHAR},"
			+ " fzphone=#{fzphone, jdbcType=VARCHAR},"
			+ " fzemail=#{fzemail, jdbcType=VARCHAR},"
			+ " fzname2=#{fzname2, jdbcType=VARCHAR},"
			+ " fzzw2=#{fzzw2, jdbcType=VARCHAR},"
			+ " fzphone2=#{fzphone2, jdbcType=VARCHAR},"
			+ " fzemail2=#{fzemail2, jdbcType=VARCHAR},"
			+ " domain=#{domain, jdbcType=VARCHAR},"
			+ " req_desc=#{reqDesc, jdbcType=VARCHAR},"
			+ " req_new=#{reqNew, jdbcType=VARCHAR},"
			+ " req_other=#{reqOther, jdbcType=VARCHAR},"
			+ " req_background=#{reqBack, jdbcType=VARCHAR},"
			+ " req_get=#{reqGet, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where id=#{id}")
	int update(LibRequirements e);
	
	@SelectProvider(type = LibReqProvider.class, method = "findByCondition")
	@Results(
		id = "libCollege",
		value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "userId", column = "user_id"),
		    @Result(property = "reqName", column = "req_name"),
		    @Result(property = "fzname", column = "fzname"),
		    @Result(property = "fzzw", column = "fzzw"),
		    @Result(property = "fzphone", column = "fzphone"),
		    @Result(property = "fzemail", column = "fzemail"),
		    @Result(property = "fzname2", column = "fzname2"),
		    @Result(property = "fzzw2", column = "fzzw2"),
		    @Result(property = "fzphone2", column = "fzphone2"),
		    @Result(property = "fzemail2", column = "fzemail2"),
		    @Result(property = "domain", column = "domain"),
		    @Result(property = "reqDesc", column = "req_desc"),
		    @Result(property = "reqNew", column = "req_new"),
		    @Result(property = "reqOther", column = "req_other"),
		    @Result(property = "reqBack", column = "req_background"),
		    @Result(property = "reqGet", column = "req_get"),
		    @Result(property = "form", column = "form_id", one = @One(select = "com.bz.xtcx.manager.mapper.BusUserFormHisMapper.findById") ),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibRequirements> findByCondition(VoQuery e);
	
	@Select("select * from `lib_requirements` where user_id = #{userId}")
	@ResultMap("libCollege")
	LibRequirements findByUserId(String userId);
	
	@Select("select * from `lib_requirements` where id = #{id}")
	@ResultMap("libCollege")
	LibRequirements findById(String id);
	
	@Select("select * from `lib_requirements` where form_id = #{id}")
	@ResultMap("libCollege")
	LibRequirements findByFormId(String id);
}
