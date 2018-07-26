package com.bz.xtcx.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bz.xtcx.manager.mapper.LibCollegeMapper;
import com.bz.xtcx.manager.mapper.LibEnterpriseMapper;
import com.bz.xtcx.manager.mapper.LibExpertMapper;
import com.bz.xtcx.manager.mapper.LibServiceMapper;

@Service
public class LibService {

	@Autowired
	private LibEnterpriseMapper libEnterpriseMapper;
	
	@Autowired
	private LibServiceMapper libServiceMapper;
	
	@Autowired
	private LibCollegeMapper libCollegeMapper;
	
	@Autowired
	private LibExpertMapper libExpertMapper;
	
	
}
