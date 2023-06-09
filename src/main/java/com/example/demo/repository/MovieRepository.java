package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer>{
	Optional<MovieEntity> findByMovieId(String moveId);
	@Transactional
	void deleteByMovieId(String moveId);
	
}
