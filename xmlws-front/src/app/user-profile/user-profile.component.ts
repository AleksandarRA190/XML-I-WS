import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../dto/UserDTO';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: UserDTO = new UserDTO();
  private lat: any;
  private lon: any;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<UserDTO>('http://localhost:8762/ticketSales-service/users/' + localStorage.getItem('username')).subscribe((data) => {
      console.log(data);
      this.user = data;

      navigator.geolocation.getCurrentPosition((position) => {
        this.lon = position.coords.longitude;
        this.lat = position.coords.latitude;
        console.log(this.lat);
        console.log(this.lon);

        let distance;
        distance = Math.sqrt(Math.pow(this.lat - this.user.address.latitude, 2) - Math.pow(this.lon - this.user.address.longitude, 2));
        console.log(distance);
      });

    });

  }

  

}
