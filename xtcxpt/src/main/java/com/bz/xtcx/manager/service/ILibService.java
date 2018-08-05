package com.bz.xtcx.manager.service;

import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.entity.LibServices;
import com.bz.xtcx.manager.vo.VoQuery;
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
	 * 管理员 删除 资源库信息
	 * @param form
	 * @return
	 */
	VoResponse delUserDetail(String id, String type);
	
	/**
	 * 根据用户类别、名称和证件号查找资源库中是否存在对应的信息
	 * @param userType
	 * @param name
	 * @param code
	 * @return
	 */
	Object getLibsByUser(int userType, String name, String code);
	
	/**
	 * 获取用户是否存在资源库中信息
	 * @return
	 */
	VoResponse getLibsByUser();
	
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
	
	PageInfo<LibExpert> getExpertPageByCondition(VoQuery lib, int pageNum, int pageSize, String orderBy);
	
	PageInfo<LibCollege> getCollegePageByCondition(VoQuery lib, int pageNum, int pageSize, String orderBy);
	
	PageInfo<LibEnterprise> getEnterprisePageByCondition(VoQuery lib, int pageNum, int pageSize, String orderBy);
	
	PageInfo<LibServices> getServicePageByCondition(VoQuery lib, int pageNum, int pageSize, String orderBy);
	
	/**
	 * 获取资源库待办任务
	 * @return
	 */
	VoResponse taskDoing(int type);
}
