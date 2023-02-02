package com.jsp.jspwfm.Services;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.jspwfm.Dao.CarDao;
import com.jsp.jspwfm.Dao.ImageDao;

import com.jsp.jspwfm.Models.Entities.Car;
import com.jsp.jspwfm.Models.Entities.Image;



@Service
public class CarService 
{

	@Autowired
	CarDao cardao;
	
	@Autowired
	ImageDao imageDao;
	
	public Image saveimage(MultipartFile file) throws IOException 
	{
		Image img= new Image(  file.getBytes(), file.getOriginalFilename(), file.getContentType());
		return imageDao.save(img);		
	}
	
	public Image getimagebyid(int imid) 
	{
		return imageDao.findById(imid).get();
	}
	
	public void deleteImage(int imid)
	{
		imageDao.deleteById(imid);
	}

	public Car addcar(Car car) 
	{
		Car exsistcar= cardao.getByReg_no(car.getReg_no());
		if(exsistcar== null)
		{
			Car newcar= cardao.save(car);
			return newcar;
		}
		else
		{
			return null;
		}	
	}


	public Car getcar(long id)
	{
		return cardao.findById(id).get();
	}


	public List<Car> getallcars(long merchant_ids) 
	{
		return  cardao.findAllById(merchant_ids);
	}


	public boolean removecar(long id)
	{
		cardao.deleteById(id);
		return true;	
	}


	public Car handleupdatebike(Car car) {
		System.out.println("handle bike upadte 2 method is executed");
		Car car1= cardao.findById(car.getCar_id()).get();
		car1.setColor(car.getColor());
		car1.setCompany(car.getCompany());
		car1.setEngineCC(car.getEngineCC());
		car1.setFueltankcapacity(car.getFueltankcapacity());
		car1.setFueltype(car.getFueltype());
		car1.setImage(car.getImage());
		car1.setMileage(car.getMileage());
		car1.setModel(car.getModel());
		car1.setPrice(car.getPrice());
		car1.setReg_no(car.getReg_no());
		car1.setSell_rent(car.getSell_rent());
		Car car2= cardao.save(car1);
	System.out.println();
	System.out.println("handle bike upadte 2 method is done");
		return car2;
	}

}
