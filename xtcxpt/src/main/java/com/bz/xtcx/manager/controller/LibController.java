package com.bz.xtcx.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xtcx/lib")
public class LibController {

	@GetMapping("getBasicData")
	public Object  getBasicData(@RequestParam("userId") String userId) {
		String msg = "<enterprise></enterprise>";
		return msg;
	}
	
	

}
