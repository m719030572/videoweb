package com.springboot.controller;

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
import com.springboot.repository.UserRepository;
import com.springboot.service.MailService;
import com.springboot.vo.FindPasswdVo;
import com.springboot.vo.LoginVo;

@Controller
@RequestMapping("/passwd")
public class PasswdController 
{
    @Autowired
    private MailService mailService;
	@Autowired
	private UserRepository ur;

	@GetMapping("/forgetpasswd")
	public String fogetPasswd(Model model)
	{
		model.addAttribute("user",new FindPasswdVo());
		return "forgetpw";
	}
	@PostMapping("/getverify")
	public String sendCheckCode(Model model,@ModelAttribute FindPasswdVo fv,HttpServletRequest request)
	{
		User user=ur.findById(fv.getUid()).orElse(null);
		if(user!=null)
		{
			System.out.println(user);
			System.out.println(user.getUid());
			System.out.println(user.getEmail());
			HttpSession session=request.getSession();
			int check_code=mailService.sendCheckCode(user.getEmail());
			session.setAttribute(fv.getUid()+"email",check_code); 
			session.setAttribute("uid",fv.getUid()); 
			model.addAttribute("user", fv);
			return "forgetpw";
		}
		else
		{
			return "/forgetpasswd?user=-1";
		}
	}
	@PostMapping("/dealforgetpasswd")
	public String dealForgetPasswd(Model model,@ModelAttribute FindPasswdVo fv,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		System.out.println(fv.getUid());
		fv.setUid((String)session.getAttribute("uid"));
		System.out.println(fv.getUid());
		if(fv.getUid()==null)
		{
			return "/forgetpw";
		}
		User user=ur.findById(fv.getUid()).get();
		if(user!=null)
		{
			System.out.println(session.getAttribute(fv.getUid()+"email"));
			System.out.println(fv.getCheckcode());
			int checkcode=(int)session.getAttribute(fv.getUid()+"email");
			int dcheckcode=Integer.parseInt(fv.getCheckcode());
			if(checkcode==dcheckcode)
			{
				user.setUserid(fv.getUid());
				user.setPassword(fv.getNewpasswd());
				System.out.println("success");
//				ur.saveAndFlush(user);
				ur.save(user);
				model.addAttribute("user", new LoginVo());
				return "redirect:/index";
			}
			else
			{
				System.out.println("error");
				return "checkcodewrong";
			}

		}
		else
		{
			return "fogetpasswd?user=-1";
		}
	}

	
	
	@GetMapping("/setpasswd")
	public String setPasswd(Model model,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		session.setAttribute("url", request.getRequestURI());
		model.addAttribute("user",new FindPasswdVo());
		return "/modify-passwd";
	}
	@PostMapping("/dealsetpasswd")
	public String dealSetPasswd(Model model,HttpServletRequest request,@ModelAttribute FindPasswdVo fv)
	{
		HttpSession session=request.getSession();
		//String uid=(String)session.getAttribute("userid");
		String uid=fv.getUid();
		User user=ur.findById(uid).get();
		user.setPassword(fv.getPasswd());
//		ur.saveAndFlush(user);
		ur.save(user);
		model.addAttribute("user", new LoginVo());
		return "redirect:/login/login";
	}
}
