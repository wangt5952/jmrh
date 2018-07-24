package com.bz.xtcx.manager.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.entity.SysRole;
import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.mapper.BusUserMapper;
import com.bz.xtcx.manager.mapper.SysMenuMapper;
import com.bz.xtcx.manager.mapper.SysUserMapper;
import com.bz.xtcx.manager.service.ISysUserService;
import com.bz.xtcx.manager.vo.VoResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysUserService extends BaseService implements ISysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private BusUserMapper busUserMapper;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public List<SysUser> getUserByEmail(String email) {
		return null;
	}

	@Override
	public VoResponse saveOrUpdate(SysUser user) {
		VoResponse voRes = new VoResponse();
		int result = 0;
		if(user.getId() == null) {//add
			SysUser query = new SysUser();
			query.setUserName(user.getUserName());
			List<SysUser> list = sysUserMapper.findByCondition(query);
			if(list != null && list.size() > 0) {
				voRes.setFail(voRes);
				voRes.setMessage("用户名已经存在");
				return voRes;
			}
			String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());//md5加密
			user.setPassword(md5Password);
			user.setCreater(this.getUserName());
			user.setStatus(1);
			result = sysUserMapper.insert(user);
			
		}else {
			
		}
		List<SysRole> roles = user.getRoles();
		if(roles != null && roles.size() > 0) {//add role
			result = sysUserMapper.delUserRoles(user.getId());
			result = sysUserMapper.addUserRoles(user);
		}
		voRes.setData(result);
		return voRes;
		
	}
	
	@Override
	public VoResponse signIn(String username, String password, boolean isAdmin) {
		VoResponse voRes = new VoResponse();
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());//md5加密
		SysUser user = null;
		if(isAdmin) {
			user = this.getUserByUsername(username);
			if(user != null && user.getPassword().equals(md5Password)) {
				HttpSession session = getSession();
				user.setToken(session.getId());
				this.createRedisUser(user.getId(), user);
				voRes.setData(user);
				return voRes;
			}
		}else {
			
		}
		voRes.setFail(voRes);
		voRes.setMessage("用户名或者密码错误");
		return voRes;
	}
	
	@Override
	public void signOut() {
		this.destroyedRedisUser();
	}

	@Override
	public SysUser getUserByUsername(String username) {
		SysUser user = sysUserMapper.findByUserameOrEmail(username);
		return user;
	}

	@Override
	public PageInfo<SysUser> getPageByCondition(SysUser user, int pageNum, int pageSize, String orderBy) {
		Page<SysUser> page = PageHelper.startPage(pageNum, pageSize);
		if(StringUtils.isEmpty(orderBy)) {
			PageHelper.orderBy("create_time desc");
		}else {
			PageHelper.orderBy(orderBy);
		}
		sysUserMapper.findByCondition(user);
		PageInfo<SysUser> info = new PageInfo<SysUser>(page);
		return info;
	}

	@Override
	public int del(String id) {
		int result = sysUserMapper.del(id);
		return result;
	}

	@Override
	public List<SysUser> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysMenu> getCurrentUserMenus() {
		String userId = getUserId();
		if(userId == null) {
			return null;
		}
		List<SysMenu> list =  this.getUserMenuById(userId);
		setMenu(list, null);
		return list;
	}
	
	public List<SysMenu> getUserMenuById(String userId){
		SysUser user = sysUserMapper.findById(userId);
		List<SysRole> roles = user.getRoles();
		List<SysMenu> myMenus = new ArrayList<SysMenu>();
		List<SysMenu> allMenus = sysMenuMapper.findAll();
		for(SysRole role : roles){
			if(role.getRoleName().equals("超级管理员")){//管理员获取所有菜单
				return allMenus;
			}
			List<SysMenu> menus = role.getMenus();
			if(myMenus.size() == 0){
				myMenus.addAll(menus);
				continue;
			}
			boolean flag = false;
			for(SysMenu e : menus){
				flag = false;
				for(SysMenu menu : myMenus){
					if(e.getId().equals(menu.getId())){
						flag = true;
						continue;
					}
				}
				if(!flag){
					myMenus.add(e);
				}
			}
		}
		transMenus(myMenus, allMenus);
		return allMenus;
	}

	/**
	 * 转前端树形菜单数据
	 * @param list
	 * @param lastMenu
	 * @return
	 */
	private List<SysMenu> setMenu(List<SysMenu> list, SysMenu lastMenu){
		Iterator<SysMenu> it = list.iterator();
		List<SysMenu> listnull = new ArrayList<>();
		while (it.hasNext()) {
			SysMenu menu = it.next();
			menu.setLeaf(true);
			if(menu.getMenus() != null && menu.getMenus().size() > 0 && menu.getMenuLevel() < 1000){
				menu.setLeaf(false);
				setMenu(menu.getMenus(), menu);
			}else if(menu.getMenuLevel() == 1000){
				lastMenu.setAuthmenus(list);
				lastMenu.setMenus(listnull);
				lastMenu.setLeaf(true);
			}
		}
		return list;
	}
	
	/**
	 * 迭代出菜单树形的目录
	 * @param myMenus用户的菜单列表
	 * @param menus所有的树形菜单
	 */
	private void transMenus(List<SysMenu> myMenus, List<SysMenu> menus){
		Iterator<SysMenu> itMenu = menus.iterator();
		while(itMenu.hasNext()){
			SysMenu menu = itMenu.next();
			boolean flag = false;
			for(SysMenu mymenu : myMenus){
				if(menu.getId().equals(mymenu.getId())){
					flag = true;
					break;
				}
			}
			if(!flag){
				itMenu.remove();
				flag = false;
				continue;
			}
			if(menu.getMenus() != null && menu.getMenus().size() > 0){
				transMenus(myMenus, menu.getMenus());
			}
		}
	}
}
