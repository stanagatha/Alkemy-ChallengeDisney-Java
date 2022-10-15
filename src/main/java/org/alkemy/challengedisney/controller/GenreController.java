package org.alkemy.challengedisney.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.alkemy.challengedisney.dto.GenreWithMoviesDTO;
import org.alkemy.challengedisney.dto.request.GenreRequestDTO;
import org.alkemy.challengedisney.model.Genre;
import org.alkemy.challengedisney.model.Movie;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenreController {
	
	@Autowired	
	private GenreService genreService;
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public ResponseEntity<Iterable<GenreWithMoviesDTO>> getAll(){
	  	Iterable<GenreWithMoviesDTO> genres = genreService.findAll();
	  	return new ResponseEntity<Iterable<GenreWithMoviesDTO>>(genres,null,HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<GenreWithMoviesDTO> getGenre(@PathVariable("id") Integer genreId){
		GenreWithMoviesDTO genre = genreService.getById(genreId);
   		return new ResponseEntity<GenreWithMoviesDTO>(genre,null,HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable("id") Integer genreId){
		genreService.deleteById(genreId);
    	return new ResponseEntity<Object>(null,null,HttpStatus.NO_CONTENT); 
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<ResponseCreatedVO> addGenre(@RequestBody GenreRequestDTO genre){
   	 	
		Genre savedGenre = genreService.save(prepareGenre(null, genre));
	
   	 	ResponseCreatedVO response = new ResponseCreatedVO();	
		response.setStatus(HttpStatus.CREATED);
		response.setId(savedGenre.getGenreId());
		
		return new ResponseEntity<ResponseCreatedVO>(response,null,HttpStatus.CREATED);
		
	}
	 
	 @ResponseStatus(code = HttpStatus.NO_CONTENT)
	 @PutMapping("/{id}")
	 public ResponseEntity<Object> updateGenre(@PathVariable("id") Integer genreId, @RequestBody GenreRequestDTO genre){	
		genreService.update(prepareGenre(genreId, genre));    	
    	return new ResponseEntity<Object>(null,null,HttpStatus.NO_CONTENT);
	}
	 
	 // Mappers
    private Genre prepareGenre(Integer genreId, GenreRequestDTO genre) {
    	Genre finalGenre = new Genre();	
		List<Movie> listMovies = new ArrayList<Movie>();		
		
		// Eliminar películas/series duplicadas
		if(genre.getMoviesIds()!=null) {
			genre.setMoviesIds(genre
					.getMoviesIds()
					.stream()
					.distinct() 
					.collect(Collectors.toList()));
			
			genre.getMoviesIds().forEach(id -> {
				Movie m = movieService.findById(id);
				m.setGenre(finalGenre);
				listMovies.add(m);
			});
		}
		
		if(genreId!=null) {
			finalGenre.setGenreId(genreId);	
		}
		
		finalGenre.setMoviesIds(listMovies);
		finalGenre.setName(genre.getName());
		finalGenre.setImage(genre.getImage());
		
		return finalGenre;
    }

}
