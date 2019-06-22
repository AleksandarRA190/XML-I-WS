import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class CommunicationService {
    constructor() { }

    private emitChangeSource = new Subject<boolean>();

    changeEmitted$ = this.emitChangeSource.asObservable();

    emitChange(data: boolean) {
        this.emitChangeSource.next(data);
    }

}