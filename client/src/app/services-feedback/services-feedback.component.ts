import { Component, OnInit, Input } from '@angular/core';
import { ServicesFeedback } from './services-feedback';
import { ServicesFeedbackService } from './services-feedback.service';
import { Techservice } from '../techservice/techservice';
import { User } from '../users/user';
import { TechserviceService } from '../techservice/techservice.service';
import { UsersService } from '../users/users.service';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'services-feedback',
  templateUrl: './services-feedback.component.html',
  styleUrls: ['./services-feedback.component.scss']
})
export class ServicesFeedbackComponent implements OnInit {

  user: User;
  techservice: Techservice;

  allFeedback: ServicesFeedback[] = [];
  @Input() serviceId: number = null;
  @Input() userName: string = null;

  workers: number[] = [30]; //hardcode

  constructor(private servicesFeedbackService: ServicesFeedbackService, 
              private techserviceService: TechserviceService,
              private tokenStorage: TokenStorageService, 
              private userService: UsersService) { }

  ngOnInit() {
    this.getTechserviceByCurrentUser().then(() => {
      this.serviceId = this.techservice.stoId;
      
      if(this.serviceId) {
        this.servicesFeedbackService.getFeedbackByServiceId(this.serviceId).subscribe(data => this.allFeedback = data);
      } else if (this.userName) {
        this.servicesFeedbackService.getFeedbackByUserName(this.userName).subscribe(data => this.allFeedback = data);
      }
    });
  }

  getCurrentUser() {
    return new Promise((resolve, reject) => {
    this.userService.getUserByUsername(this.tokenStorage.getUsername())
        .subscribe(data => {
          this.user = data;
          this.getTechservice(this.user).then(() => {
            console.log(this.user);
            console.log(this.techservice);
            resolve();
          });
        });
    })
  }

  getTechservice(user: User) {
    return new Promise((resolve, reject) => {
      this.techserviceService.getTechnicalServiceByUsername(this.tokenStorage.getUsername())
        .subscribe(data => {
          if (data != null) {
            this.techservice = data;
          }
          resolve();
        });
    });
  }

  getTechserviceByCurrentUser() {
    return new Promise((resolve, reject) => {
      this.techserviceService.getTechnicalServiceByUsername(this.tokenStorage.getUsername())
        .subscribe(data => {
          if (data != null) {
            this.techservice = data;
          }
          resolve();
        });
    });
  }
}
