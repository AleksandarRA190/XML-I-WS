import { ServiceDTO } from 'app/dto/ServiceDTO';

export class ServiceCheckTable {
    _checked : boolean;
    _service: ServiceDTO;

    get service() : ServiceDTO {
        return this._service;
    }

    set service(theService: ServiceDTO) {
        this._service = theService;
    }   

    get checked() : boolean {
        return this._checked;
    }

    set checked(theChecked: boolean) {
        this._checked = theChecked;
    }
}