package org.alkemy.challengedisney.dao;

import java.util.Optional;

import org.alkemy.challengedisney.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreDAO extends CrudRepository<Genre, Integer>{

	public Iterable<Genre> findAll();
	public Optional<Genre> findById(Integer genreId);
	public void deleteById(Integer genreId);
	public boolean existsByName(String name);
	public Iterable<Genre> findByName(String name);
	
}
