package com.in28minutes.rest.webservices.restfulwebservices.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String description;
	
	//only fetch user when we do post.getUser();
	//if we do not use lazy then what happens is post.getUser() will give User object then since post is also defined in User object
	//it will fetch Post which again will try to fetch User hence the infinite loop
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_ki_id")
	//we dont want user details in post object as it will create recursive loop & throws 'Could not write JSON: Infinite recursion '
	@JsonIgnore
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/*public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}*/
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
}
