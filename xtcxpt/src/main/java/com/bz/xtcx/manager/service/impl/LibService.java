package com.bz.xtcx.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bz.xtcx.manager.entity.BusUserDetail;
import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.entity.User;
import com.bz.xtcx.manager.enums.UserTypeEnum;
import com.bz.xtcx.manager.mapper.BusUserDetailMapper;
import com.bz.xtcx.manager.mapper.BusUserFormHisMapper;
import com.bz.xtcx.manager.mapper.BusUserFormMapper;
import com.bz.xtcx.manager.mapper.LibCollegeMapper;
import com.bz.xtcx.manager.mapper.LibEnterpriseMapper;
import com.bz.xtcx.manager.mapper.LibExpertMapper;
import com.bz.xtcx.manager.mapper.LibServiceMapper;
import com.bz.xtcx.manager.service.ILibService;
import com.bz.xtcx.manager.vo.VoResponse;

@Service
public class LibService extends BaseService implements ILibService{

	@Autowired
	private LibEnterpriseMapper libEnterpriseMapper;
	
	@Autowired
	private LibServiceMapper libServiceMapper;
	
	@Autowired
	private LibCollegeMapper libCollegeMapper;
	
	@Autowired
	private LibExpertMapper libExpertMapper;
	
	@Autowired
	private BusUserFormHisMapper busUserFormHisMapper;
	
	@Autowired
	private BusUserFormMapper busUserFormMapper;
	
	@Autowired
	private BusUserDetailMapper busUserDetailMapper;
	
	@Override
	public BusUserForm getUserForm(int type) {
		BusUserForm from = busUserFormMapper.findByType(type);
		return from;
	}

	@Override
	public VoResponse setUserForm(BusUserForm form) {
		VoResponse voRes = new VoResponse();
		if(StringUtils.isEmpty(form.getId())) {
			//BusUserForm from = busUserFormMapper.findByType(form.getFormType());
		}
		User user = this.getUser();
		form.setCreater(user.getUserName());
		busUserFormMapper.insert(form);
		return voRes;
	}
	
	@Override
	public BusUserForm getUserDetail(String userId) {
		BusUserForm from = busUserFormMapper.findByUserId(userId);
		return from;
	}
	
	@Override
	public VoResponse setUserDetail(String detail) {
		VoResponse voRes = new VoResponse();
		JSONObject json = null;
		try {
			json = JSON.parseObject(detail);
		}catch (Exception e) {
			e.printStackTrace();
			voRes.setFail(voRes);
			voRes.setData(e.getMessage());
			return voRes;
		}
		//System.out.println(jsonObject.get("registerNature"));
		
		User user = this.getUser();
		int result = 0;
		if(!StringUtils.isEmpty(user.getUserId())) {
			if(StringUtils.isEmpty(json.getString("detailId"))) {//add
				BusUserForm form = new BusUserForm();
				form.setDetail(detail);
				form.setCheckStatus(0);
				form.setUserId(user.getUserId());
				form.setCreater(user.getUserName());
				result = busUserFormHisMapper.insert(form);
				if(result == 0) {
					voRes.setFail(voRes);
					return voRes;
				}
				if(user.getUserType() == UserTypeEnum.Enterprise.value()) {
					LibEnterprise e = new LibEnterprise();
					e.setEnterprise_name(json.getString("enterprise_name"));
					e.setRegistered_capital(json.getString("registered_capital"));
					e.setRegistered_type(json.getString("registered_type"));
					e.setIs_high_new_tech(json.getIntValue("is_high_new_tech"));
					e.setDomain(json.getString("domain"));
					e.setCountry(json.getString("country"));
					result = libEnterpriseMapper.insert(e);
					
				}else if(user.getUserType() == UserTypeEnum.Service.value()) {
					com.bz.xtcx.manager.entity.LibService e = new com.bz.xtcx.manager.entity.LibService();
					e.setName(json.getString("name"));
					e.setOrg_type(json.getString("org_type"));
					e.setLinkman(json.getString("linkman"));
					e.setService_amount_last(json.getIntValue("service_amount_last"));
					e.setService_amount_before(json.getIntValue("service_amount_before"));
					e.setService_amount_previous(json.getIntValue("service_amount_previous"));
					e.setService_quantity_last(json.getIntValue("service_quantity_last"));
					e.setService_quantity_before(json.getIntValue("service_quantity_before"));
					e.setService_quantity_previous(json.getIntValue("service_quantity_previous"));
					e.setHonor(json.getString("honor"));
					result = libServiceMapper.insert(e);
					
				}else if(user.getUserType() == UserTypeEnum.Expert.value()) {
					LibExpert e = new LibExpert();
					e.setName(json.getString("name"));
					e.setResearch_field(json.getString("research_field"));
					e.setResearch_area(json.getString("research_area"));
					e.setCountry(json.getString("country"));
					e.setProject_desc(json.getString("project_desc"));
					e.setSuccess_record(json.getString("success_record"));
					e.setWork_unit(json.getString("work_unit"));
					result = libExpertMapper.insert(e);
					
				}else if(user.getUserType() == UserTypeEnum.College.value()) {
					LibCollege e= new LibCollege();
					e.setName(json.getString("name"));
					e.setOrg_code(json.getString("org_code"));
					e.setCountry(json.getString("country"));
					e.setAdress(json.getString("adress"));
					e.setZip_code(json.getString("zip_code"));
					e.setUnit_url(json.getString("unit_url"));
					e.setMajor_platform(json.getString("major_platform"));
					e.setIntroduction(json.getString("introduction"));
					result = libCollegeMapper.insert(e);
				}
				if(result == 0) {
					voRes.setFail(voRes);
					return voRes;
				}
				
			}else {
				
			}
			return voRes;
		}
		voRes.setFail(voRes);
		return voRes;
	}
}
