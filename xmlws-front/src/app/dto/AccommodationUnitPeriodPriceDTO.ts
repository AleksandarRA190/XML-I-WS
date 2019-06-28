import { AccommodationUnitDTO } from './AccommodationUnitDTO';

export class AccommodationUnitPeriodPrice {

    _accommodationUnit: AccommodationUnitDTO;
    _price: number;

    get accommodationUnit() : AccommodationUnitDTO {
        return this._accommodationUnit;
    }

    set accommodationUnit(theAccommodationUnit: AccommodationUnitDTO) {
        this.accommodationUnit = theAccommodationUnit;
    }

    get price() : number {
        return this._price;
    }

    set price(thePrice: number) {
        this._price = thePrice;
    }
}