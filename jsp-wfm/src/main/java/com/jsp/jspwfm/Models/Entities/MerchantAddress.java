package com.jsp.jspwfm.Models.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(value= {"hibernateLazyInitializer"})
public class MerchantAddress 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int merchantaddress_id;
	private String organisation_name;
	private String street;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private int pincode;
	
}
