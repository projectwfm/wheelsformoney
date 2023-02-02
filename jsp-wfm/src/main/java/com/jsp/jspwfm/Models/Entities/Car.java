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
public class Car 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long car_id;
	private long merchant_ids;
	private String carname;
	private String reg_no;
	private String company;
	private String model;
	private String type;
	private String climatisation;
	private int no_seats;
	private String color;
	private double price;
	private double mileage;
	private double engineCC;
	private double power;	
	private String fueltype;
	private long fueltankcapacity;
	private String gearbox;
	private String emission_sticker;
	private String first_reg;
	private String sell_rent;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="imgId")
	private Image image;
	
	public Car() {}

	public Car(long car_id, long merchant_ids, String carname, String reg_no, String company, String model, String type,
			String climatisation, int no_seats, String color, double price, double mileage, double engineCC,
			double power, String fueltype, long fueltankcapacity, String gearbox, String emission_sticker,
			String first_reg, String sell_rent, Image image) {
		super();
		this.car_id = car_id;
		this.merchant_ids = merchant_ids;
		this.carname = carname;
		this.reg_no = reg_no;
		this.company = company;
		this.model = model;
		this.type = type;
		this.climatisation = climatisation;
		this.no_seats = no_seats;
		this.color = color;
		this.price = price;
		this.mileage = mileage;
		this.engineCC = engineCC;
		this.power = power;
		this.fueltype = fueltype;
		this.fueltankcapacity = fueltankcapacity;
		this.gearbox = gearbox;
		this.emission_sticker = emission_sticker;
		this.first_reg = first_reg;
		this.sell_rent = sell_rent;
		this.image = image;
	}

	public long getCar_id() {
		return car_id;
	}

	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}

	public long getMerchant_ids() {
		return merchant_ids;
	}

	public void setMerchant_ids(long merchant_ids) {
		this.merchant_ids = merchant_ids;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClimatisation() {
		return climatisation;
	}

	public void setClimatisation(String climatisation) {
		this.climatisation = climatisation;
	}

	public int getNo_seats() {
		return no_seats;
	}

	public void setNo_seats(int no_seats) {
		this.no_seats = no_seats;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public double getEngineCC() {
		return engineCC;
	}

	public void setEngineCC(double engineCC) {
		this.engineCC = engineCC;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

	public long getFueltankcapacity() {
		return fueltankcapacity;
	}

	public void setFueltankcapacity(long fueltankcapacity) {
		this.fueltankcapacity = fueltankcapacity;
	}

	public String getGearbox() {
		return gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public String getEmission_sticker() {
		return emission_sticker;
	}

	public void setEmission_sticker(String emission_sticker) {
		this.emission_sticker = emission_sticker;
	}

	public String getFirst_reg() {
		return first_reg;
	}

	public void setFirst_reg(String first_reg) {
		this.first_reg = first_reg;
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

	
	
	
}
