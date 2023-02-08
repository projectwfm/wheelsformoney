package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
@Entity
public class Bike 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long bike_id;
	private String reg_no;
	private String companyname;
	private int enginecc;
	private double weight;
	private double mileage;
	private double fueltankcapacity;
	private double price;
	private String color;
	private String fueltype;
	private String model;
	private long merchant_ids;
	private String sell_rent;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="imgId")
	private Image image;
	

}
