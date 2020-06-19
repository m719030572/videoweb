package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.domain.Video;
import com.springboot.repository.VideoRepository;
import com.springboot.service.VideoService;

@Controller

public class IndexController {
	@Autowired
	private VideoRepository vr;
	@Autowired
	private VideoService vs;
	@GetMapping("index")
	public String Index(Model model)
	{
		List<Video> videoList=vr.findAll();
		model.addAttribute("videolist",videoList );
		return "indexd";
	}
	@GetMapping("index/*")
	public String IndexAll(Model model)
	{
		List<Video> videoList=vr.findAll();
		model.addAttribute("videolist",videoList );
		return "indexd";
	}
	@GetMapping("/")
	public String IndexDefault(Model model)
	{
		List<Video> videoList=vr.findAll();
		model.addAttribute("videolist",videoList );
		return "indexd";
	}
	@RequestMapping("search.do")
	public String search(Model model,HttpServletRequest request)
	{
		String vid=request.getParameter("vid");
		List<Video> videoList=new ArrayList<>();
		Video video=vs.findByVid(vid);
		videoList.add(video);
		model.addAttribute("videolist",videoList );
		return "indexd";
	}
}
