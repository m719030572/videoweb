package com.springboot.repository;

import org.springframework.stereotype.Repository;

import com.springboot.domain.Collection;
import com.springboot.domain.Uhistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface HistoryRepository extends JpaRepository <Uhistory,Integer>
{
	List<Uhistory> findByUid(String uid);
	Uhistory findByUidAndVid(String uid,String vid);
	void deleteByUidAndVid(String uid,String vid);
}
