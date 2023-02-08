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
public class Car 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long car_id;
	private long merchant_ids;
	private String carname;
	private String reg_no;
	private String company;
	private String model;
	private String type;
	private String climatisation;
	private int no_seats;
	private String color;
	private double price;
	private double mileage;
	private double engineCC;
	private double power;	
	private String fueltype;
	private long fueltankcapacity;
	private String gearbox;
	private String emission_sticker;
	private String first_reg;
	private String sell_rent;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="imgId")
	private Image image;
	
	
}
