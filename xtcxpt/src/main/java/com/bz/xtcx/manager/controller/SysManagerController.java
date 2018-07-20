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

import com.bz.xtcx.manager.entity.SysOrg;
import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.service.ISysOrgService;
import com.bz.xtcx.manager.service.ISysUserService;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;


@RestController
@RequestMapping("xtcx/sysManager")
public class SysManagerController extends BaseController{

	@Autowired
	private ISysOrgService sysOrgService;
	
	@Autowired
	private ISysUserService sysUserService;
	
	@PostMapping("user")
	public Object addSysUser(@RequestBody SysUser user) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) return voRes;
		voRes = sysUserService.saveOrUpdate(user);
		return voRes;
	}
	
	@PostMapping("user/page")
	public Object getAllUsers(@RequestBody SysUser user, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<SysUser> info = sysUserService.getPageByCondition(user, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@GetMapping("org")
	public Object getAllOrgs() {
		VoResponse voRes = new VoResponse();
		voRes.setData(sysOrgService.getAll());
		return voRes;
	}
	
	@DeleteMapping("org")
	public Object del(@RequestParam("id") String id) {
		VoResponse voRes = getVoResponse();
		return voRes;
	}
	
	@PostMapping("org")
	public Object add(@RequestBody SysOrg org) {
		VoResponse voRes = sysOrgService.saveOrUpdate(org);
		return voRes;
	}
	
	@PutMapping("org")
	public Object update(@RequestBody SysOrg org) {
		VoResponse voRes = getVoResponse();
		return voRes;
	}
	

}
