package com.chicolira.movielist.service;

import com.chicolira.movielist.model.GenreList;

public interface IGenreListService {
	GenreList findAll(String language);
}
