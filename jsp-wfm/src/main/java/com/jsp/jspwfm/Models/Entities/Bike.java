package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Bike 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long bike_id;
//	@Lob
//	private String pic;
	private String reg_no;
	private String companyname;
	private int enginecc;
	private double weight;
	private double mileage;
	private double fueltankcapacity;
	private double price;
	private String color;
	private String fueltype;
	private String model;
	private long merchant_ids;
	private String sell_rent;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="imgId")
//	@JoinTable(name="bikeimages",
//	joinColumns= {@JoinColumn(name="image")},inverseJoinColumns= {@JoinColumn(name="imageid")})
	private Image image;
	
	public Bike()
	{
		
	}

	public long getBike_id() {
		return bike_id;
	}

	public void setBike_id(long bike_id) {
		this.bike_id = bike_id;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public int getEnginecc() {
		return enginecc;
	}

	public void setEnginecc(int enginecc) {
		this.enginecc = enginecc;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public double getFueltankcapacity() {
		return fueltankcapacity;
	}

	public void setFueltankcapacity(double fueltankcapacity) {
		this.fueltankcapacity = fueltankcapacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getMerchant_ids() {
		return merchant_ids;
	}

	public void setMerchant_ids(long merchant_ids) {
		this.merchant_ids = merchant_ids;
	}

	public String getSell_rent() {
		return sell_rent;
	}

	public void setSell_rent(String sell_rent) {
		this.sell_rent = sell_rent;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Bike(long bike_id, String reg_no, String companyname, int enginecc, double weight,
			double mileage, double fueltankcapacity, double price, String color, String fueltype, String model,
			long merchant_ids, String sell_rent, Image image) {
		super();
		this.bike_id = bike_id;
		this.reg_no = reg_no;
		this.companyname = companyname;
		this.enginecc = enginecc;
		this.weight = weight;
		this.mileage = mileage;
		this.fueltankcapacity = fueltankcapacity;
		this.price = price;
		this.color = color;
		this.fueltype = fueltype;
		this.model = model;
		this.merchant_ids = merchant_ids;
		this.sell_rent = sell_rent;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Bike [bike_id=" + bike_id + ", reg_no=" + reg_no + ", companyname=" + companyname + ", enginecc="
				+ enginecc + ", weight=" + weight + ", mileage=" + mileage + ", fueltankcapacity=" + fueltankcapacity
				+ ", price=" + price + ", color=" + color + ", fueltype=" + fueltype + ", model=" + model
				+ ", merchant_ids=" + merchant_ids + ", sell_rent=" + sell_rent + ", image=" + image + "]";
	}
	
	
	

}
