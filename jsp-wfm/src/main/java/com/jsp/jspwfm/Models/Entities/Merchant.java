package com.jsp.jspwfm.Models.Entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

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

	public Merchant() 
	{
		System.out.println("Merchant entity class excuted");
	}

	public long getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getMerchantname() {
		return merchantname;
	}

	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public MerchantAddress getMaddress() {
		return maddress;
	}

	public void setMaddress(MerchantAddress maddress) {
		this.maddress = maddress;
	}

	public Merchant(long merchant_id, String merchantname, String email, long mobile, String password, String gst_no,
			String pan_no, MerchantAddress maddress) {
		super();
		this.merchant_id = merchant_id;
		this.merchantname = merchantname;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.gst_no = gst_no;
		this.pan_no = pan_no;
		this.maddress = maddress;
	}	


}
