import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Car } from './car';
import {Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Globals } from '../globals';

// const httpOptions = {
//   headers: new HttpHeaders({
//    'Content-Type':  'application/json'
//   })
// };

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  constructor(private http:HttpClient) { }

  getOwnerCarsByUsername(username: String): Observable<Array<Car>> {
    return this.http.get<Array<Car>>(Globals.baseURL + '/owner/' + username + '/car',     
    { headers: {
      'Content-Type': 'application/json'
        }})
    .pipe(
      catchError(this.handleError)
    );
  }

  getOwnerCarsById(id: Number): Observable<Car[]> {
    return this.http.get<Car[]>(Globals.baseURL + '/owner/' + id + '/cars')
    .pipe(
      catchError(this.handleError)
    );
  }

  deleteCarById(id: number){
    return this.http.delete(Globals.baseURL + '/car/' + id)
  }

  getCarById(id: number): Observable<Car> {
    return this.http.get<Car>(Globals.baseURL + '/car/' + id)
    .pipe(
      catchError(this.handleError)
    );
  }

  getCarByNumber(number: String): Observable<Car> {
    return this.http.get<Car>(Globals.baseURL + '/car/' + number + '/number')
    .pipe(
      catchError(this.handleError)
    );
  }

  getCarByVin(vin: String): Observable<Car> {
    return this.http.get<Car>(Globals.baseURL + '/car/' + vin + '/vin')
    .pipe(
      catchError(this.handleError)
    );
  }

  postFile(fileToUpload: FormData): Observable<HttpResponse<File>> {
    return this.http.post<File>(Globals.baseURL + '/norms', fileToUpload,  { observe: 'response' })
    .pipe(
      catchError(this.handleError)
    );
}

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };

}

