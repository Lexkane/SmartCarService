import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ReportDto} from "./vi-report/report-dto";
import {Globals} from "../globals";

@Injectable()
export class ServiceHistoryService {

  private URL: string = Globals.baseURL + '/report';

  constructor(private http: HttpClient) {
  }


  public getPdfFromDB(reportId: number) {

    const httpOptions = {
      'responseType': 'arraybuffer' as 'json'
    };

    return this.http.get<any>(`${this.URL}/pdf/${reportId}`, httpOptions);

  }


  public getAllReportsByCarId(carId: number): Observable<Array<ReportDto>> {
    return this.http.get<Array<ReportDto>>(`${this.URL}/car/${carId}`)
  }

}
