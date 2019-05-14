import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Dealer} from 'src/app/dealer/dealer';
import {Apply} from './apply';
import {Observable} from "rxjs";
import { Globals } from '../globals';
import {text} from "@angular/core/src/render3";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DealersService {

  constructor(private http:HttpClient) { }

  getAllDealers(){
    return this.http.get<Dealer[]>(Globals.baseURL + '/dealer/getAll');
  }

  // createDealer(dealer: Dealer,username: String):Observable<HttpResponse<Dealer>>{
  //
  //   return this.http.post<Dealer>('http://localhost:9501/api/dealer/create/'+username,dealer,{observe: 'response' });
  // }

  applyToDealer(apply:Apply):Observable<HttpResponse<Apply>>{
    return this.http.post<Apply>(Globals.baseURL + '/dealer/epplyToDealer',apply,{observe: 'response' });
  }


}
