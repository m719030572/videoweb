package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.domain.Uaction;

public interface UactionRepository extends JpaRepository<Uaction,Integer>  {
	Uaction findByAid(int aid);
	Uaction findByUidAndVid(String uid,String vid);

}
