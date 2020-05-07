package com.capg.bookingmgmt.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.capg.bookingmgmt.util.TicketStatus;

@Entity
public class Ticket {
	@Id
	@GeneratedValue
	private int ticketId;
	private int noOfSeats;
	@ElementCollection
	private List<String> seatName;
	private TicketStatus ticketStatus;
	private String screenName;
	
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
	public TicketStatus isTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	
}
