package org.alkemy.challengedisney.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.alkemy.challengedisney.dao.CharacterDAO;
import org.alkemy.challengedisney.dto.CharacterWithMoviesDTO;
import org.alkemy.challengedisney.dto.MovieWithoutCharactersDTO;
import org.alkemy.challengedisney.exception.NotFoundException;
import org.alkemy.challengedisney.model.Character;
import org.alkemy.challengedisney.model.Movie;
import org.alkemy.challengedisney.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository{

	@Autowired
	CharacterDAO characterDao;

	@Override
	public CharacterWithMoviesDTO getCharacter(Integer id) {

		Optional<Character> character = characterDao.findById(id); 		
			
		if(!character.isPresent()) {
			throw new NotFoundException(Messages.CHARACTER_NOT_FOUND + ": id " + id);
		}
		
		return mapperCharacterToCharacterWithMoviesDTO(character.get());
	
	}

	@Override
	public Iterable<Character> findAll() { 
		Iterable<Character> characters = characterDao.findAll();
		if(!characters.iterator().hasNext()) {
			throw new NotFoundException(Messages.CHARACTERS_NOT_FOUND);
		}
		return characters;
	}

	@Override
	public <S extends Character> S save(S entity) {
		return characterDao.save(entity);
	}

	@Override
	public <S extends Character> S update(S entity) {	
		return characterDao.save(entity);
	}
	
	@Override
	public void deleteById(Integer id) {
		characterDao.deleteById(id);
	}

	@Override
	public Iterable<CharacterWithMoviesDTO> findByName(String name) {
		Iterable<Character> characters = characterDao.findByName(name);
		if(!characters.iterator().hasNext()) {
			throw new NotFoundException(Messages.CHARACTERS_NOT_FOUND);
		}
		
		Collection<CharacterWithMoviesDTO> characterWithMoviesDTO = new ArrayList<>();
		characters.forEach( c -> {
			characterWithMoviesDTO.add(mapperCharacterToCharacterWithMoviesDTO(c));	
		});
				
		return characterWithMoviesDTO;
	}

	@Override
	public Iterable<Character> findByAge(Integer age) {
		Iterable<Character> characters = characterDao.findByAge(age);
		if(!characters.iterator().hasNext()) {
			throw new NotFoundException(Messages.CHARACTERS_NOT_FOUND);
		}
		return characters;
	}

	@Override
	public Iterable<Character> findByMovieId(Integer movieId) {
		Iterable<Character> characters = characterDao.findByMovieId(movieId);
		if(!characters.iterator().hasNext()) {
			throw new NotFoundException(Messages.CHARACTERS_NOT_FOUND);
		}
		return characters;	
	}

	@Override
	public Iterable<Character> findByWeight(Integer weight) {
		Iterable<Character> characters = characterDao.findByWeight(weight);
		if(!characters.iterator().hasNext()) {
			throw new NotFoundException("No se encontraron personajes");
		}
		return characters;
	}

	@Override
	public Character findById(Integer characterId) {
		Optional<Character> character = characterDao.findById(characterId);
		
		if(!character.isPresent()) {
			throw new NotFoundException(Messages.CHARACTER_NOT_FOUND + ": id " + characterId);
		}
		
		return character.get();
	}

	// Mappers
	private CharacterWithMoviesDTO mapperCharacterToCharacterWithMoviesDTO(Character character) {
		// Mapeo las películas en el DTO
		CharacterWithMoviesDTO characterDto = new CharacterWithMoviesDTO();
		characterDto.setCharacterId(character.getCharacterId());
		characterDto.setAge(character.getAge()); 
		characterDto.setImage(character.getImage());
		characterDto.setHistory(character.getHistory());
		characterDto.setName(character.getName()); 
		characterDto.setWeight(character.getWeight());
		// Armo una lista de de las películas sin los otros personajes
		List<MovieWithoutCharactersDTO> listMovies   = new ArrayList<>();
		for(Movie item :character.getMoviesIds()) {
			listMovies.add(new MovieWithoutCharactersDTO(
				item.getMovieId(),
				item.getImage(),
				item.getTitle(),
				item.getCreatedAt(),
				item.getRating(),
				item.getGenre()
				));
			 }
		
		characterDto.setMovies(listMovies); 
		 
		return characterDto;
	}
}
