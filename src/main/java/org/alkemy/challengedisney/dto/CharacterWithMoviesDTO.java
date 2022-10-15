package org.alkemy.challengedisney.dto;

import java.io.Serializable;
import java.util.List;

public class CharacterWithMoviesDTO implements Serializable{
	
	
	private static final long serialVersionUID = -1579434075208169418L;
	private Integer characterId; 	
	private int weight;	
	private int age;
	private String name;	
	private String image;
	private String history;
	private List<MovieWithoutCharactersDTO> movies;
	public CharacterWithMoviesDTO() {}
	public CharacterWithMoviesDTO(Integer characterId, int weight, int age, String name, String image, String history,
			List<MovieWithoutCharactersDTO> movies) {
		super();
		this.characterId = characterId;
		this.weight = weight;
		this.age = age;
		this.name = name;
		this.image = image;
		this.history = history;
		this.movies = movies;
	}
	
	public Integer getCharacterId() {
		return characterId;
	}
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getHistory() {
		return history;
	}
	public List<MovieWithoutCharactersDTO> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieWithoutCharactersDTO> movies) {
		this.movies = movies;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setHistory(String history) {
		this.history = history;
	}
}
