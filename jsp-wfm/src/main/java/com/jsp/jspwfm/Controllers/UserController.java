package com.jsp.jspwfm.Controllers;


import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.jspwfm.Models.Entities.User;
import com.jsp.jspwfm.Services.UserService;
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    
	static Logger log= LogManager.getLogger(UserController.class);
	
    @Autowired
    private UserService userService;
    @RequestMapping("/getUser")
    public ResponseEntity testFetchUser(@RequestParam String username,@RequestParam String password)
    {
        return new ResponseEntity<>(userService.getUser(username,password), HttpStatusCode.valueOf(200));
    }
    @PostMapping("/signUp")
	public ResponseEntity signUp(@RequestBody User user) throws Exception
	{
    	log.info("New user trying to register...");
		if(userService.handleSignUp(user))
		{
			log.info("New user registration successfull!");
			return new ResponseEntity<>(HttpStatusCode.valueOf(200));
		}
		log.info("New user registration failed!");
		return new ResponseEntity<>(HttpStatusCode.valueOf(400));
	}
    @RequestMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader String user,@RequestHeader String password)
    { 
    	log.info("New user trying to login!");
    	if(userService.login(user, password) instanceof User)
    	{
    		log.info("New user login successfull!");
    		return ResponseEntity.status(200).body(userService.login(user, password));
    	}
    	log.info("logged in failed due to invalid credential");
    	return ResponseEntity.status(400).body(userService.login(user, password)); 
    } 
   
    @RequestMapping("/psdreset")
    public ResponseEntity<Object> pswdrequest(@RequestHeader String email)
    {
    	log.info("User trying to reset password...");
    	Object OTP=userService.pswdrequest(email);
    	
    	if(!OTP.toString().equals(0))
    	{
    		log.info("user reseted pasword successfully with email");
    		return new ResponseEntity<Object> (OTP, HttpStatusCode.valueOf(200));
    	}
    	else {
    		log.info("user reseted pasword failed..!!");
    		return new ResponseEntity<Object> (OTP, HttpStatusCode.valueOf(400));
    	}
    	
    } 
    
    @RequestMapping("/passwordresetrequest")
    public ResponseEntity<Integer> pswdrequest(@RequestHeader String email, @RequestHeader String type)
    {
    	log.info(type+"Type of User trying to reset password...");
    	int OTP=userService.pswdrequesttype(email, type);
    	if(OTP!=0)
    	{
    		log.info("successfull...!");
    		return new ResponseEntity<Integer> (OTP, HttpStatusCode.valueOf(200));
    	}
    	else {
    		log.info("failed...!");
    		return new ResponseEntity<Integer> (OTP, HttpStatusCode.valueOf(400));
    	}
    	
    } 
    
    @RequestMapping("/setnewpassword")
    public ResponseEntity<String> verifyOTP(@RequestHeader String newpassword, @RequestHeader String email)
    {
    	log.info("user "+ email+ " trying to reset password...");
    	String s=userService.resetpassword(newpassword, email);
    	if("Password updated succesfully....!!!!!!".equals(s))
    	{
    		log.info("user reseted pasword successfully with "+ email);
    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(200));
    	}
    	else {
    		log.info("Reset password for user with "+ email+ " failed due to mentioned email is not present");
    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(400));
    	}
    	
    } 

    
    @RequestMapping("/sendotp")
    public ResponseEntity<Object> verifymail(@RequestHeader String email,@RequestHeader String type)
    {
    	Object otp=userService.verifymail(email,type);
    	if(otp.toString().equals("User Not Found")||otp.toString().equals("User Already Exists"))
    	{
    		log.info("sending OTP for "+email+ " failed due to "+otp.toString());
    		return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    	}
    	log.info("sent OTP for "+email+ " successfully");
    	return new ResponseEntity<>(HttpStatusCode.valueOf(200));

    }
    
    @RequestMapping("/verifyotp")
    public ResponseEntity verifyotp(@RequestHeader String email,@RequestHeader int otp)
    {
    	log.info("verifying the OTP for "+email);
    	Boolean verify = userService.verifyOTP(email,otp);
		if (verify) {
			log.info("verified OTP for "+email+ " successfully");
			return new ResponseEntity<>(HttpStatusCode.valueOf(200));
		} else {
			log.info("verified OTP for "+email+ " failed: Invalid OTP");
			return new ResponseEntity<>(HttpStatusCode.valueOf(400));
		}

    }
    
    @PutMapping("/editprofile")
    public ResponseEntity edit(@RequestBody User user)
    {
    	log.info(user + " "+" trying to edit profile details");
    	if(userService.edit(user))
    	{
    		log.info("edit profile is successfull...!");
    		return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    	}
    	log.info("edit profile is failed...!");
    	return new ResponseEntity<>(HttpStatusCode.valueOf(400 ));
    }
    
    @RequestMapping("/getUserData")
    public ResponseEntity<User> get(@RequestParam String username)
    {
    	return new ResponseEntity<>(userService.getUserData(username), HttpStatusCode.valueOf(200));
    }
    
    @RequestMapping("/getAll")
    public ResponseEntity getVehicle()
    {
    	log.info("Trying to get all vehicle details..!");
    	Map vehicle = userService.getVehicle();
		if (!vehicle.isEmpty()) {
			log.info("successfull...!");
			return new ResponseEntity<>(vehicle,HttpStatusCode.valueOf(200));
		} else {
			log.info("Failed...!");
			return new ResponseEntity<>(vehicle,HttpStatusCode.valueOf(400));
		}

    }
    @RequestMapping("/cardpayment")
    public ResponseEntity<String> cardpayment(@RequestHeader String cardno,@RequestHeader String cvv,@RequestHeader String email)
    {
    	log.info("user trying to do payment through card!");
    	long cardno1=Long.parseLong(cardno); 
    	int cvv1=Integer.parseInt(cvv); 
    	boolean s=userService.cardpayment(cardno1, cvv1,email);
    	if(s==true)
    	{
    		log.info("payment done sucessfully.!");
    		return ResponseEntity.status(200).body("payment done sucessfully");
    	}
    	else 
    	{
    		log.info("payment is unsucessful.!");
    		return ResponseEntity.status(400).body("payment is unsucessful");
    	}
    }
    @RequestMapping("/upipayment")
    public ResponseEntity<String> upipayment(@RequestHeader String upi_id,@RequestHeader String email)
    {
    	log.info("user trying do payment through UPI!");
    	boolean s=userService.upipayment(upi_id,email);
    	if(s==true)
    	{
    		log.info("payment done sucessfully.!");
    		return ResponseEntity.status(200).body("payment done sucessfully");
    	}
    	else {
    		log.info("payment is unsucessful.!");
    		return ResponseEntity.status(400).body("payment is unsucessful");
    	}
    	
    }
}
