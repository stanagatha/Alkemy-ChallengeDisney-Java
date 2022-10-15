package org.alkemy.challengedisney.service;

import org.alkemy.challengedisney.dto.CharacterWithMoviesDTO;
import org.alkemy.challengedisney.model.Character;
import org.alkemy.challengedisney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService{
	
	@Autowired
	CharacterRepository characterRepository;

	@Override
	public Iterable<Character> findAll() {
		return characterRepository.findAll();
	}

	@Override
	public Character save(Character character) {
		 return characterRepository.save(character);
	}

	@Override
	public CharacterWithMoviesDTO getById(Integer characterId) {
		 return characterRepository.getCharacter(characterId);
	}

	@Override
	public void deleteById(Integer characterId) {
		 characterRepository.deleteById(characterId);
	}

	@Override
	public Character update(Character character) {
		  return characterRepository.update(character);
	}

	@Override
	public Iterable<CharacterWithMoviesDTO> findByName(String name) {
		return characterRepository.findByName(name);
	}

	@Override
	public Iterable<Character> findByAge(Integer age) {
		return characterRepository.findByAge(age);
	}

	@Override
	public Iterable<Character> findByMovieId(Integer movieId) {
		return characterRepository.findByMovieId(movieId);
	}

	@Override
	public Iterable<Character> findByWeight(Integer weight) {
		return characterRepository.findByWeight(weight);
	}

	@Override
	public Character findById(Integer characterId) {
		return characterRepository.findById(characterId);
	}

}
