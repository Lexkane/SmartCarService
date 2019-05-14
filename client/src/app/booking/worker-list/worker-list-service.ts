import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http'
import { Observable, throwError, from } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';
import { WorkerList } from './worker-list-real';
import { Worker } from './worker';
import { WorkersTime } from './workers-time';
import { Globals } from 'src/app/globals';

const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  @Injectable({
    providedIn: 'root'
  })
  export class WorkerListService{
    workerByCarUrl : string = Globals.baseURL + '/workers/skill';

    constructor(private http: HttpClient) {}

    getWorkerByCar(workerList : WorkerList): Observable<WorkersTime>{
        return this.http.post<WorkersTime>(this.workerByCarUrl, workerList, httpOptions)
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
