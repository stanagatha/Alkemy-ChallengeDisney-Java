package org.alkemy.challengedisney.dao;

import org.alkemy.challengedisney.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDAO extends CrudRepository<Movie, Integer>{
	@Query( value = "SELECT c.image, c.title, c.createdAt FROM movies c")
	public Iterable<Movie> findAll();

	@Query( 
			value = "SELECT c.image, c.title, c.createdAt FROM movies c "
			+ "inner join c.genre g "
			+ "where g.genreId=?1")
	public Iterable<Movie> findByGenres(Integer genre);

	@Query( value = "SELECT c.image, c.title, c.createdAt FROM movies c order by c.createdAt desc")
	public Iterable<Movie> getAllByOrderDesc();
	
	@Query( value = "SELECT c.image, c.title, c.createdAt FROM movies c order by c.createdAt asc")
	public Iterable<Movie> getAllByOrderAsc();
	
	public boolean existsByTitle(String title);
	
	public Iterable<Movie> findByTitle(String title);
}