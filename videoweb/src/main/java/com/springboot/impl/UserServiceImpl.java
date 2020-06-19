package com.springboot.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.domain.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;

@Service
@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository ur;
	
	@Override
	public Iterable<User> findAll() {
		return ur.findAll();
	}

	@Override
	public User findById(String id) {
		try
		{
			return ur.findById(id).get();
		}
		catch(NoSuchElementException e)
		{
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public User findByAccount(String account)
	{
		return ur.findByAccount(account);
	}
	@Override
	public User findByUserid(String userid)
	{
		return ur.findByUserid(userid);
	}

	@Override
	public int saveUser(User user) {
		ur.save(user);
		return 0;
	}

	@Override
	public List<User> findSmallUserList(int page) 
	{
		return ur.findSmallUserList(page);
		
	}

	@Override
	public void setShutupFlag(String uid, int state) {
		User user=new User();
		user=ur.findById(uid).get();
		user.setShutup(state);
		ur.save(user);
	}

	@Override
	public void setBannedFlag(String uid, int state) {
		User user=new User();
		user=ur.findById(uid).get();
		user.setBanned(state);
		ur.save(user);
		
	}

	@Override
	public User findByUid(String uid) {
		return ur.findByUid(uid);
	}

}
