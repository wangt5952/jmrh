package com.bz.xtcx.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.entity.SysMenu;
import com.bz.xtcx.manager.entity.SysRole;
import com.bz.xtcx.manager.entity.SysUser;
import com.bz.xtcx.manager.entity.User;
import com.bz.xtcx.manager.enums.UserTypeEnum;
import com.bz.xtcx.manager.mapper.BusUserMapper;
import com.bz.xtcx.manager.mapper.SysMenuMapper;
import com.bz.xtcx.manager.mapper.SysRoleMapper;
import com.bz.xtcx.manager.mapper.SysUserMapper;
import com.bz.xtcx.manager.service.IEmailService;
import com.bz.xtcx.manager.service.ISysUserService;
import com.bz.xtcx.manager.vo.VoPwd;
import com.bz.xtcx.manager.vo.VoQuery;
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
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private IEmailService emailService;
	
	@Override
	public int updateUserStatus(SysUser user) {
		SysUser oldUser = sysUserMapper.findById(user.getId());
		if(oldUser != null && !oldUser.getStatus().equals(user.getStatus())) {
			oldUser.setStatus(user.getStatus());
			return sysUserMapper.update(oldUser);
		}
		return 0;
	}
	
	@Override
	public int updateUserStatus(BusUser user) {
		BusUser oldUser = busUserMapper.findById(user.getId());
		if(oldUser != null && !oldUser.getStatus().equals(user.getStatus())) {
			oldUser.setStatus(user.getStatus());
			return busUserMapper.update(oldUser);
		}
		return 0;
	}
	
	@Override
	public VoResponse updateUserPwd(VoPwd vo) {
		VoResponse voRes = new VoResponse();
		User u = this.getUser();
		if(u.getUserType() == 0) {
			SysUser user = sysUserMapper.findById(u.getUserId());
			String md5Password = DigestUtils.md5DigestAsHex(vo.getPassword().getBytes());
			if(user.getPassword().equals(md5Password)) {
				md5Password = DigestUtils.md5DigestAsHex(vo.getNewPassword().getBytes());
				user.setPassword(md5Password);
				sysUserMapper.update(user);
			}else {
				voRes.setFail(voRes);
				voRes.setMessage("密码错误");
			}
		}else {
			BusUser busUser = busUserMapper.findById(u.getUserId());
			String md5Password = DigestUtils.md5DigestAsHex(vo.getPassword().getBytes());
			if(busUser.getPassword().equals(md5Password)) {
				md5Password = DigestUtils.md5DigestAsHex(vo.getNewPassword().getBytes());
				busUser.setPassword(md5Password);
				busUserMapper.update(busUser);
			}else {
				voRes.setFail(voRes);
				voRes.setMessage("密码错误");
			}
		}
		return voRes;
	}
	
	@Override
	public VoResponse lookUserPwd(VoPwd vo) {
		VoResponse voRes = new VoResponse();
		Object obj = this.getRedisTemplate().opsForValue().get(vo.getCode());
		if(obj != null) {
			if(obj.toString().equals(vo.getEmail())) {
				BusUser user = busUserMapper.findByEmail(vo.getEmail());
				String md5Password = DigestUtils.md5DigestAsHex(vo.getNewPassword().getBytes());
				user.setPassword(md5Password);
				busUserMapper.update(user);
				this.getRedisTemplate().delete(vo.getCode());
				return voRes;
			}
		}
		voRes.setFail(voRes);
		voRes.setMessage("错误");
		return voRes;
	}
	
	@Override
	public int updateUser(BusUser user) {
		User u = this.getUser();
		BusUser old = busUserMapper.findById(u.getUserId());
		old.setCellphone(user.getCellphone());
		old.setUserName(user.getUserName());
		int result = busUserMapper.update(old);
		return result;
	}
	
	@Override
	public Object getUserInfo() {
		User u = this.getUser();
		if(u.getUserType() == 0) {
			return sysUserMapper.findById(u.getUserId());
		}
		BusUser user = busUserMapper.findById(u.getUserId());
		return user;
	}
	
	@Override
	public boolean sendEmailCode(String email) {
		String id = String.valueOf((int)((Math.random()*9+1)*1000));
		if(emailService.sendCodeEmail(email, id)) {
			this.getRedisTemplate().opsForValue().set(id, email, 180, TimeUnit.SECONDS);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean sendEmailActivate(String email) {
		UUID uuid = UUID.randomUUID();
		//String url = "http://localhost:8080/xtcx/user/activate?activateId=" + uuid.toString();
		//String url = "http://106.14.172.38:8990/jmrh/#/xtcx/user/activate?activateId=" + uuid.toString();
		String url = "<a href=\"http://106.14.172.38:8990/jmrh/#/xtcx/user/activate?activateId="+uuid.toString()+"\" target=\"_blank\">点击此处激活</a>";
		if(!emailService.sendRegisterEmailHtml(email, url)) {
			return false;
		}
		this.getRedisTemplate().opsForValue().set(uuid.toString(), email, 180, TimeUnit.SECONDS);
		return true;
	}
	
	@Override
	public VoResponse register(BusUser user) {
		VoResponse voRes = new VoResponse();
		//检查邮箱是否已经注册
		BusUser e = busUserMapper.findByEmail(user.getEmail());
		if(e != null) {
			voRes.setFail(voRes);
			voRes.setMessage("邮箱已经被注册");
			return voRes;
		}
		if(!this.sendEmailActivate(user.getEmail())) {
			voRes.setFail(voRes);
			voRes.setMessage("邮箱验证有误，请重新输入邮箱");
			return voRes;
		}
		user.setUserName(user.getEmail().split("@")[0]);
		user.setCheckStatus(0);//未激活
		String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());//md5加密
		user.setPassword(md5Password);
		int result = busUserMapper.insert(user);
		if(result > 0) {
			voRes.setData(user);
			return voRes;
		}
		return voRes;
	}
	
	@Override
	public VoResponse activate(String uuid) {
		VoResponse voRes = new VoResponse();
		Object obj = this.getRedisTemplate().opsForValue().get(uuid);
		if(!StringUtils.isEmpty(obj)){
			String email = obj.toString();
			BusUser user = busUserMapper.findByEmail(email);
			user.setCheckStatus(1);
			int result = busUserMapper.update(user);
			this.getRedisTemplate().delete(uuid);
			if(result > 0) {
				//激活成功后自动登录
				HttpSession session = getSession();
				User e = new User();
				e.setUserId(user.getId());
				e.setUserName(user.getUserName());
				e.setUserType(user.getUserType());
				e.setToken(session.getId());
				e.setEmail(user.getEmail());
				e.setCellphone(user.getCellphone());
				this.createRedisUser(e);
				voRes.setData(e);
				
				voRes.setMessage(obj + "激活成功");
			}else {
				voRes.setMessage(obj + "激活失败");
			}
		}else {
			voRes.setFail(voRes);
			voRes.setMessage("激活失败");
		}
		return voRes;
	}

	@Override
	public VoResponse saveOrUpdate(SysUser user) {
		VoResponse voRes = new VoResponse();
		int result = 0;
		if(user.getId() == null) {//add
			SysUser e = sysUserMapper.findByUserame(user.getUserName());
			if(e != null ) {
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
			SysUser oldUser = sysUserMapper.findById(user.getId());
			if(!oldUser.getPassword().equals(user.getPassword())) {
				String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());//md5加密
				user.setPassword(md5Password);
			}
			user.setUpdater(this.getUserName());
			result = sysUserMapper.update(user);
		}
		List<SysRole> roles = user.getRoles();
		if(roles != null && roles.size() > 0) {//add role
			result = sysUserMapper.delUserRoles(user.getId());
			result = sysUserMapper.addUserRoles(user);
		}else if(roles != null && roles.size() == 0){
			result = sysUserMapper.delUserRoles(user.getId());
		}
		voRes.setData(result);
		return voRes;
		
	}
	
	@Override
	public VoResponse signIn(String username, String password, boolean isAdmin) {
		VoResponse voRes = new VoResponse();
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());//md5加密
		if(isAdmin) {
			SysUser user = sysUserMapper.findByUserameOrEmail(username);
			if(user != null && user.getPassword().equals(md5Password)) {
				HttpSession session = getSession();
				User e = new User();
				e.setUserId(user.getId());
				e.setUserName(user.getUserName());
				e.setUserType(0);
				e.setToken(session.getId());
				e.setEmail(user.getEmail());
				e.setCellphone(user.getCellphone());
				this.createRedisUser(e);
				voRes.setData(e);
				return voRes;
			}
		}else {
			BusUser user = busUserMapper.findByEmail(username);//邮箱
			if(user != null && user.getPassword().equals(md5Password)) {
				if(user.getCheckStatus() == 0) {
					voRes.setCode(10002);
					voRes.setSuccess(false);
					voRes.setMessage("用户未激活");
					return voRes;
				}
				if(user.getStatus() == 0) {
					voRes.setCode(10003);
					voRes.setSuccess(false);
					voRes.setMessage("用户被禁用");
					return voRes;
				}
				HttpSession session = getSession();
				User e = new User();
				e.setUserId(user.getId());
				e.setUserName(user.getUserName());
				e.setUserType(user.getUserType());
				e.setToken(session.getId());
				e.setEmail(user.getEmail());
				e.setCellphone(user.getCellphone());
				this.createRedisUser(e);
				voRes.setData(e);
				return voRes;
			}
		}
		voRes.setCode(10001);
		voRes.setSuccess(false);
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
	public PageInfo<SysUser> getPageByCondition(VoQuery user, int pageNum, int pageSize, String orderBy) {
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
		User user = this.getUser();
		if(user == null) {
			return null;
		}
		List<SysMenu> list =  null;
		switch(user.getUserType()) {
			case 0:
				SysUser sysuser = sysUserMapper.findById(user.getUserId());
				if(sysuser != null && sysuser.getRoles() != null) {
					list =  this.getUserMenuById(sysuser.getRoles());
				}
				break;
			case 1:
				list =  this.getUserMenuById(sysRoleMapper.findRolesByType(UserTypeEnum.Expert.theName()));
				break;
			case 2:
				list =  this.getUserMenuById(sysRoleMapper.findRolesByType(UserTypeEnum.Enterprise.theName()));
				break;
			case 3:
				list =  this.getUserMenuById(sysRoleMapper.findRolesByType(UserTypeEnum.Service.theName()));
				break;
			case 4:
				list =  this.getUserMenuById(sysRoleMapper.findRolesByType(UserTypeEnum.College.theName()));
				break;
			case 5:
				list =  this.getUserMenuById(sysRoleMapper.findRolesByType(UserTypeEnum.Military.theName()));
				break;
		}
		if(list == null) {
			return null;
		}
		setMenu(list, null);
		return list;
	}
	
	public List<SysMenu> getUserMenuById(List<SysRole> roles){
		//SysUser user = sysUserMapper.findById(userId);
		
		//List<SysRole> roles = user.getRoles();
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
	
	@Override
	public PageInfo<BusUser> getPageBusUserByCondition(VoQuery query, int pageNum, int pageSize, String orderBy) {
		Page<BusUser> page = PageHelper.startPage(pageNum, pageSize);
		if(StringUtils.isEmpty(orderBy)) {
			PageHelper.orderBy("create_time desc");
		}else {
			PageHelper.orderBy(orderBy);
		}
		busUserMapper.findByCondition(query);
		PageInfo<BusUser> info = new PageInfo<BusUser>(page);
		return info;
	}

	
	int saveEnterprise(JSONObject json){
		return 0;
	}

	@Override
	public Object getRedisUser(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		RedisOperations<String, Object> redis = this.getRedisTemplate().opsForValue().getOperations();
		if(redis.hasKey(userId)) {
			String token = (String) this.getRedisTemplate().opsForValue().get(userId);
			map.put("token", token);
			if(redis.hasKey(token)) {
				map.put("user", this.getRedisTemplate().opsForValue().get(token));
			}
		}
		String id = this.getSession().getId();
		Object obj = this.getRedisTemplate().opsForValue().get(id);
		map.put("currUser", obj);
		return map;
	}

	
}
