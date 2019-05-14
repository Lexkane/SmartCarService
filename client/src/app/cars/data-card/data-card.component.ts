import {Component, Input, OnInit} from '@angular/core';
import {ChartService} from "../../chart-page/charts/chart/chart.service";
import {ChartData} from "../../chart-page/charts/chart/chart-data";

@Component({
  selector: 'app-data-card',
  templateUrl: './data-card.component.html',
  styleUrls: ['./data-card.component.scss'],
  providers: [ChartService]
})
export class DataCardComponent implements OnInit {

  @Input()
  carId: number;

  @Input()
  icon: string;

  @Input()
  text: string;

  value: number = 0;

  constructor(private chartService: ChartService) {
  }

  ngOnInit() {
    if(this.text.toLowerCase().includes("mileage")){
      this.getMileage();
    } else if(this.text.toLowerCase().includes("speed")){
      this.getSpeed();
    } else if(this.text.toLowerCase().includes("services")){
      this.getServices();
    }
  }

  private getMileage() {
    this.chartService.getChartData("mileage", this.carId, "last")
      .subscribe(
        data => {
          let chartData: ChartData = new ChartData();
          chartData.setChartData(data);
          this.value = chartData.data[0] === undefined ? 0 : chartData.data[0];
        },
        error => console.error('Error: ', error)
      );

  }

  private getSpeed() {
    this.chartService.getChartData("speed", this.carId, "day", "max")
      .subscribe(
        data => {
          let chartData: ChartData = new ChartData();
          chartData.setChartData(data);
          this.value = chartData.data[0] === undefined ? 0 : Math.round(chartData.data[0]);
        },
        error => console.error('Error: ', error)
      );
  }

  private getServices(){

  }


}
