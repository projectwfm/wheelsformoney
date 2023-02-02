package com.jsp.jspwfm.Dao;


import com.jsp.jspwfm.Models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT u.user_id as user_id, u.username as username, u.password as password, u.email as email, u.dob as dob, u.gender as gender, u.phno as phno, u.country as country, u.city as city, u.state as state, u.pincode as pincode,  u.doorno as doorno, u.street as street,   u.area as area FROM Users u WHERE username = :username", nativeQuery = true)
	public User getUserByUsername(String username);

    @Query(value = "SELECT * FROM Users u WHERE email = :data OR username=:data", nativeQuery = true) 
	public User findByUsernameOrEmail(String data);

	@Query(value = "SELECT u.user_id as user_id, u.username as username, u.password as password, u.email as email FROM Users u WHERE    email= :data OR username = :data", nativeQuery = true)
	public User getUserBydata(String data);

	@Query(value = "SELECT u.user_id as user_id, u.username as username, u.password as password, u.email as email, u.dob as dob, u.gender as gender, u.phno as phno, u.country as country, u.city as city, u.state as state, u.pincode as pincode,  u.doorno as doorno, u.street as street,   u.area as area FROM Users u WHERE email = :email", nativeQuery = true) 
	public User getUserByemail(String email);
	

	
}
