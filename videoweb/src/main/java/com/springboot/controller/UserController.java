package com.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.domain.Collection;
import com.springboot.domain.Myvideo;
import com.springboot.domain.Uhistory;
import com.springboot.domain.User;
import com.springboot.domain.UserFollow;
import com.springboot.domain.Video;
import com.springboot.repository.UserFollowRepository;
import com.springboot.repository.CollectionRepository;
import com.springboot.repository.HistoryRepository;
import com.springboot.repository.MyvideoRepository;
import com.springboot.service.UserFollowService;
import com.springboot.service.UserService;
import com.springboot.service.VideoService;

import jdk.internal.jline.internal.Log;

@Controller
@RequestMapping("user")
public class UserController 
{
	@Autowired
	VideoService vs;
	@Autowired
	UserService us;
	@Autowired
	UserFollowService ufs;
	@Autowired
	UserFollowRepository ufr;
	@Autowired
	CollectionRepository cr;
	@Autowired
	HistoryRepository hr;
	@Autowired
	MyvideoRepository mr;
	@GetMapping("personal")
	public String persionalCenter(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		model.addAttribute("user", user);
		return "personal";	
	}
	@GetMapping("personal/follow")
	public String personalFollow(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		List<UserFollow> userfollowlist=new ArrayList<>();
		userfollowlist=ufs.findByUid(uid);
		model.addAttribute("userfollowlist", userfollowlist);
		model.addAttribute("user", user);
		return "personal-follow";	
	}
	@GetMapping("personal/disfollow")
	public String personalDisFollow(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		int cid=Integer.parseInt(request.getParameter("cid"));
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		ufr.deleteById(cid);
		return personalFollow(request,model);	
	}
	
	@GetMapping("personal/collection")
	public String personalCollection(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		model.addAttribute("user", user);
		List<Collection> collectionlist=new ArrayList<>();
		collectionlist=cr.findByUid(uid);
		List <Video> videolist=new ArrayList<>();
		for(Collection collection:collectionlist)
		{
			videolist.add(vs.findByVid(collection.getVid()));
		}
		model.addAttribute("videolist", videolist);
		return "personal-collection";	
	}
	
	@GetMapping("personal/discollection")
	public String personalDisCollection(HttpServletRequest request,Model model)
	{
		String uid=request.getParameter("uid");
		String vid=request.getParameter("vid");
		Collection collection=cr.findByUidAndVid(uid, vid);
		cr.deleteById(collection.getCid());
		return personalCollection(request,model);
	}
	
	@GetMapping("personal/history")
	public String personalHistory(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		model.addAttribute("user", user);
		List<Uhistory> historylist=new ArrayList<>();
		historylist=hr.findByUid(uid);
		List <Video> videolist=new ArrayList<>();
		for(Uhistory history:historylist)
		{
			videolist.add(vs.findByVid(history.getVid()));
		}
		model.addAttribute("videolist", videolist);
		return "personal-history";	
	}
	
	@GetMapping("personal/myvideo")
	public String personalMyVideo(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		model.addAttribute("user", user);
		List<Myvideo> myvideolist=new ArrayList<>();
		myvideolist=mr.findByUid(uid);
		List <Video> videolist=new ArrayList<>();
		for(Myvideo myvideo:myvideolist)
		{
			videolist.add(vs.findByVid(myvideo.getVid()));
		}
		model.addAttribute("videolist", videolist);
		return  "personal-myvideo";	
	}
	@GetMapping("personal/video")
	public String personalVideoOperator(HttpServletRequest request,Model model)
	{
		String type=request.getParameter("type");
		String uid=request.getParameter("uid");
		String vid=request.getParameter("vid");
		if(type.equals("recheck"))
		{
			vs.setVideoState(vid, 1);
		}
		if(type.equals("delete"))
		{
			mr.deleteById(mr.findByUidAndVid(uid, vid).getMid());
			vs.setVideoState(vid, 3);
		}
		return personalMyVideo(request,model);
	}
	@GetMapping("personal/message")
	public String persionalMessage(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		model.addAttribute("user", user);
		return "personal-message";	
	}
	
	@GetMapping("personal/message-alter")
	public String persionalMessageAlter(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		model.addAttribute("user", user);
		return "personal-message-alter";	
	}
	@RequestMapping("personal/message-done")
	public String persionalMessageDone(HttpServletRequest request,@ModelAttribute User user,Model model)
	{
		us.saveUser(user);
		System.out.println("is here");
		return persionalMessage(request,model);	
	}
	
	@GetMapping("personal/upload")
	public String persionalUpload(HttpServletRequest request,Model model)
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		model.addAttribute("user", user);
		return "personal-upload";	
	}
	@RequestMapping("personal/upload-submit")
	public String persionalUploadSubmit(HttpServletRequest request,Model model,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException
	{
		User user=new User();
		HttpSession session=request.getSession();
		String uid;
		if(session.getAttribute("uid")!=null)
		{
			uid=session.getAttribute("uid").toString();
		}
		else 
		{
			//测试
			uid=Integer.toString(2);
		}
		user=us.findByUid(uid);
		model.addAttribute("user", user);
		
		if(file.isEmpty())
			request.setAttribute("flag", 1);
		String fileName=file.getOriginalFilename();
		String suffixName=fileName.substring(fileName.lastIndexOf("."));
		String filePath="G:\\";
		String path =filePath+fileName;
		File dest=new File(path);
		if(!dest.getParentFile().exists())
		{
			dest.getParentFile().mkdirs();
		}
		file.transferTo(dest);
		request.setAttribute("flag", 0);
		
		
		return "personal-upload";	
	}
}
