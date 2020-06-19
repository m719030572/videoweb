package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.domain.Comment;
import com.springboot.domain.User;
import com.springboot.domain.Video;
import com.springboot.repository.CommentRepository;
import com.springboot.service.CommentService;
import com.springboot.service.RecommendService;
import com.springboot.service.UserService;
import com.springboot.service.VideoService;

//管理员页面
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService us;
	@Autowired
	CommentService cs;
	@Autowired
	CommentRepository cr;
	@Autowired
	VideoService vs;
	@Autowired
	RecommendService rs;
	@GetMapping("index")
	public String index()
	{
		return "admin_index";
	}
	//用户管理
	@GetMapping("useradmin")
	public String userAdmin(Model model,HttpServletRequest request)
	{
		List<User> userlist=new ArrayList<User>();
		//传入前100号数据
		
		if((request.getParameter("uid")!=null)&&(!request.getParameter("uid").equals("null")))
		{
			userlist.add(us.findByUid(request.getParameter("uid")));
		}
		else
			userlist=us.findSmallUserList(100);	
		model.addAttribute("userlist", userlist);
		return "admin_user";
	}
	@GetMapping("useradmin/operator")
	public String userOperator(Model model,HttpServletRequest request)
	{
		String type=request.getParameter("type");
		String uid=request.getParameter("uid");
		switch(type)
		{
		case "shutup"		:us.setShutupFlag(uid, 1);;break;
		case "disshutup" 	:us.setShutupFlag(uid,0);break;
		case "banned"		:us.setBannedFlag(uid, 1);;break;
		case "disbanned"	:us.setBannedFlag(uid, 0);break;
		case "send"			:break;
		case "message":System.out.println(request.getAttribute("message"));
		default:break;
		}
		request.removeAttribute("uid");
		return "redirect:/admin/useradmin";
	}
	@GetMapping("barrageadmin")
	public String commentAdmin(Model model)
	{
		return "admin_barrage";
	}
	@GetMapping("commentadmin")
	public String barrageAdmin(Model model)
	{
		System.out.println("commentadmin!!");
		return "admin_comment";
	}
	@GetMapping("commentd")
	public String commentDisplay(Model model,HttpServletRequest request)
	{
		List<Comment> commentlist=new ArrayList<>();
		if((request.getParameter("uid")!=null)&&(!request.getParameter("uid").equals("null")))
		{
			if((request.getParameter("vid")!=null)&&(!request.getParameter("vid").equals("null")))
				commentlist=cr.findByVidAndUid(request.getParameter("vid"),request.getParameter("uid"));
			else
				commentlist=cr.findByUid(request.getParameter("uid"));
		}
		else 
		{
			if((request.getParameter("vid")!=null)&&(!request.getParameter("vid").equals("null")))
				commentlist=cr.findByVid(request.getParameter("vid"));
			else
				commentlist=cs.findAllCommentAsList();
		}
		model.addAttribute("commentlist",commentlist);
		return "commentd";
	}
	@GetMapping("commentadmin/operator")
	public String commentOperator(HttpServletRequest request,Model model)
	{
		String type=request.getParameter("type");
		int cid=Integer.parseInt(request.getParameter("cid"));
		switch(type)
		{
		case "delete"	:cs.deleteByCid(cid);break;
		case "disgood" :cs.cancelGood(cid);break;
		case "good"		:cs.setGood(cid);break;
		default:break;
		}
		return commentDisplay(model,request);
	}
	@GetMapping("badcheck")
	public String badCheck(Model model)
	{
		List<Comment> commentlist=new ArrayList<>();
		commentlist=cs.findCommentByDislikeHigh();
		model.addAttribute("commentlist",commentlist);
		return "badcheck";
	}
	@GetMapping("commentadmin/badoperator")
	public String badCheckOperator(HttpServletRequest request,Model model)
	{
		int cid=Integer.parseInt(request.getParameter("cid"));
		cs.deleteByCid(cid);
		return badCheck(model);
	}
	@GetMapping("videoadmin")
	public String videoAdmin(Model model)
	{
		return "admin_video";
	}
	@GetMapping("videod")
	public String videoDisplay(Model model)
	{
		List<Video> videolist=new ArrayList<>();
		videolist=vs.findAll();
		model.addAttribute("videolist",videolist);
		return "videod";
	}
	@GetMapping("videoadmin/operator")
	public String videoOperator(HttpServletRequest request,Model model)
	{
		String type=request.getParameter("type");
		String vid=request.getParameter("vid");
		switch(type)
		{
		case "soldout"				:vs.setVideoState(vid, 3);break;
		case "soldup" 				:vs.setVideoState(vid, 4);break;
		case "opencomment"	:vs.setCommentPermission(vid, 0);break;
		case "closecomment"	:vs.setCommentPermission(vid, 1);break;
		case "delete"		:vs.deleteVideo(vid);break;
		default:break;
		}
		return videoDisplay(model);
	}
	
	@GetMapping("videoaudit")
	public String videoAudit(Model model)
	{
		List<Video> videolist=new ArrayList<>();
		videolist=vs.findVideoAsAudit();
		model.addAttribute("videolist",videolist);
		return "videoaudit";
	}
	
	@GetMapping("videoadmin/aoperator")
	public String auditOperator(HttpServletRequest request,Model model)
	{
		String type=request.getParameter("type");
		String vid=request.getParameter("vid");
		switch(type)
		{
		case "pass"				:vs.setVideoState(vid, 2);break;
		case "reject" 				:vs.setVideoState(vid, 0);break;
		case "delete"		:vs.deleteVideo(vid);break;
		default:break;
		}
		return videoAudit(model);
	}
	
	@GetMapping("permissionadmin")
	public String permissionAdmin(Model model)
	{
		return "admin_permission";
	}
	@GetMapping("recommendadmin")
	public String recommendAdmin(HttpServletRequest request,Model model)
	{
		
		return "admin_recommend";
	}
	@GetMapping("recommendadmin/operator")
	public String recommendOperator(HttpServletRequest request,Model model)
	{
		String type=request.getParameter("type");
		List<Integer>videolistint=new ArrayList<>();
		switch(type)
		{
		case "default"				:videolistint=rs.getDefaultRecommendList();model.addAttribute("videolist",videolistint);request.setAttribute("uid", "defaullt");break;
		case "user" 				:String uid=request.getParameter("uid");if(uid==null)uid=Integer.toString(100605);videolistint=rs.getListAsUid(uid);model.addAttribute("videolist",videolistint);request.setAttribute("uid", uid);break;
		case "build"		:rs.buildRecommendList();request.setAttribute("flag", 1);return "admin_recommend";
		default:break;
		}
		model.addAttribute("type",type);
		
		return "recommend-display";
	}
	@GetMapping("test")
	public String test(Model model)
	{
		return "test";
	}
}
