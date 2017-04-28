package ru.cinemaab.app.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cinemaab.app.dao.MovieDao;
import ru.cinemaab.app.model.Movie;;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieDao movieDao;

	public MovieDao getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	
	@Transactional
	public void addMovie(Movie movie) {
		this.movieDao.addMovie(movie);
	}

	
	@Transactional
	public void updateMovie(Movie movie) {
		this.movieDao.updateMovie(movie);

	}

	
	@Transactional
	public void removeMovie(int id) {
		this.movieDao.removeMovie(id);

	}
	
	@Transactional
	public void removeAllMovies(){
		this.movieDao.removeAllMovies();
	}

	
	@Transactional
	public Movie getMovieById(int id) {

		return this.movieDao.getMovieById(id);
	}
	
	@Transactional
	public List<Movie> getMovieByTitle(String title){
		return this.movieDao.getMovieByTitle(title);
	}
	
	@Transactional
	public List<Movie> listMovie() {

		return this.movieDao.listMovie();
	}
	
	@Transactional
	public Integer getCount(){
		
		return this.movieDao.getCount();
	}
}
