package com.bz.xtcx.manager.service;

import java.util.List;

import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.vo.VoPwd;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.PageInfo;

public interface ISysUserService extends IBaseService<SysUser>{
	
	/**
	 * 修改用户状态
	 * @param user
	 * @return
	 */
	public int updateUserStatus(SysUser user);
	
	
	VoResponse updateUserPwd(VoPwd vo);
	
	VoResponse lookUserPwd(VoPwd vo);
	
	/**
	 * 更新用户资料
	 * @param user
	 * @return
	 */
	int updateUser(BusUser user);
	
	/**
	 * 获取用户信息
	 * @return
	 */
	Object getUserInfo();
	
	/**
	 * 发送邮箱验证码
	 * @return
	 */
	boolean sendEmailCode(String email);
	
	/**
	 * 发送邮箱激活码
	 * @return
	 */
	boolean sendEmailActivate(String email);

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
