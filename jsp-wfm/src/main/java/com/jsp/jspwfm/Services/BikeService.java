package com.jsp.jspwfm.Services;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
	static Logger log= LogManager.getLogger(BikeService.class);
	@Autowired
	BikeDao bikedao;
	@Autowired
	ImageDao imageDao;

	public Image saveimage(MultipartFile file) throws IOException 
	{
		
		Image img= new Image(  file.getBytes(), file.getOriginalFilename(), file.getContentType());
		log.info("Image object is created");
		return imageDao.save(img);				
	}

	public Image getimagebyid(int imid) 
	{
		log.info("geting the image by Id:"+imid);
		return imageDao.findById(imid).get();
	}
	
	public void deleteImage(int imid)
	{
		log.info("deleting the image by Id:"+imid);
		imageDao.deleteById(imid);
	}
	
	public Bike addbike(Bike bike) 
	{
		Bike exsistbike= bikedao.getByReg_no(bike.getReg_no());
		if(exsistbike == null)
		{
			Bike newbike= bikedao.save(bike);
			log.info("added new bike successfully");
			return newbike;	
		}
		else
		{
			log.error("adding new bike failed");
			return null;
		}
	}

	public Bike getbike(Long id) 
	{
		log.info("fecthing the bike by specified ID: "+id);
		return bikedao.findById(id).get();
	}

	public List<Bike> getallbikes(long merchant_ids) 
	{
		log.info("fecthing all the bike for merchant ID: "+merchant_ids);
		return  bikedao.findAllById(merchant_ids);
	}

	public boolean removebike(long id)
	{
		bikedao.deleteById(id);
		log.info("removed the bike of specified ID: "+id+" successfully");
		return true;		
	}

	public Bike handleupdatebike(long id)
	{
		return bikedao.findById(id).get();
	}

	public Bike handleupdatebike(Bike bike)
	{
		log.info("handle  upadte bike method is executed");
		System.out.println(bike.getBike_id());
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
		log.info("updated bike with ID: "+bike2.getBike_id()+" successfully");
		return bike3;
	}
}
