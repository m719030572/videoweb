package com.springboot.controller;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.domain.User;
import com.springboot.impl.UserServiceImpl;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import com.springboot.vo.LoginVo;
@Controller
@RequestMapping("login")
public class LoginController 
/*
 * 登录流程
 * 用户点击登录时先进入此页，由此页发送一个对象给前台，此对象就是裁剪版的用户对象，有用户账户密码验证码等
 */
{
	@GetMapping("login")
	public String login(Model model,HttpServletRequest request)
	{
		System.out.println("login");
        model.addAttribute("user", new LoginVo());
        request.setAttribute("loginlog", -1);
    	return "login";	
	}
	private String user_not_exist_url="redirect:/login/login?loginlog=1";
	private String passwd_not_right_url="redirect:/login/login?loginlog=2";
	private String success_url="redirect:/index";
	@Autowired
	private UserRepository ur; 
	
	public UserServiceImpl us=new UserServiceImpl();
//	@RequestMapping("dd")
//    @ResponseBody
//    public House  getlinklList(String name) 
//    {
//
//        return ur.findById("1234").orElse(null);
//    }	
	 @RequestMapping("/deallogin")
	 private String checkUser(@ModelAttribute LoginVo lv,HttpServletRequest request)
	 {
		
		System.out.println(lv.getUid()+lv.getPasswd()+"\n");
		User user=ur.findByUserid(lv.getUid());
		if(user==null)
		{
			System.out.println("user_not_exist_url");
			request.setAttribute("loginlog", 1);
			return user_not_exist_url;
		}
		else if(!lv.getPasswd().equals(user.getPassword()))
		{
			System.out.println(user.getUid()+user.getPassword());
			System.out.println("passwd_not_right");
			request.setAttribute("loginlog", 2);
			return passwd_not_right_url;
		}
		else
		{
			HttpSession session=request.getSession();
			session.setAttribute("uid", lv.getUid());
			return success_url;
		}		 		 
	 }
		@GetMapping("logout")
		public String logout(Model model,HttpServletRequest request)
		{
			System.out.println("logout");
			HttpSession session=request.getSession();
			session.removeAttribute("uid");
	    	return "redirect:/index";	
		}
}
