package com.jsp.jspwfm.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.jsp.jspwfm.Models.Entities.Merchant;



public interface MerchantDao extends JpaRepository<Merchant, Long>
{
	 @Query(value = "SELECT * FROM Merchant WHERE email = :email", nativeQuery = true)
	 public Merchant findByEmail(String email);
	 
	 @Query(value = "SELECT * FROM Merchant WHERE email= :merchantnameOremail OR merchantname= :merchantnameOremail", nativeQuery = true)
	 public Merchant getByMerchantnameOrEmail(String merchantnameOremail);
	 
	 @Query(value = "SELECT * FROM Merchant WHERE merchantname = :merchantname", nativeQuery = true)
	 public Merchant getByMerchantname(String merchantname);

}
