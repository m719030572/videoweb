package com.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.domain.User;

@Service
public interface  UserService 
{
	public Iterable<User> findAll(); 
	public User findById(String id);
	public User findByAccount(String account);
	public User findByUserid(String userid);
	public User findByUid(String uid);
	public List<User>findSmallUserList(int page);
	public int saveUser(User user);
	public void setShutupFlag(String uid,int state);
	public void setBannedFlag(String uid,int state);
}
