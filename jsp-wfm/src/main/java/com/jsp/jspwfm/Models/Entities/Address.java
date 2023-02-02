package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

	private String city;
	private String state;
	private int pincode;
	private String country;
	@Embedded
	private Address1 address1;


}
