export class AccommodationUnitSearchDTO{
    _startDate: Date;
    _endDate: Date;

    get startDate() : Date {
        return this._startDate;
    } 

    set startDate(theStartDate: Date) {
        this._startDate = theStartDate;
    }

    get endDate() : Date {
        return this._endDate;
    } 

    set endDate(theEndDate: Date) {
        this._endDate = theEndDate;
    }
}