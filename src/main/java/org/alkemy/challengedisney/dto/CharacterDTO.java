package org.alkemy.challengedisney.dto;

import java.io.Serializable;



public class CharacterDTO implements Serializable {	
	
	
	private static final long serialVersionUID = -8722679080327028720L;
	private Integer characterId; 	
	private int weight;	
	private int age;
	private String name;	
	private String image;
	private String history;

	public CharacterDTO(Integer characterId, int weight, int age, String name, String image, String history) {
		super();
		this.characterId = characterId;
		this.weight = weight;
		this.age = age;
		this.name = name;
		this.image = image;
		this.history = history;
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
	public void setHistory(String history) {
		this.history = history;
	}	
}
