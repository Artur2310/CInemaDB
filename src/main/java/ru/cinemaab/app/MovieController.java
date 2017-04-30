package ru.cinemaab.app;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ru.cinemaab.app.model.Movie;
import ru.cinemaab.app.service.MovieService;

@RequestMapping(value = "/movie")
@Controller
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	@Qualifier("movieService")
	MovieService movieService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ModelAndView getMoviePage(ModelAndView model) {
		List<Movie> movies = movieService.listMovie();
		model.addObject("movies", movies);
		model.setViewName("movie");
		return model;
	}

	@RequestMapping(value = "add-movie", method = RequestMethod.GET)
	public ModelAndView addMoviePage(ModelAndView model) {

		Movie movie = new Movie();
		model.addObject("movie", movie);
		model.setViewName("add_movie");

		return model;
	}

	@RequestMapping(value = "add-movie", method = RequestMethod.POST)
	public String addMovieDatabase(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {

			return "redirect:/movie/add_movie";
		}
		movie.setPictureId(0);
		movieService.addMovie(movie);

		return "redirect:/movie/downloadImage/" + movie.getId();
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
	public String updateMovieDatabase(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult,
			HttpServletRequest request, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("movie", movie);
			return "update-movie";
		}
		movieService.updateMovie(movie);

		return "redirect:/movie";
	}

	@RequestMapping(value = "downloadImage/{movie_id}", method = RequestMethod.GET)
	public ModelAndView uploadFile(@PathVariable("movie_id") Integer id) {
		ModelAndView model = new ModelAndView();
		if(movieService.getMovieById(id)!=null){
			model.addObject("movie_id", id);
			model.setViewName("download-image-movie");
			return model;
		}
		
		model.setViewName("not-found");
		return model;
	}

	@RequestMapping(value = "downloadImage/{movie_id}", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("movie_id") Integer id) {

		String name = null;
		ModelAndView model = new ModelAndView();

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				name = file.getOriginalFilename();
				name = id.toString() + name.substring(name.lastIndexOf("."));

				File dir = new File("tmpFiles");

				if (!dir.exists()) {
					dir.mkdirs();
				}

				File uploadedFile = new File(name);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.flush();
				stream.close();

				Movie movie = movieService.getMovieById(id);
				movie.setPictureId(id);
				movieService.updateMovie(movie);

				logger.info("You successfully uploaded file=" + uploadedFile.getAbsolutePath());

				return "redirect:/movie";

			} catch (Exception e) {

				logger.info("You failed to upload " + name + " => " + e.getMessage());
				return "redirect:/movie/downloadImage/" + id;
			}
		} else {

			logger.info("You failed to upload " + name + " because the file was empty.");
			return "redirect:/movie/downloadImage/" + id;
		}
	}

}
