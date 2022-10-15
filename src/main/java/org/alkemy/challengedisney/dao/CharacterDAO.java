package org.alkemy.challengedisney.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.alkemy.challengedisney.model.Character;
  
@Repository
public interface CharacterDAO extends CrudRepository<Character, Integer>{
	@Query( value = "SELECT c.image, c.name FROM characters c")
	public Iterable<Character> findAll();

	@Query( value = "SELECT c.image, c.name FROM characters c where c.age=?1")
	public Iterable<Character> findByAge(Integer age);

	@Query( value = "SELECT c.image, c.name FROM characters c where c.weight=?1")
	public Iterable<Character> findByWeight(Integer weight);
	
	@Query( 
			value = "SELECT c.image, c.name FROM characters as c "
			+ "inner join c.moviesIds m "
			+ "where m.movieId=?1")
	public Iterable<Character> findByMovieId(Integer movieId);
	
	public boolean existsByName(String name);
	public Iterable<Character> findByName(String name);
}
