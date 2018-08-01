package com.bz.xtcx.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.mapper.SysMenuMapper;
import com.bz.xtcx.manager.service.ISysMenuService;
import com.bz.xtcx.manager.vo.VoQuery;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

@Service
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
			//SysMenu old = sysMenuMapper.findById(menu.getId());
			sysMenuMapper.update(menu);
		}
		return voRes;
	}

	@Override
	public int del(String id) {
		int result = sysMenuMapper.del(id);
		return result;
	}

	@Override
	public List<SysMenu> getAll() {
		List<SysMenu> list = sysMenuMapper.findAll();
		return list;
	}

	@Override
	public PageInfo<SysMenu> getPageByCondition(VoQuery t, int pageNum, int pageSize, String orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
