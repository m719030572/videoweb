package com.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.domain.Barrage;

@Service
public interface BarrageService {
	//这项服务可以按照视频的id来获取评论表
	public List<Barrage> findAllBarrageByVid(String vid);
}
