package ru.cinemaab.app;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ru.cinemaab.app.model.Movie;
import ru.cinemaab.app.service.MovieService;

@RequestMapping(value = "/movie")
@Controller
public class MovieController {
	
	@Autowired
	@Qualifier("movieService")
	MovieService movieService;

	@RequestMapping(value = "", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody ModelAndView getMoviePage(ModelAndView model) {
		List<Movie> movies = movieService.listMovie();
		model.addObject("movies", movies);
		model.setViewName("movie");  
		return model;
	}

	@RequestMapping(value = "add-movie", method=RequestMethod.GET)
	public ModelAndView addMoviePage(ModelAndView model) {

		
		
		Movie movie = new Movie();
		model.addObject("movie", movie);
		model.setViewName("add_movie");
		
	

		return model;
	}
	
	@RequestMapping(value = "add-movie", method = RequestMethod.POST)
	public String addMovieDatabase(@Valid @ModelAttribute("movie") Movie movie,BindingResult bindingResult, HttpServletRequest request){
		
		if(bindingResult.hasErrors()){
			return "add_movie";
		}
		movieService.addMovie(movie);
		return "redirect:/movie";
	}
	
	@RequestMapping(value = "/delete-movie/{id}", method = RequestMethod.POST, produces = "application/json")
	public String deleteMovie(@PathVariable("id") Integer id, HttpServletRequest request) {
		
		movieService.removeMovie(id);
		return "redirect:/movie";
	}
	
	@RequestMapping(value = "update-movie/{id}", method = RequestMethod.GET, produces = "application/json")
	public ModelAndView updateMoviePage(@PathVariable("id") Integer id, ModelAndView model) {
		
		Movie movie = movieService.getMovieById(id);
		model.addObject("movie", movie);
		model.setViewName("update-movie");
		return model;
	}
	
	@RequestMapping(value = "update-movie", method = RequestMethod.POST)
	public String updateMovieDatabase(@Valid @ModelAttribute("movie") Movie movie,BindingResult bindingResult,  HttpServletRequest request,Model model) {
		
		if(bindingResult.hasErrors()){
			model.addAttribute("movie", movie);
			return "update-movie";
		}
		movieService.updateMovie(movie);

		return "redirect:/movie";
	}

	

}
