import { UserDTO } from './UserDTO';
import { ReservationDTO } from './ReservationDTO';
import { AccommodationDTO } from './AccommodationDTO';

export class MessageDTO {

    id: number;
    dateTime : Date;
    
    messageContent : string;
    seen : boolean;
    sender : UserDTO;
    reciever : UserDTO;
    reservation : ReservationDTO;
    accommodation : AccommodationDTO;

}