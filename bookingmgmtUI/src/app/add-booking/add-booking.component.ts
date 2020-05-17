import { Component, OnInit } from '@angular/core';
import { Show } from '../model/show';
import { Movie } from '../model/movie';
import { Theater } from '../model/theater';
import { Screen } from '../model/screen';
import { Booking } from '../model/booking';
import { BookingModel } from '../model/bookingModel';
import { BookingServiceService } from '../services/booking-service.service';
import { BookingResponse } from '../dto/bookingresponse';
import { Observable } from 'rxjs';
import { BookingDetailsResponse } from '../dto/bookingdetailsresponse';

@Component({
  selector: 'app-add-booking',
  templateUrl: './add-booking.component.html',
  styleUrls: ['./add-booking.component.css']
})
export class AddBookingComponent implements OnInit {

  _bookingModel=new BookingModel("",[]);

  __service:BookingServiceService;

  booking:BookingResponse=null;
  seatShown=false;
  showbooking=false;

  cityList=["Delhi","Mumbai","Banglore","Chennai"];
  selectedTheaterList:Array<Theater>=[];
  selectedMovieList:Array<Movie>=[];
  selectedScreenList:Array<Screen>=[];
  selectedShowList:Array<Show>=[];
  selectedSeats:number[]=[];
  seatIds:Array<number>=[];

  constructor(__service:BookingServiceService) {  
    this.__service=__service;
  }

  ngOnInit(): void {
  }

  getTheater(){
    console.log(this._bookingModel.city);
    this.__service.getTheaterList(this._bookingModel.city).subscribe(theaterList =>{
      this.selectedTheaterList=theaterList;
    },
    error =>{
      console.log("Error "+error);
    });
  }
  
  getMovieAndScreen(theaterId:any){
    this.__service.getMovieList(theaterId).subscribe(movieList =>{
      this.selectedMovieList=movieList;
    },
    error =>{
      console.log("Error "+error);
    });

    this.__service.getScreenList(theaterId).subscribe(screenList =>{
      this.selectedScreenList=screenList;
    },
    error =>{
      console.log("Error "+error);
    });
  }

  getShow(movieId:any){
    this.__service.getShowList(movieId).subscribe(showList =>{
      this.selectedShowList=showList;
    },
    error =>{
      console.log("Error "+error);
    });
  }

  getSeats(event:any){
    this.seatIds=[];
    let showId=event.target.value;
    this.selectedShowList.forEach(show =>{
      if(showId==show.showId){
        this.seatIds=show.seatIds;
      }
    });
    this.seatShown=true;
  }

  submit(bookingForm:any){
    this.selectedSeats=[];
    let bookingDetails= bookingForm.value;
    let selectedSeatIds=this._bookingModel.selectedSeats;
    this.seatIds.forEach(seat =>{
      if(selectedSeatIds[seat]==true){
        this.selectedSeats.push(seat);
      }
    });
    let booking:Booking=new Booking(bookingDetails.movie,bookingDetails.show,bookingDetails.screen,
      bookingDetails.paymentMethod,this.selectedSeats);

    let result:Observable<BookingResponse> = this.__service.addBooking(booking);
    result.subscribe((bookingResp:BookingResponse) =>{
      this.booking=bookingResp;
    },
      err =>{
        console.log("Error "+err);
      });
    
    this.showbooking=true;
  }
}
