package com.bz.xtcx.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.service.ILibService;
import com.bz.xtcx.manager.vo.VoResponse;

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
	
}
