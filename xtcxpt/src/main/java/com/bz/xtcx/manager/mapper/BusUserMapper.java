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
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.mapper.provider.SysUserProvider;

public interface BusUserMapper {
	
	@Insert("insert into `bus_user`(user_id, user_name, password, cellphone, email, user_type, name, id_number, check_status, status)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{userName, jdbcType=VARCHAR},"
		    + " #{password, jdbcType=VARCHAR},"
		    + " #{cellphone, jdbcType=VARCHAR},"
		    + " #{email, jdbcType=VARCHAR},"
		    + " #{userType, jdbcType=INTEGER},"
		    + " #{name, jdbcType=VARCHAR},"
		    + " #{idNumber, jdbcType=VARCHAR},"
		    + " #{checkStatus, jdbcType=INTEGER},"
		    + " #{status, jdbcType=INTEGER})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(BusUser user);
	
	@Delete("delete from `bus_user` where user_id = #{id}")
	int del(String id);
	
	@Update("update `bus_user` set password=#{password},"
			+ " cellphone=#{cellphone, jdbcType=VARCHAR},"
			+ " email=#{email, jdbcType=VARCHAR},"
			+ " check_status=#{checkStatus, jdbcType=INTEGER},"
			+ " status=#{status, jdbcType=INTEGER}"
			+ " where user_id=#{id}")
	int update(BusUser user);
	
	@SelectProvider(type = SysUserProvider.class, method = "findCount")
    int findCount(BusUser user);
	
	@SelectProvider(type = SysUserProvider.class, method = "findByCondition")
	@Results(
		id = "busUser",
		value = {
		    @Result(id = true, property = "id", column = "user_id"),
		    @Result(property = "userName", column = "user_name"),
		    @Result(property = "password", column = "password"),
		    @Result(property = "cellphone", column = "cellphone"),
		    @Result(property = "email", column = "email"),
		    @Result(property = "user_type", column = "userType"),
		    @Result(property = "name", column = "userType"),
		    @Result(property = "id_number", column = "idNumber"),
		    @Result(property = "check_status", column = "checkStatus"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updateTime", column = "update_time"),
		    @Result(property = "roles", column = "user_id", many = @Many(select = "com.bz.xtcx.manager.mapper.SysRoleMapper.findRolesByUserId") ) 
	    }
	)
    List<BusUser> findByCondition(BusUser user);
	
	@Select("select * from `bus_user` where email = #{email}")
    @ResultMap("busUser")
	BusUser findByEmail(String email);
	
	/**
     * 新增用户的角色信息
     * @param user
     */
    @InsertProvider(type = SysUserProvider.class, method = "addUserRoles")
    void addUserRoles(BusUser user);

    /**
     * 根据用户ID，删除用户的角色信息
     * @param userId
     */
    @Delete("delete from `sys_user_role` where user_id=#{userId}")
    void delUserRoles(String userId);
}
