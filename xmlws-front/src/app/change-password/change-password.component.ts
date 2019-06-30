import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ChangePasswordDTO } from 'app/dto/ChangePasswordDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  confirmPassword : string;
  request : ChangePasswordDTO = new ChangePasswordDTO();

  constructor(private http: HttpClient,private router : Router) { }

  ngOnInit() {
    this.request.username = localStorage.getItem('username');
    this.confirmPassword = "";
    this.request.oldPassword = "";
    this.request.newPassword = "";
  }

  validatePasswords() : boolean {
    console.log(this.request);
    if(this.confirmPassword === "" || this.request.newPassword === "" || this.request.oldPassword === "" ) {
      alert("You must fill out the form!");
      return false;
    }
    if(this.request.newPassword.length < 4) {
      alert('New password minimum size must be at least 4!');
      return false;
    }
    if(this.request.newPassword.length > 8) {
      alert('New password maximum size can not be over 8!');
      return false;
    }
    if(!(this.request.newPassword === this.confirmPassword)) {
      alert('Passwords do not match!');
      return false;
    }

    return true;
  }

  onSubmit() {
    let allOk = this.validatePasswords();
    if(allOk) {
      console.log(this.request);
      this.http.post('http://localhost:8762/ticketSales-service/users/changePassword', this.request).subscribe((data) => {
        if(data == true) {
          alert('Password successfully changed!');
          this.router.navigate(['profile']);
        } else {
          alert('Incorrect old password!');
        }
      });
      this.request = new ChangePasswordDTO();
      this.request.username = localStorage.getItem('username');
      this.confirmPassword = "";
    }
  }



}
