package org.alkemy.challengedisney.dto.request;

import java.util.List;

public class GenreRequestDTO {

	private String name;
	private String image;
	private List<Integer> moviesIds;

	public GenreRequestDTO() {
		
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

	public List<Integer> getMoviesIds() {
		return moviesIds;
	}

	public void setMoviesIds(List<Integer> moviesIds) {
		this.moviesIds = moviesIds;
	}

}
