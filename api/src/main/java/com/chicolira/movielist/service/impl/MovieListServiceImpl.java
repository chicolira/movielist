package com.chicolira.movielist.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.chicolira.movielist.model.GenreList;
import com.chicolira.movielist.model.MovieList;
import com.chicolira.movielist.service.IGenreListService;
import com.chicolira.movielist.service.IMovieListService;

@Service
public class MovieListServiceImpl implements IMovieListService {

	@Value("${tmdb.key}")
	private String TMDB_KEY;

	@Value("${tmdb.url}")
	private String TMDB_URL;

	private RestTemplate restTemplate;

	private IGenreListService genreListService;

	@Autowired
	public MovieListServiceImpl(RestTemplate restTemplate, IGenreListService genreListService) {
		this.restTemplate = restTemplate;
		this.genreListService = genreListService;
	}

	@Override
	public MovieList findAll(Integer pageNumber, String language) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(TMDB_URL + "/movie/upcoming")
				.queryParam("api_key", TMDB_KEY).queryParam("page", pageNumber).queryParam("language", language);

		MovieList movieList = restTemplate.getForObject(uriBuilder.toUriString(), MovieList.class);
		setMoviesGenres(movieList, language);
		
		return movieList;
	}

	@Override
	public MovieList findByName(String query, Integer pageNumber, String language) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(TMDB_URL + "/search/movie")
				.queryParam("api_key", TMDB_KEY).queryParam("page", pageNumber).queryParam("language", language)
				.queryParam("query", query);
		
		MovieList movieList = restTemplate.getForObject(uriBuilder.toUriString(), MovieList.class);
		setMoviesGenres(movieList, language);
		
		return movieList;
	}

	/*
	 * Fetches the genres movies
	 * */
	private void setMoviesGenres(MovieList movieList, String language) {

		long startTime = System.nanoTime();
		
		final GenreList genres = genreListService.findAll(language);
		
		long endTime = System.nanoTime();
		
		System.out.println((endTime - startTime) / 10000);

		movieList.getMovies().forEach(m -> {

			m.setGenres(genres.getGenres()
					.stream()
					.filter(genre -> m.getListGenreIds().contains(genre.getId()))
					.collect(Collectors.toList()));
		});

	}

}
