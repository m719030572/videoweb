package com.springboot.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video,String>
{
	Video findByName(String name);
	Video findByVid(String vid);
	@Query(value="select * from video where videostate =1",nativeQuery=true)
	List<Video> findVideoNoAudit();
	List<Video> findByCategory(String category);
	@Query(value="select * from video where category !=?1",nativeQuery=true)
	List<Video> findByWithoutCategory(String category);
}
