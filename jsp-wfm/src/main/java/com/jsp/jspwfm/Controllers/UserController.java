package com.jsp.jspwfm.Controllers;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
		if(userService.handleSignUp(user))
		{
			
			return new ResponseEntity<>(HttpStatusCode.valueOf(200));
		}
		return new ResponseEntity<>(HttpStatusCode.valueOf(400));
	}
    @RequestMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader String user,@RequestHeader String password)
    { 
    	if(userService.login(user, password) instanceof User)
    	{
    		return ResponseEntity.status(200).body(userService.login(user, password));
    	}
    	return ResponseEntity.status(400).body(userService.login(user, password)); 
    } 
//    @RequestMapping("/resetpswd")
//    public ResponseEntity<String> pswdrequest(@RequestParam String data,@RequestParam String newpass)
//    {
//    	String s=userService.pswdrequest(data, newpass);
//    	if("password resetted sucessfully".equals(s))
//    	{
//    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(200));
//    	}
//    	else {
//    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(400));
//    	}
//    	
//    }   
    @RequestMapping("/psdreset")
    public ResponseEntity<Object> pswdrequest(@RequestHeader String email)
    {
    	Object OTP=userService.pswdrequest(email);
    	
    	if(!OTP.toString().equals(0))
    	{
    		return new ResponseEntity<Object> (OTP, HttpStatusCode.valueOf(200));
    	}
    	else {
    		return new ResponseEntity<Object> (OTP, HttpStatusCode.valueOf(400));
    	}
    	
    } 
    
    @RequestMapping("/passwordresetrequest")
    public ResponseEntity<Integer> pswdrequest(@RequestHeader String email, @RequestHeader String type)
    {
    	int OTP=userService.pswdrequesttype(email, type);
    	if(OTP!=0)
    	{
    		return new ResponseEntity<Integer> (OTP, HttpStatusCode.valueOf(200));
    	}
    	else {
    		return new ResponseEntity<Integer> (OTP, HttpStatusCode.valueOf(400));
    	}
    	
    } 
    
    @RequestMapping("/setnewpassword")
    public ResponseEntity<String> verifyOTP(@RequestHeader String newpassword, @RequestHeader String email)
    {
    	
    	String s=userService.resetpassword(newpassword, email);
    	if("Password updated succesfully....!!!!!!".equals(s))
    	{
    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(200));
    	}
    	else {
    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(400));
    	}
    	
    } 
    
// Below to methods are replacement of above pswdrequest method line number 75.
    
    @RequestMapping("/sendotp")
    public ResponseEntity<Object> verifymail(@RequestHeader String email,@RequestHeader String type)
    {
    	Object otp=userService.verifymail(email,type);
    	if(otp.toString().equals("User Not Found")||otp.toString().equals("User Already Exists"))
    	{
    		return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    	}
    	return new ResponseEntity<>(HttpStatusCode.valueOf(200));

    }
    
    @RequestMapping("/verifyotp")
    public ResponseEntity verifyotp(@RequestHeader String email,@RequestHeader int otp)
    {
    	Boolean verify = userService.verifyOTP(email,otp);
		if (verify) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>(HttpStatusCode.valueOf(400));
		}

    }
    
    @RequestMapping("/getAll")
    public ResponseEntity getVehicle()
    {
    	Map vehicle = userService.getVehicle();
		if (!vehicle.isEmpty()) {
			return new ResponseEntity<>(vehicle,HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>(vehicle,HttpStatusCode.valueOf(400));
		}

    }
    @RequestMapping("/cardpayment")
    public ResponseEntity<String> cardpayment(@RequestHeader String cardno,@RequestHeader String cvv,@RequestHeader String email)
    {
    	long cardno1=Long.parseLong(cardno); 
    	int cvv1=Integer.parseInt(cvv); 
    	boolean s=userService.cardpayment(cardno1, cvv1,email);
    	if(s==true)
    	{
    		return ResponseEntity.status(200).body("payment done sucessfully");
    	}
    	else 
    	{
    		return ResponseEntity.status(400).body("payment is unsucessful");
    	}
    }
    @RequestMapping("/upipayment")
    public ResponseEntity<String> upipayment(@RequestHeader String upi_id,@RequestHeader String email)
    {
    	boolean s=userService.upipayment(upi_id,email);
    	if(s==true)
    	{
    		return ResponseEntity.status(200).body("payment done sucessfully");
    	}
    	else {
    		return ResponseEntity.status(400).body("payment is unsucessful");
    	}
    	
    }
}
