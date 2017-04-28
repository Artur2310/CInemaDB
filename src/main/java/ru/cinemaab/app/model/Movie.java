package ru.cinemaab.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int id;


	@Column(name = "PICTURE_ID")
	private int pictureId;

	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@DecimalMax("10")
	@DecimalMin("0")
	@NumberFormat(style=Style.NUMBER)
	@Column(name = "IMDB")
	private float imdb;

	@Column(name = "DESCRIPTION", length = 550)
	private String description;

	@Column(name = "GENRE")
	private String genre;

//	@ManyToMany(mappedBy = "movies")
//	private Set<Person> persons = new HashSet<Person>();

	public Movie() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getImdb() {
		return imdb;
	}

	public void setImdb(float imdb) {
		this.imdb = imdb;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
	public String toString(){
		return id+" "+title+" "+imdb+" "+genre;
	}
	
	public boolean equals(Movie movie){
		return this.id == movie.getId();
		
	}
}
