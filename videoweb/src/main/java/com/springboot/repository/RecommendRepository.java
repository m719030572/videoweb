package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Recommend;
@Repository
public interface RecommendRepository extends JpaRepository<Recommend,Integer> 
{
	public List<Recommend> findByUid(String uid);
}
