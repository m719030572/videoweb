package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.springboot.domain.User;;

@Repository
public interface UserRepository extends JpaRepository<User,String>
{
	User findByName(String name);
	User findByAccount(String account);
	User findByUserid(String userid);
	User findByUid(String uid);
	@Query(value="select * from user limit 0,?1",nativeQuery=true)
	List<User> findSmallUserList(int page);
	
}