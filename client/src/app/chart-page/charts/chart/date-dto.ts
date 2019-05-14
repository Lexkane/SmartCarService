export class DateDto {

  sensorType: string;
  carId: number;
  date: string;
  selection: string;
  aggregation: string;

  constructor(sensorType: string, carId: number, date: string, selection: string, aggregation: string = null) {
    this.sensorType = sensorType;
    this.carId = carId;
    this.date = date;
    this.selection = selection;
    this.aggregation = aggregation;
  }

}
