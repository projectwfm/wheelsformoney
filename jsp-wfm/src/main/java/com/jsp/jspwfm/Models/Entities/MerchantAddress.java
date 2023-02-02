package com.jsp.jspwfm.Models.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


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
	
	public MerchantAddress()
	{
		System.out.println("Merchant Address entity class excuted");
	}
	
	
	public MerchantAddress(int merchantaddress_id, String organisation_name, String street, String landmark,
			String city, String state, String country, int pincode) {
		super();
		this.merchantaddress_id = merchantaddress_id;
		this.organisation_name = organisation_name;
		this.street = street;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	public int getMerchantaddress_id() {
		return merchantaddress_id;
	}
	public void setMerchantaddress_id(int merchantaddress_id) {
		this.merchantaddress_id = merchantaddress_id;
	}
	public String getOrganisation_name() {
		return organisation_name;
	}
	public void setOrganisation_name(String organisation_name) {
		this.organisation_name = organisation_name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
}
