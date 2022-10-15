package org.alkemy.challengedisney.service;

import org.alkemy.challengedisney.model.CustomUser;
import org.alkemy.challengedisney.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceImpl implements CustomUserService {

	@Autowired
	CustomUserRepository customUserRepository;
	
	@Override
	public CustomUser save(CustomUser user) {
		return customUserRepository.save(user);
	}

	@Override
	public CustomUser update(CustomUser user) {
		return customUserRepository.update(user);
	}
}
