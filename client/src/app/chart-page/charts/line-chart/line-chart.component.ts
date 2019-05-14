import {Component, OnInit, Input} from '@angular/core';

import {ChartComponent} from '../chart/chart.component';
import {ChartService} from '../chart/chart.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'line-chart',
  templateUrl: '../chart/chart.component.html',
})
export class LineChartComponent extends ChartComponent implements OnInit {

  constructor(private http: HttpClient) {
    super(new ChartService(http));
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.initCharts();
  }

  public chartType: string = 'line';

  public chartColors: Array<any> = [
    {
      backgroundColor: 'rgba(208,150,131, .4)',
      borderColor: '#ED8C72',
      borderWidth: 2,
    },
    {
      backgroundColor: 'rgba(208,150,178, .2)',
      borderColor: '#D09699',
      borderWidth: 2,
    },
    {
      backgroundColor: 'rgba(0, 137, 132, .2)',
      borderColor: '#336B87',
      borderWidth: 2,
    },
    {
      backgroundColor: 'rgba(75, 192, 192, 0.2)',
      borderColor: 'rgba(75, 192, 192, 1)',
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true
  };

  public chartClicked(e: any): void {
  }

  public chartHovered(e: any): void {
  }
}
