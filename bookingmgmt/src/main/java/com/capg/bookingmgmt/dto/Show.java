package com.capg.bookingmgmt.dto;

import java.time.LocalDate;
import java.util.List;

public class Show {
	private int showId;
	private LocalDate showStartTime;
	private LocalDate showEndTime;
	private List<Integer> seatIds;
	private String showName;
	private String movieName;
	public Show(int showId, LocalDate showStartTime, LocalDate showEndTime, List<Integer> seatIds, String showName,
			String movieName) {
		super();
		this.showId = showId;
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.seatIds = seatIds;
		this.showName = showName;
		this.movieName = movieName;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public LocalDate getShowStartTime() {
		return showStartTime;
	}
	public void setShowStartTime(LocalDate showStartTime) {
		this.showStartTime = showStartTime;
	}
	public LocalDate getShowEndTime() {
		return showEndTime;
	}
	public void setShowEndTime(LocalDate showEndTime) {
		this.showEndTime = showEndTime;
	}
	public List<Integer> getSeatIds() {
		return seatIds;
	}
	public void setSeatIds(List<Integer> seatIds) {
		this.seatIds = seatIds;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
}
