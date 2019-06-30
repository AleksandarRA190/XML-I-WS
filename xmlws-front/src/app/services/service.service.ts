import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '../../../node_modules/@angular/common/http';

import { Observable } from '../../../node_modules/rxjs';
import { ServicesDTO } from '../dto/ServicesDTO';

@Injectable()
export class ServiceService {

  private baseUrl = 'http://localhost:8762/accommodation-service/accommodation';
  private headers = { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' }) }

  constructor(private http: HttpClient) { }

  getServicesOfAccommodation(id: number | string) : Observable<ServicesDTO> {
    return this.http.get<ServicesDTO>(this.baseUrl + "/" + id + "/services", {responseType : 'json'});
  }
  
  getAllServices() : Observable<ServicesDTO> {
    return this.http.get<ServicesDTO>(this.baseUrl + "/services", {responseType: 'json'});
  }
}
