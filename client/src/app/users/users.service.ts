import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from './user';
import { Globals } from '../globals';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(Globals.baseURL + '/users');
  }

  deleteUserById(id: number) {
    return this.http.delete(Globals.baseURL + '/user/' + id);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(Globals.baseURL + '/user/' + id);
  }

  getUserByUsername(username: String): Observable<User> {
    return this.http.get<User>(Globals.baseURL + '/user/' + username + '/username');
  }
}
