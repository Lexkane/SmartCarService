import {WorkType} from "../../booking/skills/work-type";

export class ReportDto {

  reportId: number;

  userFullName: string;

  carBrand: string;
  carModel: string;
  carNumber: string;

  startTime: string;
  endTime: string;
  requiredTime: number;
  price: number;

  technicalServiceName: string;
  technicalServiceAddress: string;

  workerTasks: Map<string, Array<WorkType>>

}
