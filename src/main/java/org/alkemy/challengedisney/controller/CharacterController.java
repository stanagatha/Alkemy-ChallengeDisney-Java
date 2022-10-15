package org.alkemy.challengedisney.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.alkemy.challengedisney.dto.CharacterWithMoviesDTO;
import org.alkemy.challengedisney.dto.request.CharacterRequestDTO;
import org.alkemy.challengedisney.model.Character;
import org.alkemy.challengedisney.model.Movie;
import org.alkemy.challengedisney.service.CharacterService;
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
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;
    
    @Autowired
    private MovieService movieService;
   
    @GetMapping
    public ResponseEntity<Iterable<Character>> getAll(){
    	Iterable<Character> characters = characterService.findAll();
    	if(characters != null) {
    		return new ResponseEntity<Iterable<Character>>(characters,null,HttpStatus.OK);
    	}
    	return new ResponseEntity<Iterable<Character>>(null,null,HttpStatus.NOT_FOUND);
    }
    
    
    @GetMapping(params = "name")
    public ResponseEntity<Iterable<CharacterWithMoviesDTO>> findByName(@RequestParam(value="name", required=false) String name){    	
    	return new ResponseEntity<Iterable<CharacterWithMoviesDTO>>(characterService.findByName(name),null,HttpStatus.OK);
    }
    
    @GetMapping(params = "weight")
    public ResponseEntity<Iterable<Character>> findByWeight(@RequestParam(value="weight", required=false) Integer weight){    	
    	return new ResponseEntity<Iterable<Character>>(characterService.findByWeight(weight),null,HttpStatus.OK);
    }
    
    @GetMapping(params = "age")
    public ResponseEntity<Iterable<Character>> findByAge(@RequestParam(value="age", required=false) Integer age){
    	return new ResponseEntity<Iterable<Character>>(characterService.findByAge(age),null,HttpStatus.OK);
    }
    
    @GetMapping(params = "movies")
    public ResponseEntity<Iterable<Character>> findByMovieId(@RequestParam(value="movies", required=false) Integer movieId){     	
    	return new ResponseEntity<Iterable<Character>>(characterService.findByMovieId(movieId),null,HttpStatus.OK);  	
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CharacterWithMoviesDTO> getCharacter(@PathVariable("id") Integer characterId){
    	CharacterWithMoviesDTO character = characterService.getById(characterId);
    	if(character != null){ 
    		return new ResponseEntity<CharacterWithMoviesDTO>(character,null,HttpStatus.OK);	
    	}
    	return new ResponseEntity<CharacterWithMoviesDTO>(null,null,HttpStatus.NOT_FOUND);   	
    }
    
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCharacter(@PathVariable("id") Integer characterId){
    	characterService.deleteById(characterId);
    	return new ResponseEntity<Object>(null,null,HttpStatus.NO_CONTENT);    	    
    } 

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ResponseCreatedVO> addCharacter(@RequestBody CharacterRequestDTO character){
    	
		Character savedCharacter = characterService.save(prepareCharacter(null, character));
		
		ResponseCreatedVO response = new ResponseCreatedVO();	
		response.setStatus(HttpStatus.CREATED);
		response.setId(savedCharacter.getCharacterId());
		
    	return new ResponseEntity<ResponseCreatedVO>(response,null,HttpStatus.CREATED);
    }
    
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCharacter(@PathVariable("id") Integer characterId, @RequestBody CharacterRequestDTO character){		
		
		characterService.update(prepareCharacter(characterId,character));
		
    	return new ResponseEntity<Object>(null,null,HttpStatus.NO_CONTENT);
    }
    
    private Character prepareCharacter(Integer characterId, CharacterRequestDTO character) {
    	Character finalCharacter = new Character();	
		List<Movie> listMovies = new ArrayList<Movie>();		
		
		// Eliminar películas/series duplicadas
		if(character.getMoviesIds()!=null) {
			character.setMoviesIds(character
					.getMoviesIds()
					.stream()
					.distinct() 
					.collect(Collectors.toList()));
			
			character.getMoviesIds().forEach(id -> listMovies.add(movieService.findById(id)));
		}
		
		if(characterId!=null) {
			finalCharacter.setCharacterId(characterId);	
		}
		
		finalCharacter.setMoviesIds(listMovies);
		finalCharacter.setAge(character.getAge());
		finalCharacter.setHistory(character.getHistory());		
		finalCharacter.setName(character.getName());
		finalCharacter.setWeight(character.getWeight());
		finalCharacter.setImage(character.getImage());
		
		return finalCharacter;
    }
}
