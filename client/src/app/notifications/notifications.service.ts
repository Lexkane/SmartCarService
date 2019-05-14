import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Notifications } from  './notifications';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { Globals } from '../globals';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  private baseUrl = Globals.baseURL + '/notifications';
  private notificationsSource = new BehaviorSubject<Notifications[]>([]);
  currentNotifications = this.notificationsSource.asObservable();
 
  public connect(){
    let socket = new SockJS('http://localhost:9501/socket');
    let stompClient = Stomp.over(socket);
    return stompClient;
  }

  updateNotifications(notifications : Observable<Notifications[]>){
    notifications.subscribe(data => {
      data.forEach(n => this.wrapNotification(n));
      this.notificationsSource.next(data);
    });
  }

  constructor(private http: HttpClient) { }

  public getAllNotifications(id) {
    return this.http.get<Notifications[]>(this.baseUrl + "/" + id);
  }

  public deleteNotification(id){
    return this.http.post(this.baseUrl + "/" + id, null);
  }

  public wrapNotification(n){
      n.showCheckbox = false;
      n.showDropdown = false;
      n.showButton = false;
      if (n.type === 'Warning'){
        n.showCheckbox = true;
      } else if (n.type === 'Review'){
        n.showButton = true;
      } else if (n.type !== 'Information'){
        n.showDropdown = true;
      }
  }
}
