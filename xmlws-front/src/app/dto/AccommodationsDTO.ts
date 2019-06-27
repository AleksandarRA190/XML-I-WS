import { AccommodationDTO } from './AccommodationDTO';

export class AccommodationsDTO {
    _accommodations : AccommodationDTO[] = [];

    get accommodations() : AccommodationDTO[] {
        return this._accommodations;
    }

}