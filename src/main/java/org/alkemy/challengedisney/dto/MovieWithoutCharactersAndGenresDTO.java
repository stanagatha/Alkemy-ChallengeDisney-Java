package org.alkemy.challengedisney.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MovieWithoutCharactersAndGenresDTO implements Serializable {

	
	private static final long serialVersionUID = 3034247514005171410L;
	private Integer movieId; 
	private String image;
	private String title;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	private int rating;
	
	public MovieWithoutCharactersAndGenresDTO(Integer movieId, String image, String title, Date createdAt, int rating) {
		super();
		this.movieId = movieId;
		this.image = image;
		this.title = title;
		this.createdAt = createdAt;
		this.rating = rating;
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
	
}
