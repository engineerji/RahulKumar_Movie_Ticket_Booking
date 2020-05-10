package com.capg.bookingmgmt.service;

import java.util.List;

import com.capg.bookingmgmt.dto.BookingRequestDto;
import com.capg.bookingmgmt.entities.Booking;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.entities.BookingTransaction;

public interface IBookingService {
	
	Booking addBooking(Booking booking);
	Booking fetchBooking(int bookingId);
	List<Booking> fetchAllBookings();
	String cancelBooking(int bookingId);
	Ticket getTicket(int bookingId);
	BookingTransaction makePayment(String paymentMethod, double cost);
	Booking createBooking(BookingRequestDto bookingDto,double cost,int transactionId,Ticket ticket);
	String deleteBooking(int bookingId);
}
