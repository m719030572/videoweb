package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.domain.UserFollow;
@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow,Integer> 
{
	public List<UserFollow> findByUid(String uid);
}
