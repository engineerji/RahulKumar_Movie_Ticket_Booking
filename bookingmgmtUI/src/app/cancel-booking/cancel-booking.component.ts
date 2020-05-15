import { Component, OnInit } from '@angular/core';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { BookingServiceService } from '../services/booking-service.service';

@Component({
  selector: 'app-cancel-booking',
  templateUrl: './cancel-booking.component.html',
  styleUrls: ['./cancel-booking.component.css']
})
export class CancelBookingComponent implements OnInit {


  show:boolean=false;
  msg:string="";
  bookings:Array<BookingResponse>=[];
   __service:BookingServiceService;
  constructor(__service:BookingServiceService){
    this.__service=__service;
  }
  ngOnInit(): void {
  }

  cancelBooking(cancelForm:any){
    let temp=0;
    let bookingId=cancelForm.value.bookingId;
    this.bookings=this.__service.getBookings();
    this.bookings.forEach(booking =>{
      if(booking.bookingId==bookingId){
        this.msg="Cancelled";
        this.show=true;
        temp=1;
      }
    });
    if(temp==0){
      this.msg="Invalid Id"
    }
    this.show=true;
  }
}
