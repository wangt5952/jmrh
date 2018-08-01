package com.bz.xtcx.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.entity.SysRole;
import com.bz.xtcx.manager.mapper.SysRoleMapper;
import com.bz.xtcx.manager.service.ISysRoleService;
import com.bz.xtcx.manager.vo.VoQuery;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysRoleService extends BaseService implements ISysRoleService{

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public VoResponse saveOrUpdate(SysRole t) {
		VoResponse voRes = new VoResponse();
		int result = 0;
		if(t.getId() == null) {
			t.setCreater(getUserName());
			result = sysRoleMapper.insert(t);
			if(result > 0) {
				return voRes;
			}
		}else {
			t.setUpdater(getUserName());
			result = sysRoleMapper.update(t);
		}
		List<SysMenu> menus = t.getMenus();
		if(menus != null && menus.size() > 0) {//add role
			result = sysRoleMapper.delRoleMenus(t.getId());
			result = sysRoleMapper.addRoleMenus(t);
		}else if(menus != null && menus.size() == 0) {
			result = sysRoleMapper.delRoleMenus(t.getId());
		}
		voRes.setData(result);
		return voRes;
	}

	@Override
	public int del(String id) {
		int result = sysRoleMapper.del(id);
		return result;
	}

	@Override
	public List<SysRole> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<SysRole> getPageByCondition(VoQuery t, int pageNum, int pageSize, String orderBy) {
		Page<SysRole> page = PageHelper.startPage(pageNum, pageSize);
		if(StringUtils.isEmpty(orderBy)) {
			PageHelper.orderBy("create_time desc");
		}else {
			PageHelper.orderBy(orderBy);
		}
		sysRoleMapper.findByCondition(t);
		PageInfo<SysRole> info = new PageInfo<SysRole>(page);
		return info;
	}

}
