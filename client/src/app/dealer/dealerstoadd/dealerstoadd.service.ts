import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { from, Observable } from 'rxjs';
import { DealerSto } from './dealersto';
import{Techservice} from 'src/app/techservice/techservice';
import {Apply} from 'src/app/dealers/apply';
import { Globals } from 'src/app/globals';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DealerstoaddService {

  constructor(private http:HttpClient) { }

  getAllStosApply(username:String){
    return this.http.get<Techservice[]>(Globals.baseURL + '/dealer/allstosToApply/'+username);
  }

  addSto(apply:Apply):Observable<HttpResponse<Apply>>{
    return this.http.post<Apply>(Globals.baseURL + '/dealer/addStoWithApply',apply,{observe: 'response' });
  }

   deleteApply(apply:Apply):Observable<HttpResponse<Apply>>{
     return this.http.post<Apply>(Globals.baseURL + '/dealer/ignoreApply',apply,{observe: 'response' })
  }

}
