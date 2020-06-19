package com.springboot.repository;

import org.springframework.stereotype.Repository;

import com.springboot.domain.Myvideo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface MyvideoRepository extends JpaRepository <Myvideo,Integer>
{
	List<Myvideo> findByUid(String uid);
	Myvideo findByUidAndVid(String uid,String vid);
	void deleteByUidAndVid(String uid,String vid);
}
