package org.alkemy.challengedisney.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "movieId")
@Entity(name="movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movieId; 
	
	@Column(nullable = true)
	private String image;
	
	@Column(nullable = false, length = 200)
	private String title;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@NotNull
	@Range(min=1, max=5, message = "El valor del campo Rating tiene que estar en el rango 1 a 5")
	@Column(nullable = false)
	private int rating;

	@ManyToMany(mappedBy = "moviesIds",cascade = {})
	private List<Character> characters;
	
    @JsonBackReference
    @ManyToOne(cascade = {})
    @JoinColumn(name="genre_id")
    private Genre genre; 
         
	public List<Character> getCharacters() {
		return characters;
	}
		
	public Movie() {
		
	} 
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer id) {
		this.movieId = id;
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

	public Movie(Integer movieId) {
		super();
		this.movieId = movieId;
	}

}
