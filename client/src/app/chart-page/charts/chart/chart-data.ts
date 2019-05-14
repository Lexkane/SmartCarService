import {ChartDto} from './chart-dto';

export class ChartData {

  data: number[] = [];
  labels: string[] = [];

  constructor(data: number[] = [], labels: string[] = []) {
    this.data = data;
    this.labels = labels;
  }

  setChartData(dto: ChartDto, position: number = 0) {
    if (dto === null) {
      this.data = [];
    } else {
      this.data = [];
      this.labels = [];
      for (const [key, value] of Object.entries(dto.data)) {
        this.data.push(value[position]);
        this.labels.push(key);
      }
    }
  }

}
