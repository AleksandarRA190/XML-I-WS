import { Component, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReservationDTO } from 'app/dto/ReservationDTO';
import { Observable } from 'rxjs';
import { CommentDTO } from 'app/dto/CommentDTO';

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
})
export class UserReservationsComponent implements OnInit {
  
  reservations : ReservationDTO[] = [];
  lon : any;
  lat : any;


  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {
    this.http.get<ReservationDTO[]>('http://localhost:8762/ticketSales-service/users/getByUser/'+localStorage.getItem('username')).subscribe((data) => {
      this.reservations = data;
      console.log(data);
    });
  }

  public confirm(id : number) {
    this.http.get('http://localhost:8762/ticketSales-service/reservation/confirm/'+id).subscribe((data) => {
      alert('Reservation successfuly confirmed!');
      this.reload();
    });
  }

  public mica(input) : string {
    if(input == null) {
      return "";
    }

    let dateParts : string[];
    let dateString = input.toString();
    dateParts = dateString.split('T');
  
    let dateComponents : string[];
    dateComponents = dateParts[0].split('-');
 
    let day = dateComponents[2];
    let month = dateComponents[1];
    let year = dateComponents[0];

    
    let timeComponents : string[];
    timeComponents = dateParts[1].split(':');
 
    let hours = timeComponents[2];
    let minutes = timeComponents[1];
    let seconds = timeComponents[0];

    let retVal = "";
    retVal = retVal + day + "/" + month + "/" + year;
    //retVal = retVal + day + "/" + month + "/" + year + " at " + hours + " hours : " + minutes + " minutes : " + seconds + " seconds.";
    return retVal;
  }

  public deleteComment(id) {
    this.http.delete<boolean>('http://localhost:8762/ticketSales-service/reservation/comment/'+id).subscribe((data) => {
      alert('Comment successfully deleted');
      console.log(data);
      this.reload();
      
    });
  }


  public updateRating(id, rate) {
    console.log(rate);
    let myCommentDTO = new CommentDTO();
    myCommentDTO.reservationId = id;
    myCommentDTO.rate = rate;
    
    this.http.post('http://localhost:8762/ticketSales-service/reservation/rateReservation',myCommentDTO).subscribe((data) => {
      this.reload();
    });
  }

}
