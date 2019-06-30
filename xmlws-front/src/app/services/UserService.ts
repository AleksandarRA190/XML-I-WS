import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDTO } from '../dto/UserDTO';

@Injectable()
export class UserService {

    
  private baseUrl = 'http://localhost:8762/user-service/users';
  private headers = { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' }) }
  //private headers = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }

  constructor(private http: HttpClient) { }

  registerUser(user: UserDTO){
    return this.http.put("http://localhost:8762/user-service/users/register",user,this.headers).subscribe((data) => {
    });
  }



}