import { AccommodationUnitDTO } from './AccommodationUnitDTO';
import { Observable } from '../../../node_modules/rxjs';

export class AccommodationUnitsDTO {
    _accommodationUnits : AccommodationUnitDTO[] = [];

    get accommodationUnits() : AccommodationUnitDTO[] {
        return this._accommodationUnits;
    }
}