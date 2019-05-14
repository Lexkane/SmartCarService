import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {TradeIn} from './tradeIIn';
import { Globals } from 'src/app/globals';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class TradesinService {

  constructor(private http:HttpClient) { }

  getAllTradeInByDealer(username:String):Observable<TradeIn[]>{
    return this.http.get<TradeIn[]>(Globals.baseURL + '/dealer/getAllTradeIn/' + username);
  }

  deleteTradeIn(id: number){
    return this.http.delete(Globals.baseURL + '/dealer/delete/' + id)
  }

  successTradeIn(id: number){
    return this.http.delete(Globals.baseURL + '/dealer/successTradeIn/' + id)
  }
}
