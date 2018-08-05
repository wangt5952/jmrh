package com.bz.xtcx.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.entity.LibCollege;
import com.bz.xtcx.manager.entity.LibEnterprise;
import com.bz.xtcx.manager.entity.LibExpert;
import com.bz.xtcx.manager.entity.LibServices;
import com.bz.xtcx.manager.service.ILibService;
import com.bz.xtcx.manager.vo.VoQuery;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

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
	 * 新增/更新资源库信息
	 * @param detail
	 * @param isDraft
	 * @return
	 */
	@PostMapping("addLib")
	public Object addLib(@RequestBody BusUserForm form){
		VoResponse voRes = new VoResponse();
		if(form.getFormType()<=0 || form.getFormType()>=5) {
			voRes.setFail(voRes);
			return voRes;
		}
		voRes = libService.setUserDetail(form);
		return voRes;
	}
	
	/**
	 * 删除资源库信息
	 * @return
	 */
	@DeleteMapping("delLib")
	public Object delLib(@RequestParam("id") String id, @RequestParam("type") String type){
		VoResponse voRes = new VoResponse();
		libService.delUserDetail(id, type);
		return voRes;
	}
	
	/**
	 * 获取用户 在资源库中是否存在对应的信息
	 * @param user
	 * @return
	 */
	@GetMapping("isInLibs")
	public Object isInLibs(){
		VoResponse voRes = libService.getLibsByUser();
		return voRes;
	}
	
	/**
	 * 根据用户类别、名称和证件号查找资源库中是否存在对应的信息
	 * @param user
	 * @return
	 */
	@GetMapping("isInLib")
	public Object isInLib(@RequestParam("name") String name, @RequestParam("code") String code,@RequestParam("userType") int userType){
		VoResponse voRes = new VoResponse();
		voRes.setData(libService.getLibsByUser(userType, name, code));
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
	 * 获取提交的用户完善资料（资源库待办任务）
	 * @param id
	 * @return
	 */
	@GetMapping("getSubmitLibs")
	public Object getSubmitUserDetail(@RequestParam("type") int type){
		VoResponse voRes = libService.taskDoing(type);
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
	
	@PostMapping("expert/page")
	public Object getAllExperts(@RequestBody(required=false) VoQuery lib, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<LibExpert> info = libService.getExpertPageByCondition(lib, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@PostMapping("college/page")
	public Object getAllColleges(@RequestBody(required=false) VoQuery lib, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<LibCollege> info = libService.getCollegePageByCondition(lib, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@PostMapping("enterprise/page")
	public Object getAllEnterprises(@RequestBody(required=false) VoQuery lib, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<LibEnterprise> info = libService.getEnterprisePageByCondition(lib, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	@PostMapping("services/page")
	public Object getAllServices(@RequestBody(required=false) VoQuery lib, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
			@RequestParam(value="orderBy",required=false)  String orderBy) {
		VoResponse voRes = new VoResponse();
		PageInfo<LibServices> info = libService.getServicePageByCondition(lib, pageNum, pageSize, orderBy);
		voRes.setData(info);
		return voRes;
	}
	
	
}
