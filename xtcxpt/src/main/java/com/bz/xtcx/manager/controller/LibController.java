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

	@GetMapping("getForm")
	public Object getUserForm(@RequestParam("type") int type){
		VoResponse voRes = new VoResponse();
		voRes.setData(libService.getUserForm(type));
		return voRes;
	}

	@PostMapping("setForm")
	public Object setUserForm(@RequestBody BusUserForm form){
		VoResponse voRes = new VoResponse();
		voRes = libService.setUserForm(form);
		return voRes;
	}
	
	@PostMapping("setUserDetail")
	public Object setUserDetail(@RequestBody String detail){
		VoResponse voRes = new VoResponse();
		voRes = libService.setUserDetail(detail);
		return voRes;
	}
	
	@GetMapping("getUserDetail")
	public Object getUserDetail(@RequestParam("userId") String userId){
		VoResponse voRes = new VoResponse();
		voRes.setData(libService.getUserDetail(userId));
		return voRes;
	}
	
}
