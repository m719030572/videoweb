package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
	List<Comment> findByVid(String vid);
	List<Comment> findByUid(String uid);
	List<Comment> findByVidAndUid(String vid,String uid);
	@Query(value="select * from comment where dislike > 100",nativeQuery=true)
	List<Comment> findDislike();
	Comment findByCid(int cid);
}
