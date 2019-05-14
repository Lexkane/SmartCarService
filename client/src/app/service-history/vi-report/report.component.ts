import {Component, Input, OnInit} from '@angular/core';
import {ReportDto} from "./report-dto";

@Component({
  selector: 'report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  @Input()
  report: ReportDto;

  constructor() {
  }

  ngOnInit() {
  }

  public formatDate(date: string): string {
    let iso: string = new Date(date).toISOString();
    return iso.slice(0, 10);
  }

  public formatTime(date: string): string {
    let iso: string = new Date(date).toISOString();
    return iso.slice(11, 16);
  }

  public formatPeriod(startDate: string, endDate: string): string {
    let start: string = this.formatDate(startDate);
    let end: string = this.formatDate(endDate);

    if(start === end){
      return start;
    } else {
      return `${start} - ${end}`;
    }
  }

  public roundHours(requiredTime: number): number {
    return Math.round((requiredTime / 60.0) * 10) / 10;
  }

}
