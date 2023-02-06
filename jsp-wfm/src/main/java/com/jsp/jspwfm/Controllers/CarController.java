package com.jsp.jspwfm.Controllers;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.jsp.jspwfm.Models.Entities.Car;
import com.jsp.jspwfm.Models.Entities.Image;
import com.jsp.jspwfm.Services.CarService;

@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController 
{
	
	static Logger log= LogManager.getLogger(CarController.class);
	@Autowired
	CarService carservice;

	public Image saveimage (@RequestParam("image")  MultipartFile file) throws IOException
	{
		log.info("saving the car image");
		return (carservice.saveimage(file));
	}

	@RequestMapping(value={"/add"}, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Car> addcar(@RequestPart("image") MultipartFile file, @RequestPart("car") Car car) throws IOException
	{	
		log.info("add method excuted");
		Image i= saveimage(file);
		car.setImage(i);		
		Car newcar= carservice.addcar(car);
		if(newcar == null)
		{
			log.info("adding the new car failed reason: new car object is 'null' ");
			return ResponseEntity.status(400).body(null);	
		}
		else
		{
			log.info("added new car successfully");
			return ResponseEntity.status(200).body( newcar);	
		}
			
	}
	
	@RequestMapping("/getbyid/{id}")
	public ResponseEntity<Car> getcar(@PathVariable("id") long id)
	{
		
		Car car= carservice.getcar(id);
		log.info("fetched the car by ID: "+id+ "Successfully");
		return ResponseEntity.status(200).body(car);
	}
	
	@RequestMapping("/getall")
	public ResponseEntity<List<Car>> getallbikes(@RequestHeader long merchant_ids)
	{
		List<Car> allcars= carservice.getallcars(merchant_ids);
		System.out.println(allcars);
		if(allcars!= null)
		{
			log.info("fetched the all car Successfully");
			return ResponseEntity.status(200).body(allcars);
		}
		else
		{
			log.info("fetching the all car failed");
			return ResponseEntity.status(400).body(null);
		}
	}
	
	@RequestMapping("/remove/{id}")
	public ResponseEntity<String> removebike(@PathVariable("id") long id)
	{
		boolean car= carservice.removecar(id);
		log.info("removed the car by ID: "+id+ "Successfully");
		return ResponseEntity.status(200).body("removed successfully");
	}
	
	
	@RequestMapping(value={"/update"}, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Car> update(@RequestPart("image") MultipartFile file, @RequestPart("car") Car car) throws IOException
	{
		log.info("executed car update method");
		System.out.println(car.getImage().getImid());
		Image i= saveimage(file);
		
		Image i1=carservice.getimagebyid(car.getImage().getImid());
		i1.setImagedata(i.getImagedata());	
		i1.setName(i.getName());
		i1.setType(i.getType());
		car.setImage(i1);
		Car car2= carservice.handleupdatecar(car);	
		carservice.deleteImage(i.getImid());
		
		if(car2!= null)
		{
			log.info("car updated sucessfully");
			return ResponseEntity.status(200).body(car2);
		}
		else
		{
			log.info("car update failed car service layer returning null");
			return ResponseEntity.status(400).body(car2);
		}
	}
	
	
}
