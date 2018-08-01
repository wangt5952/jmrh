package com.bz.xtcx.manager.service;

import java.util.List;

import com.bz.xtcx.manager.vo.VoQuery;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

public interface IBaseService<T> {

	VoResponse saveOrUpdate(T t);
	
	int del(String id);
	
	List<T> getAll();
	
	PageInfo<T> getPageByCondition(VoQuery t, int pageNum, int pageSize, String orderBy);
}
