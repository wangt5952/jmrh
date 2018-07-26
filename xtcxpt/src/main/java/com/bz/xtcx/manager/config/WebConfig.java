package com.bz.xtcx.manager.config;

import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.github.pagehelper.PageHelper;

/**
 * @author zhangkj
 *
 */
@Configuration
public class WebConfig {
	
	@Bean
	public Filter getLoginFilter() {
		return new LoginFilter();
	}
	
	@Bean
    @Order(Integer.MAX_VALUE)
    public FilterRegistrationBean<Filter> someFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(getLoginFilter());
        registration.addUrlPatterns("/xtcx/*");
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("sessionFilter");
        return registration;
    }
	
	@Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum","true");
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
	
	/**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
