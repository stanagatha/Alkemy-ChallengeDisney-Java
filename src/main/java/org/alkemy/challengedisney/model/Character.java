package org.alkemy.challengedisney.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "characterId")

@Entity(name="characters")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer characterId; 
	
	@Column(nullable = false)
	private int weight;
	
	@Column(nullable = false)
	private int age;	
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = true)
	private String image;
	
	@Column(nullable = false, length = 200)
	private String history;	

    @ManyToMany(cascade = {}) 
    @JoinTable(name="characters_movies", 
    joinColumns = {
    		@JoinColumn(name = "character_id", referencedColumnName = "characterId")}, 
    inverseJoinColumns = {
    		@JoinColumn(name = "movie_id", referencedColumnName = "movieId")
    })
    
	private List<Movie> moviesIds;
       
	public Character() {
	}
	
	public Character(Integer id) {
		this.characterId = id;
	}

	public List<Movie> getMoviesIds() {
		return moviesIds;
	}

	public void setMoviesIds(List<Movie> moviesIds) {
		this.moviesIds = moviesIds;
	}

	public Integer getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Integer id) {
		this.characterId = id;
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
