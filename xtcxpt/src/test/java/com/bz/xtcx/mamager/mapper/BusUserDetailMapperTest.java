package com.bz.xtcx.mamager.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bz.xtcx.manager.SampleWarApplication;
import com.bz.xtcx.manager.entity.BusUserDetail;
import com.bz.xtcx.manager.entity.SysOrg;
import com.bz.xtcx.manager.mapper.BusUserDetailMapper;
import com.bz.xtcx.manager.mapper.SysOrgMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleWarApplication.class)
public class BusUserDetailMapperTest {

	@Autowired
	private BusUserDetailMapper busUserDetailMapper;
	
	@Test
	public void insert() {
		BusUserDetail e = new BusUserDetail();
		e.setUserId("");
		e.setField1("field1");
		int result = busUserDetailMapper.insert(e);
		System.out.println(result);
	}
}
