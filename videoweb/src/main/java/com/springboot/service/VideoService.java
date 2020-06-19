package com.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.domain.Video;

@Service
public interface VideoService 
{
	
	public Video findByName(String name);
	public List<Video> findAll();
	public void setVideoState(String vid,int state);
	public void setCommentPermission(String vid,int permission);
	public void deleteVideo(String vid);
	public List<Video> findVideoAsAudit();
	public Video findByVid(String vid);
}
