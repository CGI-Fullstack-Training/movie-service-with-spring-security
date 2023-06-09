package com.example.demo.dto;

import com.example.demo.entity.GENRE_TYPE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
	private String movieName;

	private GENRE_TYPE genre;
}
