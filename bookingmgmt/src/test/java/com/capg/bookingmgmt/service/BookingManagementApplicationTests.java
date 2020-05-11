package com.capg.bookingmgmt.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capg.bookingmgmt.exceptions.BookingNotFoundException;


  @DataJpaTest
  @ExtendWith(SpringExtension.class)
  @Import(BookingServiceImpl.class)
 
class BookingManagementApplicationTests {

	@Autowired
	private IBookingService bookingService;
	
	@Test
	public void fetchBooking_1() {
		Executable executable = () -> bookingService.fetchBookingById(45);
		Assertions.assertThrows(BookingNotFoundException.class, executable);
	}
	
	@Test
	public void cancelBooking_1() {
		Executable executable = () -> bookingService.cancelBooking(4);
		Assertions.assertThrows(BookingNotFoundException.class, executable);
	}
	
	@Test
	public void cancelBooking_2() {
		String result = bookingService.cancelBooking(3);
		String expected="Cancelled";
		Assertions.assertEquals(result, expected);
	}
}
