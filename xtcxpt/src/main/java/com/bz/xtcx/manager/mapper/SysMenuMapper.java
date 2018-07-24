package com.bz.xtcx.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.entity.SysRole;

public interface SysMenuMapper {

	@Insert("insert into `sys_menu`(menu_id, menu_name, menu_url, parent_id, menu_level, icon, method, sort_order, remark, status, creater)"
		    + " VALUES(#{id, jdbcType=VARCHAR},"
		    + " #{menuName, jdbcType=VARCHAR},"
		    + " #{menuUrl, jdbcType=VARCHAR},"
		    + " #{parentId, jdbcType=VARCHAR},"
		    + " #{menuLevel, jdbcType=INTEGER},"
		    + " #{icon, jdbcType=VARCHAR},"
		    + " #{method, jdbcType=VARCHAR},"
		    + " #{sortOrder, jdbcType=INTEGER},"
		    + " #{remark, jdbcType=VARCHAR},"
		    + " #{status, jdbcType=INTEGER},"
		    + " #{creater, jdbcType=VARCHAR})"
		    )
    @SelectKey(before = true, keyProperty = "id", resultType = String.class, statementType = StatementType.STATEMENT, statement="select uuid()")
	int insert(SysMenu menu);
	
	@Delete("delete from `sys_menu` where menu_id = ${id}")
	int del(String id);
	
	@Select("select * from `sys_menu` where ifnull(parent_id,'')='' order by sort_order")
    @Results(id = "sysMenu", 
		value = { 
		    @Result(id = true, property = "id", column = "menu_id"),
		    @Result(property = "menuName", column = "menu_name"),
		    @Result(property = "menuUrl", column = "menu_url"),
		    @Result(property = "parentId", column = "parent_id"),
		    @Result(property = "menuLevel", column = "menu_level"),
		    @Result(property = "icon", column = "icon"),
		    @Result(property = "method", column = "method"),
		    @Result(property = "sortOrder", column = "sort_order"),
		    @Result(property = "remark", column = "remark"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater"),
		    @Result(property = "updateTime", column = "update_time"),
		    @Result(property = "updater", column = "updater"),
		    @Result(property = "menus", column = "menu_id", many = @Many(select = "com.bz.xtcx.manager.mapper.SysMenuMapper.findByParentId"))
		}
    )
	List<SysMenu> findAll();
	
	@Select("select * from `sys_menu` where menu_id = #{id}")
	@ResultMap("sysMenu")
	SysMenu findById(String id);
	
	@Select("select * from `sys_menu` where parent_id = #{id} order by sort_order")
	@ResultMap("sysMenu")
	List<SysMenu> findByParentId(String id);
	
	@Select("select * from `sys_menu` where menu_id in(select menu_id from `sys_role_menu` where role_id = #{roleId})")
	@ResultMap("sysMenu")
    List<SysRole> findMenusByRoleId(String roleId);
}
