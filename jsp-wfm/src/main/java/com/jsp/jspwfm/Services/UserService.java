package com.jsp.jspwfm.Services;

import com.jsp.jspwfm.Dao.UsersRepository;
import com.jsp.jspwfm.Exception.UserAlreadyExistsException;
import com.jsp.jspwfm.Models.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService {

    @Autowired
    UsersRepository usersRepository;
    public User getUser(String username, String password)
    {
        return usersRepository.getUserByUsername(username);
    }
    public boolean handleSignUp(User user)
    {
    	User reslut=usersRepository.getUserByUsername(user.getUsername());
		try
		{
			if(reslut!=null)
			{
				throw new UserAlreadyExistsException();
				
			}
			else
			{
				usersRepository.save(user);
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
    }
}
