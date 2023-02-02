package com.jsp.jspwfm.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.jspwfm.Models.Entities.Bike;

public interface BikeDao  extends JpaRepository<Bike, Long>
{

	@Query(value="SELECT * FROM Bike WHERE reg_no= :reg_no", nativeQuery = true)
	public Bike getByReg_no(String reg_no);
	
	@Query(value="SELECT * FROM Bike WHERE merchant_ids= :merchant_ids", nativeQuery = true)
	public List<Bike> findAllById(long merchant_ids);
}
