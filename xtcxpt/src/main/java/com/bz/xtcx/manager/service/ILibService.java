package com.bz.xtcx.manager.service;

import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.entity.LibServices;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

public interface ILibService{
	
	BusUserForm getUserForm(int type);
	
	VoResponse setUserForm(BusUserForm form);
	
	/**
	 * 用户 提交/更新 用户的资源库表格数据
	 * @param detail
	 * @return
	 */
	VoResponse setUserDetail(String detail);
	
	/**
	 * 管理员 添加/更新 资源库信息
	 * @param form
	 * @return
	 */
	VoResponse setUserDetail(BusUserForm form);
	
	/**
	 * 根据用户类别、名称和证件号查找资源库中是否存在对应的信息
	 * @param userType
	 * @param name
	 * @param idNumber
	 * @return
	 */
	VoResponse getLibsByUser(int userType, String name, String idNumber);
	
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
