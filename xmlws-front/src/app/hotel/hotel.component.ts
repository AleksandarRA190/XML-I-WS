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
import { AccommodationUnitSearchDTO } from '../dto/AccommodationUnitSearchDTO';
import { AccommodationSearchDTO } from '../dto/AccommodationSearchDTO';
import { AccommodationUnitPeriodPrice } from '../dto/AccommodationUnitPeriodPriceDTO';
import { PeriodPriceDatesDTO } from '../dto/PeriodPriceDatesDTO';

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
  avgRating : number;
  accommodationUnitsPeriodPrices: AccommodationUnitPeriodPrice[] = [];
  startDate: Date;
  endDate: Date;
  
  isSearched: boolean = false;


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
      this.getAvgRating(this.id).subscribe(data => {
        this.avgRating = data;
        // console.log(data);
      });
    });

    this.getAccommodation(this.id).subscribe(data => {
      this.accommodation = data;
    });

    this.startDate = JSON.parse(localStorage.getItem('startDate'));
    this.endDate = JSON.parse(localStorage.getItem('endDate'));
    
    // if(this.startDate !== undefined && this.endDate !== undefined) {
    if(localStorage.length > 0) {
      this.isSearched = true;

      let accommodationUnitSearch = new AccommodationUnitSearchDTO();
      accommodationUnitSearch.startDate = this.startDate;
      accommodationUnitSearch.endDate = this.endDate;
      
      this.getFreeAccommodationUnits(this.id, accommodationUnitSearch).subscribe(data => {
        this.accommodationUnits = data.accommodationUnits;
        
        for(let i=0; i<this.accommodationUnits.length; i++) {
          let periodPriceDates = new PeriodPriceDatesDTO();
          let acupp = new AccommodationUnitPeriodPrice();
          
          periodPriceDates.fromDate = this.startDate;
          periodPriceDates.toDate = this.endDate;
          acupp._accommodationUnit = this.accommodationUnits[i];
          
          this.getPeriodPriceForMonth(this.accommodationUnits[i].id, periodPriceDates)
          .subscribe(data => {
            acupp.price = data;
          });
          this.accommodationUnitsPeriodPrices.push(acupp);
          
  
        }
      });

    } else {
      this.getAccommodationUnits(this.id).subscribe(data => {
          this.accommodationUnits = data.accommodationUnits;
      });
    }

    this.getServicesOfAccommodation(this.id).subscribe(data => {
      this.services = data.services;
    },err => {});


    
    localStorage.removeItem('startDate');
    localStorage.removeItem('endDate');
  }


  public getAccommodationUnits(id: number | string) : Observable<AccommodationUnitsDTO> {
    return this.accommodationUnitService.getAccommodationUnits(id);
  }

  public getAccommodation(id: number | string) : Observable<AccommodationDTO> {
    return this.accommodationService.getAccommodation(id);
  }

  public getAvgRating(id: number) : Observable<number> {
    return this.accommodationService.getAvgRating(id);
  }

  public getServicesOfAccommodation(id: number | string) : Observable<ServicesDTO> {
    return this.serviceService.getServicesOfAccommodation(id);
  }

  public getFreeAccommodationUnits(id: number, accommodationUnitSearch: AccommodationUnitSearchDTO) : Observable<AccommodationUnitsDTO> {
    return this.accommodationUnitService.getFreeAccommodationUnits(id,accommodationUnitSearch);
  }

  public getPeriodPriceForMonth(accommodationUnitId: number, periodPriceDates: PeriodPriceDatesDTO) : Observable<number> {
    return this.accommodationUnitService.getPeriodPriceForMonth(accommodationUnitId, periodPriceDates);
  }


  
}
