export class ServiceDTO {
    _id: number;
    _name: string;
    _description: string;

    get id() : number {
        return this._id;
    }

    get name() : string {
        return this._name;
    }

    get description() : string {
        return this._description;
    }
}