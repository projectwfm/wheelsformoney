package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Otp 
{
	public Otp() {
		System.out.println("Otp class executing");
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int count;
	private String email;
	private int otp;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public Otp(int count,String email, int otp) {
		super();
		this.count=count;
		this.email = email;
		this.otp = otp;
	}
	
}
