package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MovieRequest;
import com.example.demo.dto.MovieResponse;
import com.example.demo.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@PostMapping
	public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest movieRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(movieRequest));

	}
	
	@GetMapping
	public ResponseEntity<?> getAllMovies(){
		return ResponseEntity.ok(movieService.getAllMovies());
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<?> getAllMovies(@PathVariable("movieId") String movieId){
		return ResponseEntity.ok(movieService.findByMovieId(movieId));
	}
	
	@PutMapping("/{movieId}")
	public ResponseEntity<?> updateByMovieId(@PathVariable("movieId") String movieId, @RequestBody MovieRequest movieRequest){
		return ResponseEntity.status(HttpStatus.OK).body(movieService.updateByMovieId(movieId,movieRequest));
		
	}
	
	@DeleteMapping("/{movieId}")
	public void deleteByMovieId(@PathVariable("movieId") String movieId){
		movieService.deleteByMovieId(movieId);
	}

}
