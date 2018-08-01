package com.bz.xtcx.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.entity.SysOrg;
import com.bz.xtcx.manager.entity.SysRole;
import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.service.ISysMenuService;
import com.bz.xtcx.manager.service.ISysOrgService;
import com.bz.xtcx.manager.service.ISysRoleService;
import com.bz.xtcx.manager.service.ISysUserService;
import com.bz.xtcx.manager.vo.VoQuery;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("xtcx/sysManager")
public class SysManagerController extends BaseController{

	@Autowired
	private ISysOrgService sysOrgService;
	
	@Autowired
	private ISysUserService sysUserService;
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	@Autowired
	private ISysMenuService sysMenuService;
	
	@PostMapping("user/page")
	public Object getAllUsers(@RequestBody(required=false) VoQuery user, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<SysUser> info = sysUserService.getPageByCondition(user, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@PostMapping("user")
	public Object addSysUser(@RequestBody SysUser user) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) return voRes;
		user.setId(null);
		voRes = sysUserService.saveOrUpdate(user);
		return voRes;
	}
	
	@PutMapping("user")
	public Object updateSysUser(@RequestBody SysUser user) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(user.getId())) return voRes;
		voRes = sysUserService.saveOrUpdate(user);
		return voRes;
	}
	
	@PutMapping("sysuser/status")
	public Object updateSysUserStatus(@RequestBody SysUser user) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(user.getId()) || user.getStatus() == null ) return voRes;
		if(user.getStatus() == 0 || user.getStatus() == 1) {
			int result = sysUserService.updateUserStatus(user);
			if(result > 0) {
				voRes.setSuccess(voRes);
				voRes.setData(result);
			}
		}
		return voRes;
	}
	
	@DeleteMapping("user")
	public Object delUser(@RequestParam("id") String id) {
		VoResponse voRes = new VoResponse();
		voRes.setData(sysUserService.del(id));
		return voRes;
	}
	
	@GetMapping("org/tree")
	public Object getAllOrgs() {
		VoResponse voRes = new VoResponse();
		voRes.setData(sysOrgService.getAll());
		return voRes;
	}
	
	@DeleteMapping("org")
	public Object delOrg(@RequestParam("id") String id) {
		VoResponse voRes = new VoResponse();
		voRes.setData(sysOrgService.del(id));
		return voRes;
	}
	
	@PostMapping("org")
	public Object addOrg(@RequestBody SysOrg org) {
		VoResponse voRes = sysOrgService.saveOrUpdate(org);
		return voRes;
	}
	
	@PutMapping("org")
	public Object updateOrg(@RequestBody SysOrg org) {
		VoResponse voRes = sysOrgService.saveOrUpdate(org);
		return voRes;
	}
	
	@PostMapping("role")
	public Object addSysRole(@RequestBody SysRole role) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(role.getRoleName()) ) return voRes;
		voRes = sysRoleService.saveOrUpdate(role);
		return voRes;
	}
	
	@PostMapping("role/page")
	public Object getAllRoles(@RequestBody(required=false) VoQuery role, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<SysRole> info = sysRoleService.getPageByCondition(role, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@DeleteMapping("role")
	public Object delRole(@RequestParam("id") String id) {
		VoResponse voRes = new VoResponse();
		int result = sysRoleService.del(id);
		if(result > 0) return voRes;
		voRes.setFail(voRes);
		return voRes;
	}
	
	@GetMapping("menu/tree")
	public Object getAllMenus() {
		VoResponse voRes = new VoResponse();
		voRes.setData(sysMenuService.getAll());
		return voRes;
	}
	
	@DeleteMapping("menu")
	public Object delMenu(@RequestParam("id") String id) {
		VoResponse voRes = new VoResponse();
		int result = sysMenuService.del(id);
		if(result > 0) return voRes;
		voRes.setFail(voRes);
		return voRes;
	}
	
	@PostMapping("menu")
	public Object addMenu(@RequestBody SysMenu menu) {
		VoResponse voRes = sysMenuService.saveOrUpdate(menu);
		return voRes;
	}
	
	@PutMapping("menu")
	public Object updateMenu(@RequestBody SysMenu menu) {
		VoResponse voRes = sysMenuService.saveOrUpdate(menu);
		return voRes;
	}

	@PostMapping("bususer/page")
	public Object getAllBusUsers(@RequestBody(required=false) VoQuery user, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<BusUser> info = sysUserService.getPageBusUserByCondition(user, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@PutMapping("bususer/status")
	public Object updateBusUserStatus(@RequestBody BusUser user) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(user.getId()) || user.getStatus() == null ) return voRes;
		if(user.getStatus() == 0 || user.getStatus() == 1) {
			int result = sysUserService.updateUserStatus(user);
			if(result > 0) {
				voRes.setSuccess(voRes);
				voRes.setData(result);
			}
		}
		return voRes;
	}
}
