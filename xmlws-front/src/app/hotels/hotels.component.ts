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
import { ServiceCheckTable } from '../dto/ServiceCheckTable';
import { AccommodationSearchDTO } from '../dto/AccommodationSearchDTO';
import { AccommodationTypeCheckTable } from '../dto/AccommodationTypeCheckTable';

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
  servicesChecked: ServiceCheckTable[] = [];
  accommodationTypesChecked: AccommodationTypeCheckTable[] = [];
  countrySearch: string;
  citySearch: string;
  
  // **** fields for search ****
  startDate: Date;
  endDate: Date;
  numberOfGuests: number;
  servicesToSearch: ServiceDTO[] = [];
  accommodationTypesToSearch: string[] = [];

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
       for(let i=0; i<data.accommodationTypes.length; i++) {
         let atct = new AccommodationTypeCheckTable();
         atct.accommodationType = data.accommodationTypes[i];
         atct.checked = false;
         this.accommodationTypesChecked.push(atct);
       }
    });

    this.getServices().subscribe(data => {
      for(let i=0; i<data.services.length; i++) {
        let sct = new ServiceCheckTable();
        sct.service = data.services[i];
        sct.checked = false;
        this.servicesChecked.push(sct); 
        console.log(this.servicesChecked[i].service.name);
      }
    });


  }

  public search() {
    if(this.startDate >= this.endDate) {
      alert("Start date can not be after end date");
    } else if(this.startDate == undefined || this.endDate == undefined || this.numberOfGuests == undefined) {
      alert("Fileds: start date, end date and number of guests are mandatory");
    } else {
      let accommodationSearch = new AccommodationSearchDTO();
      accommodationSearch.startDate = this.startDate;
      accommodationSearch.endDate = this.endDate;
      accommodationSearch.numberOfGuests = this.numberOfGuests;
      accommodationSearch.services = this.servicesToSearch;
      accommodationSearch.accommodationTypes = this.accommodationTypesToSearch;

      this.searchAccommodations(accommodationSearch).subscribe(data => {
        this.accommodations = data.accommodations;
      });
    }
  }

  public change() {
    let checkedStrings = this.servicesChecked.reduce((acc, eachGroup) => {
      if (eachGroup.checked) {
        acc.push(eachGroup.service);
      }
      return acc
    }, [])
    this.servicesToSearch = checkedStrings;
    console.log(this.servicesToSearch);
  }

  public changeAccommodationType() {
    let checkedStrings = this.accommodationTypesChecked.reduce((acc, eachGroup) => {
      if (eachGroup.checked) {
        acc.push(eachGroup.accommodationType);
      }
      return acc
    }, [])
    this.accommodationTypesToSearch = checkedStrings;
    console.log(this.accommodationTypesToSearch);
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
  
  public searchAccommodations(accommodationToSearch: AccommodationSearchDTO) : Observable<AccommodationsDTO> {
    return this.accommodationService.getAccommodationsWithFreeUnits(accommodationToSearch);
  }  

 
   

 
}
