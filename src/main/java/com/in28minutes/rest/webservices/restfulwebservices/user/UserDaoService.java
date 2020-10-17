package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private List<User> users  = new ArrayList<>();
	private static int usersCount=3;
	
	{
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Jeff", new Date()));
		users.add(new User(3, "Elon", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}
	
	public User deleteById(int id) {
		for(User user:users) {
			if(user.getId() == id) {
				users.remove(user);
				return user;
			}
		}
		return null;
	}
}
