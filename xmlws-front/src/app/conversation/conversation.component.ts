import { Component, OnInit } from '@angular/core';
import { ReservationDTO } from 'app/dto/ReservationDTO';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit {

  reservations : ReservationDTO[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<ReservationDTO[]>('http://localhost:9007/users/getByUser/'+localStorage.getItem('username')).subscribe((data) => {
      this.reservations = data;
      console.log(data);
    });
  }

  public onSubmit() {
    
  }

}
