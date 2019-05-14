import { Worker } from './worker';
import { WorkInfo } from './work-info';


export class WorkersTime{
    worksInfo : Array<WorkInfo>;
    workersList : Map<string, Array<Worker>>;
    requiredTime : number;
}