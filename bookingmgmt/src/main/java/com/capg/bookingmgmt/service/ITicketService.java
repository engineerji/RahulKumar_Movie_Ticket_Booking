package com.capg.bookingmgmt.service;


import java.util.List;

import com.capg.bookingmgmt.entities.Ticket;

public interface ITicketService {

	Ticket addTicket(Ticket ticket);
	Ticket getTicket(int ticketId);
	void cancelTicket(int ticketId);
	Ticket createTicket(int noOfSeats,List<String> seatNames,String screenName);
}
