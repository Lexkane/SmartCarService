import { Injectable } from '@angular/core';
import {Dealer } from 'src/app/dealer/dealer';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from} from 'rxjs';
import {Car} from 'src/app/cars/car';
import {TradeIn} from './tradein';
import { Globals } from '../globals';
import {Message} from "./message";
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class TradeInService {
  url: string ="http://localhost:9501/api/socket";

  constructor(private http:HttpClient) { }

    getAllDealers():Observable<Dealer[]>{
      return this.http.get<Dealer[]>(Globals.baseURL + '/dealer/getAll');
     }

    getAllCarsByDealerEdr(edr:string):Observable<Car[]>{
      return this.http.get<Car[]>( Globals.baseURL + '/dealer/getAllCars/'+edr);
     }
    getAllCars():Observable<Car[]>{
      return this.http.get<Car[]>( Globals.baseURL + '/dealer/allCars');
     }

    sendTradeIn(newCarvin: String,UsedCarvin: String):Observable<TradeIn>{
      return this.http.post<TradeIn>( Globals.baseURL + '/dealer/createTradeIn/?vinNewCar='+ newCarvin+'&vinBCar='+UsedCarvin,httpOptions);
    }

  post(data: Message) {
    return this.http.post<Message>(this.url, data)
      .map((data: Message) => { return data; })
      .catch(error => {
        return new ErrorObservable(error);
      })
      ;
  }


}
