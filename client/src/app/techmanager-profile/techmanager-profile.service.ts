import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http'
import { TechmanagerProfile } from './techmanager-profile';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';
import { Globals } from '../globals';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TechmanagerProfileService {

  profileInfoUrl = Globals.baseURL + '/v1/techmanagers/3';

  constructor(private http: HttpClient) { }

  putProfileUpdate(profile: TechmanagerProfile) {
    return this.http.put(this.profileInfoUrl + '/?fullname=' + profile.fullName
                                            + '&', profile)
                    .pipe(
                      catchError(this.errorHandler)
                    );
  }

  getProfileResp(): Observable<TechmanagerProfile> {
    return this.http.get<TechmanagerProfile>(this.profileInfoUrl)
                    .pipe(
                      catchError(this.errorHandler)
                    );
  }

  errorHandler(error: HttpErrorResponse)  {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message); // A client-side or network error occurred.
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
  }
  
}
