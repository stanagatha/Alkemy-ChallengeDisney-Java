package org.alkemy.challengedisney.repository;
import org.alkemy.challengedisney.dto.MovieDTO;
import org.alkemy.challengedisney.model.Movie;

public interface MovieRepository {
	MovieDTO getMovie(Integer id);
	Iterable<Movie> findAll();
	void deleteById(Integer movieId);
	<S extends Movie> S save(S entity);
	Iterable<Movie> getAllByOrder(String order);
	Iterable<MovieDTO> findByName(String name);
	Iterable<Movie> findByGenre(Integer genre);
	Movie findById(Integer id);
	<S extends Movie> S update(S entity);
	
}