import { Component, OnInit } from '@angular/core';
import { BookingResponse } from '../dto/bookingresponse';
import { Ticket } from '../model/ticket';
import { BookingServiceService } from '../services/booking-service.service';

@Component({
  selector: 'app-get-ticket',
  templateUrl: './get-ticket.component.html',
  styleUrls: ['./get-ticket.component.css']
})
export class GetTicketComponent implements OnInit {
  ticket:Ticket=null;
  show:boolean=false;
  errorShow=false;
  bookings:Array<BookingResponse>=[];
  __service:BookingServiceService;
  constructor(__service:BookingServiceService){
    this.__service=__service;
  }
  ngOnInit(): void {
  }

  getTicket(searchTicketForm:any){
    let bookingId=searchTicketForm.value.bookingId;
    this.bookings=this.__service.getBookings();
    let temp=0;
    this.bookings.forEach(booking =>{
      if(booking.bookingId==bookingId){
        this.ticket=booking.ticket;
        this.show=true;
        this.errorShow=false;
        temp=1;
      }
    });
    if(temp==0){
      this.show=false;
      this.errorShow=true;
    }
  }

}
