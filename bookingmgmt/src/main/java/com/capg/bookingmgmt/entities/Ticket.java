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
	private List<Integer> seatIds;
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
	public List<Integer> getSeatIds() {
		return seatIds;
	}
	public void setSeatIds(List<Integer> seatIds) {
		this.seatIds = seatIds;
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
	@Override
	public int hashCode() {
		String ticketIdString = ""+ticketId;
		return ticketIdString.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null || obj instanceof Ticket)
			return false;
		Ticket other = (Ticket) obj;
		return this.ticketId == other.ticketId;
	}
	
	
}
