import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '../../../node_modules/@angular/common/http';
import { Observable, Observer } from '../../../node_modules/rxjs';
import { AccommodationsDTO } from '../dto/AccommodationsDTO';
import { AccommodationUnitsDTO } from '../dto/AccommodationUnitsDTO';
import { AccommodationUnitDTO } from '../dto/AccommodationUnitDTO';


@Injectable()
export class AccommodationUnitService {

  private baseUrl = 'http://localhost:9009/accommodation';
  private headers = { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' }) }

  constructor(private http: HttpClient) { }

  getAccommodationUnits(id: number | string) : Observable<AccommodationUnitsDTO> {
    return this.http.get<AccommodationUnitsDTO>(this.baseUrl + "/" + id + "/units", {responseType : 'json'});
  }

  getAccommodationUnit(id: number | string) : Observable<AccommodationUnitDTO> {
    return this.http.get<AccommodationUnitDTO>(this.baseUrl + "/units/" + id, {responseType : 'json'});
  }

  // getFreeAccommodationUnits(id: number, startDate: Date, endDate: Date) <AccommodationUnitsDTO> {
  //   return this.http.get<AccommodationUnitsDTO>(this.baseUrl + "/" + id + "/freeUnits", {responseType : 'json'});
  // }
}
