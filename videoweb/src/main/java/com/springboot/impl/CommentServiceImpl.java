package com.springboot.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.domain.Comment;
import com.springboot.repository.CommentRepository;
import com.springboot.service.CommentService;

@Service
@Component
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository cr;
	@Override
	public List<Comment> findAllCommentByVid(String vid) {
		// TODO Auto-generated method stub
		return cr.findByVid(vid);
	}
	@Override
	public List<Comment> findAllCommentByUid(String uid) {
		return cr.findByUid(uid);
	}
	@Override
	public List<Comment> findAllCommentByUVid(String vid, String uid) {
		// TODO Auto-generated method stub
		return cr.findByVidAndUid(vid, uid);
	}
	@Override
	public Map<String,List<Comment>> findCommentAsMapListByVid(String vid) {
		List<Comment> commentlist=new ArrayList<Comment>();
		commentlist=cr.findByVid(vid);
		Map<String,List<Comment>> finalcommentlist=new HashMap<>();
		for(Comment com:commentlist)
		{
			if(com.getCommentat()==0)
			{
				//那么他是一级评论
				List<Comment> tmplist=new ArrayList<>();
				finalcommentlist.put(Integer.toString(com.getCid()), tmplist);
				//添加到一级评论列表中
			}
		}
		for(Comment com:commentlist)
		{
			if(com.getCommentat()!=0)
			{
				//那么他是二级评论
				finalcommentlist.get(Integer.toString(com.getCommentat())).add(com);
				//添加到一级评论列表中
			}
		}
		return finalcommentlist;
	}
	@Override
	public List<Comment> findAllCommentAsList() {
		return cr.findAll();
	}
	@Override
	public void deleteByCid(int cid) {
		cr.deleteById(cid);
		
	}
	@Override
	public void setGood(int cid) {
		// TODO Auto-generated method stub
		Comment com=new Comment();
		com=cr.findById(cid).get();
		com.setIsgood(1);
		cr.save(com);
	}
	@Override
	public void cancelGood(int cid) {
		// TODO Auto-generated method stub
		Comment com=new Comment();
		com=cr.findById(cid).get();
		com.setIsgood(0);
		cr.save(com);
	}
	@Override
	public List<Comment> findCommentByDislikeHigh() {
		// TODO Auto-generated method stub
		
		return cr.findDislike();
	}

}
