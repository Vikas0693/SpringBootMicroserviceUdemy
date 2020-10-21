package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;


@RestController
public class UserJPAResource {
	
	@Autowired
	private UserDaoService service;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		//WHAT IS HATEAOS AND WHY TO USE(Added dependency in pom)
		/*
		 * What is - Usually rest response contains links of actions(hypermedia links) that can be performed from current request.
		 * Hateaos is a framework to send hypermedia links in response of rest api request on which user can perform action 
		 * Why - if we make response manually by adding links like '/users' then what if some other person changes the uri of that controller that has '/users' as its path.
		 * That user might not change the same in other controllers that have such path('/users') in their responses.So here hateaos helps to dynamically get the uri of 
		 * controller at runtime.
		 * In below whatever the uri of retrieveAllUsers is , it gets added to this controller response via key 'all-users',see blow.
		*/
		EntityModel<User> resource = EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		//all-users is key in json response which will have uri path of retrieveAllUsers() method
		resource.add(linkTo.withRel("all-users"));
		//HATEOAS
		return resource;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		//beelow is when we want to send user in response too
		//return ResponseEntity.created(location).body(savedUser);
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
}
