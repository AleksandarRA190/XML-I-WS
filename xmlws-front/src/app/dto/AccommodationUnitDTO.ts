import { AccommodationDTO } from './AccommodationDTO';
import { AccommodationUnitTypeDTO } from './AccommodationUnitTypeDTO';

export class AccommodationUnitDTO {
    id: number;
    floor : number;
    number : string;
    numberOfBeds : number;
    defaultPrice : number;
    accommodation : AccommodationDTO;
    accommodationUnitType: AccommodationUnitTypeDTO;
}