package com.capg.bookingmgmt.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Booking {
	@Id
	@GeneratedValue
	private int bookingId;
	private int movieId;
	private int showId;
	private LocalDate bookingDate;
	private int transactionId;
	private double totalCost;
	@ElementCollection
	private List<Integer> seatIds;
	@OneToOne
	private Ticket ticket;
	
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
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	public List<Integer> getSeatIds() {
		return seatIds;
	}
	public void setSeatIds(List<Integer> seatIds) {
		this.seatIds = seatIds;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	@Override
	public int hashCode() {
		String bookingIdString = ""+bookingId;
		return bookingIdString.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null || obj instanceof Booking)
			return false;
		Booking other = (Booking) obj;
		return this.bookingId == other.bookingId;
	}
	
	
	
	
}
