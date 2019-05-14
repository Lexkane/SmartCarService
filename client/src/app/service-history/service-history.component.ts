import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ServiceHistoryService} from "./service-history.service";
import {ReportDto} from "./vi-report/report-dto";

@Component({
  selector: 'service-history',
  templateUrl: './service-history.component.html',
  styleUrls: ['./service-history.component.scss'],
  providers: [ServiceHistoryService]
})
export class ServiceHistoryComponent implements OnInit {

  carId: number;

  reports: Array<ReportDto>;

  constructor(private service: ServiceHistoryService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.carId = Number(this.getFromRouterParams('carId'));
    this.getAllReportsByCarId(this.carId);
  }

  private getFromRouterParams(param: string) {
    return this.route.snapshot.queryParamMap.get(param);
  }

   public getAllReportsByCarId(carId: number) {
     this.service.getAllReportsByCarId(carId)
       .subscribe(
         data => {
           this.reports = data;
           console.log(data);
           console.log(this.reports);
         },
         error => console.error('Error: ', error)
       );
   }

}
