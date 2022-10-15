package org.alkemy.challengedisney.dao;

import org.alkemy.challengedisney.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserDAO extends JpaRepository<CustomUser, Long> {
	public CustomUser findByUserName(String username);
	public boolean existsByUserName(String username);
	public boolean existsByEmail(String email);
}
