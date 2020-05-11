package com.capg.bookingmgmt.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.exceptions.TicketNotFoundException;
import com.capg.bookingmgmt.util.TicketStatus;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(TicketServiceImpl.class)
class TicketTests {

	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void getTicket_1() {
		Executable executable = () -> ticketService.getTicket(2);
		Assertions.assertThrows(TicketNotFoundException.class, executable);
	}
	@Test
	public void getTicket_2() {
		Ticket ticket = new Ticket();
		//ticket.setTicketId(2);
		ticket.setNoOfSeats(3);
		ticket.setScreenName("Gold");
		ticket.setTicketStatus(TicketStatus.BOOKED);
		List<String> seats= new ArrayList<String>();
		seats.add("L1");
		seats.add("L2");
		seats.add("L3");
		ticket.setSeatName(seats);
		ticket=entityManager.merge(ticket);
		Ticket result = ticketService.getTicket(1);
		Assertions.assertEquals(ticket, result);;
	}
}
