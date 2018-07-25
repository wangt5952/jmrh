package com.bz.xtcx.manager.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.bz.xtcx.manager.entity.User;


public class BaseService {
	
	@Resource
    private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/**
	 * 创建redis缓存session
	 * @param userId
	 * @param session
	 */
	public void createRedisUser(String userId, User user) {
		if(redisTemplate.opsForValue().getOperations().hasKey(userId)) {
			String token = redisTemplate.opsForValue().get(userId).toString();
			boolean result = redisTemplate.delete(userId);
			System.out.println(result);
			result = redisTemplate.delete(token);
			//System.out.println(result);
		}
		redisTemplate.opsForValue().set(userId, session.getId());
		redisTemplate.opsForValue().set(session.getId(), user);
	}
	
	/**
	 * 销毁redis缓存session
	 */
	public void destroyedRedisUser() {
		String userId = this.getUserId();
		if(redisTemplate.opsForValue().getOperations().hasKey(userId)) {
			String token = redisTemplate.opsForValue().get(userId).toString();
			boolean result = redisTemplate.delete(userId);
			System.out.println(result);
			result = redisTemplate.delete(token);
			System.out.println(result);
		}
	}
	
	public void setRedisEmail(String uuid, String email) {
		redisTemplate.opsForValue().set(uuid, email);
	}
	
	public String getRedisEmail(String uuid){
		String email = "";
		Object obj = redisTemplate.opsForValue().get(uuid);
		if(obj != null){
			email = obj.toString();
		}
		return email;
	}
	
	public String getUserName(){
		String username = "auto";
		String token = request.getHeader("token");
		if(token == null) {
			return username;
		}
		Object obj = redisTemplate.opsForValue().get(token);
		if(obj != null){
			username = ((User)obj).getUserName();
		}
		return username;
	}
	
	public String getUserId(){
		String userId = null;
		String token = request.getHeader("token");
		if(token == null) {
			return userId;
		}
		Object obj = redisTemplate.opsForValue().get(token);
		if(obj != null){
			userId = ((User)obj).getUserId();
		}
		return userId;
	}

	
}
