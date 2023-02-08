package com.jsp.jspwfm.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.jspwfm.Models.Entities.Otp;

public interface OtpRepository extends JpaRepository<Otp,Integer> 
{

	@Query(value = "SELECT * FROM Otp u WHERE  email= :email", nativeQuery = true)
	public Otp findByEmail(String email);
}

