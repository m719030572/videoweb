package com.springboot.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.domain.Collection;
import com.springboot.domain.Comment;
import com.springboot.domain.Uaction;
import com.springboot.domain.User;
import com.springboot.domain.Video;
import com.springboot.repository.CollectionRepository;
import com.springboot.repository.CommentRepository;
import com.springboot.repository.UactionRepository;
import com.springboot.repository.VideoRepository;
import com.springboot.service.BarrageService;
import com.springboot.service.CommentService;
import com.springboot.service.UserService;
import com.springboot.service.VideoService;

@Controller
@RequestMapping("/video")
public class VideoController 
{
	@Autowired
	CommentRepository cr;
	@Autowired
	CommentService cs;
	@Autowired
	VideoRepository vr;
	@Autowired
	CollectionRepository clr;
	@Autowired
	BarrageService bs;
	@Autowired
	VideoService vs;
	@Autowired
	UserService us;
	@Autowired
	UactionRepository ar;
	//这里会用get方法把视频id传进来
	@GetMapping("play")
	public String SendVideoMessage(HttpServletRequest request,Model model)
	{
		String vid=request.getParameter("vid");
		HttpSession session=request.getSession();
		if(session.getAttribute("uid")==null)
		{
			Uaction action=new Uaction();
			action.setUid("test");
			action.setVid("test");
			model.addAttribute("action", action);
		}
		else
		{
			String uid=session.getAttribute("uid").toString();
			if(ar.findByUidAndVid(uid, vid)==null)
			{
				Uaction action=new Uaction();
				action.setUid(uid);
				action.setVid(vid);
				ar.save(action);
				model.addAttribute("action", action);
			}
			else
			{
				Uaction action=ar.findByUidAndVid(uid, vid);
				model.addAttribute("action", action);
			}	
		}
		
		//
		//仅供测试
		if(vid==null)
			vid=Integer.toString(2);
		//
		Video video =new Video();
		User user=new User();
		List<Video> videolist=new ArrayList<>();
		Comment comment=new Comment();
		comment.setVid(vid);
		List<Comment> commentlist=new ArrayList<>();
		commentlist=cs.findAllCommentByVid(vid);
		video=vs.findByVid(vid);
		video.setBrocount(video.getBrocount()+1);
		vr.save(video);
		videolist=vr.findByCategory(video.getCategory());
		videolist.addAll(vr.findByWithoutCategory(video.getCategory()));

		System.out.println("vid:"+video.getVid());
		System.out.println("uid:"+video.getUid());

		user=us.findById(video.getUid());
		model.addAttribute("video", video);
		//接着发送评论表
		model.addAttribute("videolist", videolist);
		model.addAttribute("commentlist",commentlist);
		model.addAttribute("comment",comment);
		model.addAttribute("user",user);
		return "video-display";
	}
	@RequestMapping("sendcomment")
	public String sendComment(@ModelAttribute Comment comment,Model model,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		if(session.getAttribute("uid")==null)
			return "redirect:/login/login";
		else
		{
			System.out.println(comment);
			comment.setTime(new Timestamp(new Date().getTime()));
			comment.setUid(session.getAttribute("uid").toString());
			cr.save(comment);
			request.setAttribute("vid", comment.getVid());
			System.out.println(comment);
			return SendVideoMessage(request,model);
		}
	}
	
	@GetMapping("operator")
	public String videoOperator(Model model,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		if(session.getAttribute("uid")==null)
			return "redirect:/login/login";
		else
		{
			String uid=session.getAttribute("uid").toString();
			String vid=request.getParameter("vid");
			String type=request.getParameter("type");

			Video video=new Video();
			Collection collection=new Collection();
			Uaction action=new Uaction();
			switch (type)
			{
			case "favour":video=vs.findByVid(vid);video.setPracount(video.getPracount()+1);vr.save(video);action=ar.findByUidAndVid(uid, vid);action.setEnshrine(1);ar.save(action);break;
			case "dislike":video=vs.findByVid(vid);video.setTrecount(video.getTrecount()+1);vr.save(video);action=ar.findByUidAndVid(uid, vid);action.setDislike(1);ar.save(action);break;
			case "collection":collection=clr.findByUidAndVid(uid, vid);{collection=new Collection();collection.setUid(uid);collection.setVid(vid);clr.save(collection);video=vs.findByVid(vid);video.setColcount(video.getColcount()+1);vr.save(video);}action=ar.findByUidAndVid(uid, vid);action.setDownload(1);ar.save(action);break;
			case "disfavour":video=vs.findByVid(vid);video.setPracount(video.getPracount()-1);vr.save(video);action=ar.findByUidAndVid(uid, vid);action.setEnshrine(0);ar.save(action);break;
			case "disdislike":video=vs.findByVid(vid);video.setTrecount(video.getTrecount()-1);vr.save(video);action=ar.findByUidAndVid(uid, vid);action.setDislike(0);ar.save(action);break;
			case "discollection":collection=clr.findByUidAndVid(uid, vid);{collection=new Collection();collection.setUid(uid);collection.setVid(vid);clr.delete(collection);video=vs.findByVid(vid);video.setColcount(video.getColcount()-1);vr.save(video);}action=ar.findByUidAndVid(uid, vid);action.setDownload(0);ar.save(action);break;
			}
		}
			return SendVideoMessage(request,model);
		}
		@GetMapping("operatorc")
		public String videoOperatorc(Model model,HttpServletRequest request)
		{
			HttpSession session=request.getSession();
			if(session.getAttribute("uid")==null)
				return "redirect:/login/login";
			else
			{
				String uid=session.getAttribute("uid").toString();
				String vid=request.getParameter("vid");
				String type=request.getParameter("type");
				int cid=Integer.parseInt(request.getParameter("cid"));	
				Comment comment=new Comment();
				switch (type)
				{
				case "favour":comment=cr.findByCid(cid);comment.setFavour(comment.getFavour()+1);cr.save(comment);break;
				case "dislike":comment=cr.findByCid(cid);comment.setDislike(comment.getDislike()+1);cr.save(comment);break;
				}
				return SendVideoMessage(request,model);
			}
		
	}
}
