import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommunicationService } from './communication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  
  title = 'xmlws-front';
  ima : boolean = true;

  constructor(private router : Router, private _communicationService: CommunicationService) {
    this._communicationService.changeEmitted$.subscribe(data => {
      //this.ima = data;
      console.log(data);
      });
  
  }

  
  ngOnInit() {
    this.loadDuzina();
  }

  public loadDuzina() {
    if(localStorage.length > 0) {
      this.ima = true;
    } else {
      this.ima = false;
    }
      
  }

  logOut() {
    localStorage.clear();
    this.loadDuzina();
    this.router.navigate(['home']);
  }
  

}
