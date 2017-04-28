package ru.cinemaab.app.dao;

import java.util.List;

import ru.cinemaab.app.model.Movie;

public interface MovieDao {

	void addMovie(Movie movie);

	public void updateMovie(Movie movie);

	public void removeMovie(int id);

	public void removeAllMovies();

	public Movie getMovieById(int id);
	
	public List<Movie> getMovieByTitle(String title);

	public List<Movie> listMovie();
	
	public Integer getCount();
	
	

}
