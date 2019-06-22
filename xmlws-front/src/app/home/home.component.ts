import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { CommunicationService } from 'app/communication.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @Output() ima = new EventEmitter<boolean>();


  constructor(private _communicationService: CommunicationService) { }

  ngOnInit() {
    let has = localStorage.getItem('username');
    if(localStorage.length == 0) {
      this._communicationService.emitChange(false);
    } else {
      this._communicationService.emitChange(true);
    }
       
    //this.getSearchStatusChange.emit(newValue)
    console.log(localStorage);
  }

}
