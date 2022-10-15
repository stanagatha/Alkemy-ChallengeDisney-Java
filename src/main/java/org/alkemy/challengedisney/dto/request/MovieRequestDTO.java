package org.alkemy.challengedisney.dto.request;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MovieRequestDTO {
	
	private String image;
	private String title;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	private int rating;
	private List<Integer> charactersIds;
    private Integer genre; 
    
	public MovieRequestDTO() {
		
	} 

	public List<Integer> getCharactersIds() {
		return charactersIds;
	}

	public void setCharactersIds(List<Integer> charactersIds) {
		this.charactersIds = charactersIds;
	}

	public Integer getGenre() {
		return genre;
	}

	public void setGenre(Integer genre) {
		this.genre = genre;
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
