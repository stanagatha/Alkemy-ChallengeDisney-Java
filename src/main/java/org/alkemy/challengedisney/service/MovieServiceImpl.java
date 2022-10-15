package org.alkemy.challengedisney.service;

import org.alkemy.challengedisney.dto.MovieDTO;
import org.alkemy.challengedisney.model.Movie;
import org.alkemy.challengedisney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MovieRepository movieRepository;
	
	@Override
	public Iterable<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	public MovieDTO getById(Integer movieId) {		
		return movieRepository.getMovie(movieId);
	}

	@Override
	public void deleteById(Integer movieId) {
		movieRepository.deleteById(movieId);
		
	}

	@Override
	public Movie save(Movie movie) {		
		return movieRepository.save(movie);
	}

	@Override
	public Movie update(Movie movie) {		
		return movieRepository.update(movie);
	}

	@Override
	public Iterable<MovieDTO> findByName(String name) {
		return movieRepository.findByName(name);
	}

	@Override
	public Iterable<Movie> findByGenre(Integer genre) {
		return movieRepository.findByGenre(genre);
	}

	@Override
	public Iterable<Movie> getAllByOrder(String order) {
		return movieRepository.getAllByOrder(order);
	}

	@Override
	public Movie findById(Integer movieId) {
		return movieRepository.findById(movieId);
	}

}
