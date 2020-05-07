package com.capg.bookingmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.service.ITicketService;



@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private ITicketService ticketService;
	
	@GetMapping("/get/{id}")
	ResponseEntity<Ticket> fetchTicketById(@PathVariable("id") int ticketId){
		Ticket ticket = ticketService.getTicket(ticketId);
		ResponseEntity<Ticket> response = new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
		return response;
	}
	
}
