import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  
  title = 'xmlws-front';
  duzina : number = 0;

  constructor(private router : Router) {
    
  }

  ngOnInit() {
    this.loadDuzina();
  }

  public loadDuzina() {
    this.duzina = localStorage.length;
  }

  logOut() {
    localStorage.clear();
    this.loadDuzina();
    this.router.navigate(['home']);
  }
  

}
