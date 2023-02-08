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