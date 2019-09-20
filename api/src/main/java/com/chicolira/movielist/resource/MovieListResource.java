package com.chicolira.movielist.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chicolira.movielist.model.MovieList;
import com.chicolira.movielist.service.IMovieListService;
import com.chicolira.movielist.util.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = Constants.API_PREFIX + "/movie")
@Api("Movie Resource REST Endpoint")
public class MovieListResource {

	private IMovieListService movieListService;

	@Autowired
	public MovieListResource(IMovieListService movieListService) {
		this.movieListService = movieListService;
	}

	@ApiOperation(value = "Fetches all movies from TMDB")
	@GetMapping(value = "/")
	public MovieList findAll(@RequestParam Integer pageNumber, @RequestParam String language) {
		return movieListService.findAll(pageNumber, language);
	}

	@ApiOperation(value = "Fetches movies by name from TMDB")
	@GetMapping(value = "/search")
	public MovieList findByName(@RequestParam String query, @RequestParam Integer pageNumber, String language) {
		return movieListService.findByName(query, pageNumber, language);
	}
}
