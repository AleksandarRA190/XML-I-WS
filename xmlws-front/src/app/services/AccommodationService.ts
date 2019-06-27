import { Injectable } from "@angular/core";
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AccommodationsDTO } from '../dto/AccommodationsDTO';
import { AccommodationDTO } from '../dto/AccommodationDTO';
import { AccommodationTypesDTO } from '../dto/AccommodationTypesDTO';

@Injectable()
export class AccommodationService {

    private baseUrl = 'http://localhost:9009/accommodation';
    private headers = { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' }) }
    
    constructor(private http: HttpClient) { }

    getAccommodations() : Observable<AccommodationsDTO> {
        return this.http.get<AccommodationsDTO>(this.baseUrl, {responseType : 'json'});
    }

    getAccommodation(id: number | string) : Observable<AccommodationDTO> {
        return this.http.get<AccommodationDTO>(this.baseUrl + "/" + id, {responseType : 'json'});
    }

    getAccommodationTypes() : Observable<AccommodationTypesDTO> {
        return this.http.get<AccommodationTypesDTO>(this.baseUrl+"/accommodationTypes", {responseType : 'json'} )
    }

    getAccommodationsWithFreeUnits(startDate: Date, endDate: Date, numberOfGuests: number) : Observable<AccommodationsDTO> {
        return this.http.get<AccommodationsDTO>(this.baseUrl+"/withFreeUnits", {responseType: 'json'});
    }
    
    getAvgRating(id: number) : Observable<number> {
        return this.http.get<number>(this.baseUrl+"/getAvgRating/"+id);
    }
     
}