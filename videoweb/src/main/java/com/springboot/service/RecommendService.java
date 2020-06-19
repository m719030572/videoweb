package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.Video;
import com.springboot.repository.RecommendRepository;

@Service
public interface RecommendService 
{
	public List<Integer> getDefaultRecommendList();
	public List<Integer> getListAsUid(String uid);
	public void buildRecommendList();
}
