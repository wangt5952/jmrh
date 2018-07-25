package com.bz.xtcx.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.SysRole;
import com.bz.xtcx.manager.mapper.SysRoleMapper;
import com.bz.xtcx.manager.service.ISysRoleService;
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
		if(t.getId() == null) {
			t.setCreater(getUserName());
			int result = sysRoleMapper.insert(t);
			if(result > 0) {
				return voRes;
			}
		}else {
			
		}
		return null;
	}

	@Override
	public int del(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysRole> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<SysRole> getPageByCondition(SysRole t, int pageNum, int pageSize, String orderBy) {
		Page<SysRole> page = PageHelper.startPage(pageNum, pageSize);
		if(StringUtils.isEmpty(orderBy)) {
			PageHelper.orderBy("create_time desc");
		}else {
			PageHelper.orderBy(orderBy);
		}
		if(t.getRoleType() == 0) {
			t.setRoleType(1);
		}
		sysRoleMapper.findByCondition(t);
		PageInfo<SysRole> info = new PageInfo<SysRole>(page);
		return info;
	}

}
