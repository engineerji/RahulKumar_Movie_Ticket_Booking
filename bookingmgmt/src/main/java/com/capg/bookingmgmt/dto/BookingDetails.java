package com.capg.bookingmgmt.dto;

import java.time.LocalDate;
import java.util.List;

public class BookingDetails {

	private int bookingId;
	private int movieId;
	private int showId;
	private LocalDate bookingDate;
	private long transactionId;
	private double totalCost;
	private List<Integer> seatsId;
	
	public BookingDetails(int bookingId, int movieId, int showId, LocalDate bookingDate, long transactionId,
			double totalCost, List<Integer> seatsId) {
		super();
		this.bookingId = bookingId;
		this.movieId = movieId;
		this.showId = showId;
		this.bookingDate = bookingDate;
		this.transactionId = transactionId;
		this.totalCost = totalCost;
		this.seatsId = seatsId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public List<Integer> getSeatsId() {
		return seatsId;
	}
	public void setSeatsId(List<Integer> seatsId) {
		this.seatsId = seatsId;
	}
	
}
