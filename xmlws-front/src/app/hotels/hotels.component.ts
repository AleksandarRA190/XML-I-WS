import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { AccommodationDTO } from '../dto/AccommodationDTO';
import { HttpClient } from '@angular/common/http';
import { AccommodationService } from '../services/AccommodationService';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AccommodationsDTO } from '../dto/AccommodationsDTO';
import { AccommodationTypesDTO } from '../dto/AccommodationTypesDTO';
import { ServiceService } from '../services/service.service';
import { ServicesDTO } from '../dto/ServicesDTO';
import { ServiceDTO } from '../dto/ServiceDTO';

declare var $: any;
declare var jQuery: any;

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  accommodations : AccommodationDTO[] = [];
  accommodationTypes: string[] = [];
  services: ServiceDTO[] = [];
  countrySearch: string;
  citySearch: string;

  constructor(
    private http: HttpClient,
    private accommodationService: AccommodationService,
    private serviceService: ServiceService,
    private router: Router) { }

  ngOnInit() {

    this.getAccommodations().subscribe(data =>{
        this.accommodations = data.accommodations;
    });

    this.getAccommodationTypes().subscribe(data => {
      this.accommodationTypes = data.accommodationTypes;
    });

    this.getServices().subscribe(data => {
      this.services = data.services;
    });
  }


  public getAccommodations() : Observable<AccommodationsDTO> {
    return this.accommodationService.getAccommodations();
  }

  public getAccommodationTypes() : Observable<AccommodationTypesDTO> {
    return this.accommodationService.getAccommodationTypes();
  }

  public getServices() : Observable<ServicesDTO> {
    return this.serviceService.getAllServices();
  }
  

 
   

 
}
