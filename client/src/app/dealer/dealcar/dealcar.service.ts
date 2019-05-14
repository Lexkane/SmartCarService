import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import{Car} from 'src/app/cars/car';
import { from, Observable } from 'rxjs';
import { Globals } from 'src/app/globals';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};


@Injectable({
  providedIn: 'root'
})
export class DealcarService {

  constructor(private http:HttpClient) {}

  createCar(username:String,carProfile:Car):Observable<HttpResponse<Car>>{
    return this.http.post<Car>(Globals.baseURL + '/dealer/createcar/'+username,carProfile, {observe: 'response' });
  }

}
