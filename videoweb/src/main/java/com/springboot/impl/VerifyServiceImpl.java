package com.springboot.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.domain.Verify;
import com.springboot.repository.VerifyRepository;
import com.springboot.service.VerifyService;

@Service
@Component
public class VerifyServiceImpl implements VerifyService{

	@Autowired
	VerifyRepository vr;
	@Override
	public Verify getVerifyByUid(String uid) 
	{
		return vr.findById(uid).get();
		
	}
	@Override
	public int addVerify(Verify verify) {
		vr.save(verify);
		return 0;
	}
	@Override
	public int deleteVerify(Verify verify) {
		vr.delete(verify);
		return 0;
	}
	@Override
	public Verify getVerifyByEmail(String email) {
		
		return vr.findByEmail(email);
	}

}
