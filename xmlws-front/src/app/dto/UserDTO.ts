import { AddressDTO } from './AddressDTO';

export class UserDTO {
    id: number;
    username : string;
    password : string;
    email : string;
    name : string;
    lastname : string;
    address : AddressDTO = new AddressDTO();
    role : string = "REGISTERED_USER";
}