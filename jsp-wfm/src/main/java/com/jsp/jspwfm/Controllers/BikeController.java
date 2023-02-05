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

import com.jsp.jspwfm.Models.Entities.Bike;
import com.jsp.jspwfm.Models.Entities.Image;
import com.jsp.jspwfm.Services.BikeService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("bike")
public class BikeController
{
	static Logger log= LogManager.getLogger(BikeController.class);
	@Autowired
	BikeService bikeservice;
	
	public Image saveimage (@RequestParam("image")  MultipartFile file) throws IOException
	{
		log.info("Saving the image");
		return (bikeservice.saveimage(file));
	}

	@RequestMapping(value={"/add"}, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Bike> addbike(@RequestPart("image") MultipartFile file, @RequestPart("bike") Bike bike) throws IOException
	{
		log.info("Adding the Bike");
		Image i= saveimage(file);
		bike.setImage(i);		
		Bike newbike= bikeservice.addbike(bike);
		if(newbike == null)
		{
			log.error("Adding new bike failed");
			return ResponseEntity.status(400).body( null);	
		}
		else
		{
			log.info("Added new bike done successfully");
			return ResponseEntity.status(200).body( newbike);	
		}
			
	}
	
	@RequestMapping("/getbyid/{id}")
	public ResponseEntity<Bike> getbike(@PathVariable("id") long id)
	{
		
		Bike bike= bikeservice.getbike(id);
		System.out.println(bike);
		log.info("returning the fetched bike object based on ID: "+id);
		return ResponseEntity.status(200).body(bike);
	}
	
	@RequestMapping("/getall")
	public ResponseEntity<List<Bike>> getallbikes(@RequestHeader long merchant_ids)
	{
		List<Bike> allbikes= bikeservice.getallbikes(merchant_ids);
		System.out.println(allbikes);
		if(allbikes!= null)
		{
			log.info("returning the fetched all bike object successfully ");
			return ResponseEntity.status(200).body(allbikes);
		}
		else
		{
			log.info("returning the fetched all bike object failed ");
			return ResponseEntity.status(400).body(null);
		}
	}
	@RequestMapping("/removebyid/{id}")
	public ResponseEntity<String> removebike(@PathVariable("id") long id)
	{
		boolean bike= bikeservice.removebike(id);
		log.info("Bike removed successfully with id: "+id);
		return ResponseEntity.status(200).body("removed successfully");
	}
	
//	@RequestMapping("/updatebyid/{id}")
//	public ResponseEntity<Bike> update(@PathVariable("id") long id)
//	{
//		Bike bike= bikeservice.handleupdatebike(id);
//		if(bike!= null)
//		{
//			return ResponseEntity.status(200).body(bike);
//		}
//		else
//		{
//			return ResponseEntity.status(400).body(bike);
//		}
//	}
	
	@RequestMapping(value={"/update"}, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Bike> update1(@RequestPart("image") MultipartFile file, @RequestPart("bike") Bike bike) throws IOException
	{
		log.info("Update method excuted");
		Image i= saveimage(file);
		Image i1=bikeservice.getimagebyid(bike.getImage().getImid());
		i1.setImagedata(i.getImagedata());	
		i1.setName(i.getName());
		i1.setType(i.getType());
		bike.setImage(i1);
		Bike bike2= bikeservice.handleupdatebike(bike);
		
		log.info("updated bike info: "+bike2.toString());
		
		bikeservice.deleteImage(i.getImid());
		
		if(bike2!= null)
		{
			log.info("Updated bike with ID: "+bike2.getBike_id()+" successfully");
			return ResponseEntity.status(200).body(bike);
		}
		else
		{
			log.error("Update bike with ID: "+bike2.getBike_id()+" failed");
			return ResponseEntity.status(400).body(bike);
		}
	}
}
