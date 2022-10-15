package org.alkemy.challengedisney.repository;

import org.alkemy.challengedisney.dao.CustomUserDAO;
import org.alkemy.challengedisney.exception.ConflictException;
import org.alkemy.challengedisney.exception.NotFoundException;
import org.alkemy.challengedisney.model.CustomUser;
import org.alkemy.challengedisney.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

	@Autowired
	CustomUserDAO customUserDao;

	@Override
	public CustomUser save(CustomUser user) {
		
		if(customUserDao.existsByUserName(user.getUserName())) {
			throw new ConflictException("Usuario ya registrado: " + user.getUserName());
		}	
		if(customUserDao.existsByEmail(user.getEmail())) {
			throw new ConflictException("Usuario ya registrado: " + user.getUserName());
		}	
		
		return customUserDao.save(user);
	}

	@Override
	public CustomUser update(CustomUser user) {	
		CustomUser customUser = customUserDao.findByUserName(user.getUserName());
		
		// Ya existe un nombre y no es del usuario que estoy actualizando?
		if(customUser.getUserName().equals(user.getUserName())) {
			if(!customUser.getId().equals(user.getId())) {
				throw new ConflictException(Messages.NAME_EXISTS);	
			}							
		}
		
		return customUserDao.save(user);
	}
	
	@Override
	public CustomUser findByUserName(String username) {
		CustomUser customUser = customUserDao.findByUserName(username);
		if(customUser==null) {
			throw new NotFoundException("Usuario no encontrado: " + username);
		}
		
		return customUser;
	}

}
