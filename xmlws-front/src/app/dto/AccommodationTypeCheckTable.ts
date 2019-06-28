export class AccommodationTypeCheckTable {
    _checked: boolean;
    _accommodationType: string;

    get checked() : boolean {
        return this._checked;
    }

    set checked(theChecked: boolean) {
        this._checked = theChecked;
    }

    get accommodationType(): string {
        return this._accommodationType;
    }

    set accommodationType(theAccommodationType: string) {
        this._accommodationType = theAccommodationType;
    }
}