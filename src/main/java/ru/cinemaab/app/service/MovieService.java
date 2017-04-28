package ru.cinemaab.app.service;

import java.util.List;

import ru.cinemaab.app.model.Movie;

public interface MovieService {

	void addMovie(Movie movie);
	
	public void updateMovie(Movie movie);

	public void removeMovie(int id);
	
	public void removeAllMovies();

	public Movie getMovieById(int id);

	public List<Movie> listMovie();
	
	public Integer getCount();
	
}
