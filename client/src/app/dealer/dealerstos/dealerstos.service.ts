import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import{Techservice} from 'src/app/techservice/techservice';
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
export class DealerstosService {

  constructor(private http:HttpClient) { }

  getAllDealersSto(username:String){
    return this.http.get<Techservice[]>(Globals.baseURL + '/dealer/allstos/'+username);
  }

  deleteFromDealer(id:number){
    return this.http.delete(Globals.baseURL +'/dealer/deleteSto/'+id);
  }
}
