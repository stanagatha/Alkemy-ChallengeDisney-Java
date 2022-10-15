package org.alkemy.challengedisney.repository;

import org.alkemy.challengedisney.dto.CharacterWithMoviesDTO;
import org.alkemy.challengedisney.model.Character;

public interface CharacterRepository {
	CharacterWithMoviesDTO getCharacter(Integer id);
	Iterable<Character> findAll();
	<S extends Character> S save(S entity);
	void deleteById(Integer id);
	Iterable<CharacterWithMoviesDTO> findByName(String name);
	Iterable<Character> findByAge(Integer age);
	Iterable<Character> findByMovieId(Integer movieId);
	Iterable<Character> findByWeight(Integer weight);
	<S extends Character> S update(S entity);
	Character findById(Integer characterId);
}