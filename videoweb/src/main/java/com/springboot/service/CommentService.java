package com.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.domain.Comment;

@Service
public interface CommentService {
	//这项服务可以按照视频的id来获取评论表
	public List<Comment> findAllCommentByVid(String vid);
	public List<Comment> findAllCommentByUid(String uid);
	public List<Comment> findAllCommentByUVid(String vid,String uid);
	public Map<String,List<Comment>> findCommentAsMapListByVid(String vid);
	public List<Comment> findAllCommentAsList();
	public void deleteByCid(int cid);
	public void setGood(int cid);
	public void cancelGood(int cid);
	public List<Comment> findCommentByDislikeHigh();
}
