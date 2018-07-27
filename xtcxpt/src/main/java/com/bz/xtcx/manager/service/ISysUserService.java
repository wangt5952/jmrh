package com.bz.xtcx.manager.service;

import java.util.List;

import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

public interface ISysUserService extends IBaseService<SysUser>{
	
	public int updateUserStatus(SysUser user);
	
	/**
	 * 更新用户资料
	 * @param user
	 * @return
	 */
	int updateUser(BusUser user);

	public VoResponse register(BusUser user);
	
	public VoResponse activate(String uuid);
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public VoResponse signIn(String username, String password, boolean isAdmin);
	
	/**
	 * 登出
	 */
	public void signOut();
	
	/**
	 * 用户名或者邮箱查找用户信息
	 * @param username
	 * @return
	 */
	public SysUser getUserByUsername(String username);
	
	public List<SysMenu> getCurrentUserMenus();
	
	PageInfo<BusUser> getPageBusUserByCondition(BusUser user, int pageNum, int pageSize, String orderBy);

	Object getRedisUser(String userId);
}
