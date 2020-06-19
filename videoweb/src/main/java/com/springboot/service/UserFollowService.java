package com.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.domain.UserFollow;

@Service
public interface UserFollowService {
	List<UserFollow> findByUid(String uid);
}
