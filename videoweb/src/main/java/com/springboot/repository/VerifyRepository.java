package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Verify;

@Repository
public interface VerifyRepository extends JpaRepository<Verify,String>
{
	Verify findByEmail(String email);
}
