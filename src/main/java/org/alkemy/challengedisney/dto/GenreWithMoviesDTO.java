package org.alkemy.challengedisney.dto;

import java.util.List;

public class GenreWithMoviesDTO {

	private Integer genreId;
	private String name;
	private String image;
	private List<MovieWithoutCharactersAndGenresDTO> movies;
	
	public GenreWithMoviesDTO() {
		
	}
	
	public GenreWithMoviesDTO(Integer genreId, String name, String image, List<MovieWithoutCharactersAndGenresDTO> movies) {
		super();
		this.genreId = genreId;
		this.name = name;
		this.image = image;
		this.movies = movies;
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

	public List<MovieWithoutCharactersAndGenresDTO> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieWithoutCharactersAndGenresDTO> movies) {
		this.movies = movies;
	}
}
