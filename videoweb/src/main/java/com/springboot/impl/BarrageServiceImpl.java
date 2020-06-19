package com.springboot.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.domain.Barrage;
import com.springboot.repository.BarrageRepository;
import com.springboot.service.BarrageService;

@Service
@Component
public class BarrageServiceImpl implements BarrageService {
	@Autowired
	BarrageRepository br;
	
	@Override
	public List<Barrage>  findAllBarrageByVid(String vid)
	{
		return br.findByVid(vid);
	
	}

}
