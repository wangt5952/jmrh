package com.bz.xtcx.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.entity.User;
import com.bz.xtcx.manager.enums.UserTypeEnum;
import com.bz.xtcx.manager.mapper.BusUserDetailMapper;
import com.bz.xtcx.manager.mapper.BusUserFormHisMapper;
import com.bz.xtcx.manager.mapper.BusUserFormMapper;
import com.bz.xtcx.manager.mapper.BusUserMapper;
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
	public BusUserForm getUserDetail(boolean isDraft) {
		User user = this.getUser();
		BusUserForm form = null;
		if(isDraft) {
			form = busUserFormHisMapper.findByUserId(user.getUserId(), -1);
			if(form != null) {
				return form;
			}
		}
		form = busUserFormHisMapper.findByUserId(user.getUserId());
		return form;
	}
	
	@Override
	public VoResponse setUserDetail(String detail) {
		VoResponse voRes = new VoResponse();
		User user = this.getUser();
		int result = 0;
		BusUserForm form = busUserFormHisMapper.findByUserId(user.getUserId(), 0);
		if(form == null) {
			form = new BusUserForm();
			form.setDetail(detail);
			form.setCheckStatus(0);
			form.setUserId(user.getUserId());
			form.setCreater(user.getUserName());
			result = busUserFormHisMapper.insert(form);
		}else {
			form.setDetail(detail);
			form.setUpdater(user.getUserName());
			result = busUserFormHisMapper.update(form);
		}
		if(result == 0) {
			voRes.setFail(voRes);
			return voRes;
		}
		return voRes;
	}

	@Override
	public VoResponse addOrUpdateExpert(LibExpert e) {
		if(StringUtils.isEmpty(e.getUserId())) {
			LibExpert old = libExpertMapper.findByUserId(e.getUserId());
			if(old == null) {
				libExpertMapper.insert(e);
			}else {
				
			}
		}
		return null;
	}

	@Override
	public VoResponse addOrUpdateEnterprise(LibEnterprise e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoResponse addOrUpdateService(com.bz.xtcx.manager.entity.LibService e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoResponse addOrUpdateCollege(LibCollege e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveFormHis(String form) {
		User user = this.getUser();
		BusUserForm e = busUserFormHisMapper.findByUserId(user.getUserId(), -1);
		int result = 0;
		if(e == null) {
			e = new BusUserForm();
			e.setDetail(form);
			e.setCheckStatus(-1);
			e.setUserId(user.getUserId());
			e.setCreater(user.getUserName());
			result = busUserFormHisMapper.insert(e);
		}else {
			e.setDetail(form);
			e.setUpdater(user.getUserName());
			result = busUserFormHisMapper.update(e);
		}
		return result;
	}
	
	/**
	 * 表格数据保存到对应的资源库中
	 * @param form
	 * @param username
	 * @return
	 */
	VoResponse saveFormHisToLib(BusUserForm form) {
		VoResponse voRes = new VoResponse();
		JSONObject json = null;
		try {
			json = JSON.parseObject(form.getDetail());
		}catch (Exception e) {
			e.printStackTrace();
			voRes.setFail(voRes);
			voRes.setData(e.getMessage());
			return voRes;
		}
		//System.out.println(jsonObject.get("registerNature"));
		int result = 0;
		if(form.getFormType() == UserTypeEnum.Enterprise.key()) {
			LibEnterprise e = null;
			e = libEnterpriseMapper.findByUserId(form.getUserId());
			if(e == null) {
				e = new LibEnterprise();
				e.setUserId(form.getUserId());
				e.setEnterprise_name(json.getString("enterprise_name"));
				e.setRegistered_capital(json.getString("registered_capital"));
				e.setRegistered_type(json.getString("registered_type"));
				e.setIs_high_new_tech(json.getIntValue("is_high_new_tech"));
				e.setDomain(json.getString("domain"));
				e.setCountry(json.getString("country"));
				e.setCheck_status(0);
				e.setCreater(form.getCreater());
				result = libEnterpriseMapper.insert(e);
			}else{
				e.setEnterprise_name(json.getString("enterprise_name"));
				e.setRegistered_capital(json.getString("registered_capital"));
				e.setRegistered_type(json.getString("registered_type"));
				e.setIs_high_new_tech(json.getIntValue("is_high_new_tech"));
				e.setDomain(json.getString("domain"));
				e.setCountry(json.getString("country"));
				e.setUpdater(form.getCreater());
				libEnterpriseMapper.update(e);
			}
		}else if(form.getFormType() == UserTypeEnum.Service.key()) {
			com.bz.xtcx.manager.entity.LibService e = null;
			e = libServiceMapper.findByUserId(form.getUserId());
			if(e == null) {
				e = new com.bz.xtcx.manager.entity.LibService();
				e.setUserId(form.getUserId());
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
				e.setCreater(form.getCreater());
				result = libServiceMapper.insert(e);
			}else {
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
				e.setUpdater(form.getCreater());
				libServiceMapper.update(e);
			}
		}else if(form.getFormType() == UserTypeEnum.Expert.key()) {
			LibExpert e = null;
			e = libExpertMapper.findByUserId(form.getUserId());
			if(e == null) {
				e = new LibExpert();
				e.setUserId(form.getUserId());
				e.setName(json.getString("name"));
				e.setResearch_field(json.getString("research_field"));
				e.setResearch_area(json.getString("research_area"));
				e.setCountry(json.getString("country"));
				e.setProject_desc(json.getString("project_desc"));
				e.setSuccess_record(json.getString("success_record"));
				e.setWork_unit(json.getString("work_unit"));
				e.setCreater(form.getCreater());
				result = libExpertMapper.insert(e);
			}else {
				e.setName(json.getString("name"));
				e.setResearch_field(json.getString("research_field"));
				e.setResearch_area(json.getString("research_area"));
				e.setCountry(json.getString("country"));
				e.setProject_desc(json.getString("project_desc"));
				e.setSuccess_record(json.getString("success_record"));
				e.setWork_unit(json.getString("work_unit"));
				e.setUpdater(form.getCreater());
				result = libExpertMapper.update(e);
			}
			
		}else if(form.getFormType() == UserTypeEnum.College.key()) {
			LibCollege e = libCollegeMapper.findByUserId(form.getUserId());
			if(e == null) {
				e = new LibCollege();
				e.setUserId(form.getUserId());
				e.setName(json.getString("name"));
				e.setOrg_code(json.getString("org_code"));
				e.setCountry(json.getString("country"));
				e.setAdress(json.getString("adress"));
				e.setZip_code(json.getString("zip_code"));
				e.setUnit_url(json.getString("unit_url"));
				e.setMajor_platform(json.getString("major_platform"));
				e.setIntroduction(json.getString("introduction"));
				e.setCreater(form.getCreater());
				result = libCollegeMapper.insert(e);
			}else {
				e.setName(json.getString("name"));
				e.setOrg_code(json.getString("org_code"));
				e.setCountry(json.getString("country"));
				e.setAdress(json.getString("adress"));
				e.setZip_code(json.getString("zip_code"));
				e.setUnit_url(json.getString("unit_url"));
				e.setMajor_platform(json.getString("major_platform"));
				e.setIntroduction(json.getString("introduction"));
				e.setUpdater(form.getCreater());
				result = libCollegeMapper.update(e);
			}
		}
		voRes.setData(result);
		return voRes;
	}

	@Override
	public VoResponse checkUserDetail(String id) {
		VoResponse voRes = new VoResponse();
		BusUserForm form = busUserFormHisMapper.findById(id);
		int result = 0;
		if(form == null) {
			voRes.setNull(voRes);
		}else if(form.getCheckStatus() == 0){
			result = busUserFormHisMapper.updateCheck(1, id);
			if(result > 0) {
				voRes = this.saveFormHisToLib(form);
			}
		}
		return voRes;
	}
}
