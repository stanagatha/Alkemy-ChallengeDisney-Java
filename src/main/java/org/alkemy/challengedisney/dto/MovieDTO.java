package org.alkemy.challengedisney.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class MovieDTO implements Serializable {
	
	
	private static final long serialVersionUID = -1310616663788323448L;
	private Integer movieId; 
	private String image;
	private String title;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	private int rating;
	private GenreDTO genre; 
	private List<CharacterDTO> characters;
	public MovieDTO() {}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<CharacterDTO> getCharacters() {
		return characters;
	}
	public void setCharacters(List<CharacterDTO> characters) {
		this.characters = characters;
	}

}
