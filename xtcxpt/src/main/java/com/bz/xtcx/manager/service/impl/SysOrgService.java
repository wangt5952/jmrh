package com.bz.xtcx.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.SysOrg;
import com.bz.xtcx.manager.mapper.SysOrgMapper;
import com.bz.xtcx.manager.service.ISysOrgService;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

@Service
public class SysOrgService implements ISysOrgService {

	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	@Override
	public List<SysOrg> getAll() {
		List<SysOrg> list = sysOrgMapper.findAll();
		return list;
	}

	@Override
	public VoResponse saveOrUpdate(SysOrg org) {
		VoResponse voRes = new VoResponse();
		if(StringUtils.isEmpty(org.getId())) {//add
			if(StringUtils.isEmpty(org.getParentId())) {
				org.setOrgType(1);
				org.setStatus(1);
			}else {
				SysOrg perOrg = sysOrgMapper.findById(org.getParentId());
				if(perOrg == null) {
					voRes.setFail(voRes);
					return voRes;
				}
				if(perOrg.getOrgType() ==3) {
					voRes.setFail(voRes);
					return voRes;
				}
				org.setOrgType(perOrg.getOrgType() + 1);
				org.setStatus(1);
			}
			sysOrgMapper.insert(org);
		}else {//update
			SysOrg oldOrg = sysOrgMapper.findById(org.getId());
			oldOrg.setSortOrder(org.getSortOrder());
			oldOrg.setOrgName(org.getOrgName());
			oldOrg.setRemark(org.getRemark());
			oldOrg.setStatus(org.getStatus());
			sysOrgMapper.update(oldOrg);
		}
		return voRes;
	}
	
	@Override
	public int del(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<SysOrg> getPageByCondition(SysOrg t, int pageNum, int pageSize, String orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
