import { ServiceDTO } from 'app/dto/ServiceDTO';

export class AccommodationSearchDTO {

    startDate : Date;
    endDate: Date;
    numberOfGuests: number;
    accommodationTypes: string[] = [];
    services: ServiceDTO[] = [];
}