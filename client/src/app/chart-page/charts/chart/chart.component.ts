import { Component, Input } from '@angular/core';

import { ChartService } from './chart.service';
import { ChartDto } from './chart-dto';
import { ChartData } from './chart-data';

import COLORS from '../../colors';

@Component({
  selector: 'chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss'],
  providers: [ChartService]
})
export class ChartComponent {

  @Input()
  sensorType: string;

  @Input()
  carId: number;

  private MONTHS = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  private SELECTIONS = ['Day', 'Month', 'Year'];
  private AGGREGATIONS = ['Avg', 'Min', 'Max'];

  public chartDatasets: Array<any> = [{ data: null, label: null }];
  public chartLabels: Array<any> = [null];
  public colors: Array<any> = [{ borderWidth: 2, }];

  private selection: string = 'day';
  private aggregation: string = 'avg';
  private date: Date = new Date();


  constructor(private chartService: ChartService) {
  }

  // *****
  // CSS
  // *****

  private changeBtnColors(ids: string[], id: string) {
    for (let i in ids) {
      if (id.indexOf(ids[i]) === -1) {
        this.setBackgroundColor(`${ids[i]}_${this.sensorType}`, COLORS.get('DARK_BTN'));
      } else {
        this.setBackgroundColor(id, COLORS.get('LIGHT_BTN'));
      }
    }
  }

  private setBackgroundColor(id: string, color: string): void {
    const el = document.getElementById(id);
    if (el !== null) {
      el.style.backgroundColor = color;
    }
  }

  private getId(period: string): string {
    return `${period.charAt(0).toUpperCase()}${period.substr(1)}_${this.sensorType}`;
  }

  // *****
  // MAIN METHODS
  // *****

  initCharts(): void {
    this.getChartDto(this.selection, this.aggregation);
    this.setBackgroundColor(`Day_${this.sensorType}`, COLORS.get('LIGHT_BTN'));
  }

  changePeriod(selection: string): void {
    this.selection = selection;
    this.aggregation = 'avg';
    this.date = new Date();
    this.getChartDto();

    this.changeBtnColors(this.SELECTIONS, this.getId(selection));
    this.changeBtnColors(this.AGGREGATIONS, `Avg_${this.sensorType}`);
  }

  changeAggregation(aggregation: string): void {
    this.aggregation = aggregation;
    this.getChartDto(this.selection, aggregation);
    this.changeBtnColors(this.AGGREGATIONS, this.getId(aggregation));
  }

  changeDate(isNext: boolean): void {
    this.setDate(isNext);
    this.getChartDto(this.selection, this.aggregation);
  }

  private setDate(isNext: boolean): void {
    if (this.selection === 'day') {
      this.date.setDate(this.incrDecrDate(isNext, this.date.getDate()));
    } else if (this.selection === 'month') {
      this.date.setMonth(this.incrDecrDate(isNext, this.date.getMonth()));
    } else {
      this.date.setFullYear(this.incrDecrDate(isNext, this.date.getFullYear()));
    }
  }

  private incrDecrDate(isNext: boolean, current: number): number {
    return isNext ? (current + 1) : (current - 1);
  }

  private getChartDto(selection = this.selection, aggregation = this.aggregation): void {
    this.chartService.getChartData(this.sensorType, this.carId, selection, aggregation, this.date)
      .subscribe(
        data => {
          this.setDataAndLabels(data);
          // this.setColors();
        },
        error => console.error('Error: ', error)
      );
  }

  public setDataAndLabels(chartDto: ChartDto) {
    let chartData: ChartData = new ChartData();

    if (this.sensorType == 'tire pressure') {
      const TIRES: string[] = ['frontLeft', 'frontRight', 'backLeft', 'backRight'];
      this.chartDatasets = [];

      TIRES.forEach((tire, i) => {
        chartData.setChartData(chartDto, i);
        this.chartDatasets.push({ data: chartData.data, label: tire });
      });
    } else {
      chartData.setChartData(chartDto);
      this.chartDatasets = [{ data: chartData.data, label: this.getCurrentPeriod(this.date) }];
    }

    this.chartLabels = (chartDto === null) ? null : this.changeLabels(chartData.labels);
  }

  private changeLabels(oldLabels: string[]): string[] {
    let labels: string[] = [];

    if (this.selection ==='day') {
      oldLabels.forEach(label => {
        labels.push(label.slice(0, 5));
      });
      labels = this.sortHours(labels);
    } else if (this.selection === 'month') {
      labels = oldLabels;
    } else {
      oldLabels.forEach(label => {
        labels.push(this.MONTHS[Number(label) - 1]);
      });
    }

    return labels;
  }

  private sortHours(labels: string[]): string[] {
    labels.sort(function (a, b) {

      let getHour = (label: string): number => Number(label.slice(0, 2));
      let getMinutes = (label: string): number => Number(label.slice(3, 5));

      let hourA: number = getHour(a);
      let hourB: number = getHour(b);

      let minutesA: number = getMinutes(a);
      let minutesB: number = getMinutes(b);

      if (hourA > hourB) {
        return 1;
      }
      if (hourA < hourB) {
        return -1;
      } else {
        if (minutesA > minutesB) {
          return 1;
        }
        if (minutesA < minutesB) {
          return -1;
        }
      }
      return 0;
    });

    return labels;
  }

  private getCurrentPeriod(date: Date): string {
    let day = date.getDate();
    let month = this.MONTHS[date.getMonth()];
    let year = date.getFullYear();

    if (this.selection === 'day') {
      return `${day} ${month}, ${year}`
    } else if (this.selection === 'month') {
      return `${month}, ${year}`
    } else {
      return year.toString();
    }
  }

}
