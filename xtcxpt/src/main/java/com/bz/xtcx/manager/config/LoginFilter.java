package com.bz.xtcx.manager.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.bz.xtcx.manager.entity.User;
import com.bz.xtcx.manager.vo.VoResponse;

/**
 * @author zhangkj
 *
 */
public class LoginFilter implements Filter{
	
	@Resource
    private RedisTemplate<String, Object> redisTemplate;

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);   
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		VoResponse voRes = new VoResponse();
		voRes.setNull(voRes);
		voRes.setCode(401);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI();
		String method = req.getMethod();
		//logger.debug(method + "-" + url);
		System.out.println(method + "-" + url);
		String[] notFilterDirs = {"xtcx/user"};
		for (int i = 0; i < notFilterDirs.length; i++) {
			String notFilterDirValue = notFilterDirs[i];
			if (url.indexOf(notFilterDirValue) != -1) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		/*String token = req.getHeader("token");
		if(token == null) {
			res.setStatus(200);
			PrintWriter writer = response.getWriter();
			writer.write(JSON.toJSON(voRes).toString());
			return;
		}
		User user = null;
		Object obj = redisTemplate.opsForValue().get(token);
		if(obj == null){
			res.setStatus(200);
			PrintWriter writer = response.getWriter();
			writer.write(JSON.toJSON(voRes).toString());
			return;
		}
		user = (User) obj;*/
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.debug("过滤器销毁");
	}

}
