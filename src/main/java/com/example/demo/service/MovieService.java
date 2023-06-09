package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.MovieRequest;
import com.example.demo.dto.MovieResponse;

public interface MovieService {
	public MovieResponse createMovie(MovieRequest movieRequest);

	public List<MovieResponse> getAllMovies();

	public MovieResponse findByMovieId(String movieId);

	public void deleteByMovieId(String movieId);

	public MovieResponse updateByMovieId(String movieId, MovieRequest movieRequest);
}
