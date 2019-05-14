import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http'
import { Observable, throwError } from 'rxjs';
import { catchError, retry, map } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';
import { TimeList } from './time-list';
import { WorkTime } from './work-time';
import { NewBooking } from './new-booking';
import { BookingInfo } from './booking-info';
import { Report } from './new-report';
import { Globals } from 'src/app/globals';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
    providedIn: 'root'
  })
  export class TimeListService{
    bookingTime : string = Globals.baseURL + "/booking/time";
    newBooking : string = Globals.baseURL + "/booking/new";
    newReport : string = Globals.baseURL + "/report"

    constructor(private http: HttpClient) {}

    getBookingTime(timeList : TimeList): Observable<BookingInfo>{
        return this.http.post<BookingInfo>(this.bookingTime, timeList, httpOptions)
        .pipe(
            catchError(this.errorHandler)
        );
    }

    postNewBooking(newBooking : NewBooking) : Observable<HttpResponse<TimeList>>{
      return this.http.post<TimeList>(this.newBooking, newBooking, { observe: 'response' })
      .pipe(
        catchError(this.errorHandler)
      );
    }

    postNewReport(newReport : Report) : Observable<number>{
      return this.http.post<number>(this.newReport, newReport, httpOptions)
      .pipe(
        catchError(this.errorHandler)
      );
    }

    postFeedBack(workersList : Array<number>, username: string): Observable<any>{
        return this.http.post<any>(Globals.baseURL + '/users/' + username + '/leavefeedback', workersList, httpOptions)
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
