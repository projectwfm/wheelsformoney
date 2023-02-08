package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;
@Data
@Embeddable
public class Address {
	
	private String city;
	private String state;
	private int pincode;
	private String country;
	@Embedded
	private Address1 address1;

}
