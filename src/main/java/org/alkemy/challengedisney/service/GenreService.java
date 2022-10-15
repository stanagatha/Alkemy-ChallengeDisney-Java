package org.alkemy.challengedisney.service;

import java.util.Optional;

import org.alkemy.challengedisney.dto.GenreWithMoviesDTO;
import org.alkemy.challengedisney.model.Genre;

public interface GenreService {

	Iterable<GenreWithMoviesDTO>findAll();
	public Optional<Genre> findById(Integer genreId);
	public void deleteById(Integer genreId);
	public Genre save(Genre genre);
	public Genre update(Genre genre);
	GenreWithMoviesDTO getById(Integer genreId);
	
	
	

}
