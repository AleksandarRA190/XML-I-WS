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
import { AccommodationCommentsDTO } from 'app/dto/AccommodationCommentsDTO';
import { UserCommentPair } from 'app/dto/UserCommentPair';
import { ReservationDTO } from 'app/dto/ReservationDTO';
import { UserDTO } from 'app/dto/UserDTO';

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
  userCommentPairs: UserCommentPair[] = [];
  accommodationComments : AccommodationCommentsDTO = new AccommodationCommentsDTO();
  isSearched: boolean = false;

  showFile = false
  fileUploads: Observable<string[]>


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
        console.log(data);
        this.getAllComments(this.id).subscribe(data => {
          this.accommodationComments= data;
          let i = 0;
          for(let comment of this.accommodationComments.comments) {
            let pair = new UserCommentPair();
            pair.comment = comment;
            pair.userDTO = this.accommodationComments.userDTOs[i];
            this.userCommentPairs.push(pair);
            i++;
          }
          
          
          console.log(this.userCommentPairs);
        });
      });
    });

    this.getAccommodation(this.id).subscribe(data => {
      this.accommodation = data;
    });

    this.startDate = JSON.parse(localStorage.getItem('startDate'));
    this.endDate = JSON.parse(localStorage.getItem('endDate'));

    localStorage.setItem('startReservation', JSON.stringify(this.startDate));
    localStorage.setItem('endReservation', JSON.stringify(this.endDate));
    console.log('aaaa' + this.startDate);
    

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

  public getAllComments(id: number) : Observable<AccommodationCommentsDTO> {
    return this.accommodationService.getAllComments(id);
  }

  public makeReservation(unit : AccommodationUnitDTO){
    
    let res = new ReservationDTO();
    let guest = new UserDTO();
    guest.username = localStorage.getItem('username');

    res.accommodationUnit =  unit;
    res.agentConfirmed = false;
    res.confirmed = false;
    res.guest = guest;
    let temp = JSON.parse(localStorage.getItem('startReservation'));
    temp+="T00:00:00";
    res.fromDateTime = temp;
    temp =  JSON.parse(localStorage.getItem('endReservation'));
    temp+="T00:00:00";
    res.toDateTime = temp;

    console.log(res);
    this.http.put('http://localhost:9007/reservation/add', res).subscribe((data) => {});

    alert('Reserved sucessfully!');
  }

  
}
