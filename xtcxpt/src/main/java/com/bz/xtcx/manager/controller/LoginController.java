package com.bz.xtcx.manager.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bz.xtcx.manager.entity.BusUser;
import com.bz.xtcx.manager.entity.BusUserForm;
import com.bz.xtcx.manager.service.ISysUserService;
import com.bz.xtcx.manager.vo.VoResponse;
import com.bz.xtcx.manager.vo.VoUser;

@RestController
@RequestMapping("xtcx/user")
public class LoginController extends BaseController{
	
	@Autowired
	private ISysUserService sysUserService;
	
	@GetMapping
	public Object hello() {
		String msg = "Hello,LoginController";
		return msg;
	}
	
	@PostMapping("login")
	public Object login(@RequestBody VoUser vo) {
		VoResponse voRes = getVoResponse();
		if(StringUtils.isEmpty(vo.getUserName()) || StringUtils.isEmpty(vo.getPassword())) return voRes;
		voRes = sysUserService.signIn(vo.getUserName(), vo.getPassword(), vo.isAdmin());
		return voRes;
	}
	
	@PostMapping("logout")
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
		voRes = sysUserService.register(user);
		return voRes;
	}
	
	@GetMapping("activate")
	public Object activate(@RequestParam("activateId") String id){
		VoResponse voRes = sysUserService.activate(id);
		return voRes;
	}
	
	@PostMapping("setUserDetail")
	public Object setUserDetail(@RequestBody String detail){
		VoResponse voRes = new VoResponse();
		voRes = sysUserService.setUserDetail(detail);
		return voRes;
	}
	
	@GetMapping("getUserDetail")
	public Object getUserDetail(@RequestParam("userId") String userId){
		VoResponse voRes = new VoResponse();
		voRes.setData(sysUserService.getUserDetail(userId));
		return voRes;
	}
	
	@PostMapping("setUserForm")
	public Object setUserForm(@RequestBody BusUserForm form){
		VoResponse voRes = new VoResponse();
		voRes = sysUserService.setUserForm(form);
		return voRes;
	}
	
	@GetMapping("getUserForm")
	public Object getUserForm(@RequestParam("type") int type){
		VoResponse voRes = new VoResponse();
		voRes.setData(sysUserService.getUserForm(type));
		return voRes;
	}
	
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		//对密码进行 md5 加密
		String password = "123456";
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(md5Password);
	}
	
}
