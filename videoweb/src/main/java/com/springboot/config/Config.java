package com.springboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.controller.FilterController;

@Configuration
public class Config {
	    @Bean
	    public FilterRegistrationBean someFilterRegistration1() {
	        //新建过滤器注册类
	        FilterRegistrationBean registration = new FilterRegistrationBean();
	        // 添加我们写好的过滤器
	        registration.setFilter( new FilterController());
	        // 设置过滤器的URL模式
	        registration.addUrlPatterns("/*");
	        return registration;
	    }


}