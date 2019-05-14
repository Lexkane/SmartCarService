import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import{Car} from 'src/app/cars/car';
import { Observable } from 'rxjs';

import { from } from 'rxjs';
import { Globals } from 'src/app/globals';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DealercarsService {

   constructor(private http:HttpClient) { }

    getAllDealerCars(username:String):Observable<Car[]> {
      return this.http.get<Car[]>(Globals.baseURL + '/dealer/cars/' + username);
    }

}
