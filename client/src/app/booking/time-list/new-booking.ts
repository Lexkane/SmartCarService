import { WorkInfo } from '../worker-list/work-info';

export class NewBooking{
    workersId : Array<number>;
    start : string;
    carId : number;
    worksInfo : Array<WorkInfo>;
}