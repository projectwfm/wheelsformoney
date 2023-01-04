package com.jsp.jspwfm.Controllers;

import com.jsp.jspwfm.Models.Entities.User;
import com.jsp.jspwfm.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Object> login(@RequestParam String user,@RequestParam String password)
    { 
    	if(userService.login(user, password) instanceof User)
    	{
    		return ResponseEntity.status(200).body(userService.login(user, password));
    	}
    	return ResponseEntity.status(400).body(userService.login(user, password)); 
    } 
    @RequestMapping("/resetpswd")
    public ResponseEntity<String> pswdrequest(@RequestParam String data,@RequestParam String newpass)
    {
    	String s=userService.pswdrequest(data, newpass);
    	if("password resetted sucessfully".equals(s))
    	{
    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(200));
    	}
    	else {
    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(400));
    	}
    	
    }    
    
}
