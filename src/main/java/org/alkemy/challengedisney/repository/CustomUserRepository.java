package org.alkemy.challengedisney.repository;

import org.alkemy.challengedisney.model.CustomUser;

public interface CustomUserRepository {
	CustomUser save(CustomUser user);
	CustomUser findByUserName(String username);
	CustomUser update(CustomUser user);
}
