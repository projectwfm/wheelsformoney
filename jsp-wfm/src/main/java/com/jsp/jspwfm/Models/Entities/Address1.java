package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
@Data
@Embeddable
public class Address1 {

	private String street;
	private int doorno;
	private String area;
	
}
