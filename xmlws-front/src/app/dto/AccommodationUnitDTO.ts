import { AccommodationDTO } from './AccommodationDTO';

export class AccommodationUnitDTO {
    id: number;
    floor : number;
    number : string;
    numberOfBeds : number;
    defaultPrice : number;
    accommodation : AccommodationDTO;
}