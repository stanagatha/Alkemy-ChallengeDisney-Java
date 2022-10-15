package org.alkemy.challengedisney.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.alkemy.challengedisney.dao.MovieDAO;
import org.alkemy.challengedisney.dto.CharacterDTO;
import org.alkemy.challengedisney.dto.GenreDTO;
import org.alkemy.challengedisney.dto.MovieDTO;
import org.alkemy.challengedisney.exception.NotFoundException;
import org.alkemy.challengedisney.model.Character;
import org.alkemy.challengedisney.model.Genre;
import org.alkemy.challengedisney.model.Movie;
import org.alkemy.challengedisney.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

	@Autowired
	MovieDAO movieDao;

	@Override
	public MovieDTO getMovie(Integer id) {

		// Traigo la película utilizando la capa DAO
		Optional<Movie> movie = movieDao.findById(id);

		if(!movie.isPresent()) {
			throw new NotFoundException(Messages.MOVIE_NOT_FOUND + ": id " + id);
		}
		
		return mapperMovieToMovieDTO(movie.get());
		
	}

	public Iterable<Movie> findAll() {
		Iterable<Movie> movies = movieDao.findAll();
		if(!movies.iterator().hasNext()) {
			throw new NotFoundException(Messages.MOVIES_NOT_FOUND);
		}
		return movies;		
	}
	
	@Override
	public <S extends Movie> S save(S entity) {
		return movieDao.save(entity);
	}
	
	@Override
	public <S extends Movie> S update(S entity) {		
		return movieDao.save(entity);
	}
	
	public void deleteById(Integer id) {
		movieDao.deleteById(id);
	}

	@Override
	public Iterable<Movie> getAllByOrder(String order) {
		Iterable<Movie> movies;
		if(order.equalsIgnoreCase("asc")) {
			movies = movieDao.getAllByOrderAsc();
		}else if(order.equalsIgnoreCase("desc")) {
			movies = movieDao.getAllByOrderDesc();
		}else {
			throw new NotFoundException("El orden " + order + " no existe");	
		}
		
		if(!movies.iterator().hasNext()) {
			throw new NotFoundException(Messages.MOVIES_NOT_FOUND);
		}
		return movies;
	}

	@Override
	public Iterable<MovieDTO> findByName(String title) {
		Iterable<Movie> movies = movieDao.findByTitle(title);
		if(!movies.iterator().hasNext()) {
			throw new NotFoundException(Messages.MOVIES_NOT_FOUND);
		}
		
		Collection<MovieDTO> moviesDTO = new ArrayList<>();
		movies.forEach( m -> {
			moviesDTO.add(mapperMovieToMovieDTO(m));	
		});
		
		
		return moviesDTO;
	}

	@Override
	public Iterable<Movie> findByGenre(Integer genre) {
		Iterable<Movie> movies = movieDao.findByGenres(genre);
		if(!movies.iterator().hasNext()) {
			throw new NotFoundException(Messages.MOVIES_NOT_FOUND);
		}
		return movies;
	}

	@Override
	public Movie findById(Integer id) {
		Optional<Movie> movie = movieDao.findById(id);
		if(movie.isPresent()) {
			return movie.get();
		}
		
		throw new NotFoundException("No se encontro la película: id " + id);
	}

	// Mappers
	private MovieDTO mapperMovieToMovieDTO(Movie movie) {
		
		// Mapeo la película en el DTO
		MovieDTO movieDto = new MovieDTO();
		movieDto.setMovieId(movie.getMovieId());
		movieDto.setTitle(movie.getTitle());
		movieDto.setCreatedAt(movie.getCreatedAt());
		movieDto.setImage(movie.getImage());
		movieDto.setRating(movie.getRating());
 
		// Armo una lista de personajes sin tener en cuenta en que otras películas participaron
		List<CharacterDTO> listCharacters   = new ArrayList<>();
		for(Character item :movie.getCharacters()) {
			listCharacters.add(new CharacterDTO(
					item.getCharacterId(),
					item.getWeight(),
					item.getAge(),
					item.getName(),
					item.getImage(),
					item.getHistory()					
					));
		 }
				
		Genre genre = movie.getGenre();
		GenreDTO genreDTO = new GenreDTO();
		if(genre!=null) {						
			genreDTO.setGenreId(genre.getGenreId());
			genreDTO.setName(genre.getName());
			genreDTO.setImage(genre.getImage());
			
			movieDto.setGenre(genreDTO);
		}
		
		movieDto.setCharacters(listCharacters);
		
		return movieDto; 
	}
}
