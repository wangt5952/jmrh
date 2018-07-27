package com.bz.xtcx.manager.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.bz.xtcx.manager.entity.User;


public class BaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);   
	
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
	public void createRedisUser(User user) {
		boolean result = false;
		//同一个session
		if(redisTemplate.opsForValue().getOperations().hasKey(user.getToken())) {
			User oldUser = (User) redisTemplate.opsForValue().get(user.getToken());
			result = redisTemplate.delete(oldUser.getUserId());
			logger.warn("session : " + result);
			result = redisTemplate.delete(user.getToken());
			logger.warn("session : " + result);
		}
		if(redisTemplate.opsForValue().getOperations().hasKey(user.getUserId())) {
			String oldToken = redisTemplate.opsForValue().get(user.getUserId()).toString();
			result = redisTemplate.delete(user.getUserId());
			logger.warn("userId : " + result);
			result = redisTemplate.delete(oldToken);
			logger.warn("userId : " + result);
		}
		//add
		redisTemplate.opsForValue().set(user.getUserId(), user.getToken());
		redisTemplate.opsForValue().set(user.getToken(), user);
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
	
	public User getUser(){
		User user = null;
		String token = request.getHeader("token");
		if(token == null) {
			return user;
		}
		Object obj = redisTemplate.opsForValue().get(token);
		if(obj != null){
			user = ((User)obj);
		}
		return user;
	}

	
}
