package com.bz.xtcx.manager.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.service.ISysUserService;
import com.bz.xtcx.manager.vo.VoPwd;
import com.bz.xtcx.manager.vo.VoResponse;
import com.bz.xtcx.manager.vo.VoUser;

@RestController
@RequestMapping("xtcx/user")
public class LoginController extends BaseController{
	
	@Autowired
	private ISysUserService sysUserService;
	
	@PostMapping("login")
	public Object login(@RequestBody VoUser vo) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(vo.getUserName()) || StringUtils.isEmpty(vo.getPassword())) return voRes;
		voRes = sysUserService.signIn(vo.getUserName(), vo.getPassword(), vo.isAdmin());
		return voRes;
	}
	
	/**
	 * 修改密码
	 * @param vo
	 * @return
	 */
	@PostMapping("resetPwd")
	public Object resetPassword(@RequestBody VoPwd vo) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(vo.getNewPassword())) {
			voRes.setFail(voRes);
			voRes.setData("密码不能为空");
			return voRes;
		}
		voRes = sysUserService.updateUserPwd(vo);
		return voRes;
	}
	
	/**
	 * 找回密码
	 * @param vo
	 * @return
	 */
	@PostMapping("lookPwd")
	public Object lookPassword(@RequestBody VoPwd vo) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(vo.getCode())) {
			voRes.setData("验证码不能为空");
			return voRes;
		}
		if(StringUtils.isEmpty(vo.getNewPassword())) {
			voRes.setFail(voRes);
			voRes.setData("密码不能为空");
			return voRes;
		}
		voRes = sysUserService.lookUserPwd(vo);
		return voRes;
	}
	
	@PostMapping("sendEmailCode")
	public Object sendEmailCode(@RequestBody VoPwd vo){
		VoResponse voRes = new VoResponse();
		if(!sysUserService.sendEmailCode(vo.getEmail())) {
			voRes.setFail(voRes);
		}
		return voRes;
	}
	
	/**
	 * 获取用户资料
	 * @return
	 */
	@GetMapping
	public Object getUser(){
		VoResponse voRes = new VoResponse();
		voRes.setData(sysUserService.getUserInfo());
		return voRes;
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@PutMapping
	public Object updateBusUser(@RequestBody BusUser user) {
		VoResponse voRes =  new VoResponse();
		if(StringUtils.isEmpty(user.getId())) return voRes;
		voRes.setData(sysUserService.updateUser(user));
		return voRes;
	}
	
	
	@GetMapping("logout")
	public Object logout(){
		VoResponse voRes = new VoResponse();
		sysUserService.signOut();
		return voRes;
	}
	
	@GetMapping("getMenus")
	public Object getCurrentUserMenus(){
		VoResponse voRes = new VoResponse();
		voRes.setData(sysUserService.getCurrentUserMenus());
		return voRes;
	}

	@PostMapping("register")
	public Object register(@RequestBody BusUser user) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(user.getEmail())) {
			voRes.setNull(voRes);
			voRes.setMessage("邮箱地址不能为空");
			return voRes;
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			voRes.setNull(voRes);
			voRes.setMessage("密码不能为空");
			return voRes;
		}
		if(user.getUserType() == 0) {
			voRes.setNull(voRes);
			voRes.setMessage("用户类型不能为空");
			return voRes;
		}
		voRes = sysUserService.register(user);
		return voRes;
	}
	
	@GetMapping("activate")
	public Object activate(@RequestParam("activateId") String id){
		VoResponse voRes = sysUserService.activate(id);
		return voRes;
	}
	
	@GetMapping("getRedisUser")
	public Object getRedisUser(@RequestParam("userId") String id){
		return sysUserService.getRedisUser(id);
	}
	
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		//对密码进行 md5 加密
		String password = "123456";
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(md5Password);
	}
	
}
