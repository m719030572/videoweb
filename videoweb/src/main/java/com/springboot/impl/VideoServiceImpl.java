package com.springboot.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.domain.Video;
import com.springboot.repository.VideoRepository;
import com.springboot.service.VideoService;

@Service
@Component
public class VideoServiceImpl implements VideoService 
{
	@Autowired
	public VideoRepository vr;
	
	@Override
	public Video findByName(String name)
	{
		return vr.findByName(name);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return vr.findAll();
	}

	@Override
	public void setVideoState(String vid, int state) {
		Video video=new Video();
		video=vr.findById(vid).get();
		video.setVideostate(state);
		vr.save(video);
	}

	@Override
	public void setCommentPermission(String vid, int permission) {
		Video video=new Video();
		video=vr.findById(vid).get();
		video.setCompermission(permission);
		vr.save(video);
	}

	@Override
	public void deleteVideo(String vid) {
		vr.deleteById(vid);
	}

	@Override
	public List<Video> findVideoAsAudit() {
		return vr.findVideoNoAudit();
		
	}

	@Override
	public Video findByVid(String vid) {
		return vr.findByVid(vid);
	}
}
