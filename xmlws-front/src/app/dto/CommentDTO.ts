import { UserDTO } from './UserDTO';
import { AccommodationUnitDTO } from './AccommodationUnitDTO';
import { LoginComponent } from 'app/login/login.component';

export class CommentDTO {
    contentOfComment : string;
    approvedComment : boolean;
    reservationId : number;
    rate:number;
}