package com.tk.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tk.registration.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	
	public Users findByEmaill(String email);

}
