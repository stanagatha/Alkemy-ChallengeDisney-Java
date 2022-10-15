package org.alkemy.challengedisney.service;

import org.alkemy.challengedisney.model.CustomUser;

public interface CustomUserService {
	CustomUser save(CustomUser user);

	CustomUser update(CustomUser user);
}
