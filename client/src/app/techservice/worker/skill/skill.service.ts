import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Skill } from './skill';
import { catchError } from 'rxjs/operators';
import { Globals } from 'src/app/globals';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  allSkillsURL = '/skills';

  constructor(private http: HttpClient) { }

  getAllSkills() {
    return this.http.get<Skill[]>(Globals.baseURL + this.allSkillsURL)
                      .pipe(catchError(this.errorHandler));
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
