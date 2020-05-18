import { Theater } from './theater';

export class BookingModel{
    city:string;
    selectedSeats:boolean[];
    language:String;
    constructor(city:string,selectedSeats:boolean[],language:String){
        this.city=city;
        this.selectedSeats=selectedSeats;
        this.language=language;
    }
}