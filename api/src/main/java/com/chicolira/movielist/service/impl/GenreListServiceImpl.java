package com.chicolira.movielist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.chicolira.movielist.model.GenreList;
import com.chicolira.movielist.service.IGenreListService;

@Service
public class GenreListServiceImpl implements IGenreListService {
	
	@Value("${tmdb.key}")
	private String TMDB_KEY;
	
	@Value("${tmdb.url}")
	private String TMDB_URL;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public GenreListServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	@Cacheable("genreList")
	public GenreList findAll(String language) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
			    .fromUriString(TMDB_URL + "/genre/movie/list")
			    .queryParam("api_key", TMDB_KEY)
				.queryParam("language", language);
		
		return restTemplate.getForObject(uriBuilder.toUriString(), GenreList.class);
	}
}
