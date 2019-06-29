import { UserDTO } from './UserDTO';
import { AccommodationUnitDTO } from './AccommodationUnitDTO';
import { CommentDTO } from './CommentDTO';

export class ReservationDTO {
    id: number;
    fromDateTime : Date;
    toDateTime : Date;
    confirmed : boolean;
    agentConfirmed : boolean;
    guest : UserDTO;
    accommodationUnit : AccommodationUnitDTO = new AccommodationUnitDTO();
    commentDTO : CommentDTO = new CommentDTO();
    price : number;

}