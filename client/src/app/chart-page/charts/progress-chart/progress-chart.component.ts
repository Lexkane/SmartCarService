import {Component, OnInit, Input} from '@angular/core';

import {ChartService} from '../chart/chart.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'progress-chart',
  templateUrl: './progress-chart.component.html',
})
export class ProgressChartComponent implements OnInit {

  @Input()
  sensorType: string;

  @Input()
  carId: number;

  chartService: ChartService;

  private percent: number = 100;

  constructor(private http: HttpClient) {
    this.chartService = new ChartService(http);
  }

  ngOnInit() {
    this.getChartDto();
  }

  ngAfterViewInit(): void {
  }

  private getChartDto(): void {
    this.chartService.getChartData(this.sensorType, this.carId, 'last')
      .subscribe(
        dto => {
          for (const [key, value] of Object.entries(dto.data)) {
            this.percent = value[0];
          }
        },
        error => console.error('Error: ', error)
      );
  }

}
