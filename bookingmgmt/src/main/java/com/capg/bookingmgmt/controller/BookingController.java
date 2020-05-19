package com.capg.bookingmgmt.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.bookingmgmt.dto.BookingDetails;
import com.capg.bookingmgmt.dto.CreateBookingRequest;
import com.capg.bookingmgmt.dto.Movie;
import com.capg.bookingmgmt.dto.Screen;
import com.capg.bookingmgmt.dto.Seat;
import com.capg.bookingmgmt.dto.Show;
import com.capg.bookingmgmt.dto.Theater;
import com.capg.bookingmgmt.dto.TicketDto;
import com.capg.bookingmgmt.entities.Booking;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.exceptions.BookingNotFoundException;
import com.capg.bookingmgmt.exceptions.TicketNotFoundException;
import com.capg.bookingmgmt.service.IBookingService;
import com.capg.bookingmgmt.util.SeatStatus;


@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	private static final Logger Log = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private IBookingService bookingService;
	
	/**
	 * adding a booking and return bookingDetails
	 * @Param bookintDto
	 * @return
	 */
	@PostMapping("/add")
	ResponseEntity<BookingDetails> bookingprocess(@RequestBody CreateBookingRequest bookingDto){
		List<Seat> seats = choosenSeats(bookingDto.getChoosenSeats());
		double cost = getCost(seats);
		
		Booking booking=convertBookingDto(bookingDto,cost);
		booking = bookingService.createBooking(booking,bookingDto.getPaymentMethod(),bookingDto.getScreenName());
		
		BookingDetails bookingDetails = convertBooking(booking);
		ResponseEntity<BookingDetails> response = new ResponseEntity<BookingDetails>(bookingDetails,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching all bookings
	 * @return
	 */
	@GetMapping
	ResponseEntity<List<Booking>> fetchAllBookings(){
		List<Booking> bookingList = bookingService.fetchAllBookings();
		ResponseEntity<List<Booking>> response = new ResponseEntity<List<Booking>>(bookingList,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching ticket with bookingId
	 * @param bookingId
	 * @return
	 */
	@GetMapping("/getTicket/{id}")
	ResponseEntity<TicketDto> fetchTicket(@PathVariable("id") int bookingId){
		Ticket ticket = bookingService.showTicket(bookingId);
		TicketDto ticketDto=convertTicketDto(ticket);
		ResponseEntity<TicketDto> response = new ResponseEntity<TicketDto>(ticketDto,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching booking 
	 * @param bookingId
	 * @return
	 */
	@GetMapping("/get/{id}")
	ResponseEntity<Booking> fetchBooking(@PathVariable("id") int bookingId){
		Booking booking = bookingService.fetchBookingById(bookingId);
		ResponseEntity<Booking> response = new ResponseEntity<Booking>(booking,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Cancel booking
	 * @param bookingId
	 * @return
	 */
	@DeleteMapping("/cancel/{id}")
	ResponseEntity<String> cancelBooking(@PathVariable("id") int bookingId){
		bookingService.cancelBooking(bookingId);
		ResponseEntity<String> response = new ResponseEntity<String>("",HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching theaters on city basis
	 * @param city
	 * @return
	 */
	@GetMapping("/getTheaters/{city}")
	ResponseEntity<List<Theater>> findTheaters(@PathVariable("city") String city){
		List<Theater> selectedTheaters = new ArrayList<Theater>();
		List<Theater> theaterList = getTheaters();
		for (Theater theater : theaterList) {
			if(theater.getTheaterCity().equals(city)) {
				selectedTheaters.add(theater);
			}
		}
		ResponseEntity<List<Theater>> response = new ResponseEntity<List<Theater>>(selectedTheaters,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching all screen on theater basis
	 * @param theaterId
	 * @return
	 */
	@GetMapping("/getScreens/{id}")
	ResponseEntity<List<Screen>> findScreens(@PathVariable("id") int theaterId){
		List<Screen> selectedScreens = null;
		List<Theater> theaterList = getTheaters();
		for (Theater theater : theaterList) {
			if(theater.getTheaterId()==theaterId) {
				selectedScreens= theater.getScreenList();
				break;
			}
		}
		ResponseEntity<List<Screen>> response = new ResponseEntity<List<Screen>>(selectedScreens,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching all movies
	 * @return
	 */
	@GetMapping("/getMovies")
	ResponseEntity<List<Movie>> findMovies(){
		List<Movie> movieList = getMovies();
		ResponseEntity<List<Movie>> response = new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching all shows on movie basis
	 * @param movieId
	 * @return
	 */
	@GetMapping("/getShows/{movieId}")
	ResponseEntity<List<Show>> findShows(@PathVariable("movieId") int movieId){
		List<Show> selectedShows = new ArrayList<Show>();
		String movieName="";
		List<Movie> selectedMovies = getMovies();
		for(Movie movie:selectedMovies){
			if(movie.getMovieId()==movieId){
				movieName=movie.getMovieName();
				break;
			}
		}
		List<Show> showList = getShows();
		for (Show show : showList) {
			if(show.getMovieName().equals(movieName)) {
				selectedShows.add(show);
			}
		}
		ResponseEntity<List<Show>> response = new ResponseEntity<List<Show>>(selectedShows,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching choosenSeats
	 * @param seatIds
	 * @return
	 */
	public List<Seat> choosenSeats(List<Integer> seatIds) {
		List<Seat> seats = new ArrayList<Seat>();
		for(Integer id:seatIds) {
			Seat seat = new Seat(id, SeatStatus.BOOKED, 1542);
			seats.add(seat);
		}
		return seats;
	}
	
	/**
	 * converting bookingDto to booking
	 * @param bookingDto
	 * @return
	 */
	public Booking convertBookingDto(CreateBookingRequest bookingDto,double cost) {
		Booking booking = new Booking();
		booking.setMovieId(bookingDto.getMovieId());
		booking.setShowId(bookingDto.getShowId());
		booking.setSeatIds(bookingDto.getChoosenSeats());
		booking.setTotalCost(cost);
		return booking;
	}
	
	/**
	 * converting bookingDetails
	 * @param booking
	 * @return
	 */
	public BookingDetails convertBooking(Booking booking) {
		BookingDetails bookingDetails = new BookingDetails(booking.getBookingId(), booking.getMovieId(), 
				booking.getShowId(), booking.getBookingDate(),
				booking.getTransactionId(), booking.getTotalCost(), booking.getSeatIds());
		return bookingDetails;
	}
	
	/**
	 * Calculating TotalCost
	 * @param seatList
	 * @return
	 */
	public double getCost(List<Seat> seatList) {
		double price=0;
		for(Seat seat:seatList) {
			price=price+seat.getSeatPrice();
		}
		return price;
	}
	
	/**
	 * Converting ticket to ticketDto
	 * @param ticket
	 * @return
	 */
	public TicketDto convertTicketDto(Ticket ticket) {
		TicketDto ticketDto = new TicketDto(ticket.getTicketId(),ticket.getNoOfSeats(),ticket.getSeatIds(),ticket.getScreenName());
		return ticketDto;
	}
	
	/**
	 * Handling BookingNotFoundException
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException exception){
		Log.error("Booking Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	
	/**
	 * Handling TicketNotFoundException
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<String> handleTicketNotFoundException(TicketNotFoundException exception){
		Log.error("Ticket Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("exception caught", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
    
    
    public List<Theater> getTheaters(){
    	List<Integer> showList = new ArrayList<Integer>();
    	showList.add(87);
    	Screen screen1 = new Screen(25,200,"HD",showList,1,6);
        Screen screen2 = new Screen(26,201,"3D",showList,1,6);
        Screen screen3 = new Screen(27,202,"IMax",showList,1,6);
        Screen screen4 = new Screen(28,203,"SD",showList,1,6);
        List<Screen> screenList = new ArrayList<Screen>();
        screenList.add(screen1);
        screenList.add(screen2);
        screenList.add(screen3);
        screenList.add(screen4);
        
        List<Integer> movieIds = new ArrayList<Integer>();
    	movieIds.add(456);
    	movieIds.add(766);
    	movieIds.add(656);
    	movieIds.add(246);
        Theater theater1= new Theater(200,"pvr","Delhi",movieIds,screenList,"dasd","sdsrew");
        Theater theater2= new Theater(201,"INox","Mumbai",movieIds,screenList,"dasd","sdsrew");
        Theater theater3= new Theater(202,"Delight","Banglore",movieIds,screenList,"dasd","sdsrew");
        Theater theater4= new Theater(203,"pvr gold","Chennai",movieIds,screenList,"dasd","sdsrew");
        
        List<Theater> theaterList = new ArrayList<Theater>();
        theaterList.add(theater1);
        theaterList.add(theater2);
        theaterList.add(theater3);
        theaterList.add(theater4);
        return theaterList;
    }
    
    public List<Movie> getMovies(){
    	List<Movie> movieList = new ArrayList<Movie>();
    	List<String> languages = new ArrayList<String>();
    	languages.add("English");
    	languages.add("Hindi");
    	Movie movie1 = new Movie(456,"F&F","abc",127,LocalDate.now(),languages,"Action");
        Movie movie2 = new Movie(766,"Dhamaal","dbc",126,LocalDate.now(),languages,"Comedy");
        Movie movie3 = new Movie(656,"Golmaal","abc",128,LocalDate.now(),languages,"Comedy");
        Movie movie4 = new Movie(246,"Dhoom","abc",125,LocalDate.now(),languages,"Action");
    	
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        movieList.add(movie4);
    	return movieList;
    }
    
    public List<Show> getShows(){
    	List<Show> showList = new ArrayList<Show>();
    	List<Integer> seatIds = new ArrayList<Integer>();
    	seatIds.add(42);
    	seatIds.add(58);
    	seatIds.add(67);
    	List<Integer> seatIds2 = new ArrayList<Integer>();
    	seatIds2.add(4);
    	seatIds2.add(5);
    	seatIds2.add(6);
    	List<Integer> seatIds3 = new ArrayList<Integer>();
    	seatIds3.add(22);
    	seatIds3.add(28);
    	seatIds3.add(27);
    	Show show1=new Show(87,LocalDate.now(),LocalDate.now(),seatIds,"Morning","F&F");
    	Show show2=new Show(88,LocalDate.now(),LocalDate.now(),seatIds2,"Day","Dhamaal");
    	Show show3=new Show(89,LocalDate.now(),LocalDate.now(),seatIds3,"Night","Golmaal");
    	Show show4=new Show(90,LocalDate.now(),LocalDate.now(),seatIds,"Evening","Dhoom");
    	
    	showList.add(show1);
    	showList.add(show2);
    	showList.add(show3);
    	showList.add(show4);
    	return showList;
    }
}
