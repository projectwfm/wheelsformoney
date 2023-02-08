package com.jsp.jspwfm.Models.Entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
@Entity
public class Merchant 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long merchant_id;
	private String merchantname;
	private String email;
	private long mobile;
	private String password;
	private String gst_no;
	private String pan_no;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="maddress")
	private MerchantAddress maddress;

	
}
