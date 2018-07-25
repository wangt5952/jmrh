package com.bz.xtcx.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.mapper.provider.SysUserProvider;

public interface SysUserMapper {
	
	@Insert("insert into `sys_user`(user_id, user_name, password, cellphone, email, org_id, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userName, jdbcType=VARCHAR},"
		    + " #{password, jdbcType=VARCHAR},"
		    + " #{cellphone, jdbcType=VARCHAR},"
		    + " #{email, jdbcType=VARCHAR},"
		    + " #{orgId, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(SysUser user);
	
	@Delete("delete from `sys_user` where user_id = #{id}")
	int del(String id);
	
	@Update("update `sys_user` set password=#{password},"
			+ " cellphone=#{cellphone, jdbcType=VARCHAR},"
			+ " email=#{email, jdbcType=VARCHAR},"
			+ " org_id=#{orgId, jdbcType=VARCHAR},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where user_id=#{id}")
	int update(SysUser user);

	@SelectProvider(type = SysUserProvider.class, method = "findCount")
    int findCount(SysUser user);
	
	@SelectProvider(type = SysUserProvider.class, method = "findByCondition")
	@Results(
		id = "sysUser",
		value = {
		    @Result(id = true, property = "id", column = "user_id"),
		    @Result(property = "userName", column = "user_name"),
		    @Result(property = "password", column = "password"),
		    @Result(property = "cellphone", column = "cellphone"),
		    @Result(property = "email", column = "email"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time"),
		    @Result(property = "roles", column = "user_id", many = @Many(select = "com.bz.xtcx.manager.mapper.SysRoleMapper.findRolesByUserId") ),
		    @Result(property = "org", column = "org_id", one = @One(select = "com.bz.xtcx.manager.mapper.SysOrgMapper.findById") ),
	    }
	)
    List<SysUser> findByCondition(SysUser user);
	
	@Select("select * from `sys_user` where user_name = #{username} or email = #{username}")
    @ResultMap("sysUser")
	SysUser findByUserameOrEmail(String username);
	
	@Select("select * from `sys_user` where user_id = #{id}")
    @ResultMap("sysUser")
	SysUser findById(String id);
	
	
	/**
     * 新增用户的角色信息
     * @param user
     */
    @InsertProvider(type = SysUserProvider.class, method = "addUserRoles")
    int addUserRoles(SysUser user);

    /**
     * 根据用户ID，删除用户的角色信息
     * @param userId
     */
    @Delete("delete from `sys_user_role` where user_id=#{userId}")
    int delUserRoles(String userId);
}
