package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.domain.User;
import com.springboot.service.UserService;
import com.springboot.vo.DebugVo;

@Controller
@RequestMapping("/debug")
public class TestController 
{
	@Autowired
	UserService us;
	@GetMapping("/sendDebug")
	public String sendDebug(Model model)
	{
		User user=new User();
		user=us.findById("sda");
		DebugVo debug=new DebugVo();
		debug.setDebug("test");
		debug.setName("mxl");
		if(user==null)
		{
			System.out.println("select failed");
		}
		System.out.println(user.getUid());
		model.addAttribute("user",user);
		return "debug.html";
	}
}
