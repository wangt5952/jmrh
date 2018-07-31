package com.bz.xtcx.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bz.xtcx.manager.entity.BusUserImage;
import com.bz.xtcx.manager.entity.User;
import com.bz.xtcx.manager.mapper.BusUserImageMapper;
import com.bz.xtcx.manager.service.IUserImageService;

@Service
public class UserImageService extends BaseService implements IUserImageService {

	@Autowired
	private BusUserImageMapper busUserImageMapper;
	
	@Override
	public int saveOrUpdate(int type, String filename) {
		User user = this.getUser();
		BusUserImage t = busUserImageMapper.findByUserId(user.getUserId());
		if(t == null) {
			t = new BusUserImage();
			t.setUserId(user.getUserId());
			t.setCreater(user.getUserName());
		}
		if(type == 1) {
			t.setField1(filename);
		}else if(type == 2){
			t.setField2(filename);
		}else if(type == 3){
			t.setField3(filename);
		}else if(type == 4){
			t.setField4(filename);
		}else if(type == 5){
			t.setField5(filename);
		}else{
			return 0;
		}
		int result = 0;
		if(t.getId() == null) {
			result = busUserImageMapper.insert(t);
		}else {
			result = busUserImageMapper.update(t);
		}
		return result;
	}


	
}
