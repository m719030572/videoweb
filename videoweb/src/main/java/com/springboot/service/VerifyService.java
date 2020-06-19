package com.springboot.service;

import org.springframework.stereotype.Service;

import com.springboot.domain.User;
import com.springboot.domain.Verify;

@Service
public interface VerifyService 
{
	public Verify getVerifyByEmail(String uid);
	public Verify getVerifyByUid(String uid);
	public int addVerify(Verify verify);
	public int deleteVerify(Verify verify);
}
