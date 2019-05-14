import {Component, OnInit, Input} from '@angular/core';

import {ChartComponent} from '../chart/chart.component';
import {ChartService} from '../chart/chart.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'bar-chart',
  templateUrl: '../chart/chart.component.html',
})
export class BarChartComponent extends ChartComponent implements OnInit {

  constructor(private http: HttpClient) {
    super(new ChartService(http));
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.initCharts();
    // this.chartColors = this.colors;
  }

  public chartType: string = 'bar';

  public chartColors: Array<any> = [
    {
      backgroundColor: 'rgba(0, 137, 132, .2)',
      borderColor: '#336B87',
      borderWidth: 2,
    },
    {
      backgroundColor: [
        // 'rgba(255, 99, 132, 0.2)',
        // 'rgba(54, 162, 235, 0.2)',
        // 'rgba(255, 206, 86, 0.2)',
        // 'rgba(75, 192, 192, 0.2)',
        // 'rgba(153, 102, 255, 0.2)',
        // 'rgba(255, 159, 64, 0.2)'
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
        'rgba(0, 137, 132, .2)',
      ],
      borderColor: [
        // 'rgba(255,99,132,1)',
        // 'rgba(54, 162, 235, 1)',
        // 'rgba(255, 206, 86, 1)',
        // 'rgba(75, 192, 192, 1)',
        // 'rgba(153, 102, 255, 1)',
        // 'rgba(255, 159, 64, 1)'
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
        '#336B87',
      ],
      borderWidth: 2,
    }
  ];

  // setColor(){
  //   console.log(this.chartDatasets);
  //   console.log(this.chartColors[0].backgroundColor);
  //   console.log();
  //   for(let i = 0; i < this.chartDatasets[0].data.lenght(); i++){
  //     this.chartColors[0].backgroundColor.push('rgba(153, 102, 255, 0.2)');
  //     this.chartColors[0].borderColor.push('rgba(153, 102, 255, 1)');
  //   }
  // }

  public chartOptions: any = {
    responsive: true
  };

  public chartClicked(e: any): void {
  }

  public chartHovered(e: any): void {
  }

}
