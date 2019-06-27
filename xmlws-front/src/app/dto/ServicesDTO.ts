import { ServiceDTO } from 'app/dto/ServiceDTO';

export class ServicesDTO {
    _services : ServiceDTO[] = [];

    get services() : ServiceDTO[] {
        return this._services;
    }
    
}