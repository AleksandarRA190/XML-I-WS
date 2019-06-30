import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '../../../node_modules/@angular/common/http';
import { Observable, Observer } from '../../../node_modules/rxjs';
import { AccommodationsDTO } from '../dto/AccommodationsDTO';
import { AccommodationUnitsDTO } from '../dto/AccommodationUnitsDTO';
import { AccommodationUnitDTO } from '../dto/AccommodationUnitDTO';
import { AccommodationUnitSearchDTO } from '../dto/AccommodationUnitSearchDTO';
import { PeriodPriceDatesDTO } from '../dto/PeriodPriceDatesDTO';
import { ReservationDTO } from 'app/dto/ReservationDTO';


@Injectable()
export class AccommodationUnitService {

  private baseUrl = 'http://localhost:8762/ticketSales-service/accommodation';
  private headers = { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' }) }

  constructor(private http: HttpClient) { }

  getAccommodationUnits(id: number | string) : Observable<AccommodationUnitsDTO> {
    return this.http.get<AccommodationUnitsDTO>(this.baseUrl + "/" + id + "/units", {responseType : 'json'});
  }

  getAccommodationUnit(id: number | string) : Observable<AccommodationUnitDTO> {
    return this.http.get<AccommodationUnitDTO>(this.baseUrl + "/units/" + id, {responseType : 'json'});
  }

  getFreeAccommodationUnits(accommodationId: number, accommodationUnitSearch: AccommodationUnitSearchDTO) : Observable<AccommodationUnitsDTO> {
    return this.http.post<AccommodationUnitsDTO>(this.baseUrl + "/" + accommodationId + "/freeUnits", accommodationUnitSearch);
  }

  getPeriodPriceForMonth(accommodationUnitId: number, periodPriceDates: PeriodPriceDatesDTO) : Observable<number> {
    return this.http.post<number>(this.baseUrl + "/units/" + accommodationUnitId + "/priceForPeriod", periodPriceDates);
  }

  makeReservation(reservationDTO: ReservationDTO) {
    console.log('aaaaddd');
    return this.http.put('http://localhost:8762/ticketSales-service/reservation/add', reservationDTO);
  }
  
}
