import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../dto/UserDTO';
import { AddressDTO } from '../dto/AddressDTO';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { UserService } from '../services/UserService';
import { Router } from '@angular/router';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user : UserDTO = new UserDTO();
  confirmPassword : string;
  colors : string[] = ['black','black','black','black','black','black','black','black'];
  

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.user.address = new AddressDTO();
    this.user.address.city = ""; 
    this.user.address.country = "";
    this.user.address.apartmentNumber = "";
    this.user.address.id = 0;
    this.user.address.latitude = 0;
    this.user.address.longitude = 0;
    this.user.address.number = "";
    this.user.address.postalCode = 0;

    this.user.username = "";
    this.user.password = "";
    this.user.email = "";
    this.user.lastname = "";
    this.user.name = "";
    this.confirmPassword = "";
  }

  validateEmpty() {
     
    let success = true;

    if(this.user.name == "") {
      this.colors[0] = 'red';
      success = false;
    } else {
      this.colors[0] = 'black';
    }
    
    if(this.user.lastname == "") {
      this.colors[1] = 'red';
      success = false;
    } else {
      this.colors[1] = 'black';
    }

    if(this.user.email == "") {
      this.colors[2] = 'red';
      success = false;
    } else {
      this.colors[2] = 'black';
    }

    if(this.user.username == "") {
      this.colors[3] = 'red';
      success = false;
    } else {
      this.colors[3] = 'black';
    }
    
    if(this.user.password == "") {
      this.colors[4] = 'red';
      success = false;
    } else {
      this.colors[4] = 'black';
    }

    if(this.confirmPassword == "") {
      this.colors[5] = 'red';
      success = false;
    } else {
      this.colors[5] = 'black';
    }
    
    if(this.user.address.country == "") {
      this.colors[6] = 'red';
      success = false;
    } else {
      this.colors[6] = 'black';
    }
    

    if(this.user.address.city == "") {
      this.colors[7] = 'red';
      success = false;
    } else {
      this.colors[7] = 'black';
    }

    return success;
  }

  validateUsername() {
    if(this.user.username.length < 4) {
      this.colors[3] = 'red';
      return false;
    }

    this.colors[3] = 'black';
    return true;
  }

  validatePassword() {
    if(this.user.password.length < 4) {
      this.colors[4] = 'red';
      return false;
    }

    this.colors[4] = 'black';
    return true;
  }

  validation() {
      let allOk = true;
      if(!(this.confirmPassword === this.user.password)) {
        allOk = false;
        alert("Password do not match");
      } else if (!this.validateEmpty()) {
        allOk = false;
        alert("Please fill in all required fields");
      } else if (!this.validateUsername()) {
        alert("Username length must be minimum 4!");
        allOk = false;
      } else if (!this.validatePassword()) {
        alert("Password length must be minimum 4!");
        allOk = false;
      }
      else {
        for (let i = 0 ; i < this.colors.length ; i++) {
           this.colors[i] = 'black';
        }
      }

      return allOk;
    }

  onSubmit() {
    let allOk = this.validation();
    if(allOk) {
      console.log(this.user);
      this.http.put('http://localhost:8762/ticketSales-service/users/register', this.user).subscribe((data) => {});
      alert("Go to your email to activate your account");
      this.user = new UserDTO();
      this.confirmPassword = "";
    }
  }





}
