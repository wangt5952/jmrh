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

import com.bz.xtcx.manager.entity.SysRole;
import com.bz.xtcx.manager.mapper.provider.SysRoleProvider;

public interface SysRoleMapper {

	@Insert("insert into `sys_role`(role_id, role_name, role_desc, role_type, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{roleName, jdbcType=VARCHAR},"
		    + " #{roleDesc, jdbcType=VARCHAR},"
		    + " #{roleType, jdbcType=INTEGER},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(SysRole role);
	
	@Delete("delete from `sys_role` where role_id = #{id}")
	int del(String id);
	
	@Update("update `sys_role` set role_name=#{roleName, jdbcType=VARCHAR},"
			+ " role_desc=#{roleDesc, jdbcType=VARCHAR},"
			+ " role_type=#{roleType, jdbcType=INTEGER},"
			+ " status=#{status, jdbcType=INTEGER},"
			+ " updater=#{updater, jdbcType=VARCHAR}"
			+ " where role_id=#{id}")
	int update(SysRole role);

	@SelectProvider(type = SysRoleProvider.class, method = "findCount")
    int findCount(SysRole role);
	
	@SelectProvider(type = SysRoleProvider.class, method = "findByCondition")
	@Results(
		id = "sysRole",
		value = {
		    @Result(id = true, property = "id", column = "role_id"),
		    @Result(property = "roleName", column = "role_name"),
		    @Result(property = "roleDesc", column = "role_desc"),
		    @Result(property = "roleType", column = "role_type"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "updateTime", column = "update_time"),
		    @Result(property = "menus", column = "role_id", many = @Many(select = "com.bz.xtcx.manager.mapper.SysMenuMapper.findMenusByRoleId") ) 
	    }
	)
    List<SysRole> findByCondition(SysRole role);
	
    @InsertProvider(type = SysRoleProvider.class, method = "addRoleMenus")
    void addRoleMenus(SysRole role);

    /**
     * 根据用户ID，删除用户的角色信息
     * @param userId
     */
    @Delete("delete from `sys_role_menu` where role_id=#{roleId}")
    void delRoleMenus(String roleId);
    
    @Select("select * from `sys_role` where role_id in(select role_id from `sys_user_role` where user_id = #{userId})")
    @ResultMap("sysRole")
    List<SysRole> findRolesByUserId(String userId);
}
