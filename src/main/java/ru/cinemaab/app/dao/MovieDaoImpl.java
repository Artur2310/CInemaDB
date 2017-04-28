package ru.cinemaab.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ru.cinemaab.app.model.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {

	private static final Logger logger = LoggerFactory.getLogger(MovieDaoImpl.class);

	@Autowired
	@Qualifier("hibernate4AnnotatedSessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addMovie(Movie movie) {

		Session session = this.sessionFactory.getCurrentSession();
		session.save(movie);
		logger.info("Movie succesfully saved. Movie details: " + movie);
		

	}

	public void updateMovie(Movie movie) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(movie);
		logger.info("Movie succesfully update. Movie details :" + movie);

	}

	public void removeMovie(int id) {

		Session session = this.sessionFactory.getCurrentSession();

		List<Movie> movies = (List<Movie>) session.createCriteria(Movie.class).add(Restrictions.eq("id", id)).list();

		if (!movies.isEmpty()) {

			Movie movie = movies.get(0);
			session.delete(movie);
			logger.info("Movie succesfully removed. Movie details :" + movie);

		} else {
			logger.info("Movie not found!");
		}

	}

	public void removeAllMovies() {

		Session session = this.sessionFactory.getCurrentSession();
		session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
		System.out.println(session.createSQLQuery("TRUNCATE TABLE cinemadb.movie").executeUpdate());
		System.out.println(session.createSQLQuery("TRUNCATE TABLE cinemadb.person_movie").executeUpdate());

		session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
	}

	public Movie getMovieById(int id) {
		Session session = this.sessionFactory.getCurrentSession();

		List<Movie> movies = (List<Movie>) session.createCriteria(Movie.class).add(Restrictions.eq("id", id)).list();

		if (!movies.isEmpty()) {
			Movie movie = movies.get(0);

			logger.info("Movie succesfully loaded. Movie details " + movie);
			return movie;
		} else {
			logger.info("Movie not found");
			return null;
		}
	}
	
	public List<Movie> getMovieByTitle(String title){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Movie where name like :title");
		query.setParameter("title","%"+title+"%");
		return (List<Movie>) query.list();
	}


	public List<Movie> listMovie() {
		Session session = this.sessionFactory.getCurrentSession();

		List<Movie> movieList = null;

		movieList = session.createCriteria(Movie.class).list();

		return movieList;
	}
	
	public Integer getCount(){
		
		Session session = this.sessionFactory.getCurrentSession();

		Object result = session.createCriteria(Movie.class)
                .setProjection(Projections.rowCount()).uniqueResult();
 
         return Integer.parseInt(result.toString());
	}
}
