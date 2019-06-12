import { Component, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReservationDTO } from 'app/dto/ReservationDTO';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
})
export class UserReservationsComponent implements OnInit {
  
  reservations : ReservationDTO[] = [];


  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {
    this.http.get<ReservationDTO[]>('http://localhost:9007/users/getByUser/'+localStorage.getItem('username')).subscribe((data) => {
      this.reservations = data;
      console.log(data);
    });
  }

  public confirm(id : number) {
    this.http.get('http://localhost:9007/reservation/confirm/'+id).subscribe((data) => {
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

}
