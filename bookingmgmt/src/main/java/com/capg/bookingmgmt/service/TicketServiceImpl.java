package com.capg.bookingmgmt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.bookingmgmt.dao.ITicketDao;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.util.TicketStatus;

@Service
@Transactional
public class TicketServiceImpl implements ITicketService{

	@Autowired
	ITicketDao ticketDao;
	
	@Override
	public Ticket addTicket(Ticket ticket) {
		ticket = ticketDao.save(ticket);
		return ticket;
	}

	@Override
	public Ticket getTicket(int ticketId) {
		Ticket ticket = ticketDao.findById(ticketId).get(); 
		return ticket;
	}

	@Override
	public void cancelTicket(int ticketId) {
		Ticket ticket = ticketDao.findById(ticketId).get();
		ticket.setTicketStatus(TicketStatus.CANCELLED);
		ticketDao.save(ticket);
	}

	@Override
	public Ticket createTicket(int noOfSeats,List<String> seatNames,String screenName) {
		Ticket ticket= new Ticket();
		ticket.setNoOfSeats(noOfSeats);
		ticket.setSeatName(seatNames);
		ticket.setScreenName(screenName);
		ticket.setTicketStatus(TicketStatus.BOOKED);
		ticket = addTicket(ticket);
		return ticket;
	}

	
	
	
	
}
