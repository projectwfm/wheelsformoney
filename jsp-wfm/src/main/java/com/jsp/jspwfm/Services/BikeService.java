package com.jsp.jspwfm.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.jspwfm.Dao.BikeDao;
import com.jsp.jspwfm.Dao.ImageDao;
import com.jsp.jspwfm.Models.Entities.Bike;
import com.jsp.jspwfm.Models.Entities.Image;

@Service
public class BikeService 
{
	@Autowired
	BikeDao bikedao;
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
	
	public Bike addbike(Bike bike) 
	{
		Bike exsistbike= bikedao.getByReg_no(bike.getReg_no());
		if(exsistbike== null)
		{
			Bike newbike= bikedao.save(bike);
			return newbike;
		}
		else
		{
			return null;
		}		
	}

	public Bike getbike(Long id) 
	{
		return bikedao.findById(id).get();
	}

	public List<Bike> getallbikes(long merchant_ids) 
	{
		return  bikedao.findAllById(merchant_ids);
	}

	public boolean removebike(long id)
	{
		bikedao.deleteById(id);
		return true;		
	}

	public Bike handleupdatebike(long id)
	{
		return bikedao.findById(id).get();
	}

	public Bike handleupdatebike(Bike bike)
	{
		System.out.println("handle bike upadte 2 method is executed");
		Bike bike2= bikedao.findById(bike.getBike_id()).get();
		bike2.setColor(bike.getColor());
		bike2.setCompanyname(bike.getCompanyname());
		bike2.setEnginecc(bike.getEnginecc());
		bike2.setFueltankcapacity(bike.getFueltankcapacity());
		bike2.setFueltype(bike.getFueltype());
		bike2.setImage(bike.getImage());
		bike2.setMileage(bike.getMileage());
		bike2.setModel(bike.getModel());
		bike2.setPrice(bike.getPrice());
		bike2.setReg_no(bike.getReg_no());
		bike2.setSell_rent(bike.getSell_rent());
		bike2.setWeight(bike.getWeight());
		Bike bike3= bikedao.save(bike2);
	System.out.println();
	System.out.println("handle bike upadte 2 method is done");
		return bike3;
	}
}
