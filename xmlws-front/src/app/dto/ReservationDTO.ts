import { UserDTO } from './UserDTO';
import { AccommodationUnitDTO } from './AccommodationUnitDTO';

export class ReservationDTO {
    id: number;
    fromDateTime : Date;
    toDateTime : Date;
    confirmed : boolean;
    guest : UserDTO;
    accommodationUnit : AccommodationUnitDTO = new AccommodationUnitDTO();
}