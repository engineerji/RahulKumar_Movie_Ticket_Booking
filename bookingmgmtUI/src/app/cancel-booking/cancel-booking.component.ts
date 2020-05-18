import { Component, OnInit } from '@angular/core';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { BookingServiceService } from '../services/booking-service.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cancel-booking',
  templateUrl: './cancel-booking.component.html',
  styleUrls: ['./cancel-booking.component.css']
})
export class CancelBookingComponent implements OnInit {

  cancelBookingId:number;
  show:boolean=false;
  msg:string="";
  bookings:Array<BookingResponse>=[];
   __service:BookingServiceService;

  constructor(__service:BookingServiceService){
    this.__service=__service;
  }
  ngOnInit(): void {
  }

  cancelBooking(){
    let response:Observable<String>= this.__service.cancelBooking(this.cancelBookingId);
    response.subscribe((mesg:String) =>{
      this.msg="Cancelled";
      console.log(mesg);
      this.show=true;
  },
    error =>{
      this.msg="Invalid Id"
      this.show=true;
      console.log("Error "+error)
    });
  }
}
