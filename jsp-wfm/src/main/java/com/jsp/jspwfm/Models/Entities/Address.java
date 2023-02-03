package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Address {
	

	
	public Address()
	{
		System.out.println("address class executing");
	}
	public Address(String city, String state, int pincode, String country, Address1 address1) {
		super();
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
		this.address1 = address1;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", pincode=" + pincode + ", country=" + country
				+ ", address1=" + address1 + "]";
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Address1 getAddress1() {
		return address1;
	}
	public void setAddress1(Address1 address1) {
		this.address1 = address1;
	}
	private String city;
	private String state;
	private int pincode;
	private String country;
	@Embedded
	private Address1 address1;

	
	
	

}
