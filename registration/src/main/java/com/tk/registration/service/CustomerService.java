package com.tk.registration.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tk.registration.entity.Users;
import com.tk.registration.repository.UserRepository;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	public Users saveCustomer(Users user) {

		String encodePwd = pwdEncoder.encode(user.getPassword());
		user.setPassword(encodePwd);
		String encodeConfirmPwd = pwdEncoder.encode(user.getConfirmPassword());
		user.setConfirmPassword(encodeConfirmPwd);

		return userRepo.save(user);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userRepo.findByEmaill(username);

		return new User(user.getEmaill(), user.getPassword(), Collections.emptyList());
	}

}
