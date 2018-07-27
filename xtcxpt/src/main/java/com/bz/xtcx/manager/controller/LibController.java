package com.bz.xtcx.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.entity.LibServices;
import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.service.ILibService;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("xtcx/lib")
public class LibController {
	
	@Autowired
	private ILibService libService;

	/**
	 * 获取表格基础数据
	 * @param type
	 * @return
	 */
	@GetMapping("getForm")
	public Object getUserForm(@RequestParam("type") int type){
		VoResponse voRes = new VoResponse();
		voRes.setData(libService.getUserForm(type));
		return voRes;
	}

	/**
	 * 设置表格基础数据
	 * @param form
	 * @return
	 */
	@PostMapping("setForm")
	public Object setUserForm(@RequestBody BusUserForm form){
		VoResponse voRes = new VoResponse();
		voRes = libService.setUserForm(form);
		return voRes;
	}
	
	/**
	 * 用户保存，提交资源库信息
	 * @param detail
	 * @param isDraft
	 * @return
	 */
	@PostMapping("setUserDetail")
	public Object setUserDetail(@RequestBody String detail, @RequestParam(value="isDraft", required=false) boolean isDraft){
		VoResponse voRes = new VoResponse();
		if(isDraft) {
			voRes.setData(libService.saveFormHis(detail));
		}else {
			voRes = libService.setUserDetail(detail);
		}
		return voRes;
	}
	
	/**
	 * 用户获取资源库信息
	 * @param userId
	 * @return
	 */
	@GetMapping("getUserDetail")
	public Object getUserDetail(@RequestParam(value="isDraft", required=false) boolean isDraft){
		VoResponse voRes = new VoResponse();
		voRes.setData(libService.getUserDetail(isDraft));
		return voRes;
	}
	
	/**
	 * 管理员审核资源库信息
	 * @param id
	 * @return
	 */
	@GetMapping("checkUserDetail")
	public Object checkUserDetail(@RequestParam("id") String id){
		VoResponse voRes = new VoResponse();
		voRes = libService.checkUserDetail(id);
		return voRes;
	}
	
	@PostMapping("expert/page")
	public Object getAllExperts(@RequestBody(required=false) LibExpert lib, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<LibExpert> info = libService.getExpertPageByCondition(lib, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@PostMapping("college/page")
	public Object getAllColleges(@RequestBody(required=false) LibCollege lib, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<LibCollege> info = libService.getCollegePageByCondition(lib, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@PostMapping("enterprise/page")
	public Object getAllEnterprises(@RequestBody(required=false) LibEnterprise lib, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<LibEnterprise> info = libService.getEnterprisePageByCondition(lib, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@PostMapping("services/page")
	public Object getAllServices(@RequestBody(required=false) LibServices lib, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<LibServices> info = libService.getServicePageByCondition(lib, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
}
