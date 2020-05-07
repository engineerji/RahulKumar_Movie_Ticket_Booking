package com.capg.bookingmgmt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.bookingmgmt.dao.IBookingDao;
import com.capg.bookingmgmt.dao.ITransactionDao;
import com.capg.bookingmgmt.dto.BookingRequestDto;
import com.capg.bookingmgmt.entities.Booking;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.entities.BookingTransaction;
import com.capg.bookingmgmt.exceptions.BookingNotFoundException;
import com.capg.bookingmgmt.exceptions.TicketNotFoundException;
import com.capg.bookingmgmt.util.TicketStatus;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService{

	@Autowired
	private IBookingDao bookingDao;
	
	@Autowired
	private ITransactionDao transactionDao;
	
	@Override
	public Booking addBooking(Booking booking) {
		booking = bookingDao.save(booking);
		return booking;
	}

	@Override
	public Booking fetchBooking(int bookingId) {
		Optional<Booking> option= bookingDao.findById(bookingId);
		if(!option.isPresent()) {
			throw new BookingNotFoundException("Bookiing id is wrong. No booking exist with this booking id :"+bookingId);
		}
		Booking booking = option.get();
		return booking;
	}

	@Override
	public List<Booking> fetchAllBookings() {
		List<Booking> bookingList = bookingDao.findAll();
		return bookingList;
	}

	@Override
	public String cancelBooking(int bookingId) {
		Booking booking = fetchBooking(bookingId);
		Ticket ticket=booking.getTicket();
		if(ticket==null) throw new TicketNotFoundException("No ticket is booked yet");
		ticket.setTicketStatus(TicketStatus.CANCELLED);
		return "Ticket Cancelled";
	}
	
	
	@Override
	public BookingTransaction makePayment(String paymentMethod, double cost) {
		BookingTransaction transaction = new BookingTransaction();
		transaction.setTransactionAmount(cost);
		transaction.setTransactionMethod(paymentMethod);
		transaction = transactionDao.save(transaction);
		return transaction;
	}

	@Override
	public Ticket getTicket(int bookingId) {
		Booking booking = fetchBooking(bookingId);
		Ticket ticket = booking.getTicket();
		if(ticket==null) {
			throw new TicketNotFoundException("No Ticket has booked yet.");
		}
		return ticket;
	}

	@Override
	public Booking createBooking(BookingRequestDto bookingDto, double cost, int transactionId, Ticket ticket) {
		Booking booking = new Booking();
		booking.setMovieId(bookingDto.getMovieId());
		booking.setShowId(bookingDto.getShowId());
		booking.setBookingDate(LocalDate.now());
		booking.setSeatsId(bookingDto.getChoosenSeats());
		booking.setTicket(ticket);
		booking.setTotalCost(cost);
		booking.setTransactionId(transactionId);
		booking = addBooking(booking);
		return booking;
	}
	
	
}
