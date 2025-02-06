package com.tk.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tk.registration.entity.Users;
import com.tk.registration.repository.UserRepository;
import com.tk.registration.service.CustomerService;

@RestController
public class UserController {
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/create/")
	ResponseEntity<Users> createUser(@RequestBody Users user){
		
		custService.saveCustomer(user);
		
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@PostMapping("/login/")
	ResponseEntity<String> login(@RequestBody Users user){
		
		
		
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getEmaill(), user.getPassword());
		
		Authentication auth=authManager.authenticate(token);
		
		boolean status=auth.isAuthenticated();
		
		
		
		if(status) {
			
			return new ResponseEntity<>("You are logged in ,  thank  you......",HttpStatus.OK);
		}
		
		else {
			return new ResponseEntity<>("not able to login " , HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		
		
		
		
	}
	
	

}
