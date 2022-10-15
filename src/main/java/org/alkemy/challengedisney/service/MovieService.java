package org.alkemy.challengedisney.service;

import org.alkemy.challengedisney.dto.MovieDTO;
import org.alkemy.challengedisney.model.Movie;

public interface MovieService {

	Iterable<Movie>findAll();
	public Movie findById(Integer movieId);
	public void deleteById(Integer movieId);
	public Movie save(Movie movie);
	public Movie update(Movie movie);
	Iterable<MovieDTO> findByName(String name);
	Iterable<Movie> findByGenre(Integer genre);
	Iterable<Movie> getAllByOrder(String order);
	public MovieDTO getById(Integer movieId);
}
