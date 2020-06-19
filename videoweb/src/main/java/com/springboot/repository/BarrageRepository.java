package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.domain.Barrage;

public interface BarrageRepository extends JpaRepository<Barrage,String> {
	List<Barrage> findByVid(String vid);

}
