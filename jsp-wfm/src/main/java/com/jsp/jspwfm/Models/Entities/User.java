package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@ToString
@Entity
@Table(name = "Users")
public class User {

	public User() {
		System.out.println("user class executing");
	}

	

	public User(Address address, long user_id, String username, String password, String email, String dob,
			String gender, long phno) {
		super();
		this.address = address;
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
		this.phno = phno;
	}




//		this.user_id = user_id;
//	}
//	public String getUsername() {
//		return username;
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getDob() {
//		return dob;
//	}
//	public void setDob(String dob) {
//		this.dob = dob;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public long getPhno() {
//		return phno;
//	}
//	public void setPhno(long phno) {
//		this.phno = phno;
//	}



	@Embedded
	private Address address;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long user_id;
	private String username;
	private String password;
	private String email;
	private String dob;
	private String gender;
	private long phno;
}