package com.bz.xtcx.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.mapper.SysMenuMapper;
import com.bz.xtcx.manager.service.ISysMenuService;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

public class SysMenuService extends BaseService implements ISysMenuService{

	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public VoResponse saveOrUpdate(SysMenu menu) {
		VoResponse voRes = new VoResponse();
		if(StringUtils.isEmpty(menu.getId())) {//add
			if(StringUtils.isEmpty(menu.getParentId())) {
				menu.setMenuLevel(1);
			}else {
				SysMenu permenu = sysMenuMapper.findById(menu.getParentId());
				if(permenu == null) {
					voRes.setFail(voRes);
					return voRes;
				}
				menu.setMenuLevel(permenu.getMenuLevel() + 1);
			}
			sysMenuMapper.insert(menu);
		}else {//update
			
		}
		return voRes;
	}

	@Override
	public int del(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysMenu> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<SysMenu> getPageByCondition(SysMenu t, int pageNum, int pageSize, String orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
