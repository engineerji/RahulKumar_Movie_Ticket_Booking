package com.capg.bookingmgmt.dto;

import java.util.List;


public class TicketDto {
	private int ticketId;
	private int noOfSeats;
	private List<String> seatName;
	private String screenName;
	
	
	
	public TicketDto(int ticketId, int noOfSeats, List<String> seatName, String screenName) {
		super();
		this.ticketId = ticketId;
		this.noOfSeats = noOfSeats;
		this.seatName = seatName;
		this.screenName = screenName;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public List<String> getSeatName() {
		return seatName;
	}
	public void setSeatName(List<String> seatName) {
		this.seatName = seatName;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
}
