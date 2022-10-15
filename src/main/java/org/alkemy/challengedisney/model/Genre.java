package org.alkemy.challengedisney.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name="genres")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer genreId;
	
	@Column(nullable = false, length = 200)
	private String name;
	
	@Column(nullable = true)
	private String image;
	
    @JsonManagedReference
    @OneToMany(mappedBy = "genre")
	private List<Movie> moviesIds;

	public Genre(Integer genreId) {
		this.genreId = genreId;
	}
	
	public Genre() {
		
	}
	
	public List<Movie> getMoviesIds() {
		return moviesIds;
	}

	public void setMoviesIds(List<Movie> moviesIds) {
		this.moviesIds = moviesIds;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
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

}
