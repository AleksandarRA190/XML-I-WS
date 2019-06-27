import { AddressDTO } from './AddressDTO';

export class AccommodationDTO {
    id: number;
    name : string;
    description : string;
    category : string;
    accommodationType : string;
    address: AddressDTO;
}