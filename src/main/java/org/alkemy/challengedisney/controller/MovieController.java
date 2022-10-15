package org.alkemy.challengedisney.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.alkemy.challengedisney.dto.MovieDTO;
import org.alkemy.challengedisney.dto.request.MovieRequestDTO;
import org.alkemy.challengedisney.exception.BadRequestException;
import org.alkemy.challengedisney.model.Character;
import org.alkemy.challengedisney.model.Movie;
import org.alkemy.challengedisney.service.CharacterService;
import org.alkemy.challengedisney.service.GenreService;
import org.alkemy.challengedisney.service.MovieService;
import org.alkemy.challengedisney.vo.ResponseCreatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private CharacterService characterService;
	
	@GetMapping
	public ResponseEntity<Iterable<Movie>> getAll(){
    	Iterable<Movie> movies = movieService.findAll();
    	return new ResponseEntity<Iterable<Movie>>(movies,null,HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getCharacter(@PathVariable("id") Integer movieId){
		MovieDTO movie = movieService.getById(movieId);
   		return new ResponseEntity<MovieDTO>(movie,null,HttpStatus.OK);	
	}	
	
	
    @GetMapping(params = "name")
    public ResponseEntity<Iterable<MovieDTO>> findByName(@RequestParam(value="name", required=false	) String name){    	
    	return new ResponseEntity<Iterable<MovieDTO>>(movieService.findByName(name),null,HttpStatus.OK);
    }
    
    @GetMapping(params = "genre")
    public ResponseEntity<Iterable<Movie>> findByWeight(@RequestParam(value="genre", required=false) Integer genre){    	
    	return new ResponseEntity<Iterable<Movie>>(movieService.findByGenre(genre),null,HttpStatus.OK);
    }
    
    @GetMapping(params = "order")
    public ResponseEntity<Iterable<Movie>> findByAge(@RequestParam(value="order", required=false) String order){
    	return new ResponseEntity<Iterable<Movie>>(movieService.getAllByOrder(order),null,HttpStatus.OK);
    }
	
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") Integer movieId){
    	movieService.deleteById(movieId);
    	return new ResponseEntity<Object>(null,null,HttpStatus.NO_CONTENT);       	
    }
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping 
    public ResponseEntity<ResponseCreatedVO> addMovie(@RequestBody MovieRequestDTO movie){
		
		Movie savedMovie = movieService.save(prepareMovie(null, movie,false));
   	 	
		ResponseCreatedVO response = new ResponseCreatedVO();	
		response.setStatus(HttpStatus.CREATED);
		response.setId(savedMovie.getMovieId());
		
		return new ResponseEntity<ResponseCreatedVO>(response,null,HttpStatus.CREATED);
    }
    
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable("id") Integer movieId,@RequestBody MovieRequestDTO movie){  
		
		movieService.update(prepareMovie(movieId, movie, true));
		
    	return new ResponseEntity<Object>(null,null,HttpStatus.NO_CONTENT);    
    }
	
    private Movie prepareMovie(Integer movieId, MovieRequestDTO movie,boolean isUpdate) {
    	Movie finalMovie= new Movie();	
		List<Character> listCharacters = new ArrayList<Character>();		
		
		if(isUpdate) {
			if(movie.getCharactersIds()==null || movie.getCharactersIds().isEmpty()) {
				throw new BadRequestException("No se enviaron los Ids de personajes");
			}
		}

		// Eliminar personajes duplicados		
		if(movie.getCharactersIds()!=null) {
			movie.setCharactersIds(movie
					.getCharactersIds()
					.stream()
					.distinct() 
					.collect(Collectors.toList()));

			// Genero una coleccion de personajes con la película actual asignada.
			movie.getCharactersIds().forEach(
					id -> {
						// Busca las propiedades del personaje
						Character character = characterService.findById(id);
						
						// Setea en el personaje actual la película
						character.setMoviesIds(new ArrayList<>(Arrays.asList(finalMovie)));
						
						// Agrego en la lista de personajes al personaje actual
						listCharacters.add(character);
					}
			);
		}			
		
		if(movieId!=null) {
			finalMovie.setMovieId(movieId);	
		}
			
		if(movie.getGenre()==null) {
			throw new BadRequestException("El género es requerido");
		}
		
		finalMovie.setGenre(genreService.findById(movie.getGenre()).get());

		finalMovie.setCharacters(listCharacters);

		finalMovie.setCreatedAt(movie.getCreatedAt());		
		finalMovie.setImage(movie.getImage());
		finalMovie.setRating(movie.getRating());
		finalMovie.setTitle(movie.getTitle());
		
		return finalMovie;
    }
}
