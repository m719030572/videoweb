package com.springboot.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.UserFollow;
import com.springboot.repository.UserFollowRepository;
import com.springboot.service.UserFollowService;

@Service
public class UserFollowServiceImpl implements UserFollowService {

	@Autowired
	UserFollowRepository ufr;
	@Override
	public List<UserFollow> findByUid(String uid) {
		// TODO Auto-generated method stub
		return ufr.findByUid(uid);
	}

}
