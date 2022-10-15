package org.alkemy.challengedisney.service;

import java.util.ArrayList;

import org.alkemy.challengedisney.exception.NotFoundException;
import org.alkemy.challengedisney.model.CustomUser;
import org.alkemy.challengedisney.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	CustomUserRepository customUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		CustomUser user =  customUserRepository.findByUserName(username);
		
		if (user.getUserName().equals(username)) {			
			return new User(user.getUserName(), user.getPassWord(),
					new ArrayList<>());
		} else {
			throw new NotFoundException("Usuario no encontrado: " + username); 
		}
	}
}