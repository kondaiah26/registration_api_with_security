package com.tk.registration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.tk.registration.service.CustomerService;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Autowired
	private CustomerService customerService;
	
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@SneakyThrows
	public DaoAuthenticationProvider daoAuthentication() {
		
		DaoAuthenticationProvider authprovider= new DaoAuthenticationProvider(); 
		
		authprovider.setPasswordEncoder(pwdEncoder());
		authprovider.setUserDetailsService(customerService);
		return authprovider;
	}
	
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		
		return config.getAuthenticationManager();
		
	}
	
	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				(req)->{
					req.requestMatchers("/create/","/login/")
					.permitAll()
					.anyRequest()
					.authenticated();
				});
		
		return http.csrf().disable().build();
				
		
	}

}
