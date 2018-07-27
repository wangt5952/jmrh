package com.bz.xtcx.manager.service;

import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.entity.LibServices;
import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

public interface ILibService{
	
	BusUserForm getUserForm(int type);
	
	VoResponse setUserForm(BusUserForm form);
	
	/**
	 * 用户提交表格数据
	 * @param detail
	 * @return
	 */
	VoResponse setUserDetail(String detail);
	
	BusUserForm getUserDetail(boolean isDraft);
	
	VoResponse addOrUpdateExpert(LibExpert e);
	
	VoResponse addOrUpdateEnterprise(LibEnterprise e);
	
	VoResponse addOrUpdateService(LibServices e);
	
	VoResponse addOrUpdateCollege(LibCollege e);
	
	/**
	 * 用户保存表格草稿
	 * @param form
	 * @return
	 */
	int saveFormHis(String form);
	
	VoResponse checkUserDetail(String id);
	
	PageInfo<LibExpert> getExpertPageByCondition(LibExpert lib, int pageNum, int pageSize, String orderBy);
	
	PageInfo<LibCollege> getCollegePageByCondition(LibCollege lib, int pageNum, int pageSize, String orderBy);
	
	PageInfo<LibEnterprise> getEnterprisePageByCondition(LibEnterprise lib, int pageNum, int pageSize, String orderBy);
	
	PageInfo<LibServices> getServicePageByCondition(LibServices lib, int pageNum, int pageSize, String orderBy);
}
