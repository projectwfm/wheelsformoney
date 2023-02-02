package com.jsp.jspwfm.Controllers;

import java.util.Map;

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
    	boolean reponse= merchantService.handlesignUp(merchant);
    	if(reponse== false)
    	{
    		return ResponseEntity.status(400).body(reponse);
    	}
    	else
    	{
    		return ResponseEntity.status(200).body(reponse);
    	}    	
    }	
    
    @RequestMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestHeader String merchantnameOremail, @RequestHeader String password) throws PasswordInvalidException
    {
    	
    	Map<String, String> reponse= merchantService.handlelogin(merchantnameOremail, password);
    	if(reponse==null)
    	{
    		return ResponseEntity.status(400).body(reponse);
    	}
    	else
    	{
    		return ResponseEntity.status(200).body(reponse);
    	}   
    }
    
    @RequestMapping("/setnewpassword")
    public ResponseEntity<Boolean> setnewpassword(@RequestHeader String newpassword, @RequestHeader String email)
    {
    	boolean reponse= merchantService.handleresetpassword(newpassword, email);
    	if(reponse== false)
    	{
    		return ResponseEntity.status(400).body(reponse);
    	}
    	else
    	{
    		return ResponseEntity.status(200).body(reponse);
    	}  	
    }
    
    
    @RequestMapping("/sendotp")
    public ResponseEntity<Object> verifymail(@RequestHeader String email,@RequestHeader String type)
    {
    	Object otp=merchantService.verifymail(email,type);
    	if(otp.toString().equals("Merchant Not Found")||otp.toString().equals("Merchant Already Exists"))
    	{
    		return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    	}
    	return new ResponseEntity<>(HttpStatusCode.valueOf(200));

    }
    
    @RequestMapping("/verifyotp")
    public ResponseEntity verifyotp(@RequestHeader String email,@RequestHeader int otp)
    {
    	Boolean verify = merchantService.verifyOTP(email,otp);
		if (verify) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>(HttpStatusCode.valueOf(400));
		}
    }
}
