import { Pipe, PipeTransform } from '@angular/core';
import { AccommodationDTO } from '../dto/AccommodationDTO';

@Pipe({
  name: 'accommodationpipe'
})
export class AccommodationpipePipe implements PipeTransform {

  transform(accommodations: AccommodationDTO[], countrySearch: string, citySearch: string): AccommodationDTO[] {
    if(accommodations && accommodations.length) {
      return accommodations.filter(accommodation => {
        if(countrySearch && accommodation.address.country.toLowerCase().indexOf(countrySearch.toLowerCase()) === -1) {
          return false;
        }
        if(citySearch && accommodation.address.city.toLowerCase().indexOf(citySearch.toLowerCase()) === -1) {
          return false;
        }
        return true;
      })
    } else {
      return accommodations;
    }
    
  }

}
