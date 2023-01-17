package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address1 {
	
	public Address1()
	{
		System.out.println("Address1 class executing");
	}
	
	public Address1(String street, int doorno, String area) {
		super();
		this.street = street;
		this.doorno = doorno;
		this.area = area;
	}
	@Override
	public String toString() {
		return "Address1 [street=" + street + ", doorno=" + doorno + ", area=" + area + "]";
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getDoorno() {
		return doorno;
	}
	public void setDoorno(int doorno) {
		this.doorno = doorno;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	private String street;
	private int doorno;
	private String area;

}
