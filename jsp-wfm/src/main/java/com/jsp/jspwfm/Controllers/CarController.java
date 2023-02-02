package com.jsp.jspwfm.Controllers;

import java.io.IOException;
import java.util.List;

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
@RequestMapping("car")
public class CarController 
{
	

	@Autowired
	CarService carservice;

	public Image saveimage (@RequestParam("image")  MultipartFile file) throws IOException
	{
		return (carservice.saveimage(file));
	}

	@RequestMapping(value={"/add"}, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Car> addcar(@RequestPart("image") MultipartFile file, @RequestPart("car") Car car) throws IOException
	{		
		Image i= saveimage(file);
		car.setImage(i);		
		Car newcar= carservice.addcar(car);
		if(newcar == null)
		{
			return ResponseEntity.status(400).body(null);	
		}
		else
		{
			return ResponseEntity.status(200).body( newcar);	
		}
			
	}
	
	@RequestMapping("/getbyid/{id}")
	public ResponseEntity<Car> getcar(@PathVariable("id") long id)
	{
		Car car= carservice.getcar(id);
		System.out.println(car);
		return ResponseEntity.status(200).body(car);
	}
	
	@RequestMapping("/getall")
	public ResponseEntity<List<Car>> getallbikes(@RequestHeader long merchant_ids)
	{
		List<Car> allcars= carservice.getallcars(merchant_ids);
		System.out.println(allcars);
		if(allcars!= null)
		{
			return ResponseEntity.status(200).body(allcars);
		}
		else
		{
			return ResponseEntity.status(400).body(null);
		}
	}
	
	@RequestMapping("/remove/{id}")
	public ResponseEntity<String> removebike(@PathVariable("id") long id)
	{
		boolean car= carservice.removecar(id);
		return ResponseEntity.status(200).body("removed successfully");
	}
	
	
	@RequestMapping(value={"/update"}, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Car> update(@RequestPart("image") MultipartFile file, @RequestPart("car") Car car) throws IOException
	{
		System.out.println();
		System.out.println("handle bike upadte 2 method is done");
		Image i= saveimage(file);
		Image i1=carservice.getimagebyid(car.getImage().getImid());
		i1.setImagedata(i.getImagedata());	
		i1.setName(i.getName());
		i1.setType(i.getType());
		car.setImage(i1);
		Car car2= carservice.handleupdatebike(car);	
		carservice.deleteImage(i.getImid());
		
		if(car2!= null)
		{
			System.out.println();
			System.out.println("handle bike upadte 2 method if block excuted");
			return ResponseEntity.status(200).body(car2);
		}
		else
		{
			System.out.println();
			System.out.println("handle bike upadte 2 method is else excuted");
			return ResponseEntity.status(400).body(car2);
		}
	}
	
	
}
