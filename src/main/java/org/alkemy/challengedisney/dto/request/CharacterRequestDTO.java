package org.alkemy.challengedisney.dto.request;

import java.util.List;

public class CharacterRequestDTO {
	
	private String name;
	private int weight;
	private int age;
	private String image;
	private String history;
	private List<Integer> moviesIds;
	
	public List<Integer> getMoviesIds() {
		return moviesIds;
	}

	public void setMoviesIds(List<Integer> moviesIds) {
		this.moviesIds = moviesIds;
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

	public void setHistory(String history) {
		this.history = history;
	}	
}
