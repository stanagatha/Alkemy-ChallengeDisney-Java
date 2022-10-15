package org.alkemy.challengedisney.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.alkemy.challengedisney.dao.GenreDAO;
import org.alkemy.challengedisney.dto.GenreWithMoviesDTO;
import org.alkemy.challengedisney.dto.MovieWithoutCharactersAndGenresDTO;
import org.alkemy.challengedisney.exception.ConflictException;
import org.alkemy.challengedisney.exception.NotFoundException;
import org.alkemy.challengedisney.model.Genre;
import org.alkemy.challengedisney.model.Movie;
import org.alkemy.challengedisney.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

	@Autowired
	GenreDAO genreDao;
	
	@Override
	public Iterable<GenreWithMoviesDTO> findAll() {
		Iterable<Genre> genres = genreDao.findAll();
		if(!genres.iterator().hasNext()) {
			throw new NotFoundException(Messages.GENRES_NOT_FOUND);
		}
		
		Collection<GenreWithMoviesDTO> genreWithMoviesDTO = new ArrayList<>();
		genres.forEach( c -> {
			genreWithMoviesDTO.add(mapperGenreToGenreWithMoviesDTO(c));	
		});
				
		return genreWithMoviesDTO;	
	}

	@Override
	public GenreWithMoviesDTO getById(Integer genreId) {
		
		Optional<Genre> genre = genreDao.findById(genreId);
		
		if(!genre.isPresent()) {
			throw new NotFoundException(Messages.GENRE_NOT_FOUND + ": id " + genreId);
		}
		
		return mapperGenreToGenreWithMoviesDTO(genre.get());
	}
	
	@Override
	public Optional<Genre> findById(Integer genreId) {
		
		Optional<Genre> genre = genreDao.findById(genreId);
		
		if(!genre.isPresent()) {
			throw new NotFoundException(Messages.GENRE_NOT_FOUND + ": id " + genreId);
		}
		
		return genre;
	}

	@Override
	public void deleteById(Integer genreId) {
		genreDao.deleteById(genreId);		
	}

	@Override
	public Genre save(Genre genre) {
		
		if(genreDao.existsByName(genre.getName())) {
			throw new ConflictException("El género ya existe: " + genre.getName());
		}
		
		return genreDao.save(genre);
	}

	@Override
	public Genre update(Genre genre) {
		Iterable<Genre> genres = genreDao.findByName(genre.getName());
		
		genres.forEach(m -> {			
			// Ya existe un nombre y no es del genero que estoy actualizando?
			if(m.getName().equals(genre.getName())) {
				if(!m.getGenreId().equals(genre.getGenreId())) {
					throw new ConflictException(Messages.NAME_EXISTS);	
				}							
			}
		});
		
		return genreDao.save(genre);
	}
	
	// Mappers
	private GenreWithMoviesDTO mapperGenreToGenreWithMoviesDTO(Genre genre) {
		// Mapeo las películas en el DTO
		GenreWithMoviesDTO genreDto = new GenreWithMoviesDTO();
		genreDto.setGenreId(genre.getGenreId()); 
		genreDto.setImage(genre.getImage());
		genreDto.setName(genre.getName()); 
		 		
		// Armo una lista de de las películas sin los otros personajes
		List<MovieWithoutCharactersAndGenresDTO> listMovies   = new ArrayList<>();
		for(Movie item :genre.getMoviesIds()) {
			listMovies.add(new MovieWithoutCharactersAndGenresDTO(
				item.getMovieId(),
				item.getImage(),
				item.getTitle(),
				item.getCreatedAt(),
				item.getRating()
				));
			 }
		
		genreDto.setMovies(listMovies); 
		 
		return genreDto;
	}
}
