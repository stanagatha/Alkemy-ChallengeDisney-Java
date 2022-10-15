package org.alkemy.challengedisney.service;

import java.util.Optional;

import org.alkemy.challengedisney.dto.GenreWithMoviesDTO;
import org.alkemy.challengedisney.model.Genre;
import org.alkemy.challengedisney.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreServiceImpl implements GenreService{
	
	@Autowired
	GenreRepository genreRepository;

	@Override
	public Iterable<GenreWithMoviesDTO> findAll() {		
		return genreRepository.findAll();
	}

	@Override
	public Optional<Genre> findById(Integer genreId) {
		return genreRepository.findById(genreId);
	}

	@Override
	@Transactional
	public void deleteById(Integer genreId) {
		genreRepository.deleteById(genreId);
		
	}

	@Override
	public Genre save(Genre genre) {		
		return genreRepository.save(genre);
	}

	@Override
	public Genre update(Genre genre) {		
		return genreRepository.update(genre);
	}

	@Override
	public GenreWithMoviesDTO getById(Integer genreId) {
		return genreRepository.getById(genreId);
	}

}
