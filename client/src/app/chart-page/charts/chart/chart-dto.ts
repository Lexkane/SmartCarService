export class ChartDto {

  data: Map<string, number[]>;

  constructor(data: Map<string, number[]> = null) {
    this.data = data;
  }

}
