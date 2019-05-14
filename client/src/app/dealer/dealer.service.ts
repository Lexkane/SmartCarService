import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable, from } from 'rxjs';
import{ Dealer} from './dealer';
import { Globals } from '../globals';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DealerService {

  constructor(private http:HttpClient) { }


  createDealer(dealer: Dealer,username: String):Observable<HttpResponse<Dealer>>{
    return this.http.post<Dealer>(Globals.baseURL + '/dealer/create/'+username,dealer,{observe: 'response' });
  }

  getDealer(username: String): Observable<Dealer>{
    return this.http.get<Dealer>(Globals.baseURL + '/dealer/get/'+username);
  }

  editDealer(dealer: Dealer):Observable<HttpResponse<Dealer>>{
    return this.http.post<Dealer>(Globals.baseURL + '/dealer/edit',dealer,{observe: 'response' });
  }

  getDealerByCarVin(vin:String): Observable<Dealer>{
    console.log(this.http.get<Dealer>(Globals.baseURL + '/dealer/getByCarVin/'+vin));
    return this.http.get<Dealer>(Globals.baseURL + '/dealer/getByCarVin/'+vin);
  }

}
