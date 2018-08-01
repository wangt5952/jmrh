package com.bz.xtcx.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.entity.LibServices;
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
import com.bz.xtcx.manager.vo.VoQuery;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
	
	@Autowired
	private BusUserMapper busUserMapper;
	
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
		form.setCreater(getUserName());
		busUserFormMapper.insert(form);
		return voRes;
	}
	
	@Override
	public BusUserForm getUserDetail(boolean isDraft) {
		User user = this.getUser();
		BusUserForm form = null;
		if(isDraft) {
			form = busUserFormHisMapper.findByUserIdAndCheck(user.getUserId(), -1);
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
		BusUserForm form = busUserFormHisMapper.findByUserIdAndCheck(user.getUserId(), 0);
		if(form == null) {
			form = new BusUserForm();
			form.setFormType(user.getUserType());
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
	public VoResponse delUserDetail(String id, String type) {
		VoResponse voRes = new VoResponse();
		if(type.equals("1")) {
			LibExpert lib = libExpertMapper.findById(id);
			if(lib != null) {
				libExpertMapper.del(id);
				busUserFormHisMapper.del(lib.getFormId());
			}
		}else if(type.equals("2")) {
			LibEnterprise lib = libEnterpriseMapper.findById(id);
			if(lib != null) {
				libEnterpriseMapper.del(id);
				busUserFormHisMapper.del(lib.getFormId());
			}
		}else if(type.equals("3")) {
			LibServices lib = libServiceMapper.findById(id);
			if(lib != null) {
				libServiceMapper.del(id);
				busUserFormHisMapper.del(lib.getFormId());
			}
		}else if(type.equals("4")) {
			LibCollege lib = libCollegeMapper.findById(id);
			if(lib != null) {
				libCollegeMapper.del(id);
				busUserFormHisMapper.del(lib.getFormId());
			}
		}
		return voRes;
	}
	
	@Override
	public VoResponse setUserDetail(BusUserForm form) {
		VoResponse voRes = new VoResponse();
		int result = 0;
		if(form.getId() == null) {
			form.setCreater(getUserName());
			form.setCheckStatus(1);
			form.setUserId(null);
			result = busUserFormHisMapper.insert(form);
			
		}else {
			BusUserForm old = busUserFormHisMapper.findById(form.getId());
			old.setDetail(form.getDetail());
			old.setUpdater(getUserName());
			old.setCheckStatus(1);
			result = busUserFormHisMapper.update(old);
		}
		if(result <= 0) {
			voRes.setFail(voRes);
			return voRes;
		}
		voRes = this.saveFormHisToLib(form);
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
	public VoResponse addOrUpdateService(com.bz.xtcx.manager.entity.LibServices e) {
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
		BusUserForm e = busUserFormHisMapper.findByUserIdAndCheck(user.getUserId(), -1);
		int result = 0;
		if(e == null) {
			e = new BusUserForm();
			e.setDetail(form);
			e.setFormType(user.getUserType());
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
	
	int saveOrUpdate(LibEnterprise e) {
		int result;
		if(e.getId() == null) {
			result = libEnterpriseMapper.insert(e);
		}else {
			result = libEnterpriseMapper.update(e);
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
		if(form.getFormType() == UserTypeEnum.Enterprise.key()) {//userType=2
			LibEnterprise e = null;
			e = libEnterpriseMapper.findById(form.getId());
			if(e == null) {
				e = new LibEnterprise();
				e.setName(json.getString("enterprise_name"));
				e.setCode(json.getString("code"));
				if(libEnterpriseMapper.findByNameAndCode(e.getName(), e.getCode()) != null) {
					voRes.setFail(voRes);
					voRes.setMessage("企业已经存在");
					return voRes;
				}
				e.setUserId(form.getUserId());
				e.setFormId(form.getId());
				e.setCreater(form.getCreater());
			}else{
				e.setUpdater(form.getUpdater());
			}
			e.setRegistered_capital(json.getString("registered_capital"));
			e.setRegistered_type(json.getString("registered_type"));
			e.setIs_high_new_tech(json.getString("is_high_new_tech"));
			e.setDomain(json.getString("domain"));
			e.setCountry(json.getString("country"));
			e.setLxname(json.getString("lxname"));
			e.setLxzw(json.getString("lxzw"));
			e.setLxphone(json.getString("lxphone"));
			e.setLxemail(json.getString("lxemail"));
			e.setLpname(json.getString("lpname"));
			e.setLpzw(json.getString("lpzw"));
			e.setLpphone(json.getString("lpphone"));
			e.setLpemail(json.getString("lpemail"));
			result = this.saveOrUpdate(e);
			
		}else if(form.getFormType() == UserTypeEnum.Service.key()) {
			LibServices e = null;
			e = libServiceMapper.findById(form.getId());
			if(e == null) {
				e = new com.bz.xtcx.manager.entity.LibServices();
				e.setUserId(form.getUserId());
				e.setFormId(form.getId());
				e.setName(json.getString("name"));
				e.setOrg_type(json.getString("org_type"));
				e.setLinkman(json.getString("linkman"));
				e.setService_amount_last(json.getString("service_amount_last"));
				e.setService_amount_before(json.getString("service_amount_before"));
				e.setService_amount_previous(json.getString("service_amount_previous"));
				e.setService_quantity_last(json.getString("service_quantity_last"));
				e.setService_quantity_before(json.getString("service_quantity_before"));
				e.setService_quantity_previous(json.getString("service_quantity_previous"));
				e.setHonor(json.getString("honor"));
				e.setCreater(form.getCreater());
				if(libServiceMapper.findByNameAndCode(e.getName(), e.getCode()) != null) {
					voRes.setFail(voRes);
					voRes.setMessage("服务机构已经存在");
					return voRes;
				}
				result = libServiceMapper.insert(e);
			}else {
				e.setName(json.getString("name"));
				e.setOrg_type(json.getString("org_type"));
				e.setLinkman(json.getString("linkman"));
				e.setService_amount_last(json.getString("service_amount_last"));
				e.setService_amount_before(json.getString("service_amount_before"));
				e.setService_amount_previous(json.getString("service_amount_previous"));
				e.setService_quantity_last(json.getString("service_quantity_last"));
				e.setService_quantity_before(json.getString("service_quantity_before"));
				e.setService_quantity_previous(json.getString("service_quantity_previous"));
				e.setHonor(json.getString("honor"));
				e.setUpdater(form.getUpdater());
				result = libServiceMapper.update(e);
			}
		}else if(form.getFormType() == UserTypeEnum.Expert.key()) {
			LibExpert e = null;
			e = libExpertMapper.findById(form.getId());
			if(e == null) {
				e = new LibExpert();
				e.setUserId(form.getUserId());
				e.setFormId(form.getId());
				e.setName(json.getString("name"));
				e.setCode(json.getString("id"));
				e.setResearch_field(json.getString("research_field"));
				e.setResearch_area(json.getString("research_area"));
				e.setCountry(json.getString("country"));
				e.setProject_desc(json.getString("project_desc"));
				e.setSuccess_record(json.getString("success_record"));
				e.setWork_unit(json.getString("work_unit"));
				e.setCreater(form.getCreater());
				if(libExpertMapper.findByNameAndCode(e.getName(), e.getCode()) != null) {
					voRes.setFail(voRes);
					voRes.setMessage("专家已经存在");
					return voRes;
				}
				result = libExpertMapper.insert(e);
			}else {
				e.setName(json.getString("name"));
				e.setCode(json.getString("id"));
				e.setResearch_field(json.getString("research_field"));
				e.setResearch_area(json.getString("research_area"));
				e.setCountry(json.getString("country"));
				e.setProject_desc(json.getString("project_desc"));
				e.setSuccess_record(json.getString("success_record"));
				e.setWork_unit(json.getString("work_unit"));
				e.setUpdater(form.getUpdater());
				result = libExpertMapper.update(e);
			}
		}else if(form.getFormType() == UserTypeEnum.College.key()) {
			LibCollege e = null;
			e = libCollegeMapper.findById(form.getId());
			if(e == null) {
				e = new LibCollege();
				e.setUserId(form.getUserId());
				e.setFormId(form.getId());
				e.setName(json.getString("name"));
				e.setCode(json.getString("org_code"));
				e.setCountry(json.getString("country"));
				e.setAddress(json.getString("adress"));
				e.setZip_code(json.getString("zip_code"));
				e.setUnit_url(json.getString("unit_url"));
				e.setMajor_platform(json.getString("major_platform"));
				e.setIntroduction(json.getString("introduction"));
				e.setCreater(form.getCreater());
				if(libExpertMapper.findByNameAndCode(e.getName(), e.getCode()) != null) {
					voRes.setFail(voRes);
					voRes.setMessage("高校院所已经存在");
					return voRes;
				}
				result = libCollegeMapper.insert(e);
			}else {
				e.setName(json.getString("name"));
				e.setCode(json.getString("org_code"));
				e.setCountry(json.getString("country"));
				e.setAddress(json.getString("adress"));
				e.setZip_code(json.getString("zip_code"));
				e.setUnit_url(json.getString("unit_url"));
				e.setMajor_platform(json.getString("major_platform"));
				e.setIntroduction(json.getString("introduction"));
				e.setUpdater(form.getUpdater());
				result = libCollegeMapper.update(e);
			}
		}
		if(result == 0) {
			voRes.setFail(voRes);
			return voRes;
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
			voRes = this.saveFormHisToLib(form);
			if(!voRes.getSuccess()) {
				return voRes;
			}
			result = busUserFormHisMapper.updateCheck(1, id);
			if(result <= 0) {
				voRes.setFail(voRes);
			}
		}
		return voRes;
	}

	@Override
	public PageInfo<LibExpert> getExpertPageByCondition(VoQuery lib, int pageNum, int pageSize, String orderBy) {
		Page<LibExpert> page = PageHelper.startPage(pageNum, pageSize);
		if(StringUtils.isEmpty(orderBy)) {
			PageHelper.orderBy("create_time desc");
		}else {
			PageHelper.orderBy(orderBy);
		}
		libExpertMapper.findByCondition(lib);
		PageInfo<LibExpert> info = new PageInfo<LibExpert>(page);
		return info;
	}

	@Override
	public PageInfo<LibCollege> getCollegePageByCondition(VoQuery lib, int pageNum, int pageSize, String orderBy) {
		Page<LibCollege> page = PageHelper.startPage(pageNum, pageSize);
		if(StringUtils.isEmpty(orderBy)) {
			PageHelper.orderBy("create_time desc");
		}else {
			PageHelper.orderBy(orderBy);
		}
		libCollegeMapper.findByCondition(lib);
		PageInfo<LibCollege> info = new PageInfo<LibCollege>(page);
		return info;
	}

	@Override
	public PageInfo<LibEnterprise> getEnterprisePageByCondition(VoQuery lib, int pageNum, int pageSize,
			String orderBy) {
		Page<LibEnterprise> page = PageHelper.startPage(pageNum, pageSize);
		if(StringUtils.isEmpty(orderBy)) {
			PageHelper.orderBy("create_time desc");
		}else {
			PageHelper.orderBy(orderBy);
		}
		libEnterpriseMapper.findByCondition(lib);
		PageInfo<LibEnterprise> info = new PageInfo<LibEnterprise>(page);
		return info;
	}

	@Override
	public PageInfo<LibServices> getServicePageByCondition(VoQuery lib, int pageNum, int pageSize, String orderBy) {
		Page<LibServices> page = PageHelper.startPage(pageNum, pageSize);
		if(StringUtils.isEmpty(orderBy)) {
			PageHelper.orderBy("create_time desc");
		}else {
			PageHelper.orderBy(orderBy);
		}
		libServiceMapper.findByCondition(lib);
		PageInfo<LibServices> info = new PageInfo<LibServices>(page);
		return info;
	}

	@Override
	public Object getLibsByUser(int userType, String name, String code) {
		switch(userType) {
		case 1 : 
			LibExpert expert = libExpertMapper.findByNameAndCode(name, code);
			return expert;
		case 2 : 
			LibEnterprise enterprise = libEnterpriseMapper.findByNameAndCode(name, code);
			return enterprise;
		case 3 : 
			LibServices services = libServiceMapper.findByNameAndCode(name, code);
			return services;
		case 4 : 
			LibCollege college = libCollegeMapper.findByNameAndCode(name, code);
			return college;
		default:
			return null;
		}
	}
	
	@Override
	public VoResponse getLibsByUser() {
		VoResponse voRes = new VoResponse();
		User user = this.getUser();
		BusUser busUser = busUserMapper.findById(user.getUserId());
		voRes.setData(this.getLibsByUser(busUser.getUserType(), busUser.getName(), busUser.getIdNumber()));
		return voRes;
	}

	@Override
	public VoResponse taskDoing() {
		VoResponse voRes = new VoResponse();
		List<BusUserForm> list = busUserFormHisMapper.findByCheck(0);
		voRes.setData(list);
		return voRes;
	}
}
