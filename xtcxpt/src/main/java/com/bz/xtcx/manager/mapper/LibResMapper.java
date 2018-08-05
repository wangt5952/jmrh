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

import com.bz.xtcx.manager.entity.LibResults;
import com.bz.xtcx.manager.mapper.provider.LibReqProvider;
import com.bz.xtcx.manager.vo.VoQuery;

public interface LibResMapper {

	@Insert("insert into `lib_results`(id, user_id, form_id, results_name, fzname, fzphone, fzemail, fzname2, fzphone2, fzemail2,"
			+ " domain, results_desc, new_idea, sq_patent, have_patent, other_patent, patents, stage, cooperation, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userId, jdbcType=VARCHAR},"
		    + " #{formId, jdbcType=VARCHAR},"
		    + " #{resName, jdbcType=VARCHAR},"
		    + " #{fzname, jdbcType=VARCHAR},"
		    + " #{fzphone, jdbcType=VARCHAR},"
		    + " #{fzemail, jdbcType=VARCHAR},"
		    + " #{fzname2, jdbcType=VARCHAR},"
		    + " #{fzphone2, jdbcType=VARCHAR},"
		    + " #{fzemail2, jdbcType=VARCHAR},"
		    + " #{domain, jdbcType=VARCHAR},"
		    + " #{resDesc, jdbcType=VARCHAR},"
		    + " #{newIdea, jdbcType=VARCHAR},"
		    + " #{sqPatent, jdbcType=VARCHAR},"
		    + " #{havePatent, jdbcType=VARCHAR},"
		    + " #{otherPatent, jdbcType=VARCHAR},"
		    + " #{patents, jdbcType=VARCHAR},"
		    + " #{stage, jdbcType=VARCHAR},"
		    + " #{cooperation, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(LibResults e);
	
	@Delete("delete from `lib_results` where id = #{id}")
	int del(String id);
	
	@Update("update `lib_results` set results_name=#{resName, jdbcType=VARCHAR},"
			+ " fzname=#{fzname, jdbcType=VARCHAR},"
			+ " fzphone=#{fzphone, jdbcType=VARCHAR},"
			+ " fzemail=#{fzemail, jdbcType=VARCHAR},"
			+ " fzname2=#{fzname2, jdbcType=VARCHAR},"
			+ " fzphone2=#{fzphone2, jdbcType=VARCHAR},"
			+ " fzemail2=#{fzemail2, jdbcType=VARCHAR},"
			+ " domain=#{domain, jdbcType=VARCHAR},"
			+ " results_desc=#{resDesc, jdbcType=VARCHAR},"
			+ " new_idea=#{newIdea, jdbcType=VARCHAR},"
			+ " sq_patent=#{sqPatent, jdbcType=VARCHAR},"
			+ " have_patent=#{havePatent, jdbcType=VARCHAR},"
			+ " other_patent=#{otherPatent, jdbcType=VARCHAR},"
			+ " patents=#{patents, jdbcType=VARCHAR},"
			+ " stage=#{stage, jdbcType=VARCHAR},"
			+ " cooperation=#{cooperation, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where id=#{id}")
	int update(LibResults e);
	
	@SelectProvider(type = LibReqProvider.class, method = "findByCondition")
	@Results(
		id = "libCollege",
		value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "userId", column = "user_id"),
		    @Result(property = "resName", column = "results_name"),
		    @Result(property = "fzname", column = "fzname"),
		    @Result(property = "fzphone", column = "fzphone"),
		    @Result(property = "fzemail", column = "fzemail"),
		    @Result(property = "fzname2", column = "fzname2"),
		    @Result(property = "fzphone2", column = "fzphone2"),
		    @Result(property = "fzemail2", column = "fzemail2"),
		    @Result(property = "domain", column = "domain"),
		    @Result(property = "resDesc", column = "results_desc"),
		    @Result(property = "newIdea", column = "new_idea"),
		    @Result(property = "sqPatent", column = "sq_patent"),
		    @Result(property = "havePatent", column = "have_patent"),
		    @Result(property = "otherPatent", column = "other_patent"),
		    @Result(property = "patents", column = "patents"),
		    @Result(property = "stage", column = "stage"),
		    @Result(property = "cooperation", column = "cooperation"),
		    @Result(property = "form", column = "form_id", one = @One(select = "com.bz.xtcx.manager.mapper.BusUserFormHisMapper.findById") ),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time")
	    }
	)
    List<LibResults> findByCondition(VoQuery e);
	
	@Select("select * from `lib_results` where user_id = #{userId}")
	@ResultMap("libCollege")
	LibResults findByUserId(String userId);
	
	@Select("select * from `lib_results` where id = #{id}")
	@ResultMap("libCollege")
	LibResults findById(String id);
	
	@Select("select * from `lib_results` where form_id = #{id}")
	@ResultMap("libCollege")
	LibResults findByFormId(String id);
}
