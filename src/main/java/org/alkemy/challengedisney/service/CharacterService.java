package org.alkemy.challengedisney.service;

import org.alkemy.challengedisney.dto.CharacterWithMoviesDTO;
import org.alkemy.challengedisney.model.Character;

public interface CharacterService {
	public Character save(Character character);
	Iterable<Character> findAll();
	public Character findById(Integer characterId);
	public CharacterWithMoviesDTO getById(Integer characterId);
	public void deleteById(Integer characterId);
	public Character update(Character character);
	public Iterable<CharacterWithMoviesDTO> findByName(String name);
	public Iterable<Character> findByAge(Integer age);
	public Iterable<Character> findByMovieId(Integer movieId);
	public Iterable<Character> findByWeight(Integer weight);
	
}