package com.springboot.controller;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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
import com.springboot.domain.Verify;
import com.springboot.repository.UserRepository;
import com.springboot.service.MailService;
import com.springboot.service.UserService;
import com.springboot.service.VerifyService;
import com.springboot.vo.LoginVo;
import java.sql.Time;
@Controller
@RequestMapping("/register")
public class RegisterController 
/*
 * 登录流程
 * 用户点击登录时先进入此页，由此页发送一个对象给前台，此对象就是裁剪版的用户对象，有用户账户密码验证码等
 */
{
	@GetMapping("/register")
	public String register(Model model,HttpServletRequest request)
	{
		System.out.println("regist");
        model.addAttribute("user", new LoginVo());
        request.setAttribute("loginlog", 0);
    	return "/signup";	
	}
	private String user_exist_url="redirect:/register/register?loginlog=1";
	private String check_code_wrong_url="redirect:/indexr?loginlog=2";
	private String success_url="redirect:/login/login?loginlog=0";
	private String checkcode_not_exist_url="redirect:/register?loginlog=2";

	@Autowired
	private MailService ms;
	@Autowired
	private UserService us;
	@Autowired
	private VerifyService vs;
	
	@PostMapping("/getverify")
	private String sendVerify(@ModelAttribute LoginVo lv,Model model)
	{
		System.out.println("getVerify");
		if(lv.getEmail()!=null)
		{
			System.out.println("send email to"+lv.getEmail());
			int checkcode=ms.sendCheckCode(lv.getEmail());
			Verify verify=vs.getVerifyByEmail(lv.getEmail());
			if(verify==null)
			{
				verify=new Verify();
				System.out.println(lv.getEmail());
				verify.setEmail(lv.getEmail());
				verify.setVerify(checkcode);
			}
			else
			{
				verify.setVerify(checkcode);
			}
			vs.addVerify(verify);
			model.addAttribute("user",lv);
		}
		return "signup";
	}
	@PostMapping("/dealregister")
	 private String checkUser(@ModelAttribute LoginVo lv,Model model) 
	 {
		System.out.println("dealregister");
		System.out.println("message:"+lv.getUid()+lv.getEmail()+lv.getCheckcode());
		User user=new User();
		System.out.println("uid is "+lv.getUid());
		user=us.findById(lv.getUid());
			if(user!=null)
			{
				return user_exist_url;
			}
			else
			{
				Verify verify=new Verify();
				verify=vs.getVerifyByEmail(lv.getEmail());
				if(verify==null)
				{
					System.out.println("验证码不存在  s");
					return checkcode_not_exist_url;
				}
				else
				{
					//String input_checkcode=lv.getCheckcode();
					//String standand_checkcode=verify.getVerify();
					if(lv.getCheckcode()==(verify.getVerify()))
					{
						User users=new User();
						users.setUid(lv.getUid());
						users.setEmail(lv.getEmail());
						users.setPassword(lv.getPasswd());
						us.saveUser(users);
						model.addAttribute("user",lv);
						System.out.println("注册成功 s");
						return success_url;
					}
					else
					{
						System.out.println("验证码不正确  s 您输入的验证码为s:"+lv.getCheckcode()+"正确的为s:"+verify.getVerify());
						return check_code_wrong_url;
					}
						
				}

			}
	}
	 
}
