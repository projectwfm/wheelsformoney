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

import com.jsp.jspwfm.Models.Entities.Bike;
import com.jsp.jspwfm.Models.Entities.Image;
import com.jsp.jspwfm.Services.BikeService;

@RestController
@CrossOrigin
@RequestMapping("bike")
public class BikeController
{
	@Autowired
	BikeService bikeservice;
	
	public Image saveimage (@RequestParam("image")  MultipartFile file) throws IOException
	{
		return (bikeservice.saveimage(file));
	}

	@RequestMapping(value={"/add"}, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Bike> addbike(@RequestPart("image") MultipartFile file, @RequestPart("bike") Bike bike) throws IOException
	{		
		Image i= saveimage(file);
		bike.setImage(i);		
		Bike newbike= bikeservice.addbike(bike);
		if(newbike == null)
		{
			return ResponseEntity.status(400).body( null);	
		}
		else
		{
			return ResponseEntity.status(200).body( newbike);	
		}
			
	}
	@RequestMapping("/getbyid/{id}")
	public ResponseEntity<Bike> getbike(@PathVariable("id") long id)
	{
		Bike bike= bikeservice.getbike(id);
		System.out.println(bike);
		return ResponseEntity.status(200).body(bike);
	}
	@RequestMapping("/getall")
	public ResponseEntity<List<Bike>> getallbikes(@RequestHeader long merchant_ids)
	{
		List<Bike> allbikes= bikeservice.getallbikes(merchant_ids);
		System.out.println(allbikes);
		if(allbikes!= null)
		{
			return ResponseEntity.status(200).body(allbikes);
		}
		else
		{
			return ResponseEntity.status(400).body(null);
		}
	}
	@RequestMapping("/removebyid/{id}")
	public ResponseEntity<String> removebike(@PathVariable("id") long id)
	{
		boolean bike= bikeservice.removebike(id);
		return ResponseEntity.status(200).body("removed successfully");
	}
	@RequestMapping(value={"/update"}, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Bike> update1(@RequestPart("image") MultipartFile file, @RequestPart("bike") Bike bike) throws IOException
	{
		System.out.println();
		System.out.println("handle bike upadte 2 method is done");
		Image i= saveimage(file);
		Image i1=bikeservice.getimagebyid(bike.getImage().getImid());
		i1.setImagedata(i.getImagedata());	
		i1.setName(i.getName());
		i1.setType(i.getType());
		bike.setImage(i1);
		Bike bike2= bikeservice.handleupdatebike(bike);
		
		System.out.println();
		System.out.println(bike2);
		
		bikeservice.deleteImage(i.getImid());
		
		if(bike2!= null)
		{
			System.out.println();
			System.out.println("handle bike upadte 2 method if block excuted");
			return ResponseEntity.status(200).body(bike);
		}
		else
		{
			System.out.println();
			System.out.println("handle bike upadte 2 method is else excuted");
			return ResponseEntity.status(400).body(bike);
		}
	}
}
