package org.alkemy.challengedisney.repository;

import java.util.Optional;

import org.alkemy.challengedisney.dto.GenreWithMoviesDTO;
import org.alkemy.challengedisney.model.Genre;

public interface GenreRepository{
	public Iterable<GenreWithMoviesDTO> findAll();
	public Optional<Genre> findById(Integer genreId);
	public void deleteById(Integer genreId);
	public Genre save(Genre genre);
	Genre update(Genre genre);
	GenreWithMoviesDTO getById(Integer genreId);
}
