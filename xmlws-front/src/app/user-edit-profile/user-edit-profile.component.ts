import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'app/dto/UserDTO';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-edit-profile',
  templateUrl: './user-edit-profile.component.html',
  styleUrls: ['./user-edit-profile.component.css']
})
export class UserEditProfileComponent implements OnInit {

  user : UserDTO = new UserDTO();

  constructor(private http : HttpClient, private router : Router) { }

  ngOnInit() {
    this.http.get<UserDTO>('http://localhost:8762/ticketSales-service/users/'+localStorage.getItem('username')).subscribe((data) => {
      this.user = data;
    });
  }

  public onSubmit() {
    this.http.post<UserDTO>('http://localhost:8762/ticketSales-service/users/update',this.user).subscribe((data) => {
      console.log(data);
      this.router.navigate(['profile']);
    });
  }
}
