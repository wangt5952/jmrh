package com.bz.xtcx.manager.service;

import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.vo.VoResponse;

public interface ILibService{
	
	BusUserForm getUserForm(int type);
	
	VoResponse setUserForm(BusUserForm from);
	
	VoResponse setUserDetail(String detail);
	
	BusUserForm getUserDetail(String userId);
}
