package com.chicolira.movielist.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chicolira.movielist.model.GenreList;
import com.chicolira.movielist.service.IGenreListService;
import com.chicolira.movielist.util.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = Constants.API_PREFIX + "/genre")
@Api("Genre Resource REST Endpoint")
public class GenreListResource {
	private IGenreListService genreListService;

	@Autowired
	public GenreListResource(IGenreListService genreListService) {
		this.genreListService = genreListService;
	}

	@ApiOperation(value = "Fetches all genres from TMDB")
	@GetMapping(value = "/") 
	public GenreList findAll(@RequestParam String language) {
		return genreListService.findAll(language);
	}
}
