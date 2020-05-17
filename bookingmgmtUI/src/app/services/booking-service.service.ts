import { Injectable } from '@angular/core';
import { Show } from '../model/show';
import { Movie } from '../model/movie';
import { Screen } from '../model/screen';
import { Theater } from '../model/theater';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../model/booking';
import { BookingDetailsResponse } from '../dto/bookingdetailsresponse';

@Injectable({
  providedIn: 'root'
})
export class BookingServiceService {
  client:HttpClient;
  baseBookingUrl="http://localhost:8086/bookings";


  constructor(client:HttpClient) { 
    this.client=client;
   }
     
     getTheaterList(city:String):Observable<Theater[]>{
      let url=this.baseBookingUrl+"/getTheaters/"+city;
      let observable:Observable<Theater[]> = this.client.get<Theater[]>(url);
      return observable;
   }

     getMovieList(theaterId:number):Observable<Movie[]>{
      let url=this.baseBookingUrl+"/getMovies";
      let observable:Observable<Movie[]> = this.client.get<Movie[]>(url);
      return observable;
     }
     getScreenList(theaterId:number):Observable<Screen[]>{
      let url=this.baseBookingUrl+"/getScreens/"+theaterId;
      let observable:Observable<Screen[]> = this.client.get<Screen[]>(url);
      return observable;
     }
     getShowList(movieId:number):Observable<Show[]>{
      let url=this.baseBookingUrl+"/getShows/"+movieId;
      let observable:Observable<Show[]> = this.client.get<Show[]>(url);
      return observable;
     }

     fetchAllBookings():Observable<BookingResponse[]>{
        let url=this.baseBookingUrl;
        let observable:Observable<BookingResponse[]> = this.client.get<BookingResponse[]>(url);
        return observable;
     }

     addBooking(booking:Booking):Observable<BookingResponse>{
        let url = this.baseBookingUrl+"/add";
        let result:Observable<BookingResponse> = this.client.post<BookingResponse>(url,booking);
        return result;
     }

     getBooking(bookingId:number):Observable<BookingResponse>{
        let url = this.baseBookingUrl+"/get/"+bookingId;
        let result:Observable<BookingResponse> = this.client.get<BookingResponse>(url);
        return result;
     }
     getTicket(bookingId:number):Observable<Ticket>{
      let url = this.baseBookingUrl+"/getTicket/"+bookingId;
      let result:Observable<Ticket> = this.client.get<Ticket>(url);
      return result;
   }

   cancelBooking(bookingId:number):Observable<String>{
    let url = this.baseBookingUrl+"/cancel/"+bookingId;
    let result:Observable<String> = this.client.delete<String>(url);
    return result;
 }
}
