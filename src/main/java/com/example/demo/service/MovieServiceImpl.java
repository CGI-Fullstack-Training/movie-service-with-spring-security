package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MovieRequest;
import com.example.demo.dto.MovieResponse;
import com.example.demo.entity.MovieEntity;
import com.example.demo.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public MovieResponse createMovie(MovieRequest movieRequest) {

		log.info("movieRequest:: {}  ", movieRequest);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		MovieEntity movieEntity = modelMapper.map(movieRequest, MovieEntity.class);
		movieEntity.setMovieId(UUID.randomUUID().toString());
		log.info("movie entity:: {} ", movieEntity);
		movieRepository.save(movieEntity);
		MovieResponse responseModel = modelMapper.map(movieEntity, MovieResponse.class);
		log.info("Movie saved successufully details are :: {} ", responseModel);
		return responseModel;
	}

	@Override
	public List<MovieResponse> getAllMovies() {
		List<MovieResponse> respList = new ArrayList<>();
//		List<MovieEntity> moviesList = movieRepository.findAll();
//		log.info("movies from db:: {} ",moviesList);
//		for (MovieEntity entity : moviesList) {
//			respList.add(modelMapper.map(entity, MovieResponse.class));
//		}

		movieRepository.findAll().stream()
				.forEach(entity -> respList.add(modelMapper.map(entity, MovieResponse.class)));
		return respList;
	}

	@Override
	public MovieResponse findByMovieId(String movieId) {
		Optional<MovieEntity> movie = movieRepository.findByMovieId(movieId);
		if (movie.isPresent()) {
			return modelMapper.map(movie.get(), MovieResponse.class);
		}
		return null;
	}

	@Override
	public void deleteByMovieId(String movieId) {
		Optional<MovieEntity> movie = movieRepository.findByMovieId(movieId);
		if (movie.isPresent()) {
			movieRepository.deleteByMovieId(movieId);
		}
	}

	@Override
	public MovieResponse updateByMovieId(String movieId, MovieRequest movieRequest) {
		Optional<MovieEntity> movie = movieRepository.findByMovieId(movieId);
		if (movie.isPresent()) {
			MovieEntity entity = movie.get();
			entity.setMovieName(movieRequest.getMovieName());
			entity.setGenre(movieRequest.getGenre());

			return modelMapper.map(movieRepository.save(entity), MovieResponse.class);
		}
		return null;
	}

}
