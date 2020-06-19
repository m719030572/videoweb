package com.springboot.repository;

import org.springframework.stereotype.Repository;

import com.springboot.domain.Collection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CollectionRepository extends JpaRepository <Collection,Integer>
{
	List<Collection> findByUid(String uid);
	Collection findByUidAndVid(String uid,String vid);
	void deleteByUidAndVid(String uid,String vid);
}
