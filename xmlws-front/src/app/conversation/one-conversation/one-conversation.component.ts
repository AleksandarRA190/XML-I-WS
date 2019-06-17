import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReservationDTO } from 'app/dto/ReservationDTO';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageDTO } from 'app/dto/MessageDTO';
import { UserDTO } from 'app/dto/UserDTO';
import { AccommodationDTO } from 'app/dto/AccommodationDTO';

@Component({
  selector: 'app-one-conversation',
  templateUrl: './one-conversation.component.html',
  styleUrls: ['./one-conversation.component.css']
})
export class OneConversationComponent implements OnInit {

  id: number;
  reservation: ReservationDTO;
  messageDTO: MessageDTO;
  previousMessages: string;
  previousMessageDTOs: MessageDTO[] = [];
  lastMessage: MessageDTO;

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
      this.reload();
    });

  }

  reload() {
    this.messageDTO = new MessageDTO();
    this.messageDTO.messageContent = "";

    this.http.get<MessageDTO[]>('http://localhost:9007/message/byReservation/' + this.id).subscribe((data) => {
      this.previousMessageDTOs = data;
      console.log(data);
      this.messageDTO.messageContent = "";
      this.previousMessages = "";

      if (data.length != 0) {
        for (let message of this.previousMessageDTOs) {
          this.previousMessages += message.sender.username + " : ";
          this.previousMessages += message.messageContent + "\n";
          this.lastMessage = message;
        }
      }

      this.http.get<ReservationDTO>('http://localhost:9007/reservation/' + this.id).subscribe((data) => {
        this.reservation = data;
        console.log(this.reservation);
      });

    });

  }


  onSubmit() {
    if (this.previousMessages != "") {
      this.messageDTO.seen = false;
      let sender = new UserDTO();
      sender.username = localStorage.getItem("username");
      this.messageDTO.sender = sender;
      let reservation = new ReservationDTO();
      reservation.id = this.id;
      this.messageDTO.reservation = reservation;
      this.http.put('http://localhost:9007/message/respond/' + this.lastMessage.id, this.messageDTO).subscribe((data) => {
        this.reload();
      });

    } else {
      this.messageDTO.seen = false;
      let sender = new UserDTO();
      sender.username = localStorage.getItem("username");
      this.messageDTO.sender = sender;
      let reservation = new ReservationDTO();
      reservation.id = this.id;
      this.messageDTO.reservation = reservation;
      let accommodation = new AccommodationDTO();
      accommodation.id = this.reservation.accommodationUnit.accommodation.id;
      this.messageDTO.accommodation = accommodation;
      this.http.put('http://localhost:9007/message/send', this.messageDTO).subscribe((data) => {
        this.reload();
      });
    }


  }

}
