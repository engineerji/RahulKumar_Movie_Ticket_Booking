import { Component, OnInit } from '@angular/core';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { BookingServiceService } from '../services/booking-service.service';

@Component({
  selector: 'app-find-booking',
  templateUrl: './find-booking.component.html',
  styleUrls: ['./find-booking.component.css']
})
export class FindBookingComponent implements OnInit {

 
  booking:BookingResponse=null;
  show:boolean=false;
  errorShow=false;
  bookings:Array<BookingResponse>=[];
  __service:BookingServiceService;
  constructor(__service:BookingServiceService){
    this.__service=__service;
  }
  ngOnInit(): void {
  }

  getBooking(searchBookingForm:any){
    let temp=0;
    let bookingId=searchBookingForm.value.bookingId;
    this.bookings=this.__service.getBookings();
    this.bookings.forEach(booking =>{
      if(booking.bookingId==bookingId){
        this.booking=booking;
        this.show=true;
        this.errorShow=false;
        temp=1;
      }
    });
    if(temp==0){
      this.errorShow=true;
      this.show=false;
    }
  }
}
