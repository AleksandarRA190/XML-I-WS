import { Component, OnInit } from '@angular/core';
import { AccommodationDTO } from '../dto/AccommodationDTO';
import { AccommodationUnitDTO } from '../dto/AccommodationUnitDTO';
import { Observable } from '../../../node_modules/rxjs';
import { AccommodationUnitsDTO } from '../dto/AccommodationUnitsDTO';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { AccommodationService } from '../services/AccommodationService';
import { Router, ActivatedRoute } from '../../../node_modules/@angular/router';
import { AccommodationsDTO } from '../dto/AccommodationsDTO';
import { AccommodationUnitService } from '../services/accommodation-unit.service';
import { ServiceService } from '../services/service.service';
import { ServicesDTO } from 'app/dto/ServicesDTO';
import { ServiceDTO } from 'app/dto/ServiceDTO';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  private sub : any;
  id: number;
  accommodation: AccommodationDTO;
  accommodationUnits: AccommodationUnitDTO[] = [];
  services: ServiceDTO[] = [];


  constructor( 
    private http: HttpClient,
    private accommodationUnitService: AccommodationUnitService,
    private accommodationService: AccommodationService,
    private serviceService: ServiceService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = + params['id'];
    })

    this.getAccommodation(this.id).subscribe(data => {
      this.accommodation = data;
    });

    this.getAccommodationUnits(this.id).subscribe(data => {
        this.accommodationUnits = data.accommodationUnits;
    });

    this.getServicesOfAccommodation(this.id).subscribe(data => {
      this.services = data.services;
    });
  }

  public getAccommodationUnits(id: number | string) : Observable<AccommodationUnitsDTO> {
    return this.accommodationUnitService.getAccommodationUnits(id);
  }

  public getAccommodation(id: number | string) : Observable<AccommodationDTO> {
    return this.accommodationService.getAccommodation(id);
  }

  public getServicesOfAccommodation(id: number | string) : Observable<ServicesDTO> {
    return this.serviceService.getServicesOfAccommodation(id);
  }
}
