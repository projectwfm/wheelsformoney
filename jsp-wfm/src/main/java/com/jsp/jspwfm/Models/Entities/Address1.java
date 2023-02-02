package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address1 {

	private String street;
	private int doorno;
	private String area;

}
