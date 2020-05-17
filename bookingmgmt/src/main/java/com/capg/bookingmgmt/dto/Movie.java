package com.capg.bookingmgmt.dto;

import java.time.LocalDate;
import java.util.List;

public class Movie {
	private int movieId;
	private String movieName;
	private String movieDirector;
	private int movieLength;
	private LocalDate movieReleaseDate;
	private List<String> languages;
	private String movieGenre;
	
	public Movie(int movieId, String movieName, String movieDirector, int movieLength, LocalDate movieReleaseDate,
			List<String> languages, String movieGenre) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieDirector = movieDirector;
		this.movieLength = movieLength;
		this.movieReleaseDate = movieReleaseDate;
		this.languages = languages;
		this.movieGenre = movieGenre;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	public int getMovieLength() {
		return movieLength;
	}
	public void setMovieLength(int movieLength) {
		this.movieLength = movieLength;
	}
	public LocalDate getMovieReleaseDate() {
		return movieReleaseDate;
	}
	public void setMovieReleaseDate(LocalDate movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	public String getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	
}
