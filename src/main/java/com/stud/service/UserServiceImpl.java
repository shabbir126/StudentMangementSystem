package com.stud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stud.model.User;
import com.stud.repository.UserRepository;


@Service
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User>user=userRepository.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		return user.map(CustomUserDetails:: new).get();
	}

}
