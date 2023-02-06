package com.jsp.jspwfm.Controllers;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.jspwfm.Exception.MerchantAlreadyExistsException;
import com.jsp.jspwfm.Exception.PasswordInvalidException;
import com.jsp.jspwfm.Models.Entities.Merchant;
import com.jsp.jspwfm.Services.MerchantService;


@RestController
@Component
@CrossOrigin
@RequestMapping("/merchant")
public class MerchantController
{
	static Logger log= LogManager.getLogger(MerchantController.class);
	@Autowired
	MerchantService merchantService;
	
	@RequestMapping("/getbyemail")
	public ResponseEntity<Merchant> getbyemail(@RequestHeader String  email)
	{
		Merchant m= merchantService.getMerchantByemail(email);
		return ResponseEntity.status(200).body(m);
	}
    
    @RequestMapping("/signUp")
    public ResponseEntity<Boolean> signup(@RequestBody Merchant merchant) throws MerchantAlreadyExistsException
    {
    	log.info("New Merchant "+ merchant.getMerchantname()+" with email"+ merchant.getEmail()+ " trying to register...");
    	boolean reponse= merchantService.handlesignUp(merchant);
    	if(reponse== false)
    	{
    		log.info("New Merchant registration failed! : merchant name "+ merchant.getMerchantname()+" with email"+ merchant.getEmail());
    		return ResponseEntity.status(400).body(reponse);
    	}
    	else
    	{
    		log.info("New Merchant "+ merchant.getMerchantname()+" with email"+ merchant.getEmail()+ " registered succesfuly");
    		return ResponseEntity.status(200).body(reponse);
    	}    	
    }	
    
    @RequestMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestHeader String merchantnameOremail, @RequestHeader String password) throws PasswordInvalidException
    { 	
    	log.info("Merchant "+ merchantnameOremail+ " trying to login...");
    	Map<String, String> reponse= merchantService.handlelogin(merchantnameOremail, password);
    	if(reponse.size()>0)
    	{
    		log.info("Merchant "+ merchantnameOremail+ " logged in succesfully");
    		return ResponseEntity.status(200).body(reponse);
    	}
    	else
    	{
    		log.info("Merchant "+ merchantnameOremail+ " logged in failed due to invalid credential !!!");
    		return ResponseEntity.status(400).body(reponse);
    	}   
    }
    
    @RequestMapping("/setnewpassword")
    public ResponseEntity<Boolean> setnewpassword(@RequestHeader String newpassword, @RequestHeader String email)
    {
    	log.info("Merchant "+ email+ " trying to reset password...");
    	boolean reponse= merchantService.handleresetpassword(newpassword, email);
    	if(reponse== false)
    	{
    		log.info("Reset password for Merchant with "+ email+ " failed due to merchant for the mentione email is not present");
    		return ResponseEntity.status(400).body(reponse);
    	}
    	else
    	{
    		log.info("Merchant reseted pasword successfully with "+ email);
    		return ResponseEntity.status(200).body(reponse);
    	}  	
    }
    
    
    @RequestMapping("/sendotp")
    public ResponseEntity<Object> verifymail(@RequestHeader String email,@RequestHeader String type)
    {
    	Object otp=merchantService.verifymail(email,type);
    	if(otp.toString().equals("Merchant Not Found")||otp.toString().equals("Merchant Already Exists"))
    	{
    		log.info("sending OTP for "+email+ " failed due to "+otp.toString());
    		return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    	}
    	else
    	{
    		log.info("sent OTP for "+email+ " successfully");
        	return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    	}

    }
    
    @RequestMapping("/verifyotp")
    public ResponseEntity verifyotp(@RequestHeader String email,@RequestHeader int otp)
    {
    	log.info("verifying the OTP for "+email);
    	Boolean verify = merchantService.verifyOTP(email,otp);
		if (verify)
		{
			log.info("verified OTP for "+email+ " successfully");
			return new ResponseEntity<>(HttpStatusCode.valueOf(200));
		} else
		{
			log.info("verified OTP for "+email+ " failed: Invalid OTP");
			return new ResponseEntity<>(HttpStatusCode.valueOf(400));
		}
    }
}
