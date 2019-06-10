import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../dto/UserDTO';
import { LoginDTO } from '../dto/LoginDTO';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  

  loginDto : LoginDTO = new LoginDTO();
  user : UserDTO = new UserDTO();

  constructor(private http: HttpClient, private router : Router) { }

  ngOnInit() {
    this.loginDto.password = "";
    this.loginDto.username = "";
  }

  onSubmit() {
    //validation remaining
    console.log(this.loginDto);
    this.http.post('http://localhost:9007/users/login', this.loginDto).subscribe((data) => {
      alert('Successful login!');
      localStorage.setItem('username',this.loginDto.username);
      console.log(localStorage);
      this.router.navigate(['profile']);
    },
    (err: any) => { 
      console.log(err.status); console.log(err); 
      alert('Unsuccessful login. Please check your username and password!');
    });
  }

}
