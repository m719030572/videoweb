package com.springboot.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;


@WebFilter(filterName = "FilterController", urlPatterns = "/*")
@Order(1)
public class FilterController implements Filter {

	@Override
	public void doFilter(ServletRequest requests, ServletResponse responses, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest request=(HttpServletRequest)requests;
		HttpServletResponse response=(HttpServletResponse)responses;
		HttpSession session=request.getSession();
		String request_url=request.getRequestURI();
		chain.doFilter(requests, responses);
		System.out.println("RequestURL:"+request_url);
		
	/*	if(request_url.equals("/user/personal"))
		{
			if(session.getAttribute("uid")==null)
				request.getRequestDispatcher("/login/login").forward(requests, responses);;
		}
		else
		{
				chain.doFilter(requests, responses);
		}
		*/
	}


}
