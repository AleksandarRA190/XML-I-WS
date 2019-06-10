import { Component, OnInit } from '@angular/core';
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

}
