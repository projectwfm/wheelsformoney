package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Otp 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int count;
	private String email;
	private int otp;

}
