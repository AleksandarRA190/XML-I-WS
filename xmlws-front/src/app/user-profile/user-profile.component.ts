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

  user : UserDTO = new UserDTO();

  constructor(private http : HttpClient) { }
  
  ngOnInit() { 
    this.http.get<UserDTO>('http://localhost:9007/users/'+localStorage.getItem('username')).subscribe((data) => {
      console.log(data);
      this.user = data;
    });

  }

}
