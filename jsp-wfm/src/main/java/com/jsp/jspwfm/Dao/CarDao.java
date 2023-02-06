package com.jsp.jspwfm.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.jspwfm.Models.Entities.Car;

public interface CarDao extends JpaRepository<Car, Long>
{

	@Query(value="SELECT * FROM Car WHERE reg_no= :reg_no", nativeQuery = true)
	public Car getByReg_no(String reg_no);
	
	
	@Query(value="SELECT * FROM Car WHERE merchant_ids= :merchant_ids", nativeQuery = true)
	public List<Car> findAllById(long merchant_ids);
}
