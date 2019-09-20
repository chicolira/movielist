package com.chicolira.movielist.service;

import com.chicolira.movielist.model.MovieList;

public interface IMovieListService {
	MovieList findAll(Integer pageNumber, String language);
	MovieList findByName(String query, Integer pageNumber, String language);
}
